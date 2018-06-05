package per.tom;

public class Game {
	private Config c = new Config();
	public static void main(String[] args) {
		new Game();
	}
	public Game() {
		new StartInterface(c,this);
	}
	void startStart() {
		new StartInterface(c, this);
	}
	void startQuestion() {
		new Question(c, this);
	}
	void startEnd(Gamer[] g) {
		new EndInterface(g,this);
	}
}
