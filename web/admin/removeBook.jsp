<%-- 
    Document   : removeBook
    Created on : Mar 16, 2017, 9:05:48 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <div id="mainform">
        <div id="form">
        <h3>Fill Information to remove book !</h3>
        <div>
            <label class="form-group">Book Name :</label>
            <input class="form-control" id="bookname" type="text">
        <label class="form-group">Author Name :</label>
        <input class="form-control" id="authorname" type="text"><br>
        <input class="btn btn-danger form-group" id="submit" type="button" onclick="RemoveBookFromDb()" value="Submit">
        </div>
        </div>
        </div>
