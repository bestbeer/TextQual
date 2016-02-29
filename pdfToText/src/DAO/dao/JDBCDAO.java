package DAO.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import DAO.vo.*;

public class JDBCDAO implements DAO{
	 Connection connection = null;
	 
	    public Connection getConnection(){
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            if(connection == null)
	                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/papers?user=root&password=admin");
	 //TODO check the connection problem
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
	    public void insertLingQual(PaperLingQual paperLingQual) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO papers.lexical_quality (doi ,smog, flesch_reading, flesch_kincaid, ari, gunning_fog, coleman_liau, smog_index, characters, syllables, words, complexwords, sentences, commas) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)");
	            preparedStatement.setString(1,  paperLingQual.getDoi());
	            preparedStatement.setDouble(2,  paperLingQual.getSMOG());
	            preparedStatement.setDouble(3,  paperLingQual.getFLESCH_READING());
	            preparedStatement.setDouble(4,  paperLingQual.getFLESCH_KINCAID());
	            preparedStatement.setDouble(5,  paperLingQual.getARI());
	            preparedStatement.setDouble(6,  paperLingQual.getGUNNING_FOG());
	            preparedStatement.setDouble(7,  paperLingQual.getCOLEMAN_LIAU());
	            preparedStatement.setDouble(8,  paperLingQual.getSMOG_INDEX());
	            preparedStatement.setInt(9,  paperLingQual.getCHARACTERS());
	            preparedStatement.setInt(10,  paperLingQual.getSYLLABLES());
	            preparedStatement.setInt(11,  paperLingQual.getWORDS());
	            preparedStatement.setInt(12,  paperLingQual.getCOMPLEXWORDS());
	            preparedStatement.setInt(13,  paperLingQual.getSENTENCES());
	            preparedStatement.setInt(14,  paperLingQual.getCOMMAS());
	            
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	         
	    }
	    
	    @Override
	    public List<Paper> select() {
	        List<Paper> papers = new LinkedList<Paper>();
	         try {
	                Statement statement = connection.createStatement();
	              //TODO add the stored procedure exec statement that will bring the list of papers
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM papers.down_journals_md5;"); //here we will put relevant view to select from in order to select list of downloaded papers for our specific directory - not all files were downloaded so we not processed not downloaded files 
	                 
	                Paper paper = null;
	                while(resultSet.next()){
	                    paper = new Paper();
	                    paper.setName(resultSet.getString("doi"));
	                    paper.setHashedName(resultSet.getString("md5"));
	                     
	                    papers.add(paper);
	                }
	                resultSet.close();
	                statement.close();
	                 
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            System.out.println(papers);
	            return papers;
	    }
	     
	    @Override
	    public List<PaperLingQual> selectLingQual() {
	        List<PaperLingQual> papersLingQList = new LinkedList<PaperLingQual>();
	         try {
	                Statement statement = connection.createStatement();
	              //TODO add the stored procedure exec statement that will bring the list of papers
	                ResultSet resultSet = statement.executeQuery("SELECT * FROM papers.down_acm_md5;"); //TODO change the select to select LingQual
	                 
	                PaperLingQual paperLingQual = null;
	                while(resultSet.next()){
	                	paperLingQual = new PaperLingQual();
	                	paperLingQual.setDoi(resultSet.getString("doi"));
	                	paperLingQual.setSMOG(resultSet.getDouble("SMOG"));
	                     
	                    papersLingQList.add(paperLingQual);
	                }
	                resultSet.close();
	                statement.close();
	                 
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            
	            return papersLingQList;
	    }
	    
	    
	    @Override
	    public void insertCollocation(CollocationBiGram collBiGram) {
	        try {
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO papers.collocation_eeei_801_f0_wo_stop_like (doi ,coll1, coll2, likelihood) VALUES (? , ? , ? , ? )");
	            preparedStatement.setString(1,  collBiGram.getDoi());
	            preparedStatement.setString(2,  collBiGram.getColl1());
	            preparedStatement.setString(3,  collBiGram.getColl2());
	            preparedStatement.setDouble(4,  collBiGram.getLikelihood());
	            
	            
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	         
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
