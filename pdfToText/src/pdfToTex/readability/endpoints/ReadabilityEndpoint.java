package pdfToTex.readability.endpoints;


import pdfToText.pdfToText;
import pdfToText.dao.JDBCPaperDAO;
import pdfToText.readability.engine.Readability;
import pdfToText.readability.enums.MetricType;
import pdfToText.vo.Paper;
import pdfToText.vo.PaperLingQual;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class ReadabilityEndpoint {

   
    public Map<MetricType, BigDecimal> get(String text) {
        Map<MetricType, BigDecimal> result = new HashMap<MetricType, BigDecimal>();
        Readability r = new Readability(text);

        for (MetricType metricType : MetricType.values()) {            
            BigDecimal value = new BigDecimal(Double.toString(r.getMetric(metricType)));
            value = value.setScale(3, BigDecimal.ROUND_HALF_UP);
            result.put(metricType, value);
        }

        return result;
    }
    
    public String readTxtFile(String filePath)
    {
    	return "";
    }
    
    public void writeToDbReadability (Map<MetricType, BigDecimal> readabilityMetrics, String doi)
    {
    	//will read all the txt files in the dir and write to the DB Table the readability results per DOI
    	PaperLingQual paperLingQual = new PaperLingQual();
    	paperLingQual.setDoi(doi);
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setCHARACTERS(readabilityMetrics.get(MetricType.CHARACTERS).intValue());
    	paperLingQual.setCOLEMAN_LIAU(readabilityMetrics.get(MetricType.COLEMAN_LIAU).doubleValue());
    	paperLingQual.setCOMPLEXWORDS(readabilityMetrics.get(MetricType.COMPLEXWORDS).intValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	paperLingQual.setARI(readabilityMetrics.get(MetricType.ARI).doubleValue());
    	
    	
    	JDBCPaperDAO jdbcPaperDAO = new JDBCPaperDAO();
        jdbcPaperDAO.getConnection();
        jdbcPaperDAO.insertLingQual(paperLingQual); //here the logic of which files to read will be in sql query //TODO add result good and fault result
         
        jdbcPaperDAO.closeConnection();
        
    	
    	
    }
    
    

}