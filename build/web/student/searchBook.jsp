<%-- 
    Document   : searchBook
    Created on : Mar 25, 2017, 3:45:42 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <label>Search by bookname :</label>
                    <div>
                        <input id="searchbookname" type="text" placeholder="Enter Bookname" onkeyup="SearchByBookname()">
                        
                    </div>
        
        <div id="SearchBookResult"></div>
    </body>
</html>
