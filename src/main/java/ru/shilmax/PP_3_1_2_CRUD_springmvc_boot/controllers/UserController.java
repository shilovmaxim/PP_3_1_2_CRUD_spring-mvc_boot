package ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.models.User;
import ru.shilmax.PP_3_1_2_CRUD_springmvc_boot.services.UserService;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String usersPage(ModelMap model) {
        model.addAttribute("listUsers", userService.listUsers());
        return "index";
    }

    @GetMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        return "/add";
    }

    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/add";
        }
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/find")
    public String findUser(@ModelAttribute("user") User user) {
        return "/find";
    }

    @PostMapping("/find")
    public String find(@ModelAttribute("user") User user) {
        Long id = userService.findByEmail(user.getEmail());
        return id > 0L ? "redirect:/" + id : "/not_found";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.findById(id));
        return "/edit";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.findById(id));
        return model.getAttribute("user") != null ? "/user" : "/404";
    }

    @PatchMapping("/{id}")
    public String update(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        userService.update(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}/edit")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

}
