package pl.marko.zegarki.controller.ZegarekNet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetBrand;
import pl.marko.zegarki.entity.ZegarekNet.ZegarekNetProduct;
import pl.marko.zegarki.services.ZegarekNet.ZegareknetService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class zegarekNetController {

    @Autowired
    private ZegareknetService zegareknetService;

    @RequestMapping(value = "/zegarek_net/brand", method = RequestMethod.GET)
    public ModelAndView showMainPage() {
        ModelAndView model = new ModelAndView("zeg_net_brand_table");
        List<ZegarekNetBrand> list = zegareknetService.getZegaNetBrand();
        model.addObject("zegarekNetBrand", list);
        return zegareknetService.updateTimestamp(model);
    }

    @RequestMapping(value = "/zegarek_net/update_brands", method = RequestMethod.GET)
    public String watchList() throws IOException {
        zegareknetService.saveBrandList();
        return "redirect:/zegarek_net/brand";
    }

    @RequestMapping(value = "/zegarek_net/update_all", method = RequestMethod.GET)
    public String updateAll() throws IOException {
        List<ZegarekNetBrand> brandList = zegareknetService.saveBrandList();

        for (ZegarekNetBrand list : brandList) {
            String html = list.getLink();
            System.out.println("zapisuje produkty z " + html);
            zegareknetService.saveProduktLinks(html, list);
        }
        return "redirect:/zegarek_net/brand";
    }

    @RequestMapping(value = "/zegarek_net/products", method = RequestMethod.POST)
    public String getLinks(@RequestParam String html, ZegarekNetBrand brand) throws IOException {
        zegareknetService.saveProduktLinks(html, brand);
        String brandName = brand.getBrand();
        return "redirect:/zegarek_net/products/" + brandName;
    }

    @RequestMapping(value = "/zegarek_net/products/{brand}", method = RequestMethod.GET)
    public ModelAndView deleteIncome(@PathVariable(value = "brand") ZegarekNetBrand brand) {
        ModelAndView productsModel = new ModelAndView("zeg_net_product_table");
        List<ZegarekNetProduct> productList = zegareknetService.getZegaNetProducts(brand);
        productsModel.addObject("zegarekNetProd", productList);
        productsModel.addObject("productBrandName", brand.getBrand());
        return productsModel;
    }

    @RequestMapping(value = "/zegarek_net/select_brand", method = RequestMethod.GET)
    public ModelAndView selectbrand() {
        ModelAndView model = new ModelAndView("zeg_net_find_product_table");
        List<ZegarekNetBrand> brandList = zegareknetService.getZegaNetBrand();
        model.addObject("zegarekNetBrand", brandList);
        return model;
    }

    @RequestMapping(value = "/zegarek_net/show_selected_brand", method = RequestMethod.POST)
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
