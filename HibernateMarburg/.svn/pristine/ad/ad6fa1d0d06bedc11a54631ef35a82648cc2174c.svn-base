package dbs1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Properties;

public class JDBCBeispiel {
	// properties e.g.
	// driverName = org.postgresql.Driver
	// url =  jdbc:postgresql://localhost:5432/dbs1db
	// user = scott
	// password = tiger
	/**
	 * Usage java -classpath e.g. C:\temp\postgresql-8.4-701.jdbc3.jar; JDBCdbs1 C:\temp\props.props
	 * @param args
	 */
	public static void main(String[] args) {
//		if(args.length != 1 ){
//			throw new RuntimeException("Please provide path to properties!");
//		}
//		String propsPath = args[0];
		String propsPath = "connection.properties";
		/********************************************************************************************
		* Lade Properties
		********************************************************************************************/
		Properties props = new Properties();
		Connection connection = null;
		try {
			props.load(new FileInputStream(new File(propsPath)));
			// register driver
			Class.forName(props.getProperty("driverName"));
			/*********************************************************************************************
			 * Erstelle Verbindung  
			 ********************************************************************************************/
			String url = props.getProperty("url");
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			connection = DriverManager.getConnection(url, user, password);
			// execute sql statement and show results 
			System.out.println("Connected to Database: " + props.getProperty("url"));
			/*********************************************************************************************
			 * Einfache Anfrage
			 ********************************************************************************************/
			String adHocQuery = "SELECT * FROM Personal WHERE pnr NOT IN " +
			" (SELECT pnr FROM PMZuteilung WHERE note < 4) ";
			System.out.println("Execute query: " + adHocQuery);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(adHocQuery);
			ResultSetMetaData metadata =  rs.getMetaData();
			System.out.println("Print: ResultSetMetaData: ");
			StringBuffer bf = new StringBuffer();
			for (int i = 0 ; i < metadata.getColumnCount(); i++){
				bf.append(metadata.getColumnName(i+1) + " " );
				bf.append(metadata.getColumnTypeName(i+1) + " ");
			}
			System.out.println(bf);
			System.out.println("Query Result: ");
			while(rs.next()){
				int pnr = rs.getInt(1);
				String pname = rs.getString(2);
				String vorname = rs.getString(3);
				System.out.println("( " + pnr + ", " + pname +", " + vorname+ ")" );
			}
			rs.close();
			stmt.close();
			System.out.println("*********************************************************");
			/*********************************************************************************************
			 * Parametrisierte Anfrage 
			 ********************************************************************************************/
			// alternativ ""
			int pnrN = 42;
			String badQuery = "SELECT * FROM Personal WHERE pnr =" + pnrN;
			
			String paramQuery = "SELECT * FROM Personal WHERE pnr = ?  ";
			System.out.println("Prepare statement: " + paramQuery);
			PreparedStatement pstmt = connection.prepareStatement(paramQuery);
			pstmt.setInt(1, 82); // set parameter
//			pstmt.setString(2, "Schneider ");
			rs = pstmt.executeQuery();
			while(rs.next()){
				int pnr = rs.getInt(1);
				String pname = rs.getString(2);
				String vorname = rs.getString(3);
				System.out.println("( " + pnr + ", " + pname +", " + vorname+ ")" );
			}
			rs.close();
		
			System.out.println("*********************************************************");
			/*********************************************************************************************
			 * Update 
			 ********************************************************************************************/
			String update = "UPDATE personal SET lohn = ? WHERE pnr = ? ";
			PreparedStatement pstmtUpdate = connection.prepareStatement(update);
			System.out.println("Update " + paramQuery);
			pstmtUpdate.setString(1, "C4");
			pstmtUpdate.setInt(2, 51);
			// before update 
			System.out.println("Vor dem Update");
			pstmt.setInt(1, 51);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int pnr = rs.getInt(1);
				String pname = rs.getString(2);
				String lohn = rs.getString(5);
				System.out.println("( " + pnr + ", " + pname +", " + lohn+ ")" );
			}
			rs.close();
			System.out.println("Update mit Parametern: lohn = 'C4' und  pnr = 51 ");
			pstmtUpdate.executeUpdate();
			// after update
			System.out.println("Nach dem Update");
			pstmt.setInt(1, 51);
			rs = pstmt.executeQuery();
			while(rs.next()){
				int pnr = rs.getInt(1);
				String pname = rs.getString(2);
				String lohn = rs.getString(5);
				System.out.println("( " + pnr + ", " + pname +", " + lohn + ")" );
			}
			rs.close();
			pstmtUpdate.close();
			pstmt.close();
			System.out.println("*********************************************************");
			
			/*********************************************************************************************
			 * Transaktionen und batch
			 ********************************************************************************************/
			// für Transaktionen 
			connection.setAutoCommit(false);
			// Transaktionslevel 
			connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			// NOTE: Postgresql kennt nur 2 levels 
			// read commited und serialiazable 
			// read uncommitted -> dirty read, nonrepeatable read, phantom
			// read commited -> nonrepeatable read, phantom
			// repeatable read -> phantom
			// serializable -> 
			// begin transaction 
			// pnr, name, vorname, abtnr, lohn
			// füge noch ein Abteilung ein
			stmt = connection.createStatement();
			int abtNr = 42;
			String abtName = "Computer";
			stmt.execute("insert into abteilung values (" +abtNr+",'" + abtName +"')");
			PreparedStatement pStmt = connection.prepareStatement("INSERT INTO personal VALUES( ?, ? , ?, ? ,? )");
			// füge zwei mitarbeiter ein 
			pStmt.setInt(1, 256);
			pStmt.setString(2, "Müller");
			pStmt.setString(3, "Hans");
			pStmt.setInt(4, abtNr );
			pStmt.setString(5, "C4");
			
			pStmt.addBatch();
			
			pStmt.setInt(1, 512);
			pStmt.setString(2, "Schneider");
			pStmt.setString(3, "Peter");
			pStmt.setInt(4, abtNr );
			pStmt.setString(5, "C4");
			
			pStmt.addBatch();
			
			try{
				int[] result = pStmt.executeBatch();
				for(int i :result){
					if(i == PreparedStatement.EXECUTE_FAILED){
						connection.rollback();
						break;
					}
				}		
			}
			catch(BatchUpdateException ex){
				connection.rollback();
				throw new RuntimeException("batch exec. prblem ", ex);
			}
			
			rs.close();
			stmt.close();
			connection.commit();
			
			/*********************************************************************************************
			 *Callable statement
			 ********************************************************************************************/
			String function = "CREATE OR REPLACE FUNCTION mySum(n int) RETURNS int AS $$ " +
					"DECLARE  l int := 0; " +
					"BEGIN 	" +
					"	FOR i IN 1..n LOOP 		" +
					"       l := l+i; 	" +
					"   END LOOP; 	" +
					"   RETURN l;    " +
					"END;  	$$ LANGUAGE plpgsql; ";
			System.out.println(function);
			stmt = connection.createStatement();
			stmt.executeUpdate(function);
			stmt.close();
			CallableStatement callstmt = connection.prepareCall("{ ? = call mySum( ? ) }");
			callstmt.registerOutParameter(1, Types.INTEGER);
			callstmt.setInt(2, 100);
			callstmt.execute();
			int result = callstmt.getInt(1);
			System.out.println("Result:  " + result);
			callstmt.close();
		} catch (FileNotFoundException e1) {
			System.out.println("File not found ");
			e1.printStackTrace();
			System.exit(1);
		} catch (IOException e1) {
			System.out.println("Properties Read Exception");
			e1.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			System.out.println("File not found check CLASSPATH");
			e.printStackTrace();
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if (connection != null){
				try{
					connection.close();
				}catch(SQLException e){
				
				}
			}
		}
	
		
		
	}
}
