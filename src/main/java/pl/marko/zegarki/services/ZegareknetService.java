package pl.marko.zegarki.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ZegareknetService  {

    public String getProduktLinks(String url, String brand) throws IOException {
        String dataFormat = (new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss'.csv'")).format(new Date());
        String nazwa = "Linki_" + brand + "_" + dataFormat;
        FileWriter csvWriter = new FileWriter(nazwa);

        Document start_page = Jsoup.connect(url).get();
        Element linkClass = start_page.select(".pagination").first();

        if(linkClass == null){
            Document linkDoc = Jsoup.connect(url).get();
            Elements nameClass = linkDoc.select(".products-list-name");

            for (Element el : nameClass) {
                csvWriter.append(el.select("span").first().text() + ";" + el.attr("href") + "\n");
            }
        }else {
            Elements link_class = start_page.select(".pagination").select("a");
            link_class.remove(link_class.last());

            Integer numberOfPages = Integer.valueOf(link_class.last().text());
            for (int i = 1; i <= numberOfPages; i++) {
                String START_URL = url + "?page=" + i;

                Document linkDoc = Jsoup.connect(START_URL).get();
                Elements nameClass = linkDoc.select(".products-list-name");

                for (Element el : nameClass) {
                    csvWriter.append(el.select("span").first().text() + ";" + el.attr("href") + "\n");
                }
            }
        }
        csvWriter.flush();
        csvWriter.close();
        return nazwa;
    }

    public Map<String, String> List() throws IOException {
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
