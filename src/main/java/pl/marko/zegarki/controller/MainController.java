package pl.marko.zegarki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.ProductJoin;
import pl.marko.zegarki.entity.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNetProduct;
import pl.marko.zegarki.entity.marko.MarkoProduct;
import pl.marko.zegarki.services.Marko.MarkoServices;
import pl.marko.zegarki.services.ZegareknetService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        Integer brandCount = markoServices.getMarkoBrand().size();
        Integer productCount = markoServices.getMarkoProduct().size();
        Integer shiping5dni = markoServices.getMarkoProductShiping("5 dni").size();
        Integer shiping24godz = markoServices.getMarkoProductShiping("24 godziny").size();
        List <ProductJoin> productList = markoServices.getComparedProduct();
        System.out.println(productList);
        model.addObject("brand_counter", brandCount);
        model.addObject("product_counter", productCount);
        model.addObject("product_5dni", shiping5dni);
        model.addObject("product_24godziny", shiping24godz);
        model.addObject("percent_5dni", markoServices.getPercent(shiping5dni, productCount));
        model.addObject("percent_24godziny", markoServices.getPercent(shiping24godz, productCount));
  //      model.addObject("product_list", productList);
        return model;
    }

    @RequestMapping(value = "/brand", method = RequestMethod.GET)
    public ModelAndView showMainPage() {
        ModelAndView model = new ModelAndView("zeg_net_brand_table");
        List<ZegarekNetBrand> list = zegareknetService.getZegaNetBrand();
        model.addObject("zegarekNetBrand", list);
        return zegareknetService.updateTimestamp(model);
    }

    @RequestMapping(value = "/update_brands", method = RequestMethod.GET)
    public String watchList() throws IOException {
        zegareknetService.saveBrandList();
        return "redirect:/brand";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String getLinks(@RequestParam String html, ZegarekNetBrand brand) throws IOException {
        zegareknetService.saveProduktLinks(html, brand);
        String brandName = brand.getBrand();
        return "redirect:/products/" + brandName;
    }

    @RequestMapping(value = "/products/{brand}", method = RequestMethod.GET)
    public ModelAndView deleteIncome(@PathVariable(value = "brand") ZegarekNetBrand brand) {
        ModelAndView productsModel = new ModelAndView("zeg_net_product_table");
        List<ZegarekNetProduct> productList = zegareknetService.getZegaNetProducts(brand);
        productsModel.addObject("zegarekNetProd", productList);
        productsModel.addObject("productBrandName", brand.getBrand());
        return productsModel;
    }

    @RequestMapping(value = "/select_brand", method = RequestMethod.GET)
    public ModelAndView selectbrand() {
        ModelAndView model = new ModelAndView("zeg_net_find_product_table");
        List<ZegarekNetBrand> brandList = zegareknetService.getZegaNetBrand();
        model.addObject("zegarekNetBrand", brandList);
        return model;
    }

    @RequestMapping(value = "/show_selected_brand", method = RequestMethod.POST)
    public ModelAndView showSelected(@RequestParam String selectedBrand) {
        ModelAndView model = new ModelAndView("zeg_net_find_product_table");
        List<String> brandSplitedList = Arrays.asList(selectedBrand.split(","));

        List<ZegarekNetBrand> brandList = zegareknetService.getZegaNetBrand();
        ArrayList productList = zegareknetService.findByBrands(brandSplitedList);
        model.addObject("zegarekNetProd", productList);
        model.addObject("zegarekNetBrand", brandList);
        return model;
    }


}
