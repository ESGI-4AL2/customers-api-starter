package fr.esgi.customer.servlets;

import fr.esgi.customer.exception.MissingCustomerId;
import fr.esgi.customer.exception.NotFoundCustomerException;
import fr.esgi.customer.services.CustomersFileParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/details")
public class CustomerDetailConsultationServlet extends HttpServlet {

    private final CustomersFileParser customersFileParser;

    public CustomerDetailConsultationServlet() {
        this.customersFileParser = new CustomersFileParser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParamValue = request.getParameter("id");

        if (idParamValue == null) {
            throw new MissingCustomerId("Parameter id is null");
        }

        int id = Integer.parseInt(idParamValue);

        var customer = customersFileParser.parse("customers.csv")
                .stream()
                .filter(r -> r.referenceId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundCustomerException("Not found referenceId : " + id));

        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/customerDetail.jsp").forward(request, response);
    }
}
