package pl.marko.zegarki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNetProduct;
import pl.marko.zegarki.services.ZegareknetService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ZegareknetService zegareknetService;

    @GetMapping("/main")
    public String showHomePage() {
        return "mainPage";
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
        List<ZegarekNetBrand> list = zegareknetService.getZegaNetBrand();
        model.addObject("zegarekNetBrand", list);
        return model;
    }

}
