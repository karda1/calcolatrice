import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;

import javax.swing.*;

public class Calcolatrice extends JFrame implements ActionListener{
    JButton[] arrTasti;
    char[] arrSimboli;
    JLabel labelRisultato;

    public Calcolatrice(){
        super();
        creaCalcolatrice();
    }

    public void creaCalcolatrice(){
        arrSimboli = new char[15];
        arrSimboli[0] = '1';
        arrSimboli[1] = '2';
        arrSimboli[2] = '+';
        arrSimboli[3] = '3';
        arrSimboli[4] = '4';
        arrSimboli[5] = '-';
        arrSimboli[6] = '5';
        arrSimboli[7] = '6';
        arrSimboli[8] = '*';
        arrSimboli[9] = '7';
        arrSimboli[10] = '8';
        arrSimboli[11] = '/';
        arrSimboli[12] = '9';
        arrSimboli[13] = '0';
        arrSimboli[14] = '=';

        JPanel pannelloTasti = new JPanel(); //pannello per i tasti della calcolatrice
        pannelloTasti.setBackground(Color.black);
        this.add(pannelloTasti, BorderLayout.CENTER);
        pannelloTasti.setLayout(new GridLayout(5, 3)); 

        JPanel pannelloRis = new JPanel(); //pannello dove andrà il label per il risultato
        pannelloRis.setPreferredSize(new Dimension(400, 100));
        pannelloRis.setBackground(Color.black);
        this.add(pannelloRis, BorderLayout.NORTH);
        pannelloRis.setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));  // Allinea a destra

        JLabel label = new JLabel(""); //label per il risultato
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setForeground(Color.white);
        pannelloRis.add(label);

        arrTasti = new JButton[15];
        for (int i = 0; i < 15; i++){ 
            for(int j = i; j <= i; j++){
                arrTasti[i] = new JButton(String.valueOf(arrSimboli[j]));
                arrTasti[i].setFont(new Font("Arial", Font.BOLD, 25)); //Dimensione carattere
                arrTasti[i].setForeground(Color.WHITE);      
			    pannelloTasti.add(arrTasti[i]);
			    arrTasti[i].addActionListener(this);
            }                       
		}

        for(int i = 0; i < 15; i++){
            arrTasti[i].setBackground(Color.darkGray);
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Calcolatrice");
		setSize(400, 500);
		setVisible(true); 
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        String btnTxt = clickedButton.getText();
    }

    //Metodo per calcolo del risultato
    public String calcolaRisultato(String input){
        input.replace(" ", ""); //tolgo gli spazi
    }
}