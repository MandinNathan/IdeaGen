/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_ideegen;

import org.json.JSONObject;

/**
 *
 * @author utilisateur
 */
public interface ResourceListener {
    
    public void warningEmitted(String warning);
    
    public void areaEmitted(String area);
    
    public void resourceEmitted(JSONObject resource);
    
}
