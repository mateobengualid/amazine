/*
 * CarritoComprasServlet.java
 *
 * Created on 09-nov-2007, 9:30:47
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlets;

import com.business.DetalleDeTransaccion;
import com.business.Transaccion;
import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Mateo
 */
public class CarritoComprasServlet extends HttpServlet
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

	try
	{
	    Transaccion t = (Transaccion) request.getSession().getAttribute("carritoCompras");
	    int idSeleccionado = Integer.parseInt("0");
	    t.getDetalles().remove(new DetalleDeTransaccion(idSeleccionado, 0, 0, null));
	    disp = app.getForwardDispatcher("/CarritoCompras.jsp");
	    disp.forward(request, response);
	}
	finally
	{
	    out.close();
	}
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