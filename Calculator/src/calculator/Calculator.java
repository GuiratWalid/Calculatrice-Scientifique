package calculator;

import Main.Main;
import CalculApp.*;
import HistoryApp.HistoryHelper;
import converter.Converter;
import java.awt.Dimension;
import tools.Menu;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class Calculator extends JFrame implements  ActionListener{

	JTextField textfield;
	
	JButton[] numberButton = new JButton[10];
	JButton[] functionButton = new JButton[9];
        
        Menu menu ;
	
	JPanel panel;
	
	Font myFont = new Font("Ink Free",Font.BOLD,30);
	double num1 = 0 , num2 = 0 , result = 0;
	char operator = ' ';
	
	public Calculator(){
		this.setTitle("Calculatrice");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(420,550);
		this.setLayout(null);
                this.centerFrame();
                
                menu = new Menu();
                menu.calculatrice.addActionListener(this);
                menu.historiqueCalculatrice.addActionListener(this);
                menu.convertisseur.addActionListener(this);
                menu.histroqueConvertisseur.addActionListener(this);
                menu.quitter.addActionListener(this);
                this.setJMenuBar(menu);
		
		textfield = new JTextField();
		textfield.setBounds(50,25,300,50);
		textfield.setFont(myFont);
		textfield.setEditable(false);
		
		functionButton[0] = new JButton("+");
		functionButton[1] = new JButton("-");
		functionButton[2] = new JButton("*");
		functionButton[3] = new JButton("/");
		functionButton[4] = new JButton(",");
		functionButton[5] = new JButton("=");
		functionButton[6] = new JButton("DEL");
		functionButton[7] = new JButton("CLR");
		functionButton[8] = new JButton("(-)");
		
		for(int i=0;i<9;i++) {
			functionButton[i].addActionListener(this);
			functionButton[i].setFont(myFont);
			functionButton[i].setFocusable(false);
		}
		
		for(int i=0;i<10;i++) {
			numberButton[i] = new JButton(String.valueOf(i));
			numberButton[i].addActionListener(this);
			numberButton[i].setFocusable(false);
		}
		
		functionButton[8].setBounds(50,430,100,50);
		functionButton[6].setBounds(150,430,100,50);
		functionButton[7].setBounds(250,430,100,50);
		
		panel = new JPanel();
		panel.setBounds(50,100,300,300);
		panel.setLayout(new GridLayout(4,4,10,10));
		panel.add(numberButton[1]);
		panel.add(numberButton[2]);
		panel.add(numberButton[3]);
		panel.add(functionButton[0]);
		panel.add(numberButton[4]);
		panel.add(numberButton[5]);
		panel.add(numberButton[6]);
		panel.add(functionButton[1]);
		panel.add(numberButton[7]);
		panel.add(numberButton[8]);
		panel.add(numberButton[9]);
		panel.add(functionButton[2]);
		panel.add(functionButton[4]);
		panel.add(numberButton[0]);
		panel.add(functionButton[5]);
		panel.add(functionButton[3]);
		
		this.add(panel);
		this.add(functionButton[8]);
		this.add(functionButton[6]);
		this.add(functionButton[7]);
		this.add(textfield);
		this.setVisible(true);
	}
	
        private void centerFrame() {

            Dimension windowSize = getSize();
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Point centerPoint = ge.getCenterPoint();

            int dx = centerPoint.x - windowSize.width / 2;
            int dy = centerPoint.y - windowSize.height / 2;    
            setLocation(dx, dy);
        }
        
	@Override
	public void actionPerformed(ActionEvent e) {
            try {
                ORB orb = ORB.init(Main.args, null);
                org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                Calcul calobj = (Calcul) CalculHelper.narrow(ncRef.resolve_str("ABC"));
                HistoryApp.History hisobj = (HistoryApp.History) HistoryHelper.narrow(ncRef.resolve_str("GHI"));
		for(int i=0;i<10;i++) {
			if(e.getSource() == numberButton[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}

		if(e.getSource() == functionButton[4] && !textfield.getText().contains(",")) {
			textfield.setText(textfield.getText().concat("."));
		}
		if(e.getSource() == functionButton[0]) {
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText("");
			operator = '+';
		}
		if(e.getSource() == functionButton[1]) {
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText("");
			operator = '-';
		}
		if(e.getSource() == functionButton[2]) {
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText("");
			operator = '*';
		}
		if(e.getSource() == functionButton[3]) {
			num1 = Double.parseDouble(textfield.getText());
			textfield.setText("");
			operator = '/';
		}
		if(e.getSource() == functionButton[5]) {
			num2 = Double.parseDouble(textfield.getText());
                        double num = 0;
			if(operator == '+') {
				num = calobj.addition(num1, num2);
                                hisobj.addCalculHistory(Double.toString(num1)+" + "+Double.toString(num2)+" = "+Double.toString(num));
			}
			else if(operator == '-') {
				num = calobj.soustraction(num1, num2);
                                if(num2 > 0)
                                    hisobj.addCalculHistory(Double.toString(num1)+" - "+Double.toString(num2)+" = "+Double.toString(num));
                                else
                                    hisobj.addCalculHistory(Double.toString(num1)+" + "+Double.toString(num2*(-1))+" = "+Double.toString(num));
			}
			else if(operator == '*') {
				num = calobj.multiplication(num1, num2);
                                hisobj.addCalculHistory(Double.toString(num1)+" * "+Double.toString(num2)+" = "+Double.toString(num));
			}
			else if(operator == '/') {
				num = calobj.division(num1, num2);
                                hisobj.addCalculHistory(Double.toString(num1)+" / "+Double.toString(num2)+" = "+Double.toString(num));
			}
			//operator = ' ';
			textfield.setText(Double.toString(num));
                        num1 = num;
		}
		if(e.getSource() == functionButton[8]) {
                    if(Double.parseDouble(textfield.getText())>0)
			textfield.setText("-"+textfield.getText());
                    else
                        textfield.setText(Double.toString((-1) * (Double.parseDouble(textfield.getText()))));
		}
                if(e.getSource() == functionButton[6]) {
                        textfield.setText("");
		}
                if(e.getSource() == functionButton[7]) {
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    textfield.setText("");
                    operator = ' ';
		}
                if(e.getSource() == menu.calculatrice){
                    new Calculator();
                    dispose();
                }
                else if(e.getSource() == menu.historiqueCalculatrice){
                    new calculator.History();
                    dispose();
                }
                else if(e.getSource() == menu.convertisseur){
                    new Converter();
                    dispose();
                }
                else if(e.getSource() == menu.histroqueConvertisseur){
                    new converter.History();
                    dispose();
                }
                else if(e.getSource() == menu.quitter){
                    dispose();
                }
            }catch (Exception exc) {
                textfield.setText("Erreur");
            }
                
	}


}
