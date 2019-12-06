package aedgen.forum.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class helloController {
    @GetMapping("/hello")
    public String hello (@RequestParam(name = "value") String sign, Model model) {
        model.addAttribute("value", sign);
        return "index";
    }
}
