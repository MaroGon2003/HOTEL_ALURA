package factory;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource datasource;
	
	
	public ConnectionFactory() {

		var combopooledDataSource = new ComboPooledDataSource();
		
		combopooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimezone=true&serverTimezone=UTC");
		combopooledDataSource.setUser("root");
		combopooledDataSource.setPassword("Pandequeso1234");
		combopooledDataSource.setMaxPoolSize(10);
		
		this.datasource = combopooledDataSource;
		
	}
	
	public Connection recuperarConexion(){
		try {
			return this.datasource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

