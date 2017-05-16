/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Student;

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
import java.util.ArrayList;
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
public class SearchBookServlet extends HttpServlet {

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
              
                
                
                    
                    

                
                        if(bookname.isEmpty())
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
                                ps = conn.prepareStatement("select * from BookRecord where bookname like ? ");
                                
                                
                                ps.setString(1,bookname+"%");
                                
                                ResultSet rs=ps.executeQuery();
                                ArrayList<JSONObject> al=new ArrayList<JSONObject>();//creating new generic arraylist  
                                                                
                                    while(rs.next())
                                    {
                                                            
                                                          
                                                                JSONObject obj = new JSONObject();  
                                                                
                                                                obj.put("success", "true");
                                                                obj.put("bid", rs.getInt(1));
                                                                obj.put("bookname", rs.getString(2));
                                                                obj.put("authorname", rs.getString(3));
                                                                obj.put("issuedto", rs.getString(4));
                                                                obj.put("issueddate", rs.getDate(5));
                                                                al.add(obj);
                                                                
                                    }
                                    pw.print(al);
			
                        //}
			
                        }
		}
		catch(SQLException sq)
		{
			System.out.println("Error in Database"+sq);
                        ArrayList<JSONObject> al=new ArrayList<JSONObject>();//creating new generic arraylist  
                                
                        JSONObject obj = new JSONObject();
                        obj.put("success", sq);
                        al.add(obj);
                        pw.print(al);
                        
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
