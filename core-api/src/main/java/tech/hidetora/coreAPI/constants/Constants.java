package tech.hidetora.coreAPI.constants;

public class Constants {
    public static final String BASE_URL = "/api/v1";
    public static final String CREATE_CUSTOMER_URL = "/command/customer/create";
    public static final String GET_CUSTOMER_STORE_URL = "/command/customer/eventStore/{customerId}";
    public static final String GET_ALL_CUSTOMERS_URL = "/query/customer/all";
    public static final String GET_CUSTOMER_URL = "/query/customer/{customerId}";
}
