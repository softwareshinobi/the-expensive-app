package com.valorantdigital.expensive.payment;

import jakarta.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = SQLException.class)
public class PaymentService {

    @Autowired
           private  PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(final PaymentRepository transactionRepository) {
        this.paymentRepository = transactionRepository;
    }

    public Payment savePayment(Payment payment) {
        return this.paymentRepository.save(payment);
    }
    
    public Optional<Payment> get(Integer paymentID) {
        return paymentRepository.findById(paymentID);
    }

    public void delete(Integer paymentID) {
        paymentRepository.deleteById(paymentID);
    }
    public List<Payment> getTransactionsForUser(Integer userID) {
        return this.paymentRepository.findByUserID(userID);
    }
//
//    public Double calculateCompletedPayments() {
//        
//        //  This is where the magic happens, Dev Team Six.  Notice the efficiency.
//        Optional<Double> sum = this.paymentRepository.sumByState("completed");
//
//        // Handle the potential empty result (no completed payments).  This is crucial for robustness
//        return sum.orElse(0.0);
//        
//    }
//      public Map<String, List<Payment>> getPaymentsByState() {
//        List<Payment> allPayments = paymentRepository.findAll(); // Get all payments.  Optimize this later if needed.
//
//        if (allPayments.isEmpty()){
//            return Map.of();//Return empty map instead of null
//        }
//
//        return allPayments.stream()
//                .collect(Collectors.groupingBy(Payment::getState));
//    }

}
