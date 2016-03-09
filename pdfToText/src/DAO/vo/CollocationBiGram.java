package DAO.vo;

public class CollocationBiGram {
	String doi;
	String coll1;
    String coll2;
    double likelihood;
    double corpusLikelihood;
    
    public double getCorpusLikelihood() {
		return corpusLikelihood;
	}

	public void setCorpusLikelihood(double corpusLikelihood) {
		this.corpusLikelihood = corpusLikelihood;
	}

	public String getDoi() {
        return doi;
    }
 
    public void setDoi(String doi) {
        this.doi = doi;
    }
    
    public String getColl1() {
        return coll1;
    }
    
    public void setColl1(String coll) {
        this.coll1 = coll;
    }
    
    public String getColl2() {
        return coll2;
    }
    
    public void setColl2(String coll) {
        this.coll2 = coll;
    }
    
    public double getLikelihood() {
        return likelihood;
    }
    
    public void setLikelihood(double like) {
        this.likelihood = like;
    }
    
    public String toString(){
        return "CollocationBiGram Name: "+ coll1 + coll2 +" Doi: "+doi + " Likelihood: " + likelihood;
    }
}
