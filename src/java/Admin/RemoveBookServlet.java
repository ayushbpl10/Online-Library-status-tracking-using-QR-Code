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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
public class RemoveBookServlet extends HttpServlet {

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
                        if(bookname.isEmpty()||authorname.isEmpty())
                        {
                            
                        
                        }
                        else
                        {
//                         ps = conn.prepareStatement("select * from BookRecord where bookname=? and authorname=?");
//                         ps.setString(1, bookname);
//                         ps.setString(2, authorname);
//                         ResultSet rs = ps.executeQuery();
//                         
//                            while(rs.next()) {
//                                
//                                if(rs.getInt("noc")!=1){
//                                    ps = conn.prepareStatement("update BookRecord set noc=noc-1 where bookname=? and authorname=?");
// 
//                                    ps.setString(1, bookname);
//                                    ps.setString(2, authorname);
//                                    ps.executeUpdate();
//
//                                    JSONObject obj = new JSONObject();
//                                    obj.put("success", "copies reduced");
//                                    pw.print(obj);
//                                }
//                                
//                                else{
                                    ps = conn.prepareStatement("delete from BookRecord where bookname=? and authorname=?");
                                    ps.setString(1,bookname);
                                    ps.setString(2,authorname);

                            //ps.setString(5,date);
                                    int x=ps.executeUpdate();
                                        if(x!=0)
                                        {

                                                                JSONObject obj = new JSONObject();
                                                                obj.put("success", "record removed");
                                                                pw.print(obj);

                                        }
                                        else
                                        {
                                            JSONObject obj = new JSONObject();
                                            obj.put("success", "No record found");
                                            pw.print(obj);
                                        }

                                //}
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
