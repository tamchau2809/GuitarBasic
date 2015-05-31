package chau.guitarbasic;

public class ChordItem {

	String iD;
	String ten;
	String hopam;
	public ChordItem(){
		
	}

	public ChordItem(String name, String ha) {
		super();
		this.ten = name;
		this.hopam = ha;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String te) {
		this.ten = te;
	}
	
	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getHA() {
		return hopam;
	}

	public void setHA(String h) {
		this.hopam = h;
	}
}
