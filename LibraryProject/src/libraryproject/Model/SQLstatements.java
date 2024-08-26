/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryproject.Model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import libraryproject.DBConnection;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Date;

/**
 *
 * @author iyesme
 */
public class SQLstatements {
    
    static Connection con;
    
    public static DBConnection db = new DBConnection();

    public SQLstatements() {
        try{
            if (checkLibraryDB()== true) {
                con = db.connect();
                System.out.println("database already exists");
            }else{
                con = db.connect();
                db.createDataBase();
                System.out.println("lets create database");
            }
            db.test();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public static boolean checkLibraryDB() {
        // Get the current directory of the class
        Path currentDir = Paths.get("").toAbsolutePath();
        //System.out.println(currentDir);

        Path libraryDBPath = currentDir.resolve("Library_DB");

        // Check if the file exists
        File libraryDBFile = libraryDBPath.toFile();
        //System.out.println(libraryDBFile);
        return libraryDBFile.exists(); //&& libraryDBFile.isFile();
    }
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////     
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 

    //GET REQUESTS
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 

    
    public ArrayList<String[]> getCustomersFromDatabase(){
        ArrayList<String[]> dataList = new ArrayList<>();
        try{
            //making it easier to copy and paste method
            String query = "SELECT * FROM Customers";
            ResultSet table = this.con.createStatement().executeQuery(query);
            
            while (table.next()) {                
                String id = table.getString("customer_id");
                String name = table.getString("customer_name");
                String surname = table.getString("customer_surname");
                String phone = table.getString("customer_phone");
                String email = table.getString("customer_email");
                String books = Integer.toString(table.getInt("book_amount"));
                
                String[] row = {id, name, surname, phone, email, books};
                dataList.add(row);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public ArrayList<String[]> getBooksFromDatabase(){
        ArrayList<String[]> dataList = new ArrayList<>();
        try{
            //making it easier to copy and paste method
            String query = "SELECT * FROM Books";
            ResultSet table = this.con.createStatement().executeQuery(query);
            
            while (table.next()) {                
                String bid = table.getString("book_id");
                String cid = table.getString("bcustomer_id");
                String name = table.getString("book_name");
                String genre = table.getString("book_main_genre");             
                String publish = table.getDate("publish_date").toString();
                String borrowed = table.getDate("publish_date").toString();
                
                
                String[] row = {bid, cid, name, genre, publish, borrowed};
                dataList.add(row);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public ArrayList<String[]> getBookAuthorsFromDatabase(){
        ArrayList<String[]> dataList = new ArrayList<>();
        try{
            //making it easier to copy and paste method
            String query = "SELECT * FROM BookAuthors";
            ResultSet table = this.con.createStatement().executeQuery(query);
            
            while (table.next()) {                
                String bid = table.getString("book_id");
                String aid = table.getString("author_id");
                
                String[] row = {bid, aid};
                dataList.add(row);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dataList;
    }
    
    public ArrayList<String[]> getAuthorsFromDatabase(){
        ArrayList<String[]> dataList = new ArrayList<>();
        try{
            //making it easier to copy and paste method
            String query = "SELECT * FROM Authors";
            ResultSet table = this.con.createStatement().executeQuery(query);
            
            while (table.next()) {                
                String id = table.getString("author_id");
                String name = table.getString("author_name");
                String surname = table.getString("author_surname");
                
                String[] row = {id, name, surname};
                dataList.add(row);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dataList;
    }
    

/////////////////////////////////////////////////////////////////////////////////////////////////////////////   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////     
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 

    //Insert REQUESTS
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    
    public void insertCustomersIntoDatabase(String customer_name, String customer_surname, String customer_phone, String customer_email, String book_amount){       
        try{
            //converting values if they arn't null
            if (customer_surname != null) {
                customer_surname = "'"+customer_surname+"'";
            }
            if (customer_phone != null) {
                customer_phone = "'"+customer_phone+"'";
            }
            if (customer_email != null) {
                customer_email = "'"+customer_email+"'";
            }
            
            //making it easier to copy and paste method
            String query = "INSERT INTO Customers (customer_name, customer_surname, customer_phone, customer_email, book_amount) VALUES ('"+
                            customer_name+"', "+
                            customer_surname+", "+
                            customer_phone+", "+
                            customer_email+", "+
                            book_amount+")";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void insertBooksIntoDatabase(String customer_id, String book_name, String book_main_genre, String book_publish_date, String book_borrowed_date){
        try{
            //converting if values arn't null
            if (customer_id != null) {
                customer_id = "'"+customer_id+"'";
            }
            if (book_main_genre != null) {
                book_main_genre = "'"+book_main_genre+"'";
            }
            if (book_publish_date != null) {
                book_publish_date = "'"+book_publish_date+"'";
            }
            if (book_borrowed_date != null) {
                book_borrowed_date = "'"+book_borrowed_date+"'";
            } 
            
            String query = "INSERT INTO Books (customer_id, book_name, book_main_genre, book_publish, book_borrowed) VALUES ("+
                            customer_id+", '"+
                            book_name+"', "+
                            book_main_genre+", "+
                            book_publish_date+", "+
                            book_borrowed_date+")";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insertBookAuthorsIntoDatabase(String book_id, String author_id){
        try{
            String query = "INSERT INTO BookAuthors (book_id, author_id) VALUES ("+
                            book_id+", "+
                            author_id+")";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void insertAuthorsIntoDatabase(String author_name, String author_surname){
        try{
            //converting if values arn't null
            if (author_surname != null) {
                author_surname = "'"+author_surname+"'";
            }
                        
            String query = "INSERT INTO Authors (author_name, author_surname) VALUES ('"+
                            author_name+"', "+
                            author_surname+")";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////     
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 

    //Update REQUESTS
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    ////////////////////////////
    //Update intire tables
    ////////////////////////////   
    
    public void updateCustomersInDatabase(String customer_id, String customer_name, String customer_surname, String customer_phone, String customer_email, String book_amount){     
        try{
            //converting values if they arn't null
            if (customer_surname != null) {
                customer_surname = "'"+customer_surname+"'";
            }
            if (customer_phone != null) {
                customer_phone = "'"+customer_phone+"'";
            }
            if (customer_email != null) {
                customer_email = "'"+customer_email+"'";
            }
            
            //making it easier to copy and paste method
            String query = "UPDATE Customers SET "+
                            "customer_name = '"+customer_name+"', "+
                            "customer_surname = "+customer_surname+", "+
                            "customer_phone = "+customer_phone+", "+
                            "customer_email = "+customer_email+", "+
                            "book_amount = "+book_amount+" "+
                            "WHERE customer_id = '"+customer_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void updateBooksInDatabase(String book_id, String customer_id, String book_name, String book_main_genre, String book_publish_date, String book_borrowed_date){
        try{
            //converting if values arn't null
            if (customer_id != null) {
                customer_id = "'"+customer_id+"'";
            }
            if (book_main_genre != null) {
                book_main_genre = "'"+book_main_genre+"'";
            }
            if (book_publish_date != null) {
                book_publish_date = "'"+book_publish_date+"'";
            }
            if (book_borrowed_date != null) {
                book_borrowed_date = "'"+book_borrowed_date+"'";
            } 
            
            String query = "UPDATE Books SET "+
                            "customer_id = '"+customer_id+"', "+
                            "book_name = '"+book_name+"', "+
                            "book_main_genre = "+book_main_genre+", "+
                            "book_publish_date = "+book_publish_date+", "+
                            "book_borrowed_date = "+book_borrowed_date+" "+
                            "WHERE book_id = '"+book_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateBookAuthorsAuthorInDatabase(String book_id, String author_id){
        try{
            String query = "UPDATE BookAuthors SET "+
                            "author_id = '"+author_id+"' "+                          
                            "WHERE book_id = '"+book_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateBookAuthorsBookInDatabase(String book_id, String author_id){
        try{
            String query = "UPDATE BookAuthors SET "+
                            "book_id = '"+book_id+"' "+                          
                            "WHERE author_id = '"+author_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void updateAuthorsInDatabase(String author_id, String author_name, String author_surname){
        try{
            //converting if values arn't null
            if (author_surname != null) {
                author_surname = "'"+author_surname+"'";
            }
                        
            String query = "UPDATE Authors SET "+
                            "author_name = '"+author_name+"', "+
                            "author_surname = '"+author_surname+"' "+
                            "WHERE author_id = '"+author_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    ////////////////////////////
    //Update for certain events
    ////////////////////////////
    
    
    
   public void updateCustomersBookAmountInDatabase(String customer_id, String book_amount){     
        try{           
            //making it easier to copy and paste method
            String query = "UPDATE Customers SET "+
                            "book_amount = "+book_amount+" "+
                            "WHERE customer_id = '"+customer_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
   
   
   
   public void updateBooksBorrowedCustomerInDatabase(String book_id, String customer_id, String book_borrowed_date){
        try{
            //converting if values arn't null
            if (customer_id != null) {
                customer_id = "'"+customer_id+"'";
            }
            if (book_borrowed_date != null) {
                book_borrowed_date = "'"+book_borrowed_date+"'";
            } 
            
            String query = "UPDATE Books SET "+
                            "customer_id = '"+customer_id+"', "+
                            "book_borrowed_date = "+book_borrowed_date+" "+
                            "WHERE book_id = '"+book_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    
    

/////////////////////////////////////////////////////////////////////////////////////////////////////////////   
/////////////////////////////////////////////////////////////////////////////////////////////////////////////     
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 

    //Delete REQUESTS
    
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    
    public void deleteCustomersInDatabase(String customer_id){     
        try{            
            //making it easier to copy and paste method
            String query = "DELETE FROM Customers "+
                            "WHERE customer_id = '"+customer_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public void deleteBooksInDatabase(String book_id){
        try{
            deleteBookAuthorsBookInDatabase(book_id);
            
            String query = "DELETE FROM Books "+
                            "WHERE book_id = '"+book_id+"'";
            
            this.con.createStatement().execute(query);

            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public void deleteAuthorsInDatabase(String author_id){
        try{  
            deleteBookAuthorsAuthorInDatabase(author_id);
            
            String query = "DELETE FROM Authors "+
                            "WHERE author_id = '"+author_id+"'";
            
            this.con.createStatement().execute(query);
            
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
     public void deleteBookAuthorsInDatabase(String book_id, String author_id){
        try{
            String query = "DELETE FROM BookAuthors "+                         
                            "WHERE book_id = '"+book_id+"' AND "+
                            "author_id = '"+author_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    // if you are reading this you have scrolled too far please go back up
    // you can ignore these two they make the process easier kept out of sight
    // and yes they are private, keep them that way
    // there is another function ment for bookauthors
    
    
    private void deleteBookAuthorsAuthorInDatabase(String author_id){
        try{
            String query = "DELETE FROM BookAuthors "+                       
                            "WHERE author_id = '"+author_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void deleteBookAuthorsBookInDatabase(String book_id){
        try{
            String query = "DELETE FROM BookAuthors "+                         
                            "WHERE book_id = '"+book_id+"'";
            
            this.con.createStatement().execute(query);            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
  
    
    
    
}

/*                 MICHAEL
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣶⣶⣶⣶⣦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                BUGZ
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣼⣷⡶⠶⠶⠶⠦⠤⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡆⠀⠀⠀⣤⡶⠿⣧⣼⠿⢶⣤⠀⠀⠀⢰⡟
⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣀⣠⣾⣿⣿⣶⣶⣶⣶⣶⣶⣿⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣶⣿⣶⣧⣤⣤⣤⣤⣤⣤⣤⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⡄⠀⠀⣷⣤⡶⠟⠻⢶⣤⣾⠀⠀⢠⡿
⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠟⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⣄⣀⣻⣶⠶⠿⠿⠶⣶⣟⣀⣠⣾⠃
⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢿⣿⣿⣿⡿⠿⠛⠋⣩⣿⣿⣿⡿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⣿⠶⠟⠛⠛⠻⠶⣿⠋⠉
⠀⠀⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠓⠚⣿⣿⣿⠀⠀⢠⣾⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣄⣀⠀⠀⠀⠀⠀⠀⠋⠀⠀⠀⠀⠀⠀⠙⠀⠀⠀⠀⠀⠀⣀⣠⣤
⠀⠀⠀⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⠀⠀⠀⠿⠛⠋⣀⣶⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⡆⠀⠀⠀⢀⣤⣾⣿⠀⠀⠀⠀⣿⣷⣤⡀⠀⠀⠀⢰⡟⠁
⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⣀⣤⣶⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠿⠟⠁⣴⣿⣿⣿⣿⡆⢀⡀⢰⣿⣿⣿⣿⣦⠈⠻⠿⠁
⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⡇⠈⠁⢸⣿⣿⣿⣿⣿⣷⡀
⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⡄
⠀⢀⣤⣀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⡄
⠀⣸⣿⣿⣲⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⠟⢀⡀⠀⠀⢀⡀⠻⣿⣿⣿⣿⣿⣿⣷
⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⡿⠋⠀⠈⠻⣦⣴⠟⠁⠀⠙⢿⣿⣿⣿⣿⣿
⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠻⣿⣿⣿
⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉
⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠹⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⢸⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠈⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋⠀⢀⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋⠁⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠛⠻⢿⣿⣿⣿⣷⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠸⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠈⠻⢿⣿⣿⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
*/
