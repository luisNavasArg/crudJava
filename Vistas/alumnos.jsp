<%-- 
    Document   : alumnos.jsp
    Created on : 15 jul. 2021, 20:19:23
    Author     : Usuario
--%>




<%@page import="config.Conexion"%>
<%@page import="java.sql.*"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="modelo.AlumnosDAO"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Alumnos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumnos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    </head>
    <body>
        <div class="container">
            <div class="row">
                <a class="btn btn-primary col-4 m-4" href="AlumnosController?accion=nuevo">Agregar Alumno</a>
                <table class="table table-primary">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Email</th>
                            <th>Modificar</th>
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                <tbody>
                <%
     
                    List<Alumnos> resultado=null;
                    AlumnosDAO alumno = new AlumnosDAO();
                    resultado = alumno.listarAlumnos();
                       
                        for(int i=0; i < resultado.size(); i++){
                            String ruta ="AlumnosController?accion=modificar&id=" + resultado.get(i).getId();
                            String rutaE ="AlumnosController?accion=eliminar&id=" + resultado.get(i).getId();
                 %>
                        <tr>
                            <td><%= resultado.get(i).getId()%></td>
                            <td><%=resultado.get(i).getNombre()%></td>
                            <td><%=resultado.get(i).getApellido()%></td>
                            <td><%=resultado.get(i).getEmail()%></td>
                            <td><a class="text-success" href=<%=ruta%>>X</a></td>
                            <td><a class="text-danger" href=<%=rutaE%>>X</a></td>
                        </tr>
                    </tbody>
                    <%
                        }
                    %>
                </table>
            </div>           
        </div>
    </body>
</html>