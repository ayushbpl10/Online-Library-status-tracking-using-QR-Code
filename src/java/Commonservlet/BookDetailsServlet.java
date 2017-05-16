package Commonservlet;











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

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author HOME
 */
public class BookDetailsServlet extends HttpServlet {

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
                
                String bookname = request.getParameter("bookname");
                String authorname = request.getParameter("authorname");
                String bid = request.getParameter("bid");
                        if(bookname.isEmpty()||authorname.isEmpty()||bid.isEmpty())
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
                                ps = conn.prepareStatement("select * from BookRecord where  bid=? and bookname=? and authorname=? ");
                                ps.setString(1,bid);
                                ps.setString(2,bookname);
                                ps.setString(3,authorname);
                     
                        
                                ResultSet rs = ps.executeQuery();
                                if (rs.next()) {
                                                                String found_bid = String.valueOf(rs.getInt(1));
                                                                String found_bookname = rs.getString(2);
                                                                String found_authorname = rs.getString(3);
                                                                String found_issuedto = rs.getString(4);
                                                                java.sql.Date issueddate = rs.getDate(5);
                                                                String found_stringIssuedDate;
                                                                if(issueddate!= null){
                                                                    String DATE_FORMAT_NOW = "yyyy-MM-dd";
                                                                    java.util.Date found_issueddate = new java.util.Date(issueddate.getTime());
                                                                    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
                                                                    found_stringIssuedDate = sdf.format(found_issueddate);
                                                                }
                                                                else{
                                                                    found_stringIssuedDate = null;
                                                                }
                                                                
                                                                
                                                                
                                                                JSONObject obj = new JSONObject();  
                                                               
                                                                    obj.put("success", "details found");
                                                                    obj.put("bid", found_bid);
                                                                    obj.put("bookname", found_bookname);
                                                                    obj.put("authorname", found_authorname);
                                                                    obj.put("issuedto", found_issuedto);
                                                                    obj.put("issueddate", found_stringIssuedDate);
                                                                    
                                                                
                                                                    pw.print(obj);
                                                                
                                                            
                                                }
                                                else
                                                {
                                                    JSONObject obj = new JSONObject();  
                                                               
                                                                    obj.put("success", "details not found");
                                                                    
                                                                    pw.print(obj);
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

