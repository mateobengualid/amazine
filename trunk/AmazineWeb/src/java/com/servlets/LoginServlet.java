/*
 * LoginServlet.java
 *
 * Created on 26-oct-2007, 2:18:46
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlets;

import com.business.DetalleDeTransaccion;
import com.dao.DAODVDs;
import com.dao.DBManager;
import com.business.PrivilegioAltoNivel;
import com.business.Transaccion;
import com.dao.DAOLibro;
import com.dao.DAOProducto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Mateo
 */
public class LoginServlet extends HttpServlet
{

    @Override
    public void init() throws ServletException
    {
	super.init();
	try
	{	    
	    ServletContext app = this.getServletContext();
	    DBManager sqlm = new DBManager();
	    DAOProducto daop = new DAOProducto(sqlm);
	    app.setAttribute("Productos", daop.getAll());
	    app.setAttribute("Libros", new DAOLibro(sqlm, daop).getAll());
	    app.setAttribute("DVDs", new DAODVDs(sqlm, daop).getAll());
	    app.setAttribute("CDs", new DAODVDs(sqlm, daop).getAll());
	}
	catch (Exception ex)
	{
	    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
        
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
	    
	    ResultSet rs = dbmanager.openPreparedQuery("select * " +
						       "from usuario " +
						       "where login = '" +
						       request.getParameter("user") +
						       "' and password = '" +
						       request.getParameter("password")+"'", new java.util.Hashtable());

	    // Si existe un registro al menos, es porque el login fue
	    // satisfactorio, de otro modo, no accede
	    if (!rs.first())
	    {
		throw new SecurityException("Acceso denegado, nombre de usuario " +
					    "y contraseña no validos");
	    }
	    else
	    {		
		// Aquí toda la lógica para incluir en la sesión		
		// los privilegios		
		ArrayList<PrivilegioAltoNivel> ht = new ArrayList<PrivilegioAltoNivel>();
		
		// Reemplazar esto con algo levantado por BD
                PrivilegioAltoNivel p = new PrivilegioAltoNivel();
		p.setNombre("Productos");
		p.setPagina("http://localhost:8080/AmazineWeb/ListaProductos.jsp");
		p.setPersona(null);
		p.setEsAccesible(true);
		p.setEsDeMenu(true);		
		ht.add(p);
		
		p = new PrivilegioAltoNivel();
		p.setNombre("Comprar");
		p.setPagina("http://localhost:8080/AmazineWeb/transacciones.jsp");
		p.setPersona(null);
		p.setEsAccesible(true);
		p.setEsDeMenu(true);		
                ht.add(p);

		p = new PrivilegioAltoNivel();
		p.setNombre("Index");
		p.setPagina("http://localhost:8080/AmazineWeb/index.jsp");
		p.setPersona(null);
		p.setEsAccesible(true);
		p.setEsDeMenu(false);		
                ht.add(p);
		
		request.getSession().setAttribute("privilegios", ht);
		
		request.getSession().setAttribute("carroCompras", new Transaccion(null, 0f, new Date(), new LinkedList<DetalleDeTransaccion>()));
		destination = "/index.jsp";
	    }	    
	}
	catch (Exception ex)
	{
	    ex.printStackTrace();
	    request.getSession().setAttribute("Error", ex.getMessage());
	    destination = "/error.jsp";
	    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
	}

	disp = app.getRequestDispatcher(destination);
	disp.forward(request, response);
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