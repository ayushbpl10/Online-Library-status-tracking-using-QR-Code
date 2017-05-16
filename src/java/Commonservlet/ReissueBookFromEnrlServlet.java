/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commonservlet;

import Model.DatabaseConnection;
import Model.QRCodeGenerator;
import static Model.QRCodeGenerator.createQRImage;
import com.google.zxing.WriterException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author HOME
 */
public class ReissueBookFromEnrlServlet extends HttpServlet {

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
                String issueddate = request.getParameter("issueddate");
                
                
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date date_initial =  sdf.parse(issueddate);
                    
                    Calendar c = Calendar.getInstance();
                    c.setTime(date_initial); // Now use date.
                    c.add(Calendar.DATE, 15); // Adding 15 days
                    java.sql.Date reissued_date = new java.sql.Date(c.getTimeInMillis());
                    
                    

                
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
                                ps = conn.prepareStatement("update BookRecord set issueddate=? where bid=? and bookname=? and authorname=?");
                                
                                ps.setDate(1,reissued_date);
                                ps.setString(2,bid);
                                ps.setString(3,bookname);
                                ps.setString(4,authorname);
                     
                        
                                int x=ps.executeUpdate();
                                
                                    if(x!=0)
                                    {
                                                            
                                                          
                                                                java.util.Date found_reissued_date = new java.util.Date(reissued_date.getTime());
                                                      
                                                                String found_stringReissuedDate = sdf.format(found_reissued_date);
                                                                
                                                                JSONObject obj = new JSONObject();  
                                                                
                                                                obj.put("success", "Reissued by "+issuedto);
                                                                obj.put("reissueddate", "Reissued till "+found_stringReissuedDate);
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
                catch(ParseException e){
                    JSONObject obj = new JSONObject();                                
                    obj.put("success", "date parse error : "+e.toString());
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
