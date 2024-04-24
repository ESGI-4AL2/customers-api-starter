package fr.esgi.customer.api;

import fr.esgi.customer.beans.Customer;
import fr.esgi.customer.services.CustomersFileParser;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/customers")
public class CustomerResource {

    private final CustomersFileParser customerFileParser;

    @Inject
    public CustomerResource(CustomersFileParser customerFileParser) {
        this.customerFileParser = customerFileParser;
    }

    @GET
    public List<Customer> getRentalProperties() {
        return customerFileParser.parse("customers.csv");
    }
}