package converter;

import HistoryApp.*;
import ConvertApp.Convert;
import ConvertApp.ConvertHelper;
import Main.Main;
import calculator.Calculator;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import tools.Menu;


public class Converter extends JFrame implements ItemListener, WindowListener, ActionListener{
	
	JTextField textfield1,textfield2;
	JPanel panel ;
	JComboBox<String> combobox1, combobox2;
	JButton button,switcher;
	String text1, text2;
	
	Font myFont = new Font("Ink Free",Font.BOLD,15);
	String bases [] = {"Décimal","Binaire","Octal","Hexadécimal"};
        
        Menu menu;
	
	public Converter() {
		this.setTitle("Convertisseur");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(420,400);
		this.setLayout(null);
                this.centerFrame();
                
                menu = new Menu();
                menu.calculatrice.addActionListener(this);
                menu.historiqueCalculatrice.addActionListener(this);
                menu.convertisseur.addActionListener(this);
                menu.histroqueConvertisseur.addActionListener(this);
                menu.quitter.addActionListener(this);
                this.setJMenuBar(menu);
		
		combobox1 = new JComboBox<String>(bases);
		combobox1.setFont(myFont);
		combobox1.addItemListener(this);
		
		textfield1 = new JTextField();
		textfield1.setBounds(50,25,350,50);
		textfield1.setFont(myFont);
		textfield1.setEditable(true);
		
		combobox2 = new JComboBox<String>(bases);
		combobox2.setFont(myFont);
		combobox2.addItemListener(this);
                
                switcher = new JButton("< = >");
                switcher.setFont(myFont);
                switcher.addActionListener(this);
                
                JPanel subPanel = new JPanel();
                
                subPanel.add(switcher);
               
		textfield2 = new JTextField();
		textfield2.setBounds(50,25,300,50);
		textfield2.setFont(myFont);
		textfield2.setEditable(true);
		
		button = new JButton("Convertir");
		button.setFont(myFont);
		button.addActionListener(this);

		panel = new JPanel();
		panel.setBounds(75,30,250,260);
		panel.setLayout(new GridLayout(6,1,0,10));
		panel.add(combobox1);
		panel.add(textfield1);
                panel.add(subPanel);
		panel.add(combobox2);
		panel.add(textfield2);
		panel.add(button);
		
		this.add(panel);
		this.addWindowListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(combobox1.getSelectedIndex() == combobox2.getSelectedIndex()) {
			if(e.getSource() == combobox1) {
				if(combobox1.getSelectedIndex() == 0)
					combobox2.setSelectedIndex(1);
				else 
					combobox2.setSelectedIndex(0);
			}
			else {
				if(combobox1.getSelectedIndex() == 0)
				combobox1.setSelectedIndex(1);
			else 
				combobox1.setSelectedIndex(0);
			}
		}
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
	public void windowOpened(WindowEvent e) {
		combobox2.setSelectedIndex(1);
	}
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
                    
                    try {
                        ORB orb = ORB.init(Main.args, null);
                        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                        Convert convobj = (Convert) ConvertHelper.narrow(ncRef.resolve_str("DEF"));
                        HistoryApp.History hisobj = (HistoryApp.History) HistoryHelper.narrow(ncRef.resolve_str("GHI"));
			if( combobox1.getSelectedIndex() == 0 && combobox2.getSelectedIndex() == 1) // conversion décimal binaire
			{
                            textfield2.setText(convobj.decimalToBinary(Integer.parseInt(textfield1.getText())));
                            hisobj.addConvertHistory("("+textfield1.getText()+")d = ("+textfield2.getText()+")b");
                        }
			else if (combobox1.getSelectedIndex() == 0 && combobox2.getSelectedIndex() == 2) // conversion décimal octal
			{
                           textfield2.setText(convobj.decimalToOctal(Integer.parseInt(textfield1.getText())));
                           hisobj.addConvertHistory("("+textfield1.getText()+")d = ("+textfield2.getText()+")o");
			}
			else if (combobox1.getSelectedIndex() == 0 && combobox2.getSelectedIndex() == 3) // conversion décimal hexadécimal
			{
                            textfield2.setText(convobj.decimalToHexadecimal(Integer.parseInt(textfield1.getText())).toUpperCase());
                            hisobj.addConvertHistory("("+textfield1.getText()+")d = ("+textfield2.getText()+")h");
                        }
			else if (combobox1.getSelectedIndex() == 1 && combobox2.getSelectedIndex() == 0) // conversion binaire décimal
			{
                            textfield2.setText(convobj.BinaryToDecimal(textfield1.getText()));
                            hisobj.addConvertHistory("("+textfield1.getText()+")b = ("+textfield2.getText()+")d");
			}
			else if (combobox1.getSelectedIndex() == 1 && combobox2.getSelectedIndex() == 2) // conversion binaire octal
			{
                            textfield2.setText(convobj.binaryToOctal(textfield1.getText()));	
                            hisobj.addConvertHistory("("+textfield1.getText()+")b = ("+textfield2.getText()+")o");
                        }
			else if (combobox1.getSelectedIndex() == 1 && combobox2.getSelectedIndex() == 3) // conversion binaire hexadécimal
			{
                            textfield2.setText(convobj.binaryToHexadecimal(textfield1.getText()).toUpperCase());
                            hisobj.addConvertHistory("("+textfield1.getText()+")b = ("+textfield2.getText()+")h");
                        }
			else if (combobox1.getSelectedIndex() == 2 && combobox2.getSelectedIndex() == 0) // conversion octal décimal
			{
                            textfield2.setText(convobj.octalToDecimal(Integer.parseInt(textfield1.getText())));
                            hisobj.addConvertHistory("("+textfield1.getText()+")o = ("+textfield2.getText()+")d");
			}
			else if (combobox1.getSelectedIndex() == 2 && combobox2.getSelectedIndex() == 1) // conversion octal binaire
			{
                            textfield2.setText(convobj.octalToBinary(Integer.parseInt(textfield1.getText())));
                            hisobj.addConvertHistory("("+textfield1.getText()+")o = ("+textfield2.getText()+")b");
			}
			else if (combobox1.getSelectedIndex() == 2 && combobox2.getSelectedIndex() == 3) // conversion octal hexadécimal
			{
                            textfield2.setText(convobj.octalToHexaDecimal(Integer.parseInt(textfield1.getText())).toUpperCase());
                            hisobj.addConvertHistory("("+textfield1.getText()+")o = ("+textfield2.getText()+")h");
			}
			else if (combobox1.getSelectedIndex() == 3 && combobox2.getSelectedIndex() == 0) // conversion hexadécimal décimal
			{
                            textfield2.setText(convobj.hexadecimalToDecimal(textfield1.getText()));
                            hisobj.addConvertHistory("("+textfield1.getText()+")h = ("+textfield2.getText()+")d");
			}
			else if (combobox1.getSelectedIndex() == 3 && combobox2.getSelectedIndex() == 1) // conversion hexadécimal binaire
			{
                            textfield2.setText(convobj.hexadecimalToBinary(textfield1.getText()));
                            hisobj.addConvertHistory("("+textfield1.getText()+")h = ("+textfield2.getText()+")b");
			}
			else if (combobox1.getSelectedIndex() == 3 && combobox2.getSelectedIndex() == 2) // conversion hexadécimal octal
			{
                            textfield2.setText(convobj.hexadecimalToOctal(textfield1.getText()));
                            hisobj.addConvertHistory("("+textfield1.getText()+")h = ("+textfield2.getText()+")o");
			}
                        
                    }catch (Exception exc) {
                        textfield2.setText("Erreur");
                    }
		}
                else if(e.getSource() == switcher) {
                    int index = combobox1.getSelectedIndex();
                    combobox1.setSelectedIndex(combobox2.getSelectedIndex());
                    combobox2.setSelectedIndex(index);
                }
                else if(e.getSource() == menu.calculatrice){
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
        }
		
}

