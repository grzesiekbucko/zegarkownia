package pl.marko.zegarki.controller.marko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.marko.MarkoBrand;
import pl.marko.zegarki.services.Marko.MarkoServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MarkoController {

    @Autowired
    MarkoServices markoServices;

    @RequestMapping(value = "/marko/brand", method = RequestMethod.GET)
    public ModelAndView showMainPage() {
        ModelAndView model = new ModelAndView("marko_brand_table");
        List<MarkoBrand> list = markoServices.getMarkoBrand();
        model.addObject("markoBrand", list);
        return model;
    }

    @RequestMapping(value = "/marko/update_brands", method = RequestMethod.GET)
    public String updateBrands() throws IOException {
        markoServices.saveBrandList();
        return "redirect:/marko/brand";
    }

    @RequestMapping(value = "/marko/products", method = RequestMethod.POST)
    public String getLinks(@RequestParam String html, MarkoBrand brand) throws IOException {
        markoServices.saveProdukt(html, brand);
        return "redirect:/marko/brand";
    }

    @RequestMapping(value = "/marko/update_all", method = RequestMethod.GET)
    public String updateAll() throws IOException {
        markoServices.deleteAllMarkoProducts();
        List<MarkoBrand> brandList = markoServices.saveBrandList();
        for (MarkoBrand list : brandList) {
            String html = list.getLink();
            System.out.println("zapisuje produkty z " + html);
            markoServices.saveProdukt(html, list);
        }
        return "redirect:/marko/brand";
    }

    @RequestMapping(value = "/marko/select_brand", method = RequestMethod.GET)
    public ModelAndView selectbrand() {
        ModelAndView model = new ModelAndView("marko_find_product_table");
        List<MarkoBrand> brandList = markoServices.getMarkoBrand();
        model.addObject("markoBrand", brandList);
        return model;
    }

    @RequestMapping(value = "/marko/show_selected_brand", method = RequestMethod.POST)
    public ModelAndView showSelected(@RequestParam String selectedBrand) {
        ModelAndView model = new ModelAndView("marko_find_product_table");
        List<String> brandSplitedList = Arrays.asList(selectedBrand.split(","));

        List<MarkoBrand> brandList = markoServices.getMarkoBrand();
        ArrayList productList = markoServices.findByBrands(brandSplitedList);
        model.addObject("markoProd", productList);
        model.addObject("markoBrand", brandList);
        return model;
    }

}
