<%-- 
    Document   : issueBookForm
    Created on : Apr 3, 2017, 7:04:01 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <div id="mainform">
            <div id="form">
                <h3>Fill Your Information !</h3>
                <div>
                    <label class="form-group">Book Id :</label>
                    <div id="bid_to_issue"><%= request.getParameter("bid")%></div>
                    <label class="form-group">Book Name :</label>
                    <div id="bookname_to_issue"><%= request.getParameter("bookname")%></div>
                    <label class="form-group">Author Name :</label>
                    <div id="authorname_to_issue"><%= request.getParameter("authorname")%></div>
                    <label class="form-group">Issue to :</label>
                    <div>
                        <input class="form-control" id="enrl_to_issue" type="text" placeholder="Enrollment Number">
                        <button class="btn btn-success form-group" onclick="IssueToEnrlNo()" >Issue</button>
                    </div>
                    
                </div>
            </div>
        </div>

        

        
    
  
  
      
  

