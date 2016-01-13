package pdfToText.readability.endpoints;


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
    	paperLingQual.setFLESCH_KINCAID(readabilityMetrics.get(MetricType.FLESCH_KINCAID).doubleValue());
    	paperLingQual.setFLESCH_READING(readabilityMetrics.get(MetricType.FLESCH_READING).doubleValue());
    	paperLingQual.setGUNNING_FOG(readabilityMetrics.get(MetricType.GUNNING_FOG).doubleValue());
    	paperLingQual.setSENTENCES(readabilityMetrics.get(MetricType.SENTENCES).intValue());
    	paperLingQual.setSMOG(readabilityMetrics.get(MetricType.SMOG).doubleValue());
    	paperLingQual.setSMOG_INDEX(readabilityMetrics.get(MetricType.SMOG_INDEX).doubleValue());
    	paperLingQual.setSYLLABLES(readabilityMetrics.get(MetricType.SYLLABLES).intValue());
    	paperLingQual.setWORDS(readabilityMetrics.get(MetricType.WORDS).intValue());
    	paperLingQual.setCOMMAS(readabilityMetrics.get(MetricType.COMMAS).intValue());
    	
    	JDBCPaperDAO jdbcPaperDAO = new JDBCPaperDAO();
        jdbcPaperDAO.getConnection();
        jdbcPaperDAO.insertLingQual(paperLingQual); //here the logic of which files to read will be in sql query //TODO add result good and fault result
         
        jdbcPaperDAO.closeConnection();
        
    	
    	
    }
    
    

}