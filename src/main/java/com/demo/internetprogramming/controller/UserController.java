package com.demo.internetprogramming.controller;

import com.demo.internetprogramming.model.User;
import com.demo.internetprogramming.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final String MY_SESSION_NOTES_CONSTANT = "MY_SESSION_NOTES";
    private static final Logger log = LoggerFactory.getLogger(SessionController.class);
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String showUserList(final Model model, final HttpSession session) {
        final List<String> notes = (List<String>) session.getAttribute(MY_SESSION_NOTES_CONSTANT);
        model.addAttribute("sessionNotes", !CollectionUtils.isEmpty(notes) ? notes : new ArrayList<>());
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/calculator")
    public String showCalculator(Model model) {
        return "calculator";
    }

    @GetMapping("/ajax-example")
    public String showAjaxExample(Model model) {
        return "ajax-example";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);

        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);

        return "redirect:/index";
    }
}
