package per.tom;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
		
	
	private JButton//按钮组
		start = new JButton("start"),
		exit = new JButton("exit"),
		clear = new JButton("clear");
	private JPanel buttonPanel = new JPanel();
	
	private JRadioButton //单选按钮组
		choice = new JRadioButton("选择题"),
		take = new JRadioButton("填空题"),
		probl = new JRadioButton("问答题");
	private ButtonGroup radioGroup = new ButtonGroup();
	private JPanel radioPanel = new JPanel();
	
	private JCheckBox//复选按钮组
		music = new JCheckBox("music"),
		soundEffect = new JCheckBox("soundEffect"),
		autoChange = new JCheckBox("autoChange");
	private JPanel checkPanel = new JPanel();
	
	private String[] num= {"1","2","3","4","5","6","7","8","9",
			"10","11","12","13","14","15","16","17","18","19",
			"20"};
	private JComboBox<String> 
		questionNum = new JComboBox<>(num),
		gamerNum = new JComboBox<>(num),
		sourceNum = new JComboBox<>(num);
	private JPanel numPanel = new JPanel();
	//制作ComboBox的标签
	private JLabel 
		questionNumLabel = new JLabel("选择题目数量"),
		gamerNumLabel = new JLabel("选择答题人数"),
		sourceNumLabel = new JLabel("选择每题分值");
	
	public StartInterface(Config c,Game g) {//构造方法
		setTitle("一站到底软件Java");
		setButtons(c,this,g);
		setRadioButtons(c);
		setCheckBox(c);
		setComboBox(c);
		setVisible(true);
		setSize(500,500);
		setLayout(new GridLayout(4,1));//暂时设置，后期加图片
		this.add(buttonPanel);
		this.add(radioPanel);
		this.add(checkPanel);
		this.add(numPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//设置主要按钮
	private void setButtons(Config c,StartInterface s,Game g) {
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.save();
				s.setVisible(false);
				g.startQuestion();
				dispose();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.clearConfig();
			}
		});
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(clear);
		buttonPanel.add(start);
		buttonPanel.add(exit);
	}
	
	private void setRadioButtons(Config c) {
		class RadioListener implements ActionListener{//用内部类的形式定义一个新的监听器
			@Override
			public void actionPerformed(ActionEvent e) {//直接写入配置
				if(choice.isSelected()) {
					c.setQuestion(1);
				}else if(take.isSelected()) {
					c.setQuestion(2);
				}else if(probl.isSelected()) {
					c.setQuestion(3);
				}
			}
		}
		//初始化按钮
		switch(c.getQuestionType()) {
		case 1:
			choice.setSelected(true);
			break;
		case 2:
			take.setSelected(true);
			break;
		case 3:
			probl.setSelected(true);
			break;
		}
		RadioListener rl = new RadioListener();
		choice.addActionListener(rl);//添加按钮以及监听器
		take.addActionListener(rl);
		probl.addActionListener(rl);
		radioGroup.add(choice);
		radioGroup.add(take);
		radioGroup.add(probl);
		radioPanel.setLayout(new GridLayout(1,3));
		radioPanel.add(choice);
		radioPanel.add(take);
		radioPanel.add(probl);
	}

	private void setCheckBox(Config c) {//设置复选按钮
		class CheckListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				c.setNeeds(music.isSelected(),soundEffect.isSelected(),
						autoChange.isSelected());
			}
		}
		//初始化复选按钮
		music.setSelected(c.getNeeds()[0]);
		soundEffect.setSelected(c.getNeeds()[1]);
		autoChange.setSelected(c.getNeeds()[2]);
		//添加监听器和器件
		CheckListener cl = new CheckListener();
		music.addActionListener(cl);
		soundEffect.addActionListener(cl);
		autoChange.addActionListener(cl);
		checkPanel.setLayout(new GridLayout(1,3));
		checkPanel.add(music);
		checkPanel.add(soundEffect);
		checkPanel.add(autoChange);
	}

	private void setComboBox(Config c) {
		class ComboBoxListener implements ActionListener{//构造公用监听器
		@Override
		public void actionPerformed(ActionEvent e) {
				int nums[] = new int[3];
				nums[0] = Integer.parseInt((String)(questionNum.getSelectedItem()));
				nums[1] = Integer.parseInt((String)(gamerNum.getSelectedItem()));
				nums[2] = Integer.parseInt((String)(sourceNum.getSelectedItem()));
				c.setNums(nums[0],nums[1],nums[2]);
			}
		}
		//初始化列表框
		questionNum.setSelectedItem(Integer.toString(c.getNum()[0]));
		gamerNum.setSelectedItem(Integer.toString(c.getNum()[1]));
		sourceNum.setSelectedItem(Integer.toString(c.getNum()[2]));
		//添加各种器件
		ComboBoxListener cbl = new ComboBoxListener();
		questionNum.setName("选择问题数");
		gamerNum.setName("选择答题人数");
		sourceNum.setName("选择每题分值");
		questionNum.addActionListener(cbl);
		gamerNum.addActionListener(cbl);
		sourceNum.addActionListener(cbl);
		numPanel.setLayout(new GridLayout(2,3));
		numPanel.add(questionNumLabel);
		numPanel.add(gamerNumLabel);
		numPanel.add(sourceNumLabel);
		numPanel.add(questionNum);
		numPanel.add(gamerNum);
		numPanel.add(sourceNum);
	}
}
