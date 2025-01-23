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
        String[] arrSimboli = new String[18];

        /*  
        arrSimboli[0] = "1";
        arrSimboli[1] = "2";
        arrSimboli[2] = "+";
        arrSimboli[3] = "3";
        arrSimboli[4] = "4";
        arrSimboli[5] = "-";
        arrSimboli[6] = "5";
        arrSimboli[7] = "6";
        arrSimboli[8] = "*";
        arrSimboli[9] = "7";
        arrSimboli[10] = "8";
        arrSimboli[11] = "/";
        arrSimboli[12] = "9";
        arrSimboli[13] = "0";
        arrSimboli[14] = "=";
        arrSimboli[15] = " ";
        arrSimboli[16] = "C";
        arrSimboli[17] = "end";
        */
        for(int i = 0; i < 17; i++){
            arrSimboli[i] = Integer.toString(i);
        }
        arrSimboli[17] = "End";

        JPanel pannelloTasti = new JPanel(); //pannello per i tasti della calcolatrice
        pannelloTasti.setBackground(Color.black);
        this.add(pannelloTasti, BorderLayout.CENTER);
        pannelloTasti.setLayout(new GridLayout(6, 3)); 

        JPanel pannelloRis = new JPanel(); //pannello dove andrà il label per il risultato
        pannelloRis.setPreferredSize(new Dimension(400, 100));
        pannelloRis.setBackground(Color.black);
        this.add(pannelloRis, BorderLayout.NORTH);
        pannelloRis.setLayout((LayoutManager) new FlowLayout(FlowLayout.RIGHT));  // Allinea a destra

        labelRisultato = new JLabel(""); //label per il risultato
        labelRisultato.setFont(new Font("Arial", Font.BOLD, 25));
        labelRisultato.setForeground(Color.white);
        pannelloRis.add(labelRisultato);

        arrTasti = new JButton[18];
        for (int i = 0; i < 18; i++){ 
            for(int j = i; j <= i; j++){
                arrTasti[i] = new JButton(arrSimboli[j]);
                arrTasti[i].setFont(new Font("Arial", Font.BOLD, 25)); //Dimensione carattere
                arrTasti[i].setForeground(Color.WHITE);      
			    pannelloTasti.add(arrTasti[i]);
			    arrTasti[i].addActionListener(this);
            }                       
		}

        for(int i = 0; i < 18; i++){
            arrTasti[i].setBackground(Color.darkGray);
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Calcolatrice");
		setSize(400, 500);
		setVisible(true); 
    }

    String txtPrec = "";
    String[] arrInput = new String[100];
    int index = 0;  // Indice per l'array di input

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton)e.getSource();
        String btnTxt = clickedButton.getText(); //testo del bottone schiacciato

        if(btnTxt.equals("end")){
            System.exit(0);
        }

        if(btnTxt.equals("C")){
            cancella();
            txtPrec = "";
            index = 0; //resetto l'indice
        }
        else if(btnTxt.equals("=")){
            labelRisultato.setText(calcolaRisultato());
        }
        else{
            arrInput[index] = btnTxt; 
            index++;
            txtPrec += btnTxt;
            labelRisultato.setText(txtPrec);        //lo aggiungo nel label del risultato
        }
    }

    public void cancella(){
        labelRisultato.setText("");
    }

    public String calcolaRisultato(){
        MathEvaluator evaluator = new MathEvaluator();

        try {
            return String.valueOf(evaluator.eval(txtPrec)); //restituisco il risultato come stringa
        } catch (Exception e) {
            return "Errore";
        }
    }
}




























/*int[] arrNum;
        arrNum = new int[arrInput.length];
        char strToChar = 0;
        int charToInt = 0;
        int ris = 0;

        for(int i = 0; i < arrInput.length; i++){
            if(!arrInput[i].equals("+") && !arrInput[i].equals("-") && !arrInput[i].equals("*") && !arrInput[i].equals("/") && !arrInput[i].equals("=")){
                strToChar = arrInput[i].charAt(0);
                charToInt = strToChar;
                arrNum[i] = charToInt;
            }
            else{
                if(arrInput[i].equals("+")){
                    for(int j = 0; j < arrNum.length; j++){
                        ris += arrNum[j];
                    }
                }
            }
        }


        char charRis = (char)ris;
        String strRis = new String(new char[] {charRis});

        return strRis;*/