package com.llf.controller;

import com.llf.bean.SignInBean;
import com.llf.bean.User;
import com.llf.framework.GetMapping;
import com.llf.framework.ModelAndView;
import com.llf.framework.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    private Map<String, User> userDatabase = new HashMap<String, User>() {
        {
//            List<User> users = List.of( //
//                    new User("bob@example.com", "bob123", "Bob", "This is bob."),
//                    new User("tom@example.com", "tomcat", "Tom", "This is tom."));
            List<User> users = new ArrayList<User>() {{
                add(new User("bob@example.com", "bob123", "Bob", "This is bob."));
                add(new User("tom@example.com", "tomcat", "Tom", "This is tom."));
            }};
            users.forEach(user -> {
                put(user.email, user);
            });
        }
    };

    @GetMapping("/signin")
    public ModelAndView signin() {
        return new ModelAndView("/signin.html");
    }

    @PostMapping("/signin")
    public ModelAndView doSignin(SignInBean bean, HttpServletResponse response,
                                 HttpSession session) throws IOException {
        User user = userDatabase.get(bean.email);
        if (user == null || !user.password.equals(bean.password)) {
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.write("{\"error\":\"Bad email or password\"}");
            pw.flush();
        } else {
            session.setAttribute("user", user);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.write("{\"result\":true}");
            pw.flush();
        }
        return null;
    }
    @GetMapping("/signout")
    public ModelAndView signout(HttpSession session) {
        session.removeAttribute("user");
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/user/profile")
    public ModelAndView profile(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ModelAndView("redirect:/signin");
        }
        return new ModelAndView("/profile.html", "user", user);
    }
}

