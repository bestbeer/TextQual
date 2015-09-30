package pdfToText.vo;

public class Paper {
	String hashedName;
    String name;
    public String getHashedName() {
        return hashedName;
    }
    public void setHashedName(String hashedName) {
        this.hashedName = hashedName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
     
    public String toString(){
        return "Paper Name: "+name +" Hashed Name: "+hashedName;
    }
}
