/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_rest_idees.idees;

import serveur_rest_idees.idees.dessin.*;

/**
 *
 * @author utilisateur
 */
public class Dessin implements Idee {
    
    String descDessin;
    
    String techniqueJSON;
    boolean tech = false;
    
    String sujet;
    String sujetJSON;
    boolean suj = false;
    
    String descriptionJSON;
    boolean desc = false;
    
    
    public Dessin(){
        descDessin = "{"; 
        techniqueJSON = "{";
        sujetJSON = "{";
    }
    
    public void addTechnique(){
        String[] listStyle = new String[]{"photorealisme","manga","abstrait","croquis"};
        String[] listSupport = new String[]{"papier", "toile", "tablette graphique"};
        String[] listOutil = new String[]{"stylo à billes","crayon de bois", "pastel", "pinceau", "feutres"};
        
        String style = listStyle[(int) (listStyle.length*Math.random())];
        techniqueJSON += "\"style\":\"" + style + "\"";
        
        String support = listSupport[(int) (listSupport.length*Math.random())];
        techniqueJSON += ",";
        techniqueJSON += "\"support\":\"" + support + "\"";
        
        String outil = (Math.random()<0.5) ? "libre" : listOutil[(int) (listOutil.length*Math.random())];
        techniqueJSON += ",";
        techniqueJSON += "\"outil\":\"" + outil + "\"";
        
        String couleur;
        double n1 = 0.5;
        double n2 = 0.8;
        if(null != style) switch (style) {
            case "phororealisme":
                n1 = 0.5;
                n2 = 0.75;
                break;
            case "manga":
                n1 = 0.75;
                n2 = 0.95;
                break;
            case "abstrait":
                n1 = 0.33;
                n2 = 0.5;
                break;
            case "croquis":
                n1 = 0.95;
                n2 = 0.95;
                break;
            default:
                break;
        }
        
        if(Math.random()<n1){
            //Monochrome
            if(Math.random()<n2){
                couleur = "\"couleur\":[\"monochrome\",\"000000\"]";
            }else {
                int R = (int) (255*Math.random());            
                int G = (int) (255*Math.random());
                int B = (int) (255*Math.random());
                couleur = String.format("%X", R) + String.format("%X", G) + String.format("%X", B);  
                couleur = "\"couleur\":[\"monochrome\",\"" + couleur + "\"]";
            }
        }else {
            //Polychrome
            couleur = "\"couleur\":[\"polychrome\",";
            
            int nombCouleur = 3;
            String tmpCouleur = "";
            for(int i = 0; i<nombCouleur ;i++){
                int R = (int) (255*Math.random());            
                int G = (int) (255*Math.random());
                int B = (int) (255*Math.random());
                tmpCouleur = String.format("%X", R) + String.format("%X", G) + String.format("%X", B);
                if(i<nombCouleur-1){
                    couleur += "\"" + tmpCouleur + "\",";
                }                                    
            }
            couleur += "\"" + tmpCouleur + "\"]";
        }
        techniqueJSON += ",";
        techniqueJSON += couleur;

        tech = true;
        techniqueJSON += "}";
    }
     
    public void addSujet(String s){
        String[] listSujet = new String[]{"personnage","paysage","theme"};
        
        boolean found = false;
        for (String element:listSujet) {
            if (element.equals(s)) {
                found = true;
            }
        }
        
        if(found){
            sujet = s;
        }else{
            sujet = listSujet[(int) (listSujet.length*Math.random())];
        }
        sujetJSON += "\"sujet\":\"" + sujet + "\"";
        
        suj = true;
        sujetJSON += "}";

    }   
     
    public void addDescription(){
        if(null != sujet) switch (sujet) {
            case "personnage":
                Personnage pers = new Personnage();
                pers.generatePersonnage();
                descriptionJSON = pers.toJSON();              
                break;
            case "paysage":
                Paysage pays = new Paysage();
                pays.generatePaysage();
                descriptionJSON = pays.toJSON(); 
                break;
            case "theme":
                Theme them = new Theme();
                them.generateTheme();
                descriptionJSON = them.toJSON(); 
                break;
            default:
                descriptionJSON = "{}";
                break;
        }
        desc = true;
    }
    
    public String techniqueToJSON(){
        return techniqueJSON;
    }
    
    public String sujetToJSON(){
        return sujetJSON;
    }
    
    public String descriptionToJSON(){
        return sujetJSON;
    }
    
    @Override
    public String toJSON(){
        if (tech) {
            descDessin += "\"technique\":" + techniqueJSON;
            if (suj) {
                descDessin += ",";
                descDessin += "\"sujet\":" + sujetJSON;
                if (desc){
                    descDessin += ",";
                    descDessin += "\"description\":" + descriptionJSON;
                }
            }
        } else if (suj){
            descDessin += "\"sujet\":" + sujetJSON;
            if (desc){
                descDessin += ",";
                descDessin += "\"description\":" + descriptionJSON;
            }
        }
        
        descDessin += "}";
        
        return descDessin;
    }
}
