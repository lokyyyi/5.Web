package com.el.score;

public class Score {
	private String name;
	private int kor;
	private int eng;
	private int sum;
	
	public Score() {
		
	}

	public Score(String name, int kor, int eng, int sum) {
		super();
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.sum = sum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}
	

}
