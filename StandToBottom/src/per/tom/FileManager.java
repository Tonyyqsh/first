package per.tom;

import java.io.*;

public class FileManager {
	private int questionName = 1;
	private int questionType = 1;
	File configFile = new File("config.txt");
	public String getQuestion() {
		File question = new File("question/"+questionType+(questionName+".txt"));
		if(!question.exists()) {
			return "文件不存在";
		}
		try {
			FileInputStream questionStream = new FileInputStream(question);
			byte questionBytes[] = new byte[10240];
			int len = questionStream.read(questionBytes);
			questionStream.close();
			return new String(questionBytes,0,len);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "this is a question";
	}

	public String getAnswer() {
		File answer = new File("question/"+questionType+(questionName)+"a.txt");
		if(!answer.exists()) {
			return "文件不存在";
		}
		try {
			FileInputStream answerStream = new FileInputStream(answer);
			byte answerBytes[] = new byte[10240];
			int len = answerStream.read(answerBytes);
			answerStream.close();
			return new String(answerBytes,0,len);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "this is a answer";
	}
	public void getNext() {
		questionName++;
	}
	public void deleteConfigFile() {
		configFile.delete();
	}

	void wirte(String configString) {
		if(!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream config = new FileOutputStream(configFile);
			byte[] configByte = configString.getBytes();
			config.write(configByte);
			config.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String readConfigFile() {
		if(!configFile.exists()) {
			return "#"+1+"#"+false+"#"+false+"#"
					+false+"#"+5+"#"+5
					+"#"+1;
		}
		try {
			FileInputStream configStream = new FileInputStream(configFile);
			byte configByte[] = new byte[1024];
			int len = configStream.read(configByte);
			configStream.close();
			return new String(configByte,0,len);
		} catch (Exception e) {
			return "#"+1+"#"+false+"#"+false+"#"+false+"#"+5+"#"+5
					+"#"+1;
		}
	}

}
