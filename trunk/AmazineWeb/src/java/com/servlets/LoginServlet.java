package com.servlets;

import com.business.DetalleDeTransaccion;
import com.business.Privilegio;
import com.dao.*;
import com.business.PrivilegioAltoNivel;
import com.business.Producto;
import com.business.Transaccion;
import com.business.PrivilegioAltoNivel;
import com.business.Usuario;
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
	    app.setAttribute("DVDs", new DAOPelicula(sqlm, daop).getAll());
	    app.setAttribute("CDs", new DAOPelicula(sqlm, daop).getAll());
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
            DAOUsuario daoUsuario=new DAOUsuario(dbmanager,new DAOPersona(dbmanager));
	    
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
					    "y contrase�a no validos");
	    }
	    else
	    {		
		// Aqu� toda la l�gica para incluir en la sesi�n		
		// los privilegios		
                Usuario u=daoUsuario.get(String.valueOf(rs.getInt(1)));
		ArrayList<PrivilegioAltoNivel> ht = new ArrayList<PrivilegioAltoNivel>();
                PrivilegioAltoNivel privilegioAltoNivel;
		for(Privilegio priv: u.getPrivilegios())
                {
                    privilegioAltoNivel=new PrivilegioAltoNivel();
                    privilegioAltoNivel.setNombre(priv.getCodigoPrivilegio());
                    privilegioAltoNivel.setPagina(priv.getDescripcion());		    
                    //no esta en la base de datos
                    privilegioAltoNivel.setEsDeMenu(true);
                    privilegioAltoNivel.setEsAccesible(true);
                }

		//Esto est� mal!!!!!!!!!!!!!
		Transaccion carrito = new Transaccion(null, 0f, new Date(), new LinkedList<DetalleDeTransaccion>());
		carrito.getDetalles().add(new DetalleDeTransaccion(1,1,10.99f,((LinkedList<Producto>)app.getAttribute("Libros")).get(0)));
		carrito.getDetalles().add(new DetalleDeTransaccion(1,1,15.99f,((LinkedList<Producto>)app.getAttribute("Productos")).get(0)));
		
		request.getSession().setAttribute("privilegios", ht);
		
		request.getSession().setAttribute("carroCompras", carrito);
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