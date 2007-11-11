/*
 * AVMProductoServlet.java
 * 
 * Created on 10/11/2007, 17:45:04
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servlets;

import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.business.*;
import com.dao.*;
import java.text.DateFormat;
import java.util.*;

/**
 *
 * @author Fernando
 */
public class AVMProductoServlet extends HttpServlet {
    DBManager manager=new DBManager();
   DAOProducto daoProducto=new DAOProducto(manager);
   DAOCD daoCD;
   DAOPelicula daoPelicula;
   DAOLibro daoLibro;
   HttpSession session;
    /** 
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        LinkedList <TemaMusical>temas=new LinkedList<TemaMusical>();
        session=request.getSession();
        try {
            int id=manager.getMaxId("libreria.producto", "idproducto")+1;
            String f;
            int numClase;
            Producto producto=new Producto();
            producto.setId(id);
            producto.setNombre((String)session.getAttribute("nombre"));            
            producto.setDescripcion((String)session.getAttribute("descripcion"));
            producto.setPrecio((Float)session.getAttribute("precio"));
            Categoria cat=new Categoria((String)session.getAttribute("categoria"));
            producto.setCategoria(cat);            
            f=session.getAttribute("dia").toString()+"/"+session.getAttribute("mes").toString()+"/"+
               session.getAttribute("anio").toString();
            Date fs=DateFormat.getDateInstance(DateFormat.SHORT).parse(f);
            producto.setFechaSalida(fs);            
            
            String clase=(String)request.getSession().getAttribute("TiposProducto");
                          if(clase.equals("Libro"))
                             numClase=1;
                          else if(clase.equals("CD"))
                             numClase=2;
                             else numClase=3;
            switch(numClase)
            {                
            case 1: Libro l=new Libro(producto);
                    l.setIsbn((String)session.getAttribute("isbn"));
                    l.setAutor((String)session.getAttribute("autor"));
                    l.setEditorial((String)session.getAttribute("editorial"));
                    l.setIdioma((String)session.getAttribute("idioma"));
                    l.setPaginas((Integer)session.getAttribute("paginas"));
                    daoLibro=new DAOLibro(manager, daoProducto);
                    daoLibro.insert(l);
                    break;
            case 2: CD cd =new CD(producto);
                    cd.setDuracion((String)session.getAttribute("duracion"));
                    cd.setAutor((String)session.getAttribute("autor"));
                    cd.setSelloDiscografico((String)session.getAttribute("sello"));                    
                    cargarTemas(temas);
                    cd.setTemas(temas);
                    daoCD=new DAOCD(manager, daoProducto);
                    daoCD.insert(cd);
                    break;        
             case 3: Pelicula peli =new Pelicula(producto);
                    peli.setDuracion((String)session.getAttribute("duracion"));
                    peli.setFormato((String)session.getAttribute("formato"));
                    daoPelicula=new DAOPelicula(manager, daoProducto);
                    daoPelicula.insert(peli);
                    break;       
            }        
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Carga exitosa</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Carga Exitosa</h2>"); 
            out.println("</body>");
            out.println("</html>");
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally { 
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
    * Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }

    private void cargarTemas(Collection <TemaMusical>temas) {
        StringTokenizer tokenizer=new StringTokenizer((String)session.getAttribute("temas"), "-");
        TemaMusical tm;
        while(tokenizer.hasMoreTokens())
        {
            tm=new TemaMusical();
            tm.setId(Integer.parseInt(tokenizer.nextToken()));
            tm.setNombre(tokenizer.nextToken());
            tm.setAutor(tokenizer.nextToken());
            tm.setDuracion(tokenizer.nextToken());
            temas.add(tm);
        }
    }
   
}
