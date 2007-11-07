/*
 * Libro.java
 *
 * Created on 4 de noviembre de 2007, 15:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.business;

import java.text.DateFormat;
import java.util.*;
/**
 *
 * @author Fernando
 */
public class Libro extends Producto{
    private int paginas;
    private String isbn;
    private String autor;
    private String editorial;
    private String idioma;
     
    /** Creates a new instance of Libro */
  
    public Libro(Producto p,int pag,String isbn,String autor,
            String editorial, String i) {
        
        super(p);
        paginas=pag;
        this.isbn=isbn;
        this.autor=autor;
        this.editorial=editorial;
        setIdioma(i);
    }
  

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
    
    public String toString()
    {
        String aux=super.toString();
        aux+="\nISBN: "+isbn;
        aux+="\nAutor: "+autor;
        aux+="\nEditorial: "+editorial;
        aux+="\nIdioma: "+idioma;
        aux+="\nPaginas: "+paginas;
        return aux;
    }
}
