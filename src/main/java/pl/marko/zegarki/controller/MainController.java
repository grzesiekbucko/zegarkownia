package pl.marko.zegarki.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/index")
    public String showHomePage() {
        return "index";
    }
    @GetMapping("/main")
    public String showMainPage() {
        return "mainPage";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView watchList() throws IOException {
        ModelAndView model = new ModelAndView("index");
        Map<String, String> watchlist = List();
        model.addObject("testlist", watchlist);
        model.addObject("name", watchlist.get(0));
        model.addObject("link", watchlist.get(1));
        return model;
    }

    @RequestMapping(value = "/links", method = RequestMethod.POST)
    public ModelAndView getLinks(@RequestParam String html) throws IOException {

        getProduktLinks(html);
        ModelAndView model = new ModelAndView("index");

        model.addObject("success label", "File saved!");
        return model;
    }


    public static void getProduktLinks(String url) throws IOException {
        String dataFormat = (new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss'.csv'")).format(new Date());
        String nazwa = "Linki_" + dataFormat;
        FileWriter csvWriter = new FileWriter(nazwa);

        Document start_page = Jsoup.connect(url).get();
        start_page.select(".pagination").select("a");

        for (int i = 1; i <= 8; i++) {
            String START_URL = url + "?page=" + i;
            Document linkDoc = Jsoup.connect(START_URL).get();

            Elements nameClass = linkDoc.select(".products-list-name");
            for (Element el : nameClass) {
                csvWriter.append(el.select("span").first().text() + ";" + el.attr("href") + "\n");
            }
        }
        csvWriter.flush();
        csvWriter.close();
        System.out.println("File saved!");
    }

    private static Map<String, String> List() throws IOException {
        Document linkDoc = Jsoup.connect("https://www.zegarek.net/sitemap.php").get();
        Element className = linkDoc.select(".sitemaps").first();
        Elements productLink = className.select("a");
        ArrayList list = new ArrayList();
        Map<String, String> attrMap = new HashMap<>();

        for (Element el : productLink) {
            String name = el.select("a").text().replace("Zegarki ", "");
            String link = el.attr("href");

            attrMap.put(name, link);

            System.out.println(name + " " + link);
        }
        return attrMap;
    }

}
