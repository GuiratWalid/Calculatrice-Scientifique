package calculator;

import HistoryApp.HistoryHelper;
import Main.Main;
import converter.Converter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import tools.Menu;

public class History extends JFrame implements ActionListener{
    
    JList<String> list;
    String [] data = {};
    Menu menu;
    Font myFont = new Font("Ink Free",Font.BOLD,25);
    JButton button;
    
    public History(){
        
        try {
            ORB orb = ORB.init(Main.args, null);
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            HistoryApp.History hisobj = (HistoryApp.History) HistoryHelper.narrow(ncRef.resolve_str("GHI"));
            data=hisobj.getCalculHistory();
        }catch (Exception exc) {
                exc.printStackTrace();
        }
        
        this.setTitle("Historique de la calculatrice");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(420,450);
        this.setLayout(new BorderLayout());
        this.centerFrame();
        
        menu = new Menu();
        menu.calculatrice.addActionListener(this);
        menu.historiqueCalculatrice.addActionListener(this);
        menu.convertisseur.addActionListener(this);
        menu.histroqueConvertisseur.addActionListener(this);
        menu.quitter.addActionListener(this);
        this.setJMenuBar(menu);
        
        JPanel panel1 = new JPanel();
        panel1.setBounds(10, 10, 100, 30);
        panel1.setLayout(new FlowLayout());
        
        button = new JButton("Supprimer l'historique");
        button.addActionListener(this);
        button.setBounds(10, 10, 100, 30);
        button.setFont(myFont);
        panel1.add(button);
        this.add(panel1,BorderLayout.NORTH);
        
        list = new JList(data);
        list.setFont(myFont);
        list.setBounds(20, 20, 200, 350);
        list.setAlignmentX(CENTER_ALIGNMENT);
        
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setBounds(50, 50, 200, 300);
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1,2,50,50));
        panel2.add(listScroller);
        
        this.add(panel2,BorderLayout.CENTER);
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
                else if(e.getSource() == button){
                    try {
                        ORB orb = ORB.init(Main.args, null);
                        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                        HistoryApp.History hisobj = (HistoryApp.History) HistoryHelper.narrow(ncRef.resolve_str("GHI"));
                        hisobj.deleteCalculHistory();
                        new calculator.History();
                        dispose();
                    }catch (Exception exc) {
                            exc.printStackTrace();
                    }
                }
    
    }
    
}
