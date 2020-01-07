package aedgen.forum.forum.controller;

import aedgen.forum.forum.DTO.QuestionDTO;
import aedgen.forum.forum.Mapper.QuestionMapper;
import aedgen.forum.forum.Mapper.UserMapper;
import aedgen.forum.forum.Model.Question;
import aedgen.forum.forum.Model.User;
import aedgen.forum.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {
    /*
    @GetMapping("/hello")
    public String hello (@RequestParam(name = "value") String sign, Model model) {
        model.addAttribute("value", sign);
        return "index";
    }

     */
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private QuestionService questionService;



    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }


        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions", questionList);

        return "index";
    }
}
