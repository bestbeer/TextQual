package DAO.dao;

import java.util.List;

import DAO.vo.*;

public interface DAO {
    
    public void insert(Paper paper);
    public List<Paper> select(String query);
    public void insertLingQual(PaperLingQual paperLingQual);
    public List<PaperLingQual> selectLingQual();
    public void insertCollocation(CollocationBiGram collBiGram, String tblName);
    public double selectCollocCorpusLikelihood(CollocationBiGram collBiGram); 
    
} 