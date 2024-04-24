package fr.esgi.customer.servlets;

import fr.esgi.customer.services.CustomersFileParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/list")
public class CustomersConsultationServlet extends HttpServlet {

    private final CustomersFileParser customersFileParser;

    public CustomersConsultationServlet() {
        this.customersFileParser = new CustomersFileParser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var customers = customersFileParser.parse("customers.csv");
        request.setAttribute("customers", customers);

        request.getRequestDispatcher("/customers.jsp").forward(request, response);
    }

}
