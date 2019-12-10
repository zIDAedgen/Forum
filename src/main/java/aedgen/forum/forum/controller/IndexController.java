package aedgen.forum.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    /*
    @GetMapping("/hello")
    public String hello (@RequestParam(name = "value") String sign, Model model) {
        model.addAttribute("value", sign);
        return "index";
    }

     */
    @GetMapping("/key")
    public String index() {
        return "index";
    }
}
