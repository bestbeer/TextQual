package pdfToText.vo;

public class PaperLingQual {
	
	String doi;
	double SMOG;
	double FLESCH_READING;
	double FLESCH_KINCAID;
	double ARI;
	double GUNNING_FOG;
	double COLEMAN_LIAU;
	double SMOG_INDEX;
	int CHARACTERS;
	int SYLLABLES;
	int WORDS;
	int COMPLEXWORDS;
	int SENTENCES;
	
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public double getSMOG() {
		return SMOG;
	}
	public void setSMOG(double sMOG) {
		SMOG = sMOG;
	}
	public double getFLESCH_READING() {
		return FLESCH_READING;
	}
	public void setFLESCH_READING(double fLESCH_READING) {
		FLESCH_READING = fLESCH_READING;
	}
	public double getFLESCH_KINCAID() {
		return FLESCH_KINCAID;
	}
	public void setFLESCH_KINCAID(double fLESCH_KINCAID) {
		FLESCH_KINCAID = fLESCH_KINCAID;
	}
	public double getARI() {
		return ARI;
	}
	public void setARI(double aRI) {
		ARI = aRI;
	}
	public double getGUNNING_FOG() {
		return GUNNING_FOG;
	}
	public void setGUNNING_FOG(double gUNNING_FOG) {
		GUNNING_FOG = gUNNING_FOG;
	}
	public double getCOLEMAN_LIAU() {
		return COLEMAN_LIAU;
	}
	public void setCOLEMAN_LIAU(double cOLEMAN_LIAU) {
		COLEMAN_LIAU = cOLEMAN_LIAU;
	}
	public double getSMOG_INDEX() {
		return SMOG_INDEX;
	}
	public void setSMOG_INDEX(double sMOG_INDEX) {
		SMOG_INDEX = sMOG_INDEX;
	}
	public int getCHARACTERS() {
		return CHARACTERS;
	}
	public void setCHARACTERS(int cHARACTERS) {
		CHARACTERS = cHARACTERS;
	}
	public int getSYLLABLES() {
		return SYLLABLES;
	}
	public void setSYLLABLES(int sYLLABLES) {
		SYLLABLES = sYLLABLES;
	}
	public int getWORDS() {
		return WORDS;
	}
	public void setWORDS(int wORDS) {
		WORDS = wORDS;
	}
	public int getCOMPLEXWORDS() {
		return COMPLEXWORDS;
	}
	public void setCOMPLEXWORDS(int cOMPLEXWORDS) {
		COMPLEXWORDS = cOMPLEXWORDS;
	}
	public int getSENTENCES() {
		return SENTENCES;
	}
	public void setSENTENCES(int sENTENCES) {
		SENTENCES = sENTENCES;
	}
	
	

}
