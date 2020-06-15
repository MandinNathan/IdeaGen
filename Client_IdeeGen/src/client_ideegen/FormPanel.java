/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_ideegen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;



/**
 *
 * @author utilisateur
 */
public class FormPanel extends JPanel implements ActionListener {
    
    private JLabel titleLab;
    private JButton randomIdea;
    
    private String[] areas = new String[]{"Dessin","Photo","Vidéo","Jeu vidéo", "Invention"};    
    private String[] areas_path = new String[]{"dessin","photo","video","jv", "invention"};

    private JComboBox areaList;
    private JButton areaIdea;
    
    private ResourceListener listener;
    
    private GridBagConstraints gc;
    
    public FormPanel(){
        
        super.setBackground(Color.DARK_GRAY);
        
        titleLab = new JLabel("Des idées pour en avoir une ?");
        setStyle(titleLab, Font.BOLD, 40, Color.WHITE, Color.DARK_GRAY, null);
        
        randomIdea = new JButton("Générer une idée");
        setStyle(randomIdea, 0, 20, Color.DARK_GRAY, Color.CYAN, new Dimension(500,100));
        randomIdea.addActionListener(this);

        areaList = new JComboBox(areas);
        setStyle(areaList, 0, 16, Color.DARK_GRAY, Color.WHITE, new Dimension(250,50));    
        areaIdea = new JButton("Générer");
        setStyle(areaIdea, 0, 16, Color.DARK_GRAY, Color.WHITE, new Dimension(250,50));
        areaIdea.addActionListener(this);
        
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        setLayout(new GridBagLayout());
        
        gc = new GridBagConstraints();
        
        ///// First row ///// 
        
        gc.weightx = 1;
        gc.weighty = 1;
        setGC(0,0,1,2,GridBagConstraints.CENTER,new Insets(50,0,50,0));
        add(titleLab, gc);
                
        ///// Second row ///// 
        
        gc.weightx = 1;
        gc.weighty = 1;
        setGC(0,1,1,2,GridBagConstraints.CENTER,new Insets(20,0,20,0));
        add(randomIdea, gc);
        
        ///// Third row ///// 
        
        gc.weightx = 1;
        gc.weighty = 1;
        setGC(0,2,1,1,GridBagConstraints.LINE_END,new Insets(10,10,10,10));
        add(areaList, gc);
        
        setGC(1,2,1,1,GridBagConstraints.LINE_START,new Insets(10,10,10,10));
        add(areaIdea, gc);
        
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
    
    public void setResouceListener(ResourceListener listener){
        this.listener = listener;
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) (e.getSource());
        String area = "dessin";
        if(clicked == randomIdea){
            area = getRandomArea();
            if(area == null){
                area = "dessin";
            }
        } else {
            area = getSelectedArea();
        }

        JSONObject resource = getRessourceFromArea(area);
        if(resource == null){
            listener.warningEmitted("Ce service n'est pas encore implémenter ou une erreur est survenue ...");
        } else{
            listener.resourceEmitted(resource);  
            listener.areaEmitted(area);
  
        }
    }
    
    private String getRandomArea(){
        String res = null;
        try{
            URL url = new URL("https://idees1idee.herokuapp.com/webresources/idees/aleatoire");
            HttpsURLConnection connexion = (HttpsURLConnection) url.openConnection();
            connexion.connect();

            InputStream fluxReponse = connexion.getInputStream();
            BufferedReader buffeurReception = new BufferedReader(new InputStreamReader(fluxReponse));
            String ligne = "";
            StringBuilder reponse = new StringBuilder();
            while ((ligne = buffeurReception.readLine()) != null) {
                reponse.append(ligne + "\n");
            }
            connexion.disconnect();

            JSONObject jsonObject = new JSONObject(reponse.toString());
            res = jsonObject.getString("domaine");
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(FormPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
    
    private String getSelectedArea(){
        int ind = areaList.getSelectedIndex();
        String res = areas_path[ind];
        return res;
    }
    
    private JSONObject getRessourceFromArea(String area){
        JSONObject res = null;
        try{
            URL url = new URL("https://idees1idee.herokuapp.com/webresources/idees/" + area);
            HttpsURLConnection connexion = (HttpsURLConnection) url.openConnection();
            connexion.connect();

            InputStream fluxReponse = connexion.getInputStream();
            BufferedReader buffeurReception = new BufferedReader(new InputStreamReader(fluxReponse));
            String ligne = "";
            StringBuilder reponse = new StringBuilder();
            while ((ligne = buffeurReception.readLine()) != null) {
                reponse.append(ligne + "\n");
            }
            connexion.disconnect();

            JSONObject jsonObject = new JSONObject(reponse.toString());
            int error = jsonObject.getInt("code");
            if(error == 0){
                res = jsonObject.getJSONObject("ressource");
            }
            else {
                res = null;
            }
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(FormPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    
    }    
}
