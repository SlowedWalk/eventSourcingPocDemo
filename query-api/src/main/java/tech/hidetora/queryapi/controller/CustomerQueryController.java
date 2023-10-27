package tech.hidetora.queryapi.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.hidetora.coreAPI.dto.ApiResponseDTO;
import tech.hidetora.coreAPI.queries.GetAllCustomersQuery;
import tech.hidetora.coreAPI.queries.GetCustomerByIdQuery;
import tech.hidetora.queryapi.entity.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static tech.hidetora.coreAPI.constants.Constants.*;

@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
public class CustomerQueryController {
    private final QueryGateway queryGateway;

    @GetMapping(GET_ALL_CUSTOMERS_URL)
    public ResponseEntity<ApiResponseDTO> getCustomers(HttpServletRequest request) {
        CompletableFuture<List<Customer>> query = queryGateway.query(new GetAllCustomersQuery(), ResponseTypes.multipleInstancesOf(Customer.class));
        ApiResponseDTO response = new ApiResponseDTO();
        response.setMethod(HttpMethod.GET.name());
        response.setPath(request.getRequestURI());
        response.setData(query.join());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Successfully retrieved all customers");
        return ResponseEntity.ok(response);
    }

    @GetMapping(GET_CUSTOMER_URL)
    public ResponseEntity<ApiResponseDTO> getCustomer(@PathVariable String customerId, HttpServletRequest request) {
        CompletableFuture<Customer> query = queryGateway.query(new GetCustomerByIdQuery(customerId), ResponseTypes.instanceOf(Customer.class));
        ApiResponseDTO response = new ApiResponseDTO();
        response.setMethod(HttpMethod.GET.name());
        response.setPath(request.getRequestURI());
        response.setData(query.join());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Successfully retrieved all customers");
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> exceptionHandler(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setMethod(request.getMethod());
        apiResponseDTO.setPath(request.getRequestURI());
        apiResponseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiResponseDTO.setMessage(ex.getMessage().split(": ")[1]);
        apiResponseDTO.setException(ex.getMessage().split(":")[0]);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponseDTO);
    }
}
