//  Author:         (c) 2021 Bonifacio Marco Francomano


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Ascoltatore implements ActionListener{
	private Board center;
	private JTextField t;
	private JTextField s;
	private JButton connect;
	private JButton disconnect;
	private JButton start;
	private JButton stop;
	private JButton rivela;
	private Socket sk;
	private PrintWriter pw;
	private Scanner sc;

	
	public Ascoltatore(Board center,JTextField t,JTextField s,JButton connect,JButton disconnect,JButton start,
	JButton stop,JButton rivela) {
		this.center=center;
		this.t=t;
		this.s=s;
		this.connect=connect;
		this.disconnect=disconnect;
		this.start=start;
		this.stop=stop;
		this.rivela=rivela;
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("connect")) {
			try {
				sk=new Socket(t.getText(),Integer.valueOf(s.getText()));
				pw=new PrintWriter(sk.getOutputStream());
				sc=new Scanner(sk.getInputStream());
				connect.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);
				rivela.setEnabled(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		}
		if(cmd.equals("start")) {
			center.resetGame();
			center.setGameActive(false);
			pw.write("start");
			pw.write("\r\n");
			pw.flush();
			Downloader d=new Downloader(center,sc,connect,disconnect,start,stop,rivela);
			Thread th=new Thread(d);
			th.start();
			
			stop.setEnabled(true);
			start.setEnabled(false);
			rivela.setEnabled(false);
			disconnect.setEnabled(false);
		}
		if(cmd.equals("stop")) {
			pw.write("stop");
			pw.write("\r\n");
			pw.flush();
			
			stop.setEnabled(false);
			start.setEnabled(true);
			rivela.setEnabled(true);
			disconnect.setEnabled(true);
		}
		if(cmd.equals("disconnect")) {
			pw.write("disconnect");
			pw.write("\r\n");
			pw.flush();
			try {
				sk.close();
				pw.close();
				sc.close();
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			connect.setEnabled(true);
			start.setEnabled(false);
			rivela.setEnabled(false);
			disconnect.setEnabled(false);
		}
		if(cmd.equals("rivela")) {
			center.revealBoard();
			center.setGameActive(false);
		}
		
	}

}
