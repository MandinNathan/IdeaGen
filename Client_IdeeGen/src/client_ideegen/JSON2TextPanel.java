/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_ideegen;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Iterator;
import org.json.*;

/**
 *
 * @author utilisateur
 */
public class JSON2TextPanel extends JPanel {
    
    GridBagConstraints gc;
    
    String title;
    
    Object obj2process;
    
    public JSON2TextPanel(String name){
        
        setBackground(Color.DARK_GRAY);
        
        Border outerBorder = BorderFactory.createEmptyBorder(10,10,10,10); 
        Border innerBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new GridBagLayout());
        
        gc = new GridBagConstraints();
        
        title = name;
        
    }
    
    public void setText(Object obj){
        obj2process = obj;
        processDisplay();
    }
    
    public void processDisplay(){
        JLabel lab = new JLabel(processString(title, true));
        setStyle(lab, Font.BOLD | Font.ITALIC, 30, Color.CYAN, Color.DARK_GRAY, null);
        if(obj2process instanceof JSONObject){
            int nbCol = getNbMaxColumn((JSONObject) obj2process);
            setGC(0,0,1,nbCol,GridBagConstraints.CENTER,new Insets(20,20,20,20));
            add(lab, gc);
            
            processJSONObject((JSONObject) obj2process,0,1);
        } else if (obj2process instanceof JSONArray){
            setGC(0,0,1,1,GridBagConstraints.CENTER,new Insets(20,20,20,20));
            add(lab, gc);
            processJSONArray((JSONArray) obj2process,0,1, true);
        }
    }
    
    private void processJSONObject(JSONObject obj, int x, int y) {
        Iterator<String> keys = obj.keys();        
        JLabel lab1;
        JLabel lab2;
        int ft = 0;
        int size = 13;
        while(keys.hasNext()){
            switch(x){
                case 0:
                    ft = Font.BOLD;
                    size = 15 ;
                    break; 
                case 1:
                    ft = Font.ITALIC;
                    size = 14;
                    break;
                        
                    
            }
            
            String tmp = keys.next();
            lab1 = new JLabel(processString(tmp, true));
            setStyle(lab1, ft, size, Color.WHITE, Color.DARK_GRAY, null);
            setGC(x,y,1,1,GridBagConstraints.LINE_END,new Insets(5,5,5,5));
            add(lab1, gc); 
            
            Object tmpObj = obj.get(tmp);
            
            if(tmpObj instanceof String){
                tmp = (String) tmpObj;
                lab2 = new JLabel(processString(tmp, false));
                setStyle(lab2, 0, 12, Color.WHITE, Color.DARK_GRAY, null);
                setGC(x+1,y,1,1,GridBagConstraints.LINE_START,new Insets(5,5,5,5));
                add(lab2, gc); 
                
            } else if(tmpObj instanceof Integer){
                lab2 = new JLabel(Integer.toString((int) tmpObj));
                setStyle(lab2, 0, 12, Color.WHITE, Color.DARK_GRAY, null);
                setGC(x+1,y,1,1,GridBagConstraints.LINE_START,new Insets(5,5,5,5));
                add(lab2, gc); 
                
            } else if (tmpObj instanceof JSONObject){
                processJSONObject((JSONObject) tmpObj, x+1, y); 
                y += getNbMaxRow((JSONObject) tmpObj);
                
            } else if (tmpObj instanceof JSONArray){
                processJSONArray((JSONArray) tmpObj, x+1, y, false); 
                
            } else {
                lab2 = new JLabel("Valeur Inconnue ...");
                setStyle(lab2, 0, 12, Color.WHITE, Color.DARK_GRAY, null);
                setGC(x+1,y,1,1,GridBagConstraints.LINE_START,new Insets(5,5,5,5));
                add(lab2, gc); 
            }

            y++;
        }
    }
    
    public void processJSONArray(JSONArray obj, int x, int y, boolean isMain){        
        if(isMain){
            for(int i = 0; i<obj.length();i++){
                String tmp = obj.getString(i);
                JLabel lab = new JLabel(processString(tmp, false));
                setStyle(lab, 0, 14, Color.WHITE, Color.DARK_GRAY, null);
                setGC(x,y+i,1,1,GridBagConstraints.CENTER,new Insets(5,5,5,5));
                add(lab, gc); 
            }
        } else {
            String str = "";
            for(int i = 0; i<obj.length();i++){
                str += processString(obj.getString(i), false);
                if(i < obj.length() - 1){
                    str += ", ";
                }
            }
            JLabel lab = new JLabel(str);
            setStyle(lab, 0, 12, Color.WHITE, Color.DARK_GRAY, null);
            setGC(x,y,1,1,GridBagConstraints.LINE_START,new Insets(5,5,5,5));
            add(lab, gc); 
        }
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
    
    private String processString(String str, boolean isKey){
        String add = "";
        if(isKey){
            add = " : ";
        } 
        String res = Character.toUpperCase(str.charAt(0)) + str.substring(1) + add;
        return res;
    }
    
    private void setStyle(Component comp, int fontStyle, int fontSize, Color fg, Color bg, Dimension size){
        comp.setFont(new Font("Courier New",fontStyle, fontSize));
        comp.setForeground(fg);
        comp.setBackground(bg);
        comp.setPreferredSize(size);    
    }
    
    private int getNbMaxRow(JSONObject json){
        int count = 0;
        
        Iterator<String> keys = json.keys();
        while(keys.hasNext()){
            String key = keys.next();
            Object tmp = json.get(key);
            if(tmp instanceof JSONObject){
                count += getNbMaxRow((JSONObject) tmp);
            } else {
                count++;
            }
            
        }    
        
        return count;
    }
    
    private int getNbMaxColumn(JSONObject json){
        int maxCount = 2;
        int currentCount = maxCount;
        
        Iterator<String> keys = json.keys();
        while(keys.hasNext()){
            String key = keys.next();
            Object tmp = json.get(key);
            if(tmp instanceof JSONObject){
                currentCount += getNbMaxColumn((JSONObject) tmp) - 1;
            }
            if(currentCount>maxCount){
                int tmpInt = currentCount;
                currentCount = maxCount;
                maxCount = tmpInt;
            }
        }    
        
        return maxCount;
    }


    
    
    
}
