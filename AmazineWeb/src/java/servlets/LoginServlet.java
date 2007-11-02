/*
 * LoginServlet.java
 *
 * Created on 26-oct-2007, 2:18:46
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import com.DBManager;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Mateo
 */
public class LoginServlet extends HttpServlet
{
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	RequestDispatcher disp;
	ServletContext app = this.getServletContext();	

	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	
	String destination;
	
	try
	{
	    DBManager dbmanager = new DBManager();
	    dbmanager.execute("Select");
	    destination = "index.jsp";
	}
	catch (Exception ex)
	{
	    request.getSession().setAttribute("Error", ex.getMessage());
	    destination = "error.jsp";
	    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	disp = app.getRequestDispatcher(destination);
	disp.forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     */
    public String getServletInfo()
    {
	return "Short description";
    }
    // </editor-fold>
}