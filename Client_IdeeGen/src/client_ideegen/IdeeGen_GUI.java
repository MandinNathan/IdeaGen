/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_ideegen;

import javax.swing.*;

/**
 *
 * @author utilisateur
 */
public class IdeeGen_GUI {
        public static void main(String[] args){
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    MainFrame frame = new MainFrame();
                }
            });
    }
}
