//  Author:         (c) 2021 Bonifacio Marco Francomano


import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Downloader implements Runnable {
	private boolean running;
	private Board center;
	private Scanner sc;
	private JButton connect;
	private JButton disconnect;
	private JButton start;
	private JButton stop;
	private JButton rivela;
	
	
	public Downloader(Board center,Scanner sc,JButton connect,JButton disconnect,JButton start,JButton stop,
	JButton rivela) {
		running=false;
		this.center=center;
		this.sc=sc;
		this.connect=connect;
		this.disconnect=disconnect;
		this.start=start;
		this.stop=stop;
		this.rivela=rivela;
	}
	
	
	
	
	
	@Override
	public void run() {
		if(!running)running=true;
		String line;
		while(running) {
			line=sc.nextLine();
			if(line.equals("interrupted")) {
				JOptionPane.showMessageDialog(null, "Gioco interrotto,sconfitta!");
				stop.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);
				rivela.setEnabled(true);
				center.setGameActive(false);
				running=false;
				continue;
			}
			if(line.equals("done")) {
				JOptionPane.showMessageDialog(null, "Inizia la partita!");
				stop.setEnabled(false);
				disconnect.setEnabled(true);
				start.setEnabled(true);
				rivela.setEnabled(true);
				center.setGameActive(true);
				running=false;
				continue;
			}
			String cmds[]=line.split(":");
			if(cmds[2].equals("-1")) {
				BoardButton bt=center.getButton(Integer.parseInt(cmds[0]),Integer.parseInt(cmds[1]));
				bt.setMine(true);
			}
			else {
				BoardButton bt=center.getButton(Integer.parseInt(cmds[0]),Integer.parseInt(cmds[1]));
				bt.setAdjacentMinesCount(Integer.parseInt(cmds[2]));
			}
		}
		
	}

}
