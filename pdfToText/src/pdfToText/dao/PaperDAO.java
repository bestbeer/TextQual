package pdfToText.dao;

import java.util.List;
import pdfToText.vo.*;
public interface PaperDAO {
    
    public void insert(Paper paper);
    public List<Paper> select();
 
}