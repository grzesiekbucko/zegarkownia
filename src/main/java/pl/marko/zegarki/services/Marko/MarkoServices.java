package pl.marko.zegarki.services.Marko;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marko.zegarki.entity.marko.MarkoBrand;
import pl.marko.zegarki.entity.marko.MarkoProduct;
import pl.marko.zegarki.entity.ProductJoinInterface;
import pl.marko.zegarki.repository.Marko.MarkoBrandRepository;
import pl.marko.zegarki.repository.Marko.MarkoProductRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkoServices {

    @Autowired
    private MarkoBrandRepository markoBrandRepository;

    @Autowired
    private MarkoProductRepository markoProductRepository;

    public ArrayList<MarkoBrand> getMarkoBrand() {
        return (ArrayList<MarkoBrand>) markoBrandRepository.findAll();
    }

    public ArrayList<String> getmarkoBrandName(){
        ArrayList<String> listName = new ArrayList<>();
        for(MarkoBrand name :getMarkoBrand() ){
            listName.add(name.getBrand());
        }return listName;
    }

    public ArrayList<MarkoProduct> getMarkoProduct() {
        return (ArrayList<MarkoProduct>) markoProductRepository.findAll();
    }

    public ArrayList<?> getMarkoProductShiping(String shiping) {
        return (ArrayList<?>) markoProductRepository.findProductByShiping(shiping);
    }

    public  ArrayList<ProductJoinInterface> getComparedProduct(){
        return (ArrayList<ProductJoinInterface>) markoProductRepository.findAllDtoBy();
    }

    public void deleteAllMarkoProducts(){
        markoProductRepository.deleteAll();
        markoProductRepository.flush();
    }

    public String getPercent(Integer a, Integer total){
        DecimalFormat formatter = new DecimalFormat("#0.00");
        double percent = ((double)a / (double)total)*100;
        return formatter.format(percent);
    }

    public List<MarkoBrand> saveBrandList() throws IOException {
        Document linkDoc = Jsoup.connect("https://www.marko.pl/zegarki.html").get();
        Elements className = linkDoc.select("#filter_producer");
        Elements productLink = className.select("a");

        List<MarkoBrand> markoBrandsList = new ArrayList<>();

        for (Element el : productLink) {
            String name = el.select("span").text();
            String link = el.attr("href");
            String fullLink = "https://www.marko.pl" + link;

            MarkoBrand brand = new MarkoBrand(name, fullLink);
            markoBrandRepository.save(brand);
            System.out.println("zapisano do bazy " + name);
            markoBrandsList.add(brand);
        }return markoBrandsList;
    }


    public ArrayList findByBrands(List<String> brandSplitedList) {
        List<MarkoBrand> list = markoBrandRepository.findByBrandIn(brandSplitedList);
        ArrayList productList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            MarkoBrand productList2 = (list.get(i));
            productList.addAll(productList2.getProducts());
        }
        return productList;
    }

    public void saveProdukt(String url, MarkoBrand brand) throws IOException {
        Document start_page = Jsoup.connect(url).get();
        String linkClass = start_page.select(".paginator").first().text();

        if (linkClass.equals("")) {
            Document linkDoc = Jsoup.connect(url).get();
            Elements prodBox = linkDoc.select(".product");

            ArrayList productLinkList = new ArrayList();
            for (Element el : prodBox) {
                Elements nameClass = el.select(".prodname");
                String link = nameClass.attr("href");
                String productLink = "https://www.marko.pl" + link;
                productLinkList.add(productLink);
            }

            for (Object link : productLinkList) {
                Document prodLink = Jsoup.connect(link.toString()).get();
                String productKod = prodLink.select(".k_model").select("h3").text();
                String shiping = prodLink.select(".k_ship").select(".second").text();

                if (!prodLink.select(".price").select("del").text().equals("") && !prodLink.select(".main-price").text().equals("")) {

                    String newPrice = prodLink.select(".main-price").text().replace(" ", "").replace("zł", "").replaceAll(",", ".");
                    BigDecimal newPriceDouble = new BigDecimal(newPrice);

                    String oldPrice = prodLink.select(".price").select("del").text().replace(" ", "").replace("zł", "").replaceAll(",", ".");
                    BigDecimal oldPriceDouble = new BigDecimal(oldPrice);

                    BigDecimal percent = newPriceDouble.divide(oldPriceDouble, RoundingMode.HALF_UP);
                    BigDecimal finalPercent = (BigDecimal.valueOf(1).subtract(percent)).multiply(BigDecimal.valueOf(100));

                    MarkoProduct product = new MarkoProduct(productKod, brand, link.toString(), newPriceDouble, oldPriceDouble, shiping, finalPercent);
                    markoProductRepository.save(product);
                    System.out.println("zapisano: " + productKod);

                } else if (prodLink.select(".price").select("del").text().equals("") && !prodLink.select(".main-price").text().equals("")) {
                    String newPrice = prodLink.select(".main-price").text().replace(" ", "").replace("zł", "").replaceAll(",", ".");
                    BigDecimal newPriceDouble = new BigDecimal(newPrice);

                    MarkoProduct product = new MarkoProduct(productKod, brand, link.toString(), newPriceDouble, shiping);
                    markoProductRepository.save(product);
                    System.out.println("zapisano: " + productKod);
                }
            }
        } else {
            Elements link_class = start_page.select(".paginator").select("li");
            link_class.remove(link_class.last());

            Integer numberOfPages = Integer.valueOf(link_class.last().text());

            for (int i = 1; i <= numberOfPages; i++) {
                String replaceURL = url.replace("https://www.marko.pl/zegarki.html/1", "");
                String finalURL = "https://www.marko.pl/zegarki.html/" + i + replaceURL;

                Document linkDoc = Jsoup.connect(finalURL).get();
                Elements prodBox = linkDoc.select(".product");

                ArrayList productLinkList = new ArrayList();
                for (Element el : prodBox) {
                    Elements nameClass = el.select(".prodname");
                    String link = nameClass.attr("href");
                    String productLink = "https://www.marko.pl" + link;
                    productLinkList.add(productLink);
                }

                for (Object link : productLinkList) {
                    Document prodLink = Jsoup.connect(link.toString()).get();
                    String productKod = prodLink.select(".k_model").select("h3").text();
                    String shiping = prodLink.select(".k_ship").select(".second").text();

                    if (!prodLink.select(".price").select("del").text().equals("") && !prodLink.select(".main-price").text().equals("")) {

                        String newPrice = prodLink.select(".main-price").text().replace(" ", "").replace("zł", "").replaceAll(",", ".");
                        BigDecimal newPriceDouble = new BigDecimal(newPrice);

                        String oldPrice = prodLink.select(".price").select("del").text().replace(" ", "").replace("zł", "").replaceAll(",", ".");
                        BigDecimal oldPriceDouble = new BigDecimal(oldPrice);

                        BigDecimal percent = newPriceDouble.divide(oldPriceDouble, RoundingMode.HALF_UP);
                        BigDecimal finalPercent = (BigDecimal.valueOf(1).subtract(percent)).multiply(BigDecimal.valueOf(100));

                        MarkoProduct product = new MarkoProduct(productKod, brand, link.toString(), newPriceDouble, oldPriceDouble, shiping, finalPercent);
                        markoProductRepository.save(product);
                        System.out.println("zapisano: " + productKod);

                    } else if (prodLink.select(".price").select("del").text().equals("") && !prodLink.select(".main-price").text().equals("")) {
                        String newPrice = prodLink.select(".main-price").text().replace(" ", "").replace("zł", "").replaceAll(",", ".");
                        BigDecimal newPriceDouble = new BigDecimal(newPrice);

                        MarkoProduct product = new MarkoProduct(productKod, brand, link.toString(), newPriceDouble, shiping);
                        markoProductRepository.save(product);
                        System.out.println("zapisano: " + productKod);
                    }
                }
            }
        }

    }
}
