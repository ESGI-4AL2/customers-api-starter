<%@ page import="fr.esgi.customer.beans.Customer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <title>Liste des clients</title>
</head>
<body>
<h1>Liste des clients</h1>
<p>
    <%
      List<Customer> customers = (List<Customer>) request.getAttribute("customers");
      for (Customer customer : customers) {
    %>
<ul>
  <a href="/customers-api/details?id=<% out.println(customer.referenceId()); %>">
    <% out.println(String.format("Details")); %>
  </a>
  <li><% out.println(String.format("Raison sociale : %s", customer.companyName())); %></li>
  <li><% out.println(String.format("Ville : %s", customer.town())); %></li>
  <li><% out.println(String.format("Adresse : %s", customer.address())); %></li>
  <li><% out.println(String.format("Siret : %s", customer.siret())); %></li>
</ul>
<%
  }
%>
</p>
</body>
</html>
