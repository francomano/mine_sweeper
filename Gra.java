//  Author:         (c) 2021 Bonifacio Marco Francomano


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Gra {

	public static void main(String[] args) {
		JFrame g=new JFrame();
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.getContentPane().setLayout(new BorderLayout());
		
		JPanel nord =new JPanel(new FlowLayout());
		JLabel ip=new JLabel("Server Address");
		JTextField t=new JTextField("127.0.0.1",20);
		JLabel port=new JLabel("Port");
		JTextField s=new JTextField("4400");
		JButton connect=new JButton ("Connect");
		JButton disconnect=new JButton("Disconnect");
		disconnect.setEnabled(false);
		
		nord.add(ip);
		nord.add(t);
		nord.add(port);
		nord.add(s);
		nord.add(connect);
		nord.add(disconnect);
		g.getContentPane().add(nord,BorderLayout.NORTH);
		
		BoardButton[][]matrix=new BoardButton[Board.SIZE1][Board.SIZE2];
		for(int i=0;i<Board.SIZE1;i++) {
	 		   for(int j=0;j<Board.SIZE2;j++) {
	 			  matrix[i][j]=new BoardButton();
	 			  matrix[i][j].setEnabled(false);
	 		   }
	 		}
		
		Board center=new Board(matrix);
		center.setLayout(new GridLayout(Board.SIZE1,Board.SIZE2));
		for(int i=0;i<Board.SIZE1;i++) {
	 		   for(int j=0;j<Board.SIZE2;j++) {
	 			   center.add(matrix[i][j]);
	 			   matrix[i][j].addActionListener(center);
	 		   }
	 	 }
		g.getContentPane().add(center,BorderLayout.CENTER);
		
		
		JPanel south=new JPanel(new FlowLayout());
		JButton start=new JButton("Start");
		JButton stop=new JButton("Stop");
		start.setEnabled(false);
		stop.setEnabled(false);
		JButton rivela=new JButton("Rivela");
		rivela.setEnabled(false);
		south.add(start);
		south.add(stop);
		south.add(rivela);
		g.getContentPane().add(south,BorderLayout.SOUTH);
		
		Ascoltatore l=new Ascoltatore(center,t,s,connect,disconnect,start,
				stop,rivela);
		
		connect.addActionListener(l);
		connect.setActionCommand("connect");
		disconnect.addActionListener(l);
		disconnect.setActionCommand("disconnect");
		start.addActionListener(l);
		start.setActionCommand("start");
		stop.addActionListener(l);
		stop.setActionCommand("stop");
		rivela.addActionListener(l);
		rivela.setActionCommand("rivela");

		g.setVisible(true);
		//g.pack();
		g.setSize(800,600);
		//g.setPreferredSize(new Dimension(800,600));
		g.setTitle("Marco Francomano 1883955");
		
		
	}

}
