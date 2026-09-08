package com.valorantdigital.expensive.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @Autowired
//  private  AccountRepository userRepository;
//
//    @Autowired
// private   BudgetRepository budgetRepository;
//
//    @Autowired
//    private PaymentRepository paymentRepository;

//    @Autowired
//    public HomeController(AccountRepository userRepository, BudgetRepository budgetRepository, PaymentRepository paymentRepository) {
//
//        this.userRepository = userRepository;
//
//        this.budgetRepository = budgetRepository;
//
//        this.paymentRepository = paymentRepository;
//
//    }

    @GetMapping("/")
    public String skipToLandingPage(Model model) {
        
        return "landing";

    }
    
//    @GetMapping("/about")
//    public String goToDemo(Model model) {
//
//        model.addAttribute("info", "Great");
//
//        return "about";
//
//    }

}
