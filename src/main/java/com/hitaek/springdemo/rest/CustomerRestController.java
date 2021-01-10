package com.hitaek.springdemo.rest;

import com.hitaek.springdemo.entity.Customer;
import com.hitaek.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){

        Customer theCustomer = customerService.getCustomer(customerId);

        if(theCustomer == null)
            throw new CustomerNotFoundException("Customer id not found - " + customerId);

        return theCustomer;
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {

        // also just in case the pass an id in JSON ... set id to 0
        // this is force a save of new item ... instead of update

        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @PutMapping("/customers/{customerId}")
    public void updateCustomer(@PathVariable int customerId, @RequestBody Customer customer){
        customerService.saveCustomer(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable int customerId){

        Customer customer = getCustomer(customerId);

        customerService.deleteCustomer(customer.getId());
    }



}
