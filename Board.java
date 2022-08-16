/*
 * Board.java
 *
 * NOTE: Inserire qui la direttiva package se la classe viene spostata
 * in un package che non Ã¨ quello di default.
 */

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 *     La classe {@code Board} realizza il pannello centrale dell'interfaccia
 *     grafica del client, che rappresenta il tabellone di gioco del campo minato.
 *
 *     Lo studente puÃ², a sua discrezione, completare il codice seguendo
 *     i suggerimenti inseriti ed utilizzare questa classe, oppure puÃ² fornire
 *     una sua implementazione, ignorando completamente questa classe
 *     se lo ritiene appropriato.
 *
 *     Qualora non venga utilizzata, questa classe puÃ² comunque essere
 *     consegnata insieme al resto del codice, a patto che le eventuali
 *     modifiche apportate non comportino errori di compilazione.
 * </p>
 */
public class Board extends JPanel implements ActionListener { //AGGIUNGERE AI LISTENER DEI BOARDBUTTON
    /*
     * Inserire qui una struttura dati per la memorizzazione delle istanze
     * di {@code BoardButton}. Si suggerisce di utilizzare una matrice,
     * ma Ã¨ possibile utilizzare qualsiasi altra struttura dati se lo si
     * ritiene appropriato (lista di liste, array {row,column}-major, ecc).
     */
	private BoardButton[][]matrix;
	public static final int SIZE1=10;
	public static final int SIZE2=10;

    /*
     * Inserire qui il costruttore della classe
     */
	public Board(BoardButton[][] m) {
		matrix=m;
	}

    /**
     * Restituisce l'istanza di {@code BoardButton} all'indice ({@code row}, {@code column}).
     * @param row l'indice di riga del pulsante
     * @param column l'indice di colonna del pulsante
     * @return l'istanza di {@code BoardButton} per l'indice ({@code row}, {@code column}).
     */
    public BoardButton getButton(int row, int column) {
        // TODO: completare l'implementazione
        return matrix[row][column];
    }

    /**
     * Il compito di questo metodo Ã¨ quello di attivare o disattivare
     * l'interazione con le caselle del gioco, utilizzando il metodo
     * {@code setEnabled(boolean)} sulle istanze di {@code BoardButton}.
     * Se il parametro {@code active} Ã¨ {@code true}, allora il gioco
     * viene attivato (i.e. i pulsanti possono essere premuti), altrimenti
     * viene disattivato (i.e. i pulsanti non possono essere premuti).
     */
    public void setGameActive(boolean active) {
       if(active==true) {
    	   for(int i=0;i<SIZE1;i++) {
    		   for(int j=0;j<SIZE2;j++) {
    			   matrix[i][j].setEnabled(true);
    		   }
    	   }
       }
       else {
    	   for(int i=0;i<SIZE1;i++) {
    		   for(int j=0;j<SIZE2;j++) {
    			   matrix[i][j].setEnabled(false);
    		   }
    	   }
       }
    }

    /**
     * Reset del gioco allo stato iniziale, chiamando il metodo {@code reset()}
     * per tutte le istanze di {@code BoardButton}.
     */
    public void resetGame() {
    	for(int i=0;i<SIZE1;i++) {
 		   for(int j=0;j<SIZE2;j++) {
 			  matrix[i][j].reset();
 		   }
 		}
    }

    /**
     * Rivela il contenuto di tutte le caselle del gioco
     * (i.e. chiama {@code reveal()} su tutte le istanze di {@code BoardButton}.
     */
    public void revealBoard() {
    	for(int i=0;i<SIZE1;i++) {
  		   for(int j=0;j<SIZE2;j++) {
  			  matrix[i][j].reveal();
  		   }
  		}
    }

    /**
     * La classe {@code Board} aggisce da <i>listener</i> per le istanze
     * di {@code BoardButton}. N.B. come sempre per fare ciÃ² Ã¨ necessario aggiungerla
     * alla lista dei listener per ogni istanza di {@code BoardButton}.
     *
     * Suggerimento: Ã¨ possibile ottenere un riferimento al componente che ha
     * scatenato l'evento attraverso la chiamata a {@code e.getSource()}.
     *
     * @param e l'evento scatenante la chiamata
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO: inserire qui il codice che realizza la
        // reazione alla pressione dei pulsanti del tabellone
        // (es. cosa succede se il pulsante contiene una mina?)
    	BoardButton b=(BoardButton)e.getSource();
    	if(b.hasMine()) {
    		JOptionPane.showMessageDialog(null, "you lose");
    		setGameActive(false);
    		revealBoard();
    	}
    	else {
    		//se non ce ne sono altre vuote vittoria, altrimenti mostra e poi continua
    		b.setSelected(true);
    		boolean win=true;
    		for(int i=0;i<SIZE1;i++) {
    	 		   for(int j=0;j<SIZE2;j++) {
    	 			   if(!(this.getButton(i,j).hasMine()) && !(this.getButton(i,j).isSelected())) {
    	 				   win=false;
    	 			   }
    	 		   }
    		}
    		if(win) {
    			setGameActive(false);
        		revealBoard();
        		JOptionPane.showMessageDialog(null, "you win");
        		
    		}
    		
    	}
    }

}