package pl.marko.zegarki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.ProductJoinInterface;
import pl.marko.zegarki.entity.marko.MarkoBrand;
import pl.marko.zegarki.entity.marko.MarkoProduct;
import pl.marko.zegarki.services.Marko.MarkoServices;
import pl.marko.zegarki.services.ZegarekNet.ZegareknetService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ZegareknetService zegareknetService;

    @Autowired
    private MarkoServices markoServices;

    @GetMapping("/")
    public ModelAndView showHomePage() {
        ModelAndView model = new ModelAndView("mainPage");
        Integer productCount = markoServices.getMarkoProduct().size();
        Integer shiping5dni = markoServices.getMarkoProductShiping("5 dni").size();
        Integer shiping24godz = markoServices.getMarkoProductShiping("24 godziny").size();
        List <ProductJoinInterface> productList = markoServices.getComparedProduct();
        Integer worstPriceCounter = markoServices.getComparedProduct().size();
        ArrayList<String> brand_list = markoServices.getmarkoBrandName();

        List<MarkoBrand> brandList = markoServices.getMarkoBrand();
        System.out.println("brand: " + brandList.get(0).getBrand() + ", size: " + brandList.get(0).getProducts().size());


        model.addObject("brand_counter", brand_list.size());
        model.addObject("brand_list", "testdfgjdfk jghfdkjg djfgnkd");
        model.addObject("brand_list_name", brand_list);
        model.addObject("product_counter", productCount);
        model.addObject("product_5dni", shiping5dni);
        model.addObject("product_24godziny", shiping24godz);
        model.addObject("percent_5dni", markoServices.getPercent(shiping5dni, productCount));
        model.addObject("percent_24godziny", markoServices.getPercent(shiping24godz, productCount));
        model.addObject("product_list", productList);
        model.addObject("worst_price_counter", worstPriceCounter);
        model.addObject("zegarek_net_product_counter", zegareknetService.getAllProductsZegarekNet().size());
        model.addObject("zegarek_net_brand_counter", zegareknetService.getZegaNetBrand().size());
        return model;
    }

}
