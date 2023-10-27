package tech.hidetora.commandapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.coreAPI.commands.CreateCustomerCommand;
import tech.hidetora.coreAPI.dto.CustomerRequestDTO;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static tech.hidetora.coreAPI.constants.Constants.*;

@Slf4j
@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
public class CustomerCommandController {
    private final CommandGateway commandGateway;
    private final EventStore eventStore;

    @PostMapping(CREATE_CUSTOMER_URL)
    public CompletableFuture<String> createCustomer(@RequestBody CustomerRequestDTO request) {
        return commandGateway.send(new CreateCustomerCommand(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail()
        ));
    }

    @GetMapping(GET_CUSTOMER_STORE_URL)
    public Stream eventStore(@PathVariable String customerId) {
        return eventStore.readEvents(customerId).asStream();
    }
}
