package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AlumnosDAO {
   Connection conexion;
   public AlumnosDAO(){
        Conexion con = new Conexion(); 
        conexion = con.getConnection();
   }
   
   public List<Alumnos> listarAlumnos(){
           
       PreparedStatement ps;
       ResultSet rs;
       List<Alumnos> lista = new ArrayList<>();
       try{
           ps = conexion.prepareStatement("SELECT id, nombre,apellido,email FROM alumnos");
           rs = ps.executeQuery(); 
           while(rs.next()){
               int id = rs.getInt("id");
               String nombre = rs.getString("nombre");
               String apellido = rs.getString("apellido");
               String email = rs.getString("email");    
               Alumnos alumnos = new Alumnos(id, nombre, apellido, email);
               lista.add(alumnos);
           }
           return lista;
       }catch(SQLException e){
           System.out.println(e.toString());
           return null;
       }
       
   }
   
   public Alumnos mostrarAlumno(int _id){
       PreparedStatement ps;
       ResultSet rs;
       Alumnos alumno = null;
       
       try{
           ps = conexion.prepareStatement("SELECT id, nombre,apellido,email FROM alumnos WHERE id =?");
           ps.setInt(1, _id);
           rs = ps.executeQuery();
           
           while(rs.next()){
               int id = rs.getInt("id");
               String nombre = rs.getString("nombre");
               String apellido = rs.getString("apellido");
               String email = rs.getString("email");
               
               alumno = new Alumnos(id, nombre, apellido, email);
           }
           return alumno;
       }catch(SQLException e){
           System.out.println(e.toString());
           return null;
       }
   }
   
   public boolean insertarAlumnos(Alumnos alumno){
       PreparedStatement ps;
       try{
           ps = conexion.prepareStatement("INSERT INTO alumnos(nombre, apellido,email) VALUES (?,?,?)");
           ps.setString(1,alumno.getNombre());
           ps.setString(2,alumno.getApellido());
           ps.setString(3,alumno.getEmail());
           ps.execute();
           return true;
       }catch(SQLException e){
           System.out.println(e.toString());
           return false;
       }
   }
   
   public boolean actualizarAlumno(Alumnos alumno){
        PreparedStatement ps;
       try{
           ps = conexion.prepareStatement("UPDATE alumnos SET nombre=?, apellido=?,email=? WHERE id=?");
           ps.setString(1,alumno.getNombre());
           ps.setString(2,alumno.getApellido());
           ps.setString(3,alumno.getEmail());
           ps.setInt(4, alumno.getId());
           ps.execute();
           return true;
       }catch(SQLException e){
           System.out.println(e.toString());
           return false;
       }
   }
   
   public boolean eliminarAlumno(int _id){
        PreparedStatement ps;
       try{
           ps = conexion.prepareStatement("DELETE FROM alumnos WHERE id=?");
           ps.setInt(1, _id);
           ps.execute();
           
           return true;
       }catch(SQLException e){
           System.out.println(e.toString());
           return false;
       }
   }
}
