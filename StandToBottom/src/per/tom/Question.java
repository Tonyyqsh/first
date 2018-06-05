package per.tom;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Question extends JFrame{
	private static final long serialVersionUID = 1L;
	private int questionNum;
	private int gamerNum;//ǰ����û�Ŷӣ�Ϊ�����޷�����ı�Ҫ��Ա
	Gamer gamers[];
	int score;
	FileManager fm = new FileManager();
	private int times;
	private int answered = 0;
	
	private JLabel presentGamer = new JLabel(answered+1+"");
	
	private JButton 
		right = new JButton("right"),
		wrong = new JButton("wrong"),
		next = new JButton("next"),
		dispose = new JButton("dispose");
	private JPanel oper = new JPanel();
	private JTextArea questionArea = new JTextArea();
	private JPanel questionPanel = new JPanel();
	private JTextArea timeArea = new JTextArea();
	private JPanel timePanel = new JPanel();
	private JTextArea answerArea = new JTextArea();
	private JPanel answerPanel = new JPanel();
	
	public Question(Config c,Game g) {//c.getNums��0��1��2�ֱ�Ϊ����
		setConfig(c);//
		setTitle("���ڴ���");//������Ա�����������
		setSize(500,500);
		setText();
		this.setLayout(new GridLayout(5,1));
		this.add(presentGamer);
		this.add(questionPanel);
		this.add(timePanel);
		this.add(answerPanel);
		this.add(oper);
		setButtons(this,g);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setConfig(Config c) {
		questionNum = c.getNum()[0];
		gamers = new Gamer[c.getNum()[1]];
		for(int i=0;i<gamers.length;i++) {
			gamers[i] = new Gamer(i,0);
		}
		score = c.getNum()[2];
		gamerNum = c.getNum()[1];
	}
	
	private void setButtons(Question q,Game g) {
		right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				right.setEnabled(false);
				wrong.setEnabled(false);
				gamers[answered].setScore(gamers[answered].getScore()+score);
				answerArea.setText(fm.getAnswer());
			}
		});
		wrong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				wrong.setEnabled(false);
				right.setEnabled(false);
				answerArea.setText(fm.getAnswer());
				times++;
				answered++;
			}
		});
		dispose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fm.getNext();
				answerArea.setText("");
				questionArea.setText(fm.getQuestion());
				right.setEnabled(true);
				wrong.setEnabled(true);
			}
		});
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				times++;
				answered++;
				answered = answered>=gamerNum?0:answered;
				if(times>=questionNum*gamerNum) {
					setVisible(false);
					g.startEnd(gamers);
					dispose();
				}
				fm.getNext();
				questionArea.setText(fm.getQuestion());
				answerArea.setText("");
				presentGamer.setText(""+(answered+1));
				right.setEnabled(true);
				wrong.setEnabled(true);
			}
		});
		oper.setLayout(new GridLayout(1,4));
		oper.add(dispose);
		oper.add(right);
		oper.add(wrong);
		oper.add(next);
	}
	
	private void setText() {
		questionArea.setText(fm.getQuestion());
		questionArea.setEditable(false);
		timeArea.setEditable(false);
		answerArea.setEditable(false);
		questionPanel.add(questionArea);
		answerPanel.add(answerArea);
		timePanel.add(timeArea);
	}
}
