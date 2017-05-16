<%-- 
    Document   : Extra
    Created on : 19 Jun, 2016, 2:28:15 AM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% String reason = (String)request.getAttribute("reason"); %>
        <% String topic = (String)request.getAttribute("topic"); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= topic %></title>
        
    </head>
    <body id="registerbody">
        
        <div class="headerjsp">           
        </div>
        
        <div id="extrapage"><%= reason %></div>
    </body>
</html>
