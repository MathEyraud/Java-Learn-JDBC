package com.mycompany.tennis.core.entity;

public class Score {
	
	/* 
	 * ATTRIBUT
	 */
	
	private Long id;
	private Match match;
	private Byte set1;
	private Byte set2;
	private Byte set3;
	private Byte set4;
	private Byte set5;
	
	/** 
	 * CONSTRUCTEUR
	 */
	public Score() {};
	public Score(Byte set1) {
		this.set1=set1;
		this.set2=null;
		this.set3=null;
		this.set4=null;
		this.set5=null;
	};
	public Score(Byte set1, Byte set2) {
		this.set1=set1;
		this.set2=set2;
		this.set3=null;
		this.set4=null;
		this.set5=null;
	}
	public Score(Byte set1, Byte set2, Byte set3) {
		this.set1=set1;
		this.set2=set2;
		this.set3=set3;
		this.set4=null;
		this.set5=null;
	}
	public Score(Byte set1, Byte set2, Byte set3, Byte set4) {
		this.set1=set1;
		this.set2=set2;
		this.set3=set3;
		this.set4=set4;
		this.set5=null;
	}
	public Score(Byte set1, Byte set2, Byte set3, Byte set4, Byte set5) {
		this.set1=set1;
		this.set2=set2;
		this.set3=set3;
		this.set4=set4;
		this.set5=set5;
	}
	
	/** 
	 * METHODES
	 */
	@Override
    public String toString() {
        return "Score{" +
                "set1=" + this.set1 + " / " +
                "set2=" + this.set2	+ " / " +
                "set3=" + this.set3 + " / " +
                "set4=" + this.set4 + " / " +
                "set5=" + this.set5 +
                '}';
    }
	
	/** 
	 * GETTERS/SETTERS
	 * @return
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getSet1() {
		return set1;
	}

	public void setSet1(Byte set1) {
		this.set1 = set1;
	}

	public Byte getSet2() {
		return set2;
	}

	public void setSet2(Byte set2) {
		this.set2 = set2;
	}

	public Byte getSet3() {
		return set3;
	}

	public void setSet3(Byte set3) {
		this.set3 = set3;
	}

	public Byte getSet4() {
		return set4;
	}

	public void setSet4(Byte set4) {
		this.set4 = set4;
	}

	public Byte getSet5() {
		return set5;
	}

	public void setSet5(Byte set5) {
		this.set5 = set5;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	
		
	
}
