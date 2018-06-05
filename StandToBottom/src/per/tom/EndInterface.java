package per.tom;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class EndInterface extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JButton back = new JButton("back");
	private JButton exit = new JButton("exit");
	private JPanel oper = new JPanel();
	
	private JTextArea result = new JTextArea();
	private JPanel paper = new JPanel();
	
	public EndInterface(Gamer[] g, Game game) {
		setTitle("分数如下");
		setButtons(game);
		setVisible(true);
		setSize(500,500);
		setLayout(new BorderLayout());
		add(oper, BorderLayout.SOUTH);
		add(paper, BorderLayout.CENTER);
		setText(g);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setText(Gamer[] g) {
		result.setEditable(false);
		Font f = result.getFont();
		result.setFont(new Font(f.getFontName(),f.getStyle(),40));
		paper.setSize(400,500);
		paper.add(result);
		for (int i = 0; i < g.length; i++) {
			result.append(g[i].toString()+"\n");
		}
	}
	private void setButtons(Game g) {
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				g.startStart();
				dispose();
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		oper.setLayout(new GridLayout(1,2));
		oper.add(back);
		oper.add(exit);
	}
}
