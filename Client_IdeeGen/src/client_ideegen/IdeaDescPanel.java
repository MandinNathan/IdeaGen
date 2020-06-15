/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_ideegen;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import org.json.*;

/**
 *
 * @author utilisateur
 */
public class IdeaDescPanel extends JPanel {
    
    JSONObject resource;
    String area;
    
    GridBagConstraints gc;
    
    JSON2TextPanel pan1;
    JSON2TextPanel pan2;
    JSON2TextPanel pan3;
    
    public IdeaDescPanel(){
        setBackground(Color.DARK_GRAY);
        
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        setLayout(new GridBagLayout());
        
        gc = new GridBagConstraints();
        
        setBasicDisplay("Aucune idée pour le moment ...");
    }
    
    public void setBasicDisplay(String msg){
        removeAll();
        
        JLabel lab = new JLabel(msg);
        setStyle(lab, Font.BOLD, 30, Color.WHITE, Color.DARK_GRAY, null);
               
        gc.weightx = 1;
        gc.weighty = 1;
        
        gc.gridx = 0;        
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.CENTER;
        add(lab, gc);
        
        revalidate();
        repaint();
    }   
    
    public void setDrawingDisplay(){
        removeAll();
        
        pan1 = new JSON2TextPanel("Sujet");
        pan1.setText(resource.get("sujet"));
        pan2 = new JSON2TextPanel("Technique");        
        pan2.setText(resource.get("technique"));
        pan3 = new JSON2TextPanel("Description");
        pan3.setText(resource.get("description"));

        ///// First column ///// 
        
        setGC(0,0,1,1,GridBagConstraints.LINE_END,new Insets(5,5,5,50));
        add(pan1, gc);
        
        setGC(0,1,1,1,GridBagConstraints.LINE_END,new Insets(5,5,5,50));
        add(pan2, gc);
        
        ///// Second column ///// 
        
        setGC(1,0,2,1,GridBagConstraints.LINE_START,new Insets(5,50,5,5));
        add(pan3, gc);
        
        revalidate();
        repaint();
    }  
    
    private void setGC(int x, int y, int h, int w, int a,Insets i){
        gc.gridx = x;        
        gc.gridy = y;
        gc.gridheight = h;
        gc.gridwidth = w;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = a;
        gc.insets = i;
    }
    
    private void setStyle(Component comp, int fontStyle, int fontSize, Color fg, Color bg, Dimension size){
        comp.setFont(new Font("Courier New",fontStyle, fontSize));
        comp.setForeground(fg);
        comp.setBackground(bg);
        comp.setPreferredSize(size);    
    }
        
    public void setupDisplay(String area){
        this.area = area;
        if("dessin".equals(area)){
            setDrawingDisplay();
        } else {
            setBasicDisplay("Rien");
        }
    }
    

    
    public void setupData(JSONObject resource){
        this.resource = resource;
    }
    
        //JSONObject jsonObject = new JSONObject(reponse.toString());
    //int nbBlagues = jsonObject.getInt("value");
    //jsonObject = new JSONObject(reponse.toString());
    //JSONArray jsonArray = jsonObject.getJSONArray("value");
    //JSONObject blagueEtMetadata = jsonObject.getJSONObject("value");
    //String blague = blagueEtMetadata.getString("joke");
    
}
