package com.example.demo.service;

import com.example.demo.dto.AccountDto;
import com.example.demo.dto.AccountDtoConverter;
import com.example.demo.dto.CreateAccountRequest;
import com.example.demo.model.Account;
import com.example.demo.model.Customer;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountDtoConverter  converter;




    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService,
                          AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
        this.converter = converter;
    }

    public AccountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());
        Account account= new Account(customer,
                createAccountRequest.getInitialCredit(),
                LocalDateTime.now());

        if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO)> 0){
            Transaction transaction = new Transaction(createAccountRequest.getInitialCredit(),account);

            account.getTransaction().add(transaction);

        }
        return converter.convert(accountRepository.save(account));

    }
}
