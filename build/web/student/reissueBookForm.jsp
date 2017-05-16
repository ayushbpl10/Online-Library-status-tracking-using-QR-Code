<%-- 
    Document   : returnBookForm
    Created on : Mar 24, 2017, 1:59:13 AM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


        <div id="mainform">
            <div id="form">
                <h3>Fill Your Information !</h3>
                <div>
                    <label>Book Id :</label>
                    <div id="bid_to_reissue"><%= request.getParameter("bid")%></div>
                    <label>Book Name :</label>
                    <div id="bookname_to_reissue"><%= request.getParameter("bookname")%></div>
                    <label>Author Name :</label>
                    <div id="authorname_to_reissue"><%= request.getParameter("authorname")%></div>
                    <label>Issued to :</label>
                    <div id="enrl_to_reissue"><%= request.getParameter("issuedto")%></div>
                    <label>Issued Date :</label>
                    <div id="date_to_reissue"><%= request.getParameter("issueddate")%></div>
                    <div>
                        <button onclick="ReissueFromEnrlNo()" >Reissue</button>
                    </div>
                    
                </div>
            </div>
        </div>

        

        
    
  
  
      
  


