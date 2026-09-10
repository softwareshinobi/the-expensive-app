package com.valorantdigital.expensive.payment;

import com.valorantdigital.expensive.account.Account;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("payments")
public class PaymentMVCController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("")
    public String PaymentMVCController() {
        return "PaymentMVCController";
    }

    @GetMapping("/")
    public String list_all_render(Model model, HttpServletRequest request) {

        Object currentUserObject = request.getSession().getAttribute("currentUser");

        if (currentUserObject == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        } 

            Account currentUser = (Account) currentUserObject;

            model.addAttribute("myUser", currentUser);

            List<Payment> paymentsList = this.paymentService.getTransactionsForUser(currentUser.getId());

            if (paymentsList != null) {

                model.addAttribute("payments", paymentsList);

                model.addAttribute("uniqueclients", 2);
                
                model.addAttribute("count", 3);
                
                model.addAttribute("topline", 444);
                
                model.addAttribute("clientoftheweek", "Yankee");
                
            } else {

                model.addAttribute("payments", new ArrayList<>());

                return "payments/list";

            }

            return "payments/list";        

    }

//        @GetMapping("byState")
//    public Map<String, List<Payment>> getPaymentsByState() {
//        
//        Map<String, List<Payment>> paymentsByState = this.paymentService.getPaymentsByState();
//        
//        return  paymentsByState;
//        
//    }
    
    @GetMapping("add")
    public String add_form_render(Model model, HttpServletRequest request) {

        Object currentUserObject = request.getSession().getAttribute("currentUser");

        if (currentUserObject == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Account currentUser = (Account) currentUserObject;

        model.addAttribute("myUser", currentUser);
        model.addAttribute("payment", new Payment());

        return "payments/add";

    }

    @PostMapping("add")
    public String add_form_process(
            @ModelAttribute Payment payment,
            Model model,
            HttpServletRequest request
    ) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {
            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Account currentUser = (Account) obj;

        model.addAttribute("myUser", currentUser);

        payment.setUserID(currentUser.getId());

        paymentService.savePayment(payment);

        Payment savedTransaction = this.paymentService.savePayment(payment);

        if (savedTransaction != null) {

            model.addAttribute("info", "Save Transaction success!");

            model.addAttribute("transaction", savedTransaction);

        } else {

            model.addAttribute("transaction", payment);

            model.addAttribute("error", "Save Transaction Failed!");

        }

        model.addAttribute("formTitle", "Add Transaction");

        return "payments/add";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {
            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        Optional<Payment> transactionOptional = paymentService.get(id);

        if (transactionOptional.isPresent()) {

            model.addAttribute("payment", transactionOptional.get());

            return "payments/edit";

        } else {

            model.addAttribute("error", "Transaction not found for id: " + id);

            return "redirect:/payments/list";

        }

    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id, Model model, HttpServletRequest request) {

        Object obj = request.getSession().getAttribute("currentUser");

        if (obj == null) {

            model.addAttribute("error", "Login session expired");

            return "redirect:/auth/login";

        }

        paymentService.delete(id);

        return "redirect:/payments/";

    }

}
