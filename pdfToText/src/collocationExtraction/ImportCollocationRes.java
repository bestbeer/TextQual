package collocationExtraction;

import java.io.*;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

import DAO.dao.*;
import DAO.vo.*;



public class ImportCollocationRes {

	
	 
	public ImportCollocationRes() {
		// TODO Auto-generated constructor stub
		
	}
	public static int InsertToDb(String filesDir)
	{
		//TODO read the files and run a double loop -- all files ---> all collocations in each file
		JDBCDAO jdbcDAO = new JDBCDAO();
		jdbcDAO.getConnection();
		String s = "";
		File root = new File(filesDir);
		boolean recursive = true;
		Collection <File> files = FileUtils.listFiles(root, null, recursive);
		BufferedReader data = null;
		CollocationBiGram coll;
		double collCorpusLikelihood;
		String [] strArr;
		for (File f:files) //for each file in the list
		{
			try {
				data = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while ((s = data.readLine()) != null)
				{
					s = s.replace("[", "");
					s = s.replace("]", "");
					strArr = s.split(" ");
					coll = new CollocationBiGram();
					coll.setDoi(f.getName().substring(0, f.getName().length()-4));
					coll.setColl1(strArr[0].substring(0, strArr[0].length()-1)); 
					coll.setColl2(strArr[1].substring(0, strArr[1].length()-1)); 
//					coll.setColl1((strArr[0].substring(0, strArr[0].length()-1)).replaceAll("\\s+", "")); //also remove all whitespaces and non visible characters
//					coll.setColl2((strArr[1].substring(0, strArr[1].length()-1)).replaceAll("\\s+", "")); //also remove all whitespaces and non visible characters
					coll.setLikelihood(Double.parseDouble(strArr[2].substring(0, strArr[2].length())));
					collCorpusLikelihood = jdbcDAO.selectCollocCorpusLikelihood(coll);
					
					
					coll.setCorpusLikelihood(collCorpusLikelihood);
					
					jdbcDAO.insertCollocation(coll);
					
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(s);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(s);
			}
		}
		
		return 1;
	}
		
		
	
	public static void main(String[] args) {
		boolean dir = true; // indicate if we process directory or single file
		boolean filesReady = true; //indicate if cleared collocation files are ready
		String fileToRead = "D:\\Result\\result_wo_stop_28_f0_like.txt";
		String fileToWrite = "D:\\Result\\result_wo_stop_28_f0_like_clear.txt";
		String dirToread = "G:\\Papers\\Collocation Papers\\High Quality Text\\Coll_Text";
		String dirToWrite = "G:\\Papers\\Collocation Papers\\High Quality Text\\Coll_Text_Clear";
		
		try{
			if(dir==false) //if we just need to parse one file without entering collocations to DB
			{
			
				// TODO Auto-generated method stub
				File file = new File("D:\\Result\\result_wo_stop_28_f0_like.txt");
				File file2 = new File("D:\\Result\\result_wo_stop_28_f0_like_clear.txt");
				file2.createNewFile();
				BufferedWriter writer  = new BufferedWriter(new FileWriter(file2));
		
				BufferedReader data = new BufferedReader(new FileReader(file));
				String s;
				while ((s = data.readLine()) != null) {
				    s=s.replace(", ((", "\r\n");
				    s=s.replace(")", "");
				    s=s.replace("(", "");
				    s=s.replace("'", "");
				    writer.write(s);   
				}
				data.close();
				writer.close();
		
			}
			else //working with Directory of files
			{
				if(filesReady == false) //the collocation files are need to be processed
				{
					File root = new File(dirToread);
					boolean recursive = true;
					Collection <File> files = FileUtils.listFiles(root, null, recursive);
					String s;
					BufferedReader data;
					BufferedWriter writer;
					File fileWrite;
					
					for (File f:files) //for each file in the list
					{
						s = null;
						data = new BufferedReader(new FileReader(f));
						fileWrite = new File(dirToWrite + "\\" + f.getName());
						fileWrite.createNewFile();
						writer  = new BufferedWriter(new FileWriter(fileWrite));
						while ((s = data.readLine()) != null) {
						    s=s.replace(", ((", "\r\n");
						    s=s.replace(")", "");
						    s=s.replace("(", "");
						    s=s.replace("'", "");
						    writer.write(s);   
						}
						
						data.close();
						writer.close();
						
					}
					
						InsertToDb(dirToWrite);
				}
				else //files are ready just insert to DB
				{
						InsertToDb(dirToWrite);
				}
			}
				
				
				
				
			}
	
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		
				
		
	}
}

