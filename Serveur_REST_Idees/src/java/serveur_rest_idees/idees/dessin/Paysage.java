/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveur_rest_idees.idees.dessin;

/**
 *
 * @author utilisateur
 */
public class Paysage {
    
    String descPaysage;
    
    String bgJSON;
    
    public Paysage(){
        descPaysage = "{";
        bgJSON = "{";
    }
    
    private boolean contains(String s, String[] l){
        boolean found = false;
        for (String element:l) {
            if (element.equals(s)) {
                found = true;
            }
        }
        return found;
    }
    
    private String[] initilize(int nbEl){
        String[] res = new String[nbEl];
        for(int i = 0; i<nbEl; i++){
            res[i] = "";
        }
        return res;
    }
    
    private String generateListJSON(String[] l, int nbEl){
        String res = "[";
        String[] addElList = this.initilize(nbEl);
        String tmp;
        int secur;

        for(int i = 0; i<nbEl ;i++){
            if(i<nbEl-1){
                tmp = l[(int) (l.length*Math.random())];
                secur = 0;
                
                while(this.contains(tmp, addElList) && secur<10){
                    tmp = l[(int) (l.length*Math.random())];
                    secur++;
                }
                addElList[i] = tmp;
                res += "\"" + tmp + "\",";
            }                                    
        }
        
        tmp = l[(int) (l.length*Math.random())];
        secur = 0;

        while(this.contains(tmp, addElList) && secur<10){
            tmp = l[(int) (l.length*Math.random())];
            secur++;
        }
        res += "\"" + tmp + "\"]";
        return res;
    }
    
    public void generatePaysage(){
        String[] listLieu = new String[]{"ville","campagne","ocean","ile","ciel","grotte","montagne"};
        String[] listEpoq = new String[]{"médiévale","antique","renaissance","contemporaine", 
            "moderne", "post-apocalyptique","futuriste","cyber"};
        String[] listEnv = new String[]{"arbre","batiment","montagne","lac","tour","usine",
            "temple","maison","champ","chateau","cirque","mer"};
        
        String lieu = listLieu[(int) (listLieu.length*Math.random())];
        bgJSON += "\"lieu\":\"" + lieu + "\"";
        
        String epoque = listEpoq[(int) (listEpoq.length*Math.random())];
        bgJSON += ",";
        bgJSON += "\"epoque\":\"" + epoque + "\"";

        bgJSON += ",";
        String environnement = this.generateListJSON(listEnv, (int) (2*Math.random()+3));
        bgJSON += "\"environement\":" + environnement;
        bgJSON += "}";
        
    }

    public String toJSON(){
        descPaysage = bgJSON;
        return descPaysage;
    }
}
