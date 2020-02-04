package pl.marko.zegarki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/index")
    public String showHomePage() {
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView showMainPage() {
        ModelAndView model = new ModelAndView("mainPage");
        List<ZegarekNetBrand> list = zegareknetService.getZegaNetBrand();
        model.addObject("zegarekNetBrand", list);
        return zegareknetService.updateTimestamp(model);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String watchList() throws IOException {
        zegareknetService.saveBrandList();
        return "redirect:/main";
    }

    @RequestMapping(value = "/links", method = RequestMethod.POST)
    public String getLinks(@RequestParam String html, ZegarekNetBrand brand) throws IOException {
        zegareknetService.saveProduktLinks(html, brand);
        return "redirect:/main";
    }


}
