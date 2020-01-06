package aedgen.forum.forum.controller;


import aedgen.forum.forum.DTO.AccessTokenDTO;
import aedgen.forum.forum.DTO.GithubUser;
import aedgen.forum.forum.Mapper.UserMapper;
import aedgen.forum.forum.Model.User;
import aedgen.forum.forum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class OAuthController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                            //session is fetched in http
                           //For HttpServletRequest object,it denotes the request from client
                            HttpServletRequest request,
                            HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //System.out.println(user.getName());
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            //user.setToken(UUID.randomUUID().toString());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            //
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            //user.getGmtCreate(java.time.LocalTime.now());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));
            //user is not null : we got the info of the user, login successfully
            // request.getSession().setAttribute("user", githubUser);
            //

            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
