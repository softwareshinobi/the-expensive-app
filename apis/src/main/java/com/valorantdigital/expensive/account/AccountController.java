package com.valorantdigital.expensive.account;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("user")
public class AccountController {

    //
    //  return displayUserManageScreen(model, request);
    //
    @Autowired
    final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {

        this.accountService = accountService;

    }

    @GetMapping("")
    public String eee(Model model, HttpServletRequest request) {

        return displayUserManageScreen(model, request);
        
    }

    @GetMapping("/")
    public String displayUserManageScreen(Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        //todo make a function
        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Account currentUser = (Account) obj;

        model.addAttribute("myUser", currentUser);

        List<Account> MyUserList = this.accountService.getAll();

        model.addAttribute("myUserList", MyUserList);

        return "user/list";

    }

    @GetMapping("create")
    public String displayUserCreateScreen(Model model, HttpServletRequest request) {

        model.addAttribute("myUser", new Account());

        return "user/create";

    }

    @PostMapping("create")
    public String processUserCreateForm(@ModelAttribute Account account, Model model, HttpServletRequest request) {

        System.out.println(" enter > processUserCreateForm / " + account);

        Account savedUser = this.accountService.saveUser(account);

        if (savedUser != null) {

            model.addAttribute("info", "Signup success!");

        } else {

            model.addAttribute("error", "Signup Failed");

        }

        return displayUserManageScreen(model, request);

    }

    @GetMapping("edit/{id}")
    public String renderEditForm(@PathVariable Integer id, Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {
            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Optional<Account> accountOptional = this.accountService.get(id);

        if (accountOptional.isPresent()) {

            model.addAttribute("accountToEdit", accountOptional.get());

            
            
            model.addAttribute("info", "editing the user!!!");

            return "user/edit";

        } else {

            model.addAttribute("error", "User! not found for id: " + id);

            return "redirect:/user/list";

        }

    }
    
        @PostMapping("edit")
    public String processditForm(@ModelAttribute Account account, Model model, HttpServletRequest request) {

        System.out.println(" enter > processUserCreateForm / " + account);

        Account savedUser = this.accountService.saveUser(account);

        if (savedUser != null) {

            model.addAttribute("info", "Signup success!");

        } else {

            model.addAttribute("error", "Signup Failed");

        }

        
        return displayUserManageScreen(model, request);

    }
//    @GetMapping("edit")
//    public String editUser(@RequestParam Integer id, Model model, HttpServletRequest request) {
//
//        Object currentUser = request.getSession().getAttribute("currentUser");
//
//        if (currentUser == null) {
//
//            model.addAttribute("error", "Login session expired");
//
//            return "redirect:/auth/login";
//
//        } else {
//
//            Account authenticatedUser = (Account) currentUser;
//
//            model.addAttribute("myUser", authenticatedUser);
//
//        }
//
//        Optional<Account> accountToEdit = this.accountService.get(id);
//
//        if (accountToEdit.isPresent()) {
//
//            model.addAttribute("accountToEdit", accountToEdit.get());
//
//            return "user/edit";
//
//        } else {
//
//            model.addAttribute("error", "no user account with id / " + id);
//
//            return displayUserManageScreen(model, request);
//
//        }
//
//    }

//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable Integer id, Model model, HttpServletRequest request) {
//
//        Object obj = request.getSession().getAttribute("currentUser");
//
//        if (obj == null) {
//            model.addAttribute("error", "Login session expired");
//
//            return "redirect:/auth/login";
//
//        }
//
//        //System.out.println(" enter > processUserEditForm / " + myUser);
//        //System.out.println(" myUser / " + myUser);
//        Account userToDelete = this.accountService.get(id).get();
//
//        System.out.println(" savedUser / " + userToDelete);
//
//        if (userToDelete != null) {
//
//            model.addAttribute("info", "User updated!");
//
//        } else {
//
//            model.addAttribute("error", "User edit Failed");
//
//        }
//
//        return displayUserManageScreen(model, request);
//
//    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Account authenticatedUser = (Account) obj;

        model.addAttribute("myUser", authenticatedUser);

        this.accountService.delete(id);

        return displayUserManageScreen(model, request);

    }

    //move this somewhere else
    @GetMapping("userHome")
    public String userHome(Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Account currentUser = (Account) obj;

        model.addAttribute("myUser", currentUser);

        return "home/home";

    }

}
