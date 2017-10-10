package Admin;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HOME/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.DatabaseConnection;
import Model.EmailSender;
import Model.SmsSender;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author HOME
 */
public class IssueBookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private Connection conn;
    private PreparedStatement ps;

    @Override
    public void init() throws ServletException {
       DatabaseConnection dbc= new DatabaseConnection();
       conn = dbc.dbConn(conn);
         
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        try {
                
                response.setContentType("application/json");
                
                String bid = request.getParameter("bid");
                String bookname = request.getParameter("bookname");
                String authorname = request.getParameter("authorname");
                String issuedto = request.getParameter("enrl_no");
                java.sql.Date issueddate = new java.sql.Date(new java.util.Date().getTime());
                
                        if(bookname.isEmpty()||authorname.isEmpty())
                        {
                            
                        
                        }
                        else
                        {
//                         ps = conn.prepareStatement("select * from BookRecord where bookname=? and authorname=?");
//                         ps.setString(1, bookname);
//                         ps.setString(2, authorname);
//                         ResultSet rs = ps.executeQuery();
//                            if (rs.next()) {
//                                
//                                ps = conn.prepareStatement("update BookRecord set noc=noc+1 where bookname=? and authorname=?");
// 
//                                ps.setString(1, bookname);
//                                ps.setString(2, authorname);
//                                ps.executeUpdate();
//                                
//                                JSONObject obj = new JSONObject();
//                                obj.put("success", "updated");
//                                pw.print(obj);
//                            
//                            }
//                            else{
                                ps = conn.prepareStatement("update BookRecord set issuedto=? , issueddate=? where bid=? and bookname=? and authorname=?");
                                
                                ps.setString(1,issuedto);
                                ps.setDate(2,issueddate);
                                ps.setString(3,bid);
                                ps.setString(4,bookname);
                                ps.setString(5,authorname);
                     
                        
                                int x=ps.executeUpdate();
                                
                                    if(x!=0)
                                    {
                                                            
                                                          
                                                                ps = conn.prepareStatement("select * from StudentDetails where enrollmentno=?");
                                                                ps.setString(1, issuedto);
                                                                ResultSet rs = ps.executeQuery();
                                                                if (rs.next()) {
                                                                    String email = rs.getString("email");
                                                                    String Subject = "Book Issued";
                                                                    String Message = " Hello "+rs.getString("username")+", \n You have issued a book from library. \n Book Details are: \n Book Name: "+bookname+" \n Book Author: "+authorname+" \n Regards, \n Library Administrator";
                                                                    EmailSender e = new EmailSender();
                                                                    boolean result;
                                                                    result = e.SendMail(email, Subject, Message);
                                                                   
                                                                    if(result == true){
                                                                        
                                                                    String mobile = Long.toString(rs.getLong("mobile"));
                                                                    String SMS = "Book%20issued%0ABook%20Name%20%20"+bookname;
                                                                    SmsSender sms = new SmsSender();
                                                                    result = sms.SendSMS(mobile, SMS);
                                                                        System.out.println("sms: "+result);
                                                                    }
                                                                    }
                                                                
                                                                JSONObject obj = new JSONObject();  
                                                                
                                                                obj.put("success", "issued to "+issuedto+" on "+issueddate);

                                                                pw.print(obj);
                                                                
                                    }
                                    else
                                    {

                                    }
			
                        //}
			
                        }
		}
		catch(SQLException sq)
		{
			System.out.println("Error in Database"+sq);
                        JSONObject obj = new JSONObject();
                        obj.put("success", sq);
                        pw.print(obj);
                        
		}

        }

        @Override
        public void destroy()
	{
		try {
                conn.close();
            } catch (SQLException sq) {
                System.out.println("Error in closing Database" + sq);
            }
        } // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            processRequest(request, response);
            
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }
