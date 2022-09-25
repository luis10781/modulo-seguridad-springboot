package pe.isil.moduloseguridad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;


@SpringBootApplication
public class ModuloseguridadApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ModuloseguridadApplication.class, args);

		//Cargar Driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		//Crear conexion
		Connection conexion = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/moduloseg2", "root", "Luis107878781");


		//1.Llamamos primero a toda la tabla candidatos para verificar los datos
		//LeerCandidatos(conexion);
		//2.Insertamos un nuevo dato
		//insertCandidatos(conexion, "Alan", "Garcia", "DNI6323678", "123456", "APRA",1);
		//3.Actualizamos el dato cambiando el nombre Alan por PRUEBA
		//updateCandidatos(conexion);
		//4.Eliminamos la fila por apellido
		//eliminarCandidato(conexion,"Garcia");
		//Comprobamos los cambios
		// LeerCandidatos(conexion);
		//Cerrar conexion
		conexion.close();

	}

	public static void insertCandidatos(Connection connection,String name,String lastname,String username, String pass, String partido, Integer enable) throws Exception{

		CallableStatement cs = connection.prepareCall("{call insertCandidatos(?,?,?,?,?,?)}");
		cs.setString(1,name);
		cs.setString(2,lastname);
		cs.setString(3,username);
		cs.setString(4,pass);
		cs.setString(5,partido);
		cs.setInt(6,enable);

		int filasAfectadas = cs.executeUpdate();
		System.out.println("Filas afectadas: " + filasAfectadas);
		LeerCandidatos(connection);

	}
	public static void updateCandidatos(Connection connection) throws Exception {
		//Crear statement
		Statement statement = connection.createStatement();

		//Ejecutar sentencia
		int affectedRows = statement.executeUpdate("UPDATE candidatosalcaldia SET name='PRUEBA' WHERE lastname='Garcia'");

		ResultSet resultSet = statement.executeQuery("SELECT * FROM candidatosalcaldia");
		while (resultSet.next()) {
			System.out.println(resultSet.getString("name") + " " +
								resultSet.getString("lastname") + " " +
								resultSet.getString("username") + " " +
								resultSet.getString("pass") + " " +
								resultSet.getString("partido") + " " +
								resultSet.getString("enable") );
		}
		System.out.println("Filas afectadas: " + affectedRows);
		LeerCandidatos(connection);
	}
	public static void eliminarCandidato(Connection connection, String lastname) throws Exception{

		PreparedStatement pt = connection.prepareStatement("Delete FROM candidatosalcaldia WHERE lastname= ? ");
		pt.setString(1,lastname);
		LeerCandidatos(connection);
		int filasafectadas= pt.executeUpdate();
		System.out.println("Filas afectadas: " + filasafectadas);
	}
	public static void LeerCandidatos(Connection connection) throws Exception{

		CallableStatement cs = connection.prepareCall("{call selectAllCandidates()}");
		ResultSet rs = cs.executeQuery();

		while(rs.next()){
			System.out.println(rs.getString("name") + " " +
					rs.getString("lastname") + " " +
					rs.getString("username") + " " +
					rs.getString("pass") + " " +
					rs.getString("enable")
			);
		}
	}
}


