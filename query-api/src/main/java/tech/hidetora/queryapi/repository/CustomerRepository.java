package tech.hidetora.queryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.hidetora.queryapi.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
