package dev.techtrek.techtrek.controllers;


import dev.techtrek.techtrek.models.User;
import dev.techtrek.techtrek.repositories.UsersRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResumeController {

    // FIXME: Need a DB table called resumes and Java Bean for Resume with foreign key to the User id.

    // FIXME: Need a ResumeRepo class to access data from the resumes table



    @GetMapping("/resume")
    public String showResume(Model model){
        model.addAttribute("user", new User());
        return "resume/index";
    }
}