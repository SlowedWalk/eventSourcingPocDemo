package tech.hidetora.commandapi.aggregate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import tech.hidetora.coreAPI.commands.CreateCustomerCommand;
import tech.hidetora.coreAPI.events.CustomerCreatedEvent;

@Aggregate
@Slf4j
public class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    @Getter private String name;
    @Getter private String email;

    public CustomerAggregate() {
        // required by axon
    }

    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand command) {
        log.info("📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥");
        log.info("📥📥📥 CREATE CUSTOMER COMMAND TRIGGERED 📥📥📥");
        log.info("📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥");
        AggregateLifecycle.apply(new CustomerCreatedEvent(
                command.getId(),
                command.getName(),
                command.getEmail()
        ));
    }

    @EventSourcingHandler
    public void on(CustomerCreatedEvent event) {
        log.info("📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥");
        log.info("📥📥📥 CUSTOMER CREATED EVENT OCCURRED 📥📥📥");
        log.info("📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥📥");
        this.id = event.getId();
        this.name = event.getName();
        this.email = event.getEmail();
    }
}
