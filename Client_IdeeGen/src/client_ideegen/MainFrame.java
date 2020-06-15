/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_ideegen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.json.JSONObject;

/**
 *
 * @author utilisateur
 */
public class MainFrame extends JFrame {
    
    public MainFrame(){
        super("Générateur d'idée !");
        
        setLayout(new BorderLayout());
        
        FormPanel header = new FormPanel();
        add(header, BorderLayout.NORTH);
        
        IdeaDescPanel ideaDescription = new IdeaDescPanel();
        add(ideaDescription, BorderLayout.CENTER);
                
        header.setResouceListener(new ResourceListener(){
            public void areaEmitted(String area) {
                ideaDescription.setupDisplay(area);
            }

            public void resourceEmitted(JSONObject resource) {
                ideaDescription.setupData(resource);
            }

            public void warningEmitted(String warning) {
                ideaDescription.setBasicDisplay(warning);
            }
        });
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) (tk.getScreenSize().width);        
        int ySize = (int) (tk.getScreenSize().height);

        setSize(xSize, ySize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
}
