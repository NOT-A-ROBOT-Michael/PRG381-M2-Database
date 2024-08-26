/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package libraryproject;


import libraryproject.Model.SQLstatements;

/**
 *
 * @author iyesme
 */
public class LibraryProject {
    
    //public static DBConnection db = new DBConnection();
    public static SQLstatements sqlstatements = new SQLstatements();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(){
                /*//new LibraryProject().setVisible(true);
                try{
                    if (checkLibraryDB()== true) {
                        db.connect();
                        System.out.println("database already exists");
                    }else{
                        db.connect();
                        db.createDataBase();
                        System.out.println("lets create database");
                    }
                    db.test();
                }catch(Exception e){
                    e.printStackTrace();
                }*/
            }
        }
        );
    }
    
    
}
