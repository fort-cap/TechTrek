package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.Cohort;
import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.models.UserWithRoles;
import dev.techtrek.techtrek.repositories.CohortsRepo;
import dev.techtrek.techtrek.repositories.Roles;
import dev.techtrek.techtrek.repositories.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private CohortsRepo cohortsRepo;
    //    private UserWithRoles userWithRoles;
    private Roles roles;

    public UserController(Users users, PasswordEncoder passwordEncoder, CohortsRepo cohortsRepo, Roles roles) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.cohortsRepo = cohortsRepo;
        this.roles = roles;

    }


    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    // Landing page
    @GetMapping("/")
    public String showLandingPage(Model model) {
        List<Cohort> cohorts = cohortsRepo.findAll();
        model.addAttribute("cohorts", cohorts);
        model.addAttribute("user", new User());
        return "index";
    }

    // Dashboard view
    @GetMapping("/home")
    public String showDashboard(Model model) {
        model.addAttribute("user", new User());
        return "users/index";
    }

    // Profile view
    @GetMapping("/profile")
    public String showProfile(Model model) {
        UserWithRoles userWithRoles = (UserWithRoles) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = users.getOne(userWithRoles.getId());
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping("/profile")
    public String editProfile(

            @RequestParam(name = "github_username") String githubUsername
    )
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //1. Get the current user
        User currentUser = new User(user);
        // Set the github username
        currentUser.setGithubUsername(githubUsername);

        // save the record
        users.save(currentUser);

        return "redirect:/profile";
    }

}
