package pl.marko.zegarki.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNetProduct;
import pl.marko.zegarki.repository.ZegarekNetBrandRepository;
import pl.marko.zegarki.repository.ZegarekNetProductRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ZegareknetService {

    @Autowired
    private
    ZegarekNetBrandRepository zegarekNetBrandRepository;

    @Autowired
    private
    ZegarekNetProductRepository zegarekNetProductRepository;

    public ArrayList<ZegarekNetBrand> getZegaNetBrand() {
        ArrayList<ZegarekNetBrand> listBrand = (ArrayList<ZegarekNetBrand>) zegarekNetBrandRepository.findAll();
        return listBrand;
    }

    public ArrayList<ZegarekNetProduct> getZegaNetProducts(ZegarekNetBrand brand) {
        ArrayList<ZegarekNetProduct> listProduct = (ArrayList<ZegarekNetProduct>) zegarekNetProductRepository.findByProductBrand(brand);
        return listProduct;
    }

    public ModelAndView updateTimestamp(ModelAndView model) {
        List<ZegarekNetBrand> list = getZegaNetBrand();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        if (list.size() == 0) {
            model.addObject("update_date", "-");
        } else {
            String date = simpleDateFormat.format(list.get(list.size() - 1).getUpdateDate());
            model.addObject("update_date", date);
        }

        return model;
    }

    public void saveProduktLinks(String url, ZegarekNetBrand brand) throws IOException {

        Document start_page = Jsoup.connect(url).get();
        Element linkClass = start_page.select(".pagination").first();

        if (linkClass == null) {
            Document linkDoc = Jsoup.connect(url).get();
            Elements nameClass = linkDoc.select(".products-list-name");

            for (Element el : nameClass) {
                String productKod = el.select("span").first().text();
                String link = el.attr("href");
                String productLink = "https://www.zegarek.net" + link;
                ZegarekNetProduct product = new ZegarekNetProduct(productKod, productLink, brand);
                zegarekNetProductRepository.save(product);
            }
        } else {
            Elements link_class = start_page.select(".pagination").select("a");
            link_class.remove(link_class.last());

            Integer numberOfPages = Integer.valueOf(link_class.last().text());
            for (int i = 1; i <= numberOfPages; i++) {
                String START_URL = url + "?page=" + i;

                Document linkDoc = Jsoup.connect(START_URL).get();
                Elements nameClass = linkDoc.select(".products-list-name");

                for (Element el : nameClass) {
                    String productKod = el.select("span").first().text();
                    String link = el.attr("href");
                    String productLink = "https://www.zegarek.net" + link;
                    ZegarekNetProduct product = new ZegarekNetProduct(productKod, productLink, brand);
                    zegarekNetProductRepository.save(product);
                }
            }
        }
    }

    public void saveBrandList() throws IOException {
        Document linkDoc = Jsoup.connect("https://www.zegarek.net/sitemap.php").get();
        Element className = linkDoc.select(".sitemaps").first();
        Elements productLink = className.select("a");

        for (Element el : productLink) {
            String name = el.select("a").text().replace("Zegarki ", "");
            String link = el.attr("href");

            ZegarekNetBrand brand = new ZegarekNetBrand(name, link);
            zegarekNetBrandRepository.save(brand);
        }
    }
}
