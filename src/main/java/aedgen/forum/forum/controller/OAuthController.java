package aedgen.forum.forum.controller;


import aedgen.forum.forum.DTO.AccessTokenDTO;
import aedgen.forum.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OAuthController {

    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("f3f624b867637a65cede");
        accessTokenDTO.setClient_secret("8850416daebb1fe19c497a9117b16732992fee92");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8885/callback");
        accessTokenDTO.setState(state);
        return "index";
    }
}
