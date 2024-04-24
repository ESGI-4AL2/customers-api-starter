<%@ page import="fr.esgi.customer.beans.Customer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Customer customer = (Customer) request.getAttribute("customer");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <title>Client n°<% out.println(customer.referenceId()); %></title>
</head>
<body>
<h1>Client n°<% out.println(customer.referenceId()); %></h1>
<p>
<ul>
  <li><% out.println(String.format("Raison sociale : %s", customer.companyName())); %></li>
  <li><% out.println(String.format("Ville : %s", customer.town())); %></li>
  <li><% out.println(String.format("Adresse : %s", customer.address())); %></li>
  <li><% out.println(String.format("Siret : %s", customer.siret())); %></li>
</ul>
</p>
</body>
</html>
