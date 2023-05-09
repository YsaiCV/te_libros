package com.emergentes.controlador;

import com.emergentes.modelo.Libro;
import com.emergentes.utiles.ConexionBD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ysai
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        ArrayList<Libro> lista = new ArrayList<Libro>();
        ConexionBD conexion = new ConexionBD();
        Connection conn = conexion.getConexion();

        PreparedStatement ps;
        ResultSet rs;

        if (op.equals("list")) {
            String sql = "SELECT * FROM LIBROS";
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Libro lib = new Libro();

                    lib.setId(rs.getInt("id"));
                    lib.setIsbn(rs.getString("isbn"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setCategoria(rs.getString("categoria"));

                    lista.add(lib);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (op.equals("nuevo")) {
            Libro li = new Libro();
            request.setAttribute("lib", li);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String sql = "DELETE FROM LIBROS WHERE ID=?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        if (op.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String sql = "SELECT * FROM LIBROS WHERE ID=?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                
                Libro lib = new Libro();
                while (rs.next()) {
                    lib.setId(rs.getInt("id"));
                    lib.setIsbn(rs.getString("isbn"));
                    lib.setTitulo(rs.getString("titulo"));
                    lib.setCategoria(rs.getString("categoria"));
                }
                
                request.setAttribute("lib", lib);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");

        Libro lib = new Libro();

        lib.setId(id);
        lib.setIsbn(isbn);
        lib.setTitulo(titulo);
        lib.setCategoria(categoria);

        ConexionBD canal = new ConexionBD();

        Connection conn = canal.getConexion();

        PreparedStatement ps;

        if (id == 0) {
            String sql = "INSERT INTO LIBROS (ISBN,TITULO,CATEGORIA) VALUES (?,?,?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, lib.getIsbn());
                ps.setString(2, lib.getTitulo());
                ps.setString(3, lib.getCategoria());
                ps.executeUpdate();

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }else{
            String sql = "UPDATE LIBROS SET ISBN=?, TITULO=?, CATEGORIA=? WHERE ID=?";
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, lib.getIsbn());
                ps.setString(2, lib.getTitulo());
                ps.setString(3, lib.getCategoria());
                ps.setInt(4, lib.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        response.sendRedirect("MainController");
    }
}
