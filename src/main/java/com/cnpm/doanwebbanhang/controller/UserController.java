package com.cnpm.doanwebbanhang.controller;

import com.cnpm.doanwebbanhang.model.User;
import com.cnpm.doanwebbanhang.service.UserService;
import com.cnpm.doanwebbanhang.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.Optional;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public ModelAndView showUser(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
        Page<User> users;
        if (s == null) {
            users = userService.findAll(pageable);
        } else {
            users = userService.findAllByNameContaining(s, pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
    //
    @GetMapping("/edit-user/{id}")
    public ModelAndView editUser(@PathVariable Long id) {
        Optional<User> user =userService.findById(id);
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView("/user/edit");
            modelAndView.addObject("user", user);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-user")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("/user/edit");
        modelAndView.addObject("user", user);
        modelAndView.addObject("message","Thành Công");
        return modelAndView;
    }

    @GetMapping("/product/list")
    public String admin() {
        return "product/list";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "login/403";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login/login";
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView("login/registration");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @PostMapping("/registration")
    public ModelAndView creatNewUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView("login/registration");
        userDetailService.saveUser(user);
        modelAndView.addObject("message", "User has been registered successfully");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

//    @GetMapping("create-user")
//    public ModelAndView createUser() {
//        ModelAndView modelAndView = new ModelAndView("user/create");
//        modelAndView.addObject("user", new User());
//        return modelAndView;
//    }
//
//    @PostMapping("create-user")
//    public ModelAndView createUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            ModelAndView modelAndView = new ModelAndView("user/create");
//            return modelAndView;
//        } else {
//            userService.save(user);
//            ModelAndView modelAndView = new ModelAndView("user/create");
//            modelAndView.addObject("user", user);
//            modelAndView.addObject("message", "New Create UserServiceImpl ");
//            return modelAndView;
//        }
//    }
//
//    @GetMapping("users")
//    public ModelAndView showUser(@PageableDefault(size = 10) Pageable pageable, @ModelAttribute("s") String s) {
//        Page<User> users;
//        if (s == null) {
//            users = userService.findAll(pageable);
//        } else {
//            users = userService.findAllByNameContaining(s, pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("/user/list");
//        modelAndView.addObject("users", users);
//        return modelAndView;
//    }
//
//    @GetMapping("edit-user/{id}")
//    public ModelAndView editUser(@PathVariable Long id) {
//        Optional<User> user =userService.findById(id);
//        if (user != null) {
//            ModelAndView modelAndView = new ModelAndView("/user/edit");
//            modelAndView.addObject("user", user);
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("edit-user")
//    public ModelAndView updateUser(@ModelAttribute("user") User user) {
//        userService.save(user);
//        ModelAndView modelAndView = new ModelAndView("/user/edit");
//        modelAndView.addObject("user", user);
//        modelAndView.addObject("message","Thành Công");
//        return modelAndView;
//    }
//
//    @GetMapping("delete-user/{id}")
//    public ModelAndView deleteUser(@PathVariable Long id) {
//        Optional<User> user = userService.findById(id);
//        if (user != null) {
//            ModelAndView modelAndView = new ModelAndView("/user/delete");
//            modelAndView.addObject("user", user);
//            return modelAndView;
//        } else {
//            ModelAndView modelAndView = new ModelAndView("error.404");
//            return modelAndView;
//        }
//    }
//
//    @PostMapping("delete-user")
//    public String delete(@ModelAttribute("user") User user) {
//        userService.remove(user.getId());
//        return "redirect:users";
//
//    }

}
