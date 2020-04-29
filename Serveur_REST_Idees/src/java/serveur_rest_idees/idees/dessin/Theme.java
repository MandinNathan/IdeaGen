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
public class Theme {
    
    String descTheme;
    
    String thJSON;
    
    public Theme(){
        descTheme = "{";
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
    
    public void generateTheme(){
        String[] listTheme = new String[]{"temps","passé","présent","future","alternatif","espace","nature","industrie",
            "géomètrie","psychologie",""};
        
        String themes = this.generateListJSON(listTheme, 3);        
        thJSON = themes;
        
    }

    public String toJSON(){
        descTheme = thJSON;
        return descTheme;
    }
}
