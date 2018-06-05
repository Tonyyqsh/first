package per.tom;

public class Config {
	FileManager fm = new FileManager();
	/**需要读取和写入的数据:
	 * 题库的选择
	 * 是否背景音乐、音效、自动换题（待定）
	 * 用户数和题目数和分值
	 */
	private int whichQuestion = 1;
	private boolean music,
		soundEffect,
		autoChange;
	private int questionNum,
		gamerNum,
		score;
	public Config() {
		getConfig();
	}
	public boolean[] getNeeds() {
		return new boolean[]{this.music,this.soundEffect,this.autoChange};
	}
	public void save() {
		fm.wirte("#"+whichQuestion+"#"+music+"#"+soundEffect+"#"
				+autoChange+"#"+questionNum+"#"+gamerNum
				+"#"+score);
	}
	public void getConfig() {
		String configString = fm.readConfigFile();
		String config[] = configString.split("#");
		setConfig(config);
	}
	private void setConfig(String[] config) {
		try {
			this.whichQuestion = Integer.parseInt(config[1]);
			this.music = Boolean.parseBoolean(config[2]);
			this.soundEffect = Boolean.parseBoolean(config[3]);
			this.autoChange = Boolean.parseBoolean(config[4]);
			this.questionNum = Integer.parseInt(config[5]);
			this.gamerNum = Integer.parseInt(config[6]);
			this.score = Integer.parseInt(config[7]);
		}catch(Exception e) {
			for(int i=0;i<config.length;i++) {
				System.out.println(config[i]);
			}
		}
	}
	void clearConfig() {
		fm.deleteConfigFile();
	}

	public void setQuestion(int i) {
		whichQuestion = i;
	}

	public void setNeeds(boolean music, boolean soundEffect, boolean autoChange) {
		this.music = music;
		this.soundEffect = soundEffect;
		this.autoChange = autoChange;
	}

	public void setNums(int questionNum, int gamerNum, int score) {
		this.questionNum = questionNum;
		this.gamerNum = gamerNum;
		this.score = score;
	}
	public int getQuestionType() {
		return this.whichQuestion;
	}
	public int[] getNum() {
		return new int[]{questionNum,gamerNum,score};
	}

}
