package com.valorantdigital.expensive.account;

import com.valorantdigital.expensive.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmailAllIgnoreCase(String email);

    Optional<Account> findByIdAllIgnoreCase(Integer id);

    Optional<Account> findByFirstNameAllIgnoreCase(String firstName);

    Optional<Account> findByLastNameAllIgnoreCase(String lastName);

}
