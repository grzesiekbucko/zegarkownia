package pl.marko.zegarki.services.ZegarekNet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetProduct;
import pl.marko.zegarki.repository.ZegarekNet.ZegarekNetBrandRepository;
import pl.marko.zegarki.repository.ZegarekNet.ZegarekNetProductRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;

@Service
public class ZegareknetService {

    @Autowired
    private ZegarekNetBrandRepository zegarekNetBrandRepository;

    @Autowired
    private ZegarekNetProductRepository zegarekNetProductRepository;

    public ArrayList<ZegarekNetBrand> getZegaNetBrand() {
        return (ArrayList<ZegarekNetBrand>) zegarekNetBrandRepository.findAll();
    }

    public void deleteAllZegarekNetProducts(){
        zegarekNetProductRepository.deleteAll();
        zegarekNetProductRepository.flush();
    }

    public ArrayList<ZegarekNetProduct> getZegaNetProducts(ZegarekNetBrand brand) {
        return (ArrayList<ZegarekNetProduct>) zegarekNetProductRepository.findByProductBrand(brand);
    }

    public ArrayList<ZegarekNetProduct> getAllProductsZegarekNet() {
        return (ArrayList<ZegarekNetProduct>) zegarekNetProductRepository.findAll();
    }

    public ArrayList findByBrands(List<String> brandSplitedList) {
        List<ZegarekNetBrand> list = zegarekNetBrandRepository.findByBrandIn(brandSplitedList);
        ArrayList productList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ZegarekNetBrand productList2 = (list.get(i));
            productList.addAll(productList2.getProducts());
        }
        return productList;
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
            Elements prodBox = linkDoc.select(".products-list-box");

            for (Element el : prodBox) {
                Elements nameClass = el.select(".products-list-name");
                String productKod = nameClass.select("span").first().text();
                String link = el.select("a").attr("href");
                String productLink = "https://www.zegarek.net" + link;

                if (!el.select(".old-price").text().equals("") && !el.select(".new-price").text().equals("")) {
                    String newPrice = el.select(".new-price").text().replace(".", "").replace(" zł", "").replaceAll(",", ".");
                    BigDecimal newPriceDouble = new BigDecimal(newPrice);

                    String oldPrice = el.select(".old-price").text().replace(".", "").replace(" zł", "").replaceAll(",", ".");
                    BigDecimal oldPriceDouble = new BigDecimal(oldPrice);

                    BigDecimal percent = newPriceDouble.divide(oldPriceDouble, RoundingMode.HALF_UP);
                    BigDecimal finalPercent = (BigDecimal.valueOf(1).subtract(percent)).multiply(BigDecimal.valueOf(100));

                    ZegarekNetProduct product = new ZegarekNetProduct(productKod, brand, productLink, newPriceDouble, oldPriceDouble, finalPercent);
                    zegarekNetProductRepository.save(product);

                } else if (el.select(".old-price").text().equals("") && !el.select(".new-price").text().equals("")) {
                    String newPrice = el.select(".new-price").text().replace(".", "").replace(" zł", "").replaceAll(",", ".");
                    BigDecimal newPriceDouble = new BigDecimal(newPrice);


                    ZegarekNetProduct product = new ZegarekNetProduct(productKod, brand, productLink, newPriceDouble);
                    zegarekNetProductRepository.save(product);
                }
            }
        } else {
            Elements link_class = start_page.select(".pagination").select("a");
            link_class.remove(link_class.last());

            Integer numberOfPages = Integer.valueOf(link_class.last().text());
            for (int i = 1; i <= numberOfPages; i++) {
                String START_URL = url + "?page=" + i;

                Document linkDoc = Jsoup.connect(START_URL).get();
                Elements prodBox = linkDoc.select(".products-list-box");

                for (Element el : prodBox) {
                    Elements nameClass = el.select(".products-list-name");
                    String productKod = nameClass.select("span").first().text();
                    String link = el.select("a").attr("href");
                    String productLink = "https://www.zegarek.net" + link;

                    if (!el.select(".old-price").text().equals("") && !el.select(".new-price").text().equals("")) {

                        String newPrice = el.select(".new-price").text().replace(".", "").replace(" zł", "").replaceAll(",", ".");
                        BigDecimal newPriceDouble = new BigDecimal(newPrice);

                        String oldPrice = el.select(".old-price").text().replace(".", "").replace(" zł", "").replaceAll(",", ".");
                        BigDecimal oldPriceDouble = new BigDecimal(oldPrice);

                        BigDecimal percent = newPriceDouble.divide(oldPriceDouble, RoundingMode.HALF_UP);
                        BigDecimal finalPercent = (BigDecimal.valueOf(1).subtract(percent)).multiply(BigDecimal.valueOf(100));

                        ZegarekNetProduct product = new ZegarekNetProduct(productKod, brand, productLink, newPriceDouble, oldPriceDouble, finalPercent);
                        zegarekNetProductRepository.save(product);

                    } else if (el.select(".old-price").text().equals("") && !el.select(".new-price").text().equals("")) {
                        String newPrice = el.select(".new-price").text().replace(".", "").replace(" zł", "").replaceAll(",", ".");
                        BigDecimal newPriceDouble = new BigDecimal(newPrice);

                        ZegarekNetProduct product = new ZegarekNetProduct(productKod, brand, productLink, newPriceDouble);
                        zegarekNetProductRepository.save(product);
                    }
                }

            }
        }
    }

    public List<ZegarekNetBrand> saveBrandList() throws IOException {
        Document linkDoc = Jsoup.connect("https://www.zegarek.net/sitemap.php").get();
        Element className = linkDoc.select(".sitemaps").first();
        Elements productLink = className.select("a");

        List<ZegarekNetBrand> markoBrandsList = new ArrayList<>();

        for (Element el : productLink) {
            String name = el.select("a").text().replace("Zegarki ", "");
            String link = el.attr("href");

            ZegarekNetBrand brand = new ZegarekNetBrand(name, link);
            zegarekNetBrandRepository.save(brand);
            System.out.println("zapisano do bazy " + name);
            markoBrandsList.add(brand);
        }return markoBrandsList;
    }

    public Map<String, Integer> zegNetMapNumberOfProductsByBrands(){
        Map<String, Integer> testMap = new HashMap<>();
        for(ZegarekNetBrand p : getZegaNetBrand()){
            testMap.put(p.getBrand(), p.getProducts().size());
        }
        return testMap;
    }
}
