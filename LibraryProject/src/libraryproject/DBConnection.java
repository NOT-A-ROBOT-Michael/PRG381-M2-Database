/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libraryproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author iyesme
 */
public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:Library_DB;create=true;";
    
    //connection object
    Connection con;
    
    public DBConnection(){}
    
    public Connection connect()throws ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL);
            
            if(this.con != null){
                System.out.println("Connected to Database");
                return con;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    public void test(){
        try{
            String query = "SELECT * FROM SYS.SYSTABLES";
            //Statement statement = this.con.createStatement();
            this.con.createStatement().executeQuery(query);
            System.out.println("Table one");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void createDataBase(){
        try{
            createTables();
            populateTables();
            System.out.println("working");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public void createTables(){
        createCustomerTable();
        createBooksTable();
        createAuthorsTable();
        createBookAuthorsTable();
    }
    
    public void populateTables(){
        populateCustomerTable();
        populateBooksTable();
        populateAuthorsTable();
        populateBookAuthorsTable();
    }
    
    public void createCustomerTable(){
        try{
            String query = "Create Table Customers (customer_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, customer_name VARCHAR(50) NOT NULL, customer_surname VARCHAR(50), customer_phone VARCHAR(15), customer_email VARCHAR(50), book_amount INT DEFAULT 0)";
            //Statement statement = this.con.createStatement();
            this.con.createStatement().execute(query);
            System.out.println("Table one");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void createBooksTable(){
        try{
            String query = "Create Table Books (book_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, customer_id INT, book_name VARCHAR(90) NOT NULL, book_main_genre VARCHAR(90), book_publish DATE, book_borrowed DATE, FOREIGN KEY (customer_id) REFERENCES Customers(customer_id))";
            //Statement statement = this.con.createStatement();
            this.con.createStatement().execute(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void createBookAuthorsTable(){
        try{
            String query = "Create Table BookAuthors (book_id INT NOT NULL, author_id INT NOT NULL, PRIMARY KEY (book_id, author_id), FOREIGN KEY (book_id) REFERENCES Books(book_id), FOREIGN KEY (author_id) REFERENCES Authors(author_id))";
            //Statement statement = this.con.createStatement();
            this.con.createStatement().execute(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void createAuthorsTable(){
        try{
            String query = "Create Table Authors (author_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, author_name VARCHAR(90) NOT NULL, author_surname VARCHAR(90))";
            //Statement statement = this.con.createStatement();
            this.con.createStatement().execute(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void populateCustomerTable(){
        try{
            String query =  "INSERT INTO Customers (customer_name, customer_surname, customer_phone, customer_email, book_amount) VALUES "+
                            "('John', 'Doe', '123-456-789', 'johndoe@example.com', 1),"+
                            "('Jane', 'Smith', '987-654-321', 'janesmith@example.com', 1),"+
                            "('Michael', 'Johnson', '321-654-987', 'michaelj@example.com', 1),"+
                            "('Emily', 'Davis', '654-123-789', 'emilyd@example.com', 1),"+
                            "('William', 'Brown', '789-456-123', 'williamb@example.com', 1),"+
                            "('Olivia', 'Wilson', '987-321-654', 'oliviaw@example.com', 1),"+
                            "('James', 'Taylor', '123-789-456', 'jamest@example.com', 1),"+
                            "('Sophia', 'Moore', '456-987-123', 'sophiam@example.com', 1),"+
                            "('Alexander', 'Anderson', '654-789-321', 'alexandera@example.com', 1),"+
                            "('Isabella', 'Thomas', '321-123-456', 'isabellat@example.com', 1)";
            //Statement statement = this.con.createStatement();
            this.con.createStatement().execute(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void populateBooksTable() {
    try {
        String query = "INSERT INTO Books (customer_id, book_name, book_main_genre, book_publish, book_borrowed) VALUES "
                     + "(1, 'The Great Gatsby', 'Fiction', '1925-04-10', '2024-07-01'), "
                     + "(2, 'To Kill a Mockingbird', 'Fiction', '1960-07-11', NULL), "
                     + "(3, '1984', 'Dystopian', '1949-06-08', '2024-06-15'), "
                     + "(4, 'Pride and Prejudice', 'Romance', '1813-01-28', '2024-08-01'), "
                     + "(5, 'The Catcher in the Rye', 'Fiction', '1951-07-16', NULL), "
                     + "(6, 'Brave New World', 'Dystopian', '1932-08-31', '2024-05-20'), "
                     + "(7, 'The Hobbit', 'Fantasy', '1937-09-21', NULL), "
                     + "(8, 'Moby-Dick', 'Adventure', '1851-10-18', '2024-07-10'), "
                     + "(9, 'Wuthering Heights', 'Gothic', '1847-12-01', NULL), "
                     + "(10, 'The Catcher in the Rye', 'Fiction', '1951-07-16', NULL)";
        this.con.createStatement().execute(query);
        System.out.println("Books table populated");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    public void populateAuthorsTable() {
    try {
        String query = "INSERT INTO Authors (author_name, author_surname) VALUES "
                     + "('F. Scott', 'Fitzgerald'), "
                     + "('Harper', 'Lee'), "
                     + "('George', 'Orwell'), "
                     + "('Jane', 'Austen'), "
                     + "('J.D.', 'Salinger'), "
                     + "('Aldous', 'Huxley'), "
                     + "('J.R.R.', 'Tolkien'), "
                     + "('Herman', 'Melville'), "
                     + "('Emily', 'Brontë'), "
                     + "('John', 'Grisham')";
        this.con.createStatement().execute(query);
        System.out.println("Authors table populated");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    public void populateBookAuthorsTable() {
    try {
        String query = "INSERT INTO BookAuthors (book_id, author_id) VALUES "
                     + "(1, 1), "  // The Great Gatsby - F. Scott Fitzgerald
                     + "(2, 2), "  // To Kill a Mockingbird - Harper Lee
                     + "(3, 3), "  // 1984 - George Orwell
                     + "(4, 4), "  // Pride and Prejudice - Jane Austen
                     + "(5, 5), "  // The Catcher in the Rye - J.D. Salinger
                     + "(6, 6), "  // Brave New World - Aldous Huxley
                     + "(7, 7), "  // The Hobbit - J.R.R. Tolkien
                     + "(8, 8), "  // Moby-Dick - Herman Melville
                     + "(9, 9), "  // Wuthering Heights - Emily Brontë
                     + "(10, 5)";  // The Catcher in the Rye - J.D. Salinger
        this.con.createStatement().execute(query);
        System.out.println("BookAuthors table populated");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
}
