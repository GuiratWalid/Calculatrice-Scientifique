package tools;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
    
    public JMenu optionsMenu, historiquesMenu;
    public JMenuItem calculatrice,calculatriceScientifique, convertisseur, quitter, histroqueConvertisseur, historiqueCalculatrice, historiqueCalculatriceScientifique;
    
    public Menu(){
        
        optionsMenu = new JMenu("Options");
        calculatrice = new JMenuItem("Calculatrice");
        optionsMenu.add(calculatrice);
        convertisseur = new JMenuItem("Convertisseur");
        optionsMenu.add(convertisseur);
        calculatriceScientifique = new JMenuItem("Calculatrice Scientifique");
        calculatriceScientifique.setEnabled(false);
        optionsMenu.add(calculatriceScientifique);
        optionsMenu.addSeparator();
        quitter = new JMenuItem("Quitter");
        optionsMenu.add(quitter);
        this.add(optionsMenu);
        
        historiquesMenu = new JMenu("Historiques");
        historiqueCalculatrice = new JMenuItem("Historique de la calculatrice");
        historiquesMenu.add(historiqueCalculatrice);
        histroqueConvertisseur = new JMenuItem("Historique du convertisseur");
        historiquesMenu.add(histroqueConvertisseur);
        historiqueCalculatriceScientifique = new JMenuItem("Historique de la calculatrice scientifique");
        historiqueCalculatriceScientifique.setEnabled(false);
        historiquesMenu.add(historiqueCalculatriceScientifique);
        this.add(historiquesMenu);
        
    }

}
