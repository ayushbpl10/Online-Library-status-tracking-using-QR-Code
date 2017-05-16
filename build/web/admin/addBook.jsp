<%-- 
    Document   : addBook
    Created on : Mar 16, 2017, 9:05:48 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <div id="mainform">
            <div id="form">
                <h3>Fill the Information of the book !</h3>
                <div>
                    <label class="form-group">Book Name :</label>
                    <input id="bookname" class="form-control" type="text">
                    <label class="form-group">Author Name :</label>
                    <input id="authorname" class="form-control" type="text"><br>
                <input id="submit" class="btn btn-info form-group" type="button" onclick="AddBookToDb()" value="Submit">
                </div>
            </div>
        </div>
        
        <br><br>
        <div>
                <div id="Result"></div>
             
                <div id="QRCode"></div>
        </div>

        
    
  
  
      
  

