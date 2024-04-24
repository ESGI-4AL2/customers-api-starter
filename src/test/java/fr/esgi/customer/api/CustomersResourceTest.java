package fr.esgi.customer.api;

import fr.esgi.customer.services.CustomersFileParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.esgi.customer.samples.CustomerSample.customers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomersResourceTest {

    @InjectMocks
    private CustomerResource customerResource;

    @Mock
    private CustomersFileParser customersFileParser;


    @Test
    void shouldGetRentalProperties() {
        var expectedCustomers = customers();

        when(customersFileParser.parse("customers.csv")).thenReturn(expectedCustomers);

        var customers = customerResource.getRentalProperties();

        assertThat(customers).containsExactlyInAnyOrderElementsOf(expectedCustomers);

        verify(customersFileParser).parse("customers.csv");

        verifyNoMoreInteractions(customersFileParser);
    }

}
