package dbs1.persistence.data;

public class KraftstoffMaschine extends AbstrakteMaschine{
	
	protected String kraftstroff;
	
	protected int verbrauch;
	
	public KraftstoffMaschine() {
		super();
	}

	public String getKraftstroff() {
		return kraftstroff;
	}

	public void setKraftstroff(String kraftstroff) {
		this.kraftstroff = kraftstroff;
	}

	public int getVerbrauch() {
		return verbrauch;
	}

	public void setVerbrauch(int verbrach) {
		this.verbrauch = verbrach;
	}
	
}
