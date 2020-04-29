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
public class Personnage {
    
    String descPersonnage;
    
    String carteIdenJSON;
    
    String physiqueJSON;
    String visage;
    
    String moralJSON;
    
    public Personnage(){
        descPersonnage = "{";
        carteIdenJSON = "{";
        physiqueJSON = "{";
        moralJSON = "{";
    }
    
    public void generatePersonnage(){
        this.setCarteIden();
        this.setDescPhysique();
        this.setDescMoral();
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
    
    private void setCarteIden(){
        String[] listEmploi = new String[]{"sans emploi", "scolaire", "technique", "ingenerie", "finance", "management", "art"};
        
        String nom = "???";
        carteIdenJSON += "\"nom\":\"" + nom + "\"";
        
        String prenom = "???";
        carteIdenJSON += ",";
        carteIdenJSON += "\"prenom\":\"" + prenom + "\"";
        
        String age = Integer.toString((int)(100*Math.random()));
        carteIdenJSON += ",";
        carteIdenJSON += "\"age\":" + age;
        
        String sexe = (Math.random()<0.5) ? "homme" : "femme";
        carteIdenJSON += ",";
        carteIdenJSON += "\"sexe\":\"" + sexe + "\"";
        
        String situation = (Math.random()<0.5) ? "celibataire" : "couple";
        carteIdenJSON += ",";
        carteIdenJSON += "\"situation\":\"" + situation + "\"";
        
        String emploi = listEmploi[(int)(listEmploi.length*Math.random())] ;
        carteIdenJSON += ",";
        carteIdenJSON += "\"emploi\":\"" + emploi + "\"";
        
        carteIdenJSON += "}";
    }
    
    private void setDescPhysique(){
        visage = "{";
        
        this.setVisage();
        physiqueJSON += "\"visage\":" + visage;
        
        String corp = "{";

        String taille = (Math.random()<0.5) ? "moyen" : (Math.random()<0.5) ? "petit" : "grand";
        corp += "\"taille\":\"" + taille + "\"";        
        
        String masse = (Math.random()<0.5) ? "moyen" : (Math.random()<0.5) ? "maigre" : "gros";
        corp += ",";
        corp += "\"masse\":\"" + masse + "\"";  
        corp += "}";
        physiqueJSON += ",";
        physiqueJSON += "\"corp\":" + corp;       
        
        physiqueJSON += "}";       
    }
    
    private void setVisage(){
        String[] listForme = new String[]{"rond", "carre", "ovale", "rectangle"};
        String[] listCoulChe = new String[]{"brun", "chatain", "blond", "noir", "roux"};
        String[] listForChe = new String[]{"lisses","ondules", "frises"};
        String[] listLongChe = new String[]{"ras", "courts", "mi-longs","longs"}; 
        String[] listCoulYeu = new String[]{"bleu", "marron", "noir", "vert", "gris"};
        
        String forme = listForme[(int) (listForme.length*Math.random())];
        visage += "\"forme\":\"" + forme + "\"";
        
        String rousseur = (Math.random()<0.75) ? "non" : "oui";
        visage += ",";
        visage += "\"rousseur\":\"" + rousseur + "\"";

        String cheveux = "[\"" + listCoulChe[(int)(listCoulChe.length*Math.random())] + "\",\""  
                + listForChe[(int)(listForChe.length*Math.random())] + "\",\"" 
                + listLongChe[(int)(listLongChe.length*Math.random())] + "\"]";
        visage += ",";
        visage += "\"cheveux\":" + cheveux;
        
        String yeux = listCoulYeu[(int) (listCoulYeu.length*Math.random())];
        visage += ",";
        visage += "\"yeux\":\"" + yeux + "\"";
        visage += "}";
    }
    
    private void setDescMoral(){
        String[] listTraitCarPos = new String[]{"adroit","affectueux","altruiste","attentif","brave","brillant",
            "chaleureux","charismatique","comique","courageux","cultivé","curieux","dynamique","élégant","énergique",
            "franc","généreux","gentil","habile","intelligent","imaginatif","jovial","loyal","malin",
            "minutieux","observateur","optimiste","patient","perspicace","rusé","sage","valeureux"};
        String[] listTraitCarNeg = new String[]{"agressif","asocial","brutal","capricieux","colérique","curieux",
            "dépendant","déplaisant","dépressif","détestable","énervant","énigmatique","escroc","fainéant","fier",
            "impatient","jaloux","lâche","lunatique","machiavélique","manipulateur","malhonnête","malsain","méchant",
            "mégalomane","naïf","orgueilleux","pessimiste","péteux","râleur","rancunier","rebel","sensible","sévère",
            "téméraire","têtu","vicieux","violent"};
        
        
        String pos = this.generateListJSON(listTraitCarPos, (int) (2*Math.random()+1));
        moralJSON += "\"caractere+\":" + pos;

        String neg = this.generateListJSON(listTraitCarNeg, (int) (2*Math.random()+1));
        moralJSON += ",";
        moralJSON += "\"caractere-\":" + neg;
        
        
        moralJSON += "}";
    }
    
    
    public String toJSON() {
        descPersonnage += "\"identite\":" + carteIdenJSON;
        descPersonnage += ",";
        descPersonnage += "\"physique\":" + physiqueJSON;
        descPersonnage += ",";
        descPersonnage += "\"moral\":" + moralJSON;
        descPersonnage += "}";
        
        return descPersonnage;
    }
    
}
