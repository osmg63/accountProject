package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.CustomerDtoConverter;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;

public class CustomerServiceTest {
    private CustomerService service;
    private CustomerRepository repository;
    private CustomerDtoConverter converter;


    @BeforeEach
    public void setUp(){
        repository= mock(CustomerRepository.class);
        converter=mock(CustomerDtoConverter.class);
        service = new CustomerService(repository,converter);
    }

    @Test
    public  void testFindByCustomerId_whenCustomerIdExist_shouldReturnCustomer(){
        Customer customer=new Customer("id","name","surname", Set.of());
        Mockito.when(repository.findById("id")).thenReturn(Optional.of(customer));

        Customer result =service.findCustomerById("id");
        assertEquals(result,customer);
    }
    @Test
    public  void testFindByCustomerId_whenCustomerIdDoesNotExist_shouldThrowCustomerNotFoundException(){
        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class,()->  service.findCustomerById("id"));
    }
    @Test
    public  void testGetCustomerById_whenCustomerIdExist_shouldReturnCustomer(){
        Customer customer=new Customer("id","name","surname", Set.of());
        CustomerDto customerDto=new CustomerDto("id","name","surname",Set.of());


        Mockito.when(repository.findById("id")).thenReturn(Optional.of(customer));
        Mockito.when(converter.convertToCustomerDto(customer)).thenReturn(customerDto);

        CustomerDto result =service.getCustomerById("id");
        assertEquals(result,customerDto);
    }
    @Test
    public  void testGetCustomerById_whenCustomerIdDoesNotExist_shouldThrowExceptionCustomer() {

        Mockito.when(repository.findById("id")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> service.getCustomerById("id"));
        Mockito.verifyNoInteractions(converter);

    }

}