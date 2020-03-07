/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplejavacrudapps.main;
import javax.swing.*;

/**
 *
 * @author Farras Jibran
 */
public class Main {
    
   public static
        void main(String[] args)
            {
                //  Get koneksi
                Koneksi.GetConnect();

                //  JFrame library
                JFrame jFrame = new JFrame("Crud Application");
                jFrame.setContentPane(new form_siswa().jpanel);
                jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setVisible(true);
            }
}
