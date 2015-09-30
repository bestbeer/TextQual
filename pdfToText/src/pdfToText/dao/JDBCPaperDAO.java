package pdfToText.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import pdfToText.vo.*;

public class JDBCPaperDAO implements PaperDAO{
	 Connection connection = null;
	 
	    public Connection getConnection(){
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            if(connection == null)
	                connection = DriverManager.getConnection("jdbc:mysql://localhost/hmkcode?user=root&password=");
	 
	        } catch (ClassNotFoundException e) {
	 
	            e.printStackTrace();
	             
	        } catch (SQLException e) {
	             
	            e.printStackTrace();
	             
	        }
	        return connection;
	    }
	    @Override
	    public void insert(Paper paper) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO hmkcode.persons (id ,name) VALUES (NULL , ?)");
	            preparedStatement.setString(1,  paper.getName());
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	         
	    }
	 
	    @Override
	    public List<Paper> select() {
	        List<Paper> persons = new LinkedList<Paper>();
	         try {
	                Statement statement = connection.createStatement();
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM hmkcode.persons");
	                 
	                Paper paper = null;
	                while(resultSet.next()){
	                    paper = new Person();
	                    paper.setName(Integer.parseInt(resultSet.getString("name")));
	                    paper.setHashedName(resultSet.getString("hashedName"));
	                     
	                    persons.add(paper);
	                }
	                resultSet.close();
	                statement.close();
	                 
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            System.out.println(persons);
	            return persons;
	    }
	     
	     
	    public void closeConnection(){
	        try {
	              if (connection != null) {
	                  connection.close();
	              }
	            } catch (Exception e) { 
	                //do nothing
	            }
	    }
}
