package com.example.bankaccountmicroservice;

import com.example.bankaccountmicroservice.entities.BankAccount;
import com.example.bankaccountmicroservice.entities.Customer;
import com.example.bankaccountmicroservice.enums.AccountType;
import com.example.bankaccountmicroservice.repositories.BankAccountRepository;
import com.example.bankaccountmicroservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountMicroServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            Stream.of("Yassine","Yahya","Ali","Saad","Hicham").forEach(name->{
                Customer customer = Customer.builder()
                        .name(name)
                        .build();
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(customer -> {
                for (int i = 0; i < 10; i++) {
                    BankAccount bankAccount = BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .balance(Math.random()*20000)
                            .currency("EUR")
                            .type(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                            .createdAt(new Date())
                            .customer(customer)
                            .build();
                    bankAccountRepository.save(bankAccount);
                }

            });
        };
    }


}