package life.community.controller;

import life.community.dto.AccessTokenDTO;
import life.community.dto.GithubUser;
import life.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lvconl
 * 授权控制器
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("23f7a5b0dede4b09906c");
        accessTokenDTO.setClient_secret("87529bf703f6f8e7f0f373c3f9aee296fa9cc0e1");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        GithubUser user = githubProvider.getUser(githubProvider.getAccessToken(accessTokenDTO));
        System.out.println(user.getName());
        return "index";
    }
}
