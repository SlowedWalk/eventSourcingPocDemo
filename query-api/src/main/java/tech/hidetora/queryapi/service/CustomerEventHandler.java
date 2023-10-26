package tech.hidetora.queryapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import tech.hidetora.coreAPI.events.CustomerCreatedEvent;
import tech.hidetora.queryapi.entity.Customer;
import tech.hidetora.queryapi.repository.CustomerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerEventHandler {
    private final CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.info("CUSTOMER CREATED EVENT OCCURRED");
        customerRepository.save(Customer.builder()
                .id(event.getId())
                .name(event.getName())
                .email(event.getEmail())
                .build());
    }
}
