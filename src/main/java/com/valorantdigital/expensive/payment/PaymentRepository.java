package com.valorantdigital.expensive.payment;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

//    Optional<Payment> findByName(String name);
//
    List<Payment> findByUserID(Integer userID);
//
//    Optional<Payment> findById(Integer id);
//
//    Optional<Payment> findByAmount(Double amount);
//
//    Optional<Payment> findByType(String type);
//
//    Optional<Payment> findByDescription(String description);

 //   public Optional<Double> sumByState(String completed);

}
