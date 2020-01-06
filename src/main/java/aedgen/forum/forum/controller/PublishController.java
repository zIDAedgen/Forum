package aedgen.forum.forum.controller;

import aedgen.forum.forum.Mapper.QuestionMapper;
import aedgen.forum.forum.Mapper.UserMapper;
import aedgen.forum.forum.Model.Question;
import aedgen.forum.forum.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired(required = false)
    private QuestionMapper questionMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {

        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "description", required = false) String description,
        @RequestParam(value = "tag", required = false) String tag,
        HttpServletRequest request,
        Model model ) {


        if (title == null || title == "") {
            model.addAttribute("error", "need a title");
            return "publish";
        }

        if (description == null || description == "") {
            model.addAttribute("error", "need a description");
            return "publish";
        }

        if (tag == null || tag == "") {
            model.addAttribute("error", "need a tag");
            return "publish";
        }


        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);


        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        if (user == null) {
            model.addAttribute("error", "please login!");
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }



}
