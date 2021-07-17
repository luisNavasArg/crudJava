package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Alumnos;
import modelo.AlumnosDAO;


@WebServlet(name = "AlumnosController", urlPatterns = {"/AlumnosController"})
public class AlumnosController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AlumnosDAO alumnosDao = new AlumnosDAO();  
        String accion;
        RequestDispatcher dispactcher = null;
        accion = request.getParameter("accion");
        if(accion == null || accion.isEmpty()){
            dispactcher = request.getRequestDispatcher("Vistas/alumnos.jsp");
        }else if(accion.equals("modificar")){
            dispactcher = request.getRequestDispatcher("Vistas/modificar.jsp");
            
        }else if(accion.equals("actualizar")){
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            
            Alumnos alumno = new Alumnos(id,nombre,apellido,email);
            alumnosDao.actualizarAlumno(alumno);
            
            dispactcher = request.getRequestDispatcher("Vistas/alumnos.jsp");
        }else if(accion.equals("eliminar")){
        
            int id = Integer.parseInt(request.getParameter("id"));
            alumnosDao.eliminarAlumno(id);
            
            dispactcher = request.getRequestDispatcher("Vistas/alumnos.jsp");
        }else if(accion.equals("nuevo")){
            dispactcher = request.getRequestDispatcher("Vistas/nuevo.jsp");
        }else if(accion.equals("insert")){
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            
            Alumnos alumno = new Alumnos(0,nombre,apellido,email);
            alumnosDao.insertarAlumnos(alumno);
            
            dispactcher = request.getRequestDispatcher("Vistas/alumnos.jsp");
        }else{
            dispactcher = request.getRequestDispatcher("Vistas/alumnos.jsp");
        }
        dispactcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
