/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import data.DataConnection;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class DataConnectionTest {

	@Test
	public void testConnection() {
		try{
			Connection con = DataConnection.getConnection();
			assertNotNull(con);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
