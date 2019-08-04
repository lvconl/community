package life.community.controller;

import life.community.entity.Question;
import life.community.entity.User;
import life.community.mapper.QuestionMapper;
import life.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 问题相关控制器
 * @author lvconl
 */
@Controller
public class QuestionController {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/addQuestion")
    public String addQuestion(@RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("tag") String tag,
                              HttpServletRequest request) {
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                User user = userMapper.queryUserByToken(token);
                question.setCreator(user.getId());
                questionMapper.addQuestion(question);
            }
        }
        return "redirect:/publish";
    }
}