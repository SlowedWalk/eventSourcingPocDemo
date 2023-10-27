package tech.hidetora.queryapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import tech.hidetora.coreAPI.queries.GetAllCustomersQuery;
import tech.hidetora.coreAPI.queries.GetCustomerByIdQuery;
import tech.hidetora.queryapi.entity.Customer;
import tech.hidetora.queryapi.repository.CustomerRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerQueryHandler {
    private final CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> customerList(GetAllCustomersQuery query) {
        log.info("GET ALL CUSTOMERS QUERY TRIGGERED");
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer customer(GetCustomerByIdQuery query) {
        log.info("GET CUSTOMER QUERY TRIGGERED");
        return customerRepository.findById(query.getId())
                .orElseThrow(() -> new EntityNotFoundException("No customer found for id " + query.getId()));
    }
}
