package Student;

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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HOME
 */
public class StudentLoginServlet extends HttpServlet {

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

        try {
                response.setContentType("text/html;charset=UTF-8");
                RequestDispatcher rd;
                String user = request.getParameter("username");
                String passw = request.getParameter("pwd");
                if (user.isEmpty() || passw.isEmpty()) {
                    rd = request.getRequestDispatcher("Error.jsp");
                    String topic = "Error";
                    String reason = "Please fill the fields....!<br><br> Please <a href='index.html' >Try Again</a> by clicking the link.</div>";
                    request.setAttribute("topic", topic);
                    request.setAttribute("reason", reason);
                    rd.include(request, response);
                } else {
                    ps = conn.prepareStatement("select * from StudentDetails where username=? and password=?");
                    ps.setString(1, user);
                    ps.setString(2, passw);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        rd = request.getRequestDispatcher("student/index.jsp");
                        request.setAttribute("user", user);
                        request.setAttribute("passw", passw);
                        rd.include(request, response);
                    } else {
                        rd = request.getRequestDispatcher("Error.jsp");
                        String topic = "Error";
                        String reason = "Invalid username/password.<br><br> Please <a href='index.html' >Try Again</a> by clicking the link.</div>";
                        request.setAttribute("topic", topic);
                        request.setAttribute("reason", reason);
                        rd.include(request, response);
                    }
                }
            
            } catch (SQLException sq) {
                System.out.println("Error in Database" + sq);
                throw new ServletException(sq.toString());
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
