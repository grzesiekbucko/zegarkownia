package pl.marko.zegarki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.marko.zegarki.services.ZegareknetService;

import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ZegareknetService zegareknetService;

    @GetMapping("/index")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "mainPage";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView watchList() throws IOException {
        ModelAndView model = new ModelAndView("mainPage");
        Map<String, String> watchlist = zegareknetService.List();
        model.addObject("testlist", watchlist);
        model.addObject("name", watchlist.get(0));
        model.addObject("link", watchlist.get(1));
        return model;
    }

    @RequestMapping(value = "/links", method = RequestMethod.POST)
    public ModelAndView getLinks(@RequestParam String html, String brand) throws IOException {
        String file_name = zegareknetService.getProduktLinks(html, brand);
        ModelAndView model = new ModelAndView("mainPage");
        model.addObject("success_label", "File saved! " + file_name);
        return model;
    }


}
