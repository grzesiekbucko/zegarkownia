package pl.marko.zegarki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.services.Marko.MarkoServices;
import pl.marko.zegarki.services.ZegarekNet.ZegareknetService;

import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    private ZegareknetService zegareknetService;

    @Autowired
    private MarkoServices markoServices;

    @GetMapping("/index")
    public ModelAndView showHomePage() {
        ModelAndView model = new ModelAndView("mainPage");

        Integer productCount = markoServices.getMarkoProduct().size();
        Integer shiping5dni = markoServices.getMarkoProductShiping("5 dni").size();
        Integer shiping24godz = markoServices.getMarkoProductShiping("24 godziny").size();
        ArrayList<String> brand_list = markoServices.getmarkoBrandName();


        model.addObject("brand_counter", brand_list.size());
        model.addObject("map5dni", markoServices.markoMapNumberOfProductsByShiping("5 dni"));
        model.addObject("map24godz", markoServices.markoMapNumberOfProductsByShiping("24 godziny"));
        model.addObject("map_Marko_Prod", markoServices.markoMapNumberOfProductsByBrands());
        model.addObject("map_Zega_Net_Prod", zegareknetService.zegNetMapNumberOfProductsByBrands());
        model.addObject("map_compared_prod_brand",markoServices.comparedMapOfProductsByBrand());
        model.addObject("map_compared_prod_shiping",markoServices.comparedMapNumberOfProductsByShiping("24 godziny"));
        model.addObject("map_compared_prod_shiping_5dni",markoServices.comparedMapNumberOfProductsByShiping("5 dni"));
        model.addObject("map_compared_prod_sale",markoServices.comparedMapNumberOfProductsBySale());

        model.addObject("brand_list_name", brand_list);
        model.addObject("product_counter", productCount);
        model.addObject("product_5dni", shiping5dni);
        model.addObject("product_24godziny", shiping24godz);
        model.addObject("percent_5dni", markoServices.getPercent(shiping5dni, productCount));
        model.addObject("percent_24godziny", markoServices.getPercent(shiping24godz, productCount));
        model.addObject("product_list", markoServices.getComparedProduct());
        model.addObject("worst_price_counter", markoServices.getComparedProduct().size());
        model.addObject("zegarek_net_product_counter", zegareknetService.getAllProductsZegarekNet().size());
        model.addObject("zegarek_net_brand_counter", zegareknetService.getZegaNetBrand().size());
        return model;
    }

}
