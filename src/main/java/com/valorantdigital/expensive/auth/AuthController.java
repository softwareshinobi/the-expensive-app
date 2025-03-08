package com.valorantdigital.expensive.auth;

import com.valorantdigital.expensive.account.AccountService;
import com.valorantdigital.expensive.account.Account;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService) {

        this.accountService = accountService;

    }

    @GetMapping("register")
    public String renderRegisterForm(Model model) {

        model.addAttribute("myUser", new Account());

        return "auth/register";

    }

    @PostMapping("register")
    public String processRegisterForm(@ModelAttribute Account account, Model model) {

        System.out.println(">> myUser: " + account);

        Account savedUser = this.accountService.saveUser(account);

        if (savedUser != null) {

            model.addAttribute("info", "Signup success!");

            model.addAttribute("myUser", new Account());

            return "auth/login";

        } else {

            model.addAttribute("error", "Signup Failed");

            return "auth/register";

        }

    }

    @GetMapping("login")
    public String renderLoginForm(Model model) {

        model.addAttribute("myUser", new Account());

        model.addAttribute("error", "");

        return "auth/login";

    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute Account myUser, Model model, HttpServletRequest request) {

        System.out.println(">> myUser (from form): " + myUser);

        Account account = this.accountService.authenticateUser(myUser);

        if (account != null) {

            System.out.println("user not null inside login");
            
            model.addAttribute("myUser", account);

            request.getSession(true).setAttribute("currentUser", account);

            return "home/landing";

        } else {
            
            System.out.println("bad login or something?");

            model.addAttribute("myUser", new Account());
            
            model.addAttribute("error", "Invalid credentials. Try Again.");

            return "auth/login";

        }

    }

}
