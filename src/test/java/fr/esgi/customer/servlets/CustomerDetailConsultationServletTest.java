package fr.esgi.customer.servlets;

import fr.esgi.customer.exception.MissingCustomerId;
import fr.esgi.customer.exception.NotFoundCustomerException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static fr.esgi.customer.samples.CustomerSample.oneCustomer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerDetailConsultationServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Test
    void shouldDoGet() throws ServletException, IOException {
        String jspPath = "/customerDetail.jsp";
        var customer = oneCustomer();

        var customerDetailConsultationServlet = new CustomerDetailConsultationServlet();

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(httpServletRequest.getParameter("id")).thenReturn("46890");
        when(httpServletRequest.getRequestDispatcher(jspPath)).thenReturn(requestDispatcher);

        customerDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletRequest).setAttribute("customer", customer);
        verify(httpServletRequest).getRequestDispatcher(jspPath);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        verifyNoMoreInteractions(httpServletRequest, httpServletResponse, requestDispatcher);
    }

    @Test
    void givenNullIdParamValue_shouldThrowMissingRentalPropertyId() {
        var customerDetailConsultationServlet = new CustomerDetailConsultationServlet();

        assertThatExceptionOfType(MissingCustomerId.class)
                .isThrownBy(() -> customerDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Parameter id is null"));

        verify(httpServletRequest).getParameter("id");

        verifyNoInteractions(httpServletResponse);
        verifyNoMoreInteractions(httpServletRequest);
    }

    @Test
    void givenIdParamValueNotParsableToInt_shouldThrowNumberFormatException() {
        var customerDetailConsultationServlet = new CustomerDetailConsultationServlet();

        when(httpServletRequest.getParameter("id")).thenReturn("toto");

        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> customerDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse));

        verify(httpServletRequest).getParameter("id");

        verifyNoInteractions(httpServletResponse);
        verifyNoMoreInteractions(httpServletRequest);
    }

    @Test
    void givenNonExistentIdParamValue_shouldThrowNotFoundRentalPropertyException() {
        var customerDetailConsultationServlet = new CustomerDetailConsultationServlet();

        when(httpServletRequest.getParameter("id")).thenReturn("46390");

        assertThatExceptionOfType(NotFoundCustomerException.class)
                .isThrownBy(() -> customerDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Not found referenceId : 46390"));

        verify(httpServletRequest).getParameter("id");

        verifyNoInteractions(httpServletResponse);
        verifyNoMoreInteractions(httpServletRequest);
    }

}
