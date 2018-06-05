package per.tom;

public class Gamer {
	private int number;
	private int score = 0;
	
	public Gamer(int number,int score) {
		this.number = number;
		this.score = score;
	}
	
	public Gamer(int number) {
		this.number = number;
	}
	
	public int getScore() {
		return this.score;
	}
	void setScore(int score) {
		this.score = score;
	}
	public String toString() {
		return String.format("%03d",number+1)+"的分数为"+score;
	}
}
