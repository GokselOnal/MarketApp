import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.sun.source.tree.ReturnTree;

import java.sql.*;
import java.util.Scanner;

public class GokselOnalHW1 {
    private static final String URL = "jdbc:mysql://localhost:3306/cs202_hw01?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "MyPass";

    private static Connection connection = null;

    public static void establishConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

        while(true) {
            Scanner console = new Scanner(System.in);

            String operations = "--------------------------------------------\n" +
                    "(1)  Customer registration\n" +
                    "(2)  Buying a product\n" +
                    "(3)  List customers\n" +
                    "(4)  List a customer's purchases\n" +
                    "(5)  List a customer's most recent purchases\n" +
                    "(6)  List branches\n" +
                    "(7)  List a branch’s stock\n" +
                    "(8)  Add new branch store\n" +
                    "(9)  Add new product\n" +
                    "(10) Add product to branch’s stock\n" +
                    "(11) Search customer by their phone number\n" +
                    "(12) Remove a user from system\n" +
                    "(13) Exit\n" +
                    "--------------------------------------------\n";

            System.out.println(operations);

            System.out.print("Enter a code number for the operation: ");
            String operation = console.nextLine();

            switch (operation) {
                case "1": {

                    System.out.println("Enter customers informations");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

                    String name;
                    String surname;
                    String address;
                    String pNumber;
                    System.out.print("Name: ");
                    name = console.nextLine();
                    System.out.print("Surname: ");
                    surname = console.nextLine();
                    System.out.print("Address: ");
                    address = console.nextLine();
                    System.out.print("Phone number: ");
                    pNumber = console.nextLine();

                    customerRegistration(name, surname, address, pNumber);
                    break;
                }
                case "2": {

                    System.out.println("Enter shopping informations");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

                    String cid;
                    String bid;
                    String pid;
                    String quantity;
                    String date;

                    System.out.print("Customer id: ");
                    cid = console.nextLine();
                    System.out.print("Branch id: ");
                    bid = console.nextLine();
                    System.out.print("Product id: ");
                    pid = console.nextLine();
                    System.out.print("Quantity: ");
                    quantity = console.nextLine();
                    System.out.print("Order date: ");
                    date = console.nextLine();

                    buyProduct(cid, bid, pid, quantity, date);
                    break;
                }
                case "3": {

                    listCustomers();
                    break;
                }
                case "4": {

                    String id;

                    System.out.print("Enter customer id: ");
                    id = console.nextLine();

                    System.out.println("Shopping information of the given customer");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

                    listCustomersPurchases(id);
                    break;
                }
                case "5": {

                    String id;

                    System.out.print("Enter customer id: ");
                    id = console.nextLine();

                    System.out.println("Most 5 recent shopping information of customer");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

                    listCustomersMostRecentPurchases(id);
                    break;
                }
                case "6": {

                    listBranches();
                    break;
                }
                case "7": {

                    String bid;

                    System.out.print("Enter branch id: ");
                    bid = console.nextLine();

                    branchStock(bid);
                    break;
                }
                case "8": {

                    String bname;
                    String baddress;

                    System.out.print("Enter the branch name: ");
                    bname = console.nextLine();

                    System.out.print("Enter the branch address: ");
                    baddress = console.nextLine();

                    addNewBranchStore(bname, baddress);
                    break;
                }
                case "9": {

                    String pname;
                    String description;
                    String price;

                    System.out.print("Enter Product name: ");
                    pname = console.nextLine();
                    System.out.print("Enter Product description: ");
                    description = console.nextLine();
                    System.out.print("Enter Product price: ");
                    price = console.nextLine();

                    addNewProduct(pname, description, price);
                    break;
                }
                case "10": {

                    String bid;
                    String pid;
                    String quantity;

                    System.out.print("Enter branch id: ");
                    bid = console.nextLine();
                    System.out.print("Enter Product id: ");
                    pid = console.nextLine();
                    System.out.print("Enter quantity: ");
                    quantity = console.nextLine();

                    addProductToBranchStock(bid,pid,quantity);
                    break;
                }
                case "11": {

                    String pNumber;

                    System.out.print("Enter customer phone number: ");
                    pNumber = console.nextLine();

                    System.out.println("Information of the customer whose phone number has been entered");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

                    searchCustomerByPhone(pNumber);
                    break;
                }
                case "12": {

                    System.out.println("Delete a customer");
                    System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

                    String cid;

                    System.out.print("Enter customer id: ");
                    cid = console.nextLine();

                    deleteCustomer(cid);
                    break;
                }
                case "13": {

                    System.out.println("Exiting the program...");
                    return;
                }
                default:{
                    System.out.println("Invalid operation. \nTry again.");
                    break;
                }

            }
        }
    }

    //(1) Customer registration
    public static void customerRegistration(String name,String surname,String address, String phoneNumber){

        String sqlname = "'"+name+"'";
        String sqlsurname = "'"+surname+"'";
        String sqladdress = "'"+address+"'";
        String sqlphone = "'"+phoneNumber+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO CUSTOMERS(CNAME, SURNAME, CADDRESS, PHONENUMBER) VALUES("+ sqlname + ", " + sqlsurname + ", "+ sqladdress +", "+ sqlphone +")";
            statement.executeUpdate(insertQuery);

            System.out.println("Customer is added.");
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(2) Buying a product
    public static void buyProduct(String cid, String bid, String pid,String quantity, String orderDate){

        String sqlcid = "'"+cid+"'";
        String sqlbid = "'"+bid+"'";
        String sqlpid = "'"+pid+"'";
        String sqlorderDate = "'"+orderDate+"'";

        int intSquantity = Integer.parseInt(quantity);

        try {
            establishConnection();
            Statement statement = connection.createStatement();

            String selectQuery2 = "SELECT CID FROM CUSTOMERS WHERE CID = " + sqlcid;
            ResultSet rs2 = statement.executeQuery(selectQuery2);

            //If the given cid is not in the system
            int count2 = 0;
            while(rs2.next()){
                count2++;
            }
            if(count2 == 0){
                System.out.println();
                System.out.println("Purchasing is not complete!");
                System.out.println("Given customer id is not available in the system");
                rs2.close();
                closeConnection();
                statement.close();
                return;
            }

            String selectQuery3 = "SELECT * FROM BRANCHES WHERE BID = " + sqlbid;
            ResultSet rs3 = statement.executeQuery(selectQuery3);

            //If the given bid is not in the system
            int count = 0;
            while(rs3.next()){
                count++;
            }
            if(count == 0){
                System.out.println();
                System.out.println("Purchasing is not complete!");
                System.out.println("Given branch id is not in the system.");
                rs3.close();
                closeConnection();
                statement.close();
                return;
            }


            String selectQuery4 = "SELECT * FROM PRODUCTS WHERE PID = " + sqlpid;
            ResultSet rs4 = statement.executeQuery(selectQuery4);

            //If the given pid is not in the system
            int count4 = 0;
            while(rs4.next()){
                count4++;
            }
            if(count4 == 0){
                System.out.println();
                System.out.println("Purchasing is not complete!");
                System.out.println("Given product id is not in the system.");
                rs4.close();
                closeConnection();
                statement.close();
                return;
            }


            String selectQuery5 = "SELECT * FROM STOCKS WHERE BID = " + sqlbid +" AND PID = " + sqlpid ;
            ResultSet rs5 = statement.executeQuery(selectQuery5);
            //If the given product is not available for the given branch
            int counter = 0;
            while(rs5.next()){
                counter++;
            }
            if(counter == 0){
                System.out.println("There is no any given product in the given branch");
                rs5.close();
                closeConnection();
                statement.close();
                return;
            }


            String selectQuery = "SELECT * FROM STOCKS WHERE BID = " + sqlbid +" AND PID = " + sqlpid ;
            ResultSet rs = statement.executeQuery(selectQuery);

            //If there are not enough stocks of that product in the given branch
            rs.next();
            if(rs.getInt(3) < intSquantity ){
                System.out.println();
                System.out.println("Purchasing is not complete!");
                System.out.println("There is no enough quantity of that product.");
                rs.close();
                closeConnection();
                statement.close();
                return;
            }


            String insertQuery = "INSERT INTO PURCHASES(CİD,BİD,PİD,PQUANTITY,ORDERDATE) VALUES("+ sqlcid + ", " + sqlbid + ", "+ sqlpid +", "+ quantity + ", " + sqlorderDate +")";
            String updateQuery = "UPDATE STOCKS SET SQUANTITY = SQUANTITY - " +  quantity + " WHERE BID = " + sqlbid + " AND PID = " + sqlpid ;
            statement.executeUpdate(insertQuery);
            statement.executeUpdate(updateQuery);

            System.out.println("Purchasing completed.");
            rs.close();
            rs2.close();
            rs3.close();
            rs4.close();
            rs5.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(3) List customers
    public static void listCustomers(){

        try {
            establishConnection();
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT * FROM CUSTOMERS";
            ResultSet rs = statement.executeQuery(selectQuery);

            System.out.println("Customers");
            System.out.println("¯¯¯¯¯¯¯¯¯");
            System.out.println("cid:cname:surname:caddress:phonenumber");
            System.out.println();

            int counter = 0;
            while (rs.next()) {
                System.out.println(rs.getString("CID") + " : " + rs.getString("CNAME") + " : " + rs.getString("SURNAME") + " : " + rs.getString("CADDRESS") + " : " + rs.getString("PHONENUMBER"));
                counter ++;
            }
            if(counter == 0){
                System.out.println("There is no any customer in the system.");
            }

            rs.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(4) List a customer's purchases
    public static void listCustomersPurchases(String id){

        String sqlid = "'"+id+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();

            String selectQuery2 = "SELECT * FROM CUSTOMERS WHERE CID = " + sqlid;
            ResultSet rs2 = statement.executeQuery(selectQuery2);


            System.out.println("purchaseid:cid:cname:bid:bname:pid:pname:description:pquantity:totprice:orderdate");
            System.out.println();

            //If the given customer id is not in the system
            int count = 0;
            while(rs2.next()){
                count++;
            }
            if(count == 0){
                System.out.println("There is no any customer with given id.");
                rs2.next();
                closeConnection();
                statement.close();
                return;
            }

            String selectQuery = "SELECT * FROM PURCHASES NATURAL JOIN CUSTOMERS NATURAL JOIN BRANCHES NATURAL JOIN PRODUCTS WHERE CİD = " + sqlid;
            ResultSet rs = statement.executeQuery(selectQuery);

            //If the customers purchase history is empty
            int counter = 0;
            while (rs.next()) {
                int quantity = Integer.parseInt(rs.getString("PQUANTITY"));
                double price = Double.parseDouble(rs.getString("PRICE"));
                double totPrice = quantity * price;
                String sqltotPrice = String.valueOf(totPrice);
                System.out.println(rs.getString("PURCHASEID") + " : " +rs.getString("CID") + " : " + rs.getString("CNAME") + " : " + rs.getString("BID") + " : "  + rs.getString("BNAME") + " : " + rs.getString("PID") + " : " + rs.getString("PNAME") + " : " + rs.getString("DESCRIPTION") +  " : " + rs.getString("PQUANTITY") + " : " + sqltotPrice + " : " + rs.getString("ORDERDATE"));
                counter += 1;
            }

            if(counter == 0){
                System.out.println("The customer does not have any purchase history.");
                rs.close();
                closeConnection();
                statement.close();
                return;
            }

            rs2.close();
            rs.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(5) List a customer's most recent purchases
    public static void listCustomersMostRecentPurchases(String id){

        String sqlid = "'"+id+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);


            String selectQuery2 = "SELECT * FROM CUSTOMERS WHERE CID = " + sqlid;
            ResultSet rs2 = statement.executeQuery(selectQuery2);

            //If the given customer id is not in the system
            int count = 0;
            while(rs2.next()){
                count++;
            }
            if(count == 0){
                System.out.println("There is no any customer with given id.");
                rs2.next();
                closeConnection();
                statement.close();
                return;
            }

            String selectQuery = "SELECT * FROM PURCHASES NATURAL JOIN CUSTOMERS NATURAL JOIN BRANCHES NATURAL JOIN PRODUCTS WHERE CİD = " + sqlid;
            ResultSet rs = statement.executeQuery(selectQuery);


            int iter = 5;
            int counter = 0;
            rs.afterLast();
            System.out.println("purchaseid:cid:cname:bid:bname:pid:pname:description:pquantity:totprice:orderdate");
            System.out.println();

            while (iter > 0 && rs.previous()) {
                int quantity = Integer.parseInt(rs.getString("PQUANTITY"));
                double price = Double.parseDouble(rs.getString("PRICE"));
                double totPrice = quantity * price;
                String sqltotPrice = String.valueOf(totPrice);
                System.out.println(rs.getString("PURCHASEID") + " : " +rs.getString("CID") + " : " + rs.getString("CNAME") + " : " + rs.getString("BID") + " : "  + rs.getString("BNAME") + " : " + rs.getString("PID") + " : " + rs.getString("PNAME") + " : " + rs.getString("DESCRIPTION") +  " : " + rs.getString("PQUANTITY") + " : " + sqltotPrice + " : " + rs.getString("ORDERDATE"));                iter--;
                counter++;
            }

            //If the customers purchase history is empty
            if(counter == 0) {
                System.out.println("The customer does not have any purchase history.");
            }

            rs.close();
            rs2.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     //(6) List branches
     public static void listBranches(){

        try {
            establishConnection();
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT * FROM BRANCHES NATURAL JOIN PRODUCTS NATURAL JOIN STOCKS";
            ResultSet rs = statement.executeQuery(selectQuery);

            System.out.println("Branches");
            System.out.println("¯¯¯¯¯¯¯¯");
            System.out.println("bid:bname:baddress:pid:pname:description:price:squantity");
            System.out.println();

            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getString("BID") + " : " + rs.getString("BNAME") + " : " + rs.getString("BADDRESS") + " : " + rs.getString("PID") + " : " + rs.getString("PNAME") + " : " + rs.getString("DESCRIPTION" ) + " : " + rs.getString("PRICE") + " : " + rs.getString("SQUANTITY"));
                count++;
            }

            //If the branches table is empty
            if(count == 0){
                System.out.println("There is no any branch in the system.");
            }

            rs.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(7) List a branch’s stock
    public static void branchStock(String bid){

        String sqlbid = "'"+bid+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();


            String selectQuery2 = "SELECT * FROM BRANCHES WHERE BID =" + sqlbid;
            ResultSet rs2 = statement.executeQuery(selectQuery2);

            //If the given branch id is not in the system
            int counter = 0;
            while(rs2.next()){
                counter++;
            }

            if(counter == 0){
                System.out.println("There is no any branch with given branch id.");
                rs2.close();
                closeConnection();
                statement.close();
                return;
            }


            System.out.println("Stock information of the given branch");
            System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
            System.out.println("pid:pname:description:price:squantity");
            System.out.println();

            String selectQuery = "SELECT * FROM STOCKS NATURAL JOIN PRODUCTS WHERE BID =" + sqlbid;
            ResultSet rs = statement.executeQuery(selectQuery);
            //If the branch stock is empty
            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getString("PID") + " : " + rs.getString("PNAME") + " : " + rs.getString("DESCRIPTION") + " : " + rs.getString("PRICE") + " : " + rs.getString("SQUANTITY"));
                count++;
            }
            if(count == 0){
                System.out.println("There is no any product in the given branch stock.");
            }

            rs.close();
            rs2.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(8) Add new branch store
    public static void addNewBranchStore(String name , String address){

        String sqlname = "'"+name+"'";
        String sqladdress = "'"+address+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO BRANCHES(BNAME,BADDRESS) VALUES("+ sqlname + ", "+ sqladdress +")";
            statement.executeUpdate(insertQuery);

            System.out.println("Branch is added.");
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(9) Add new product
    public static void addNewProduct(String name, String description, String price){

        String sqlname = "'"+name+"'";
        String sqldescription = "'"+description+"'";
        String sqlprice = "'"+price+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO PRODUCTS(PNAME,DESCRIPTION,PRICE) VALUES("+ sqlname + ", "+sqldescription +", "+ sqlprice +")";
            statement.executeUpdate(insertQuery);

            System.out.println("Product is added.");
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(10) Add product to branch’s stock
    public static void addProductToBranchStock(String bid, String pid, String quantity){

        String sqlbid = "'"+bid+"'";
        String sqlpid = "'"+pid+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();


            String selectQuery2 = "SELECT * FROM BRANCHES WHERE BID = " + sqlbid;
            ResultSet rs2 = statement.executeQuery(selectQuery2);


            //If the given bid is not in the system
            int count = 0;
            while(rs2.next()){
                count++;
            }
            if(count == 0){
                System.out.println();
                System.out.println("Product is not added to given branch!");
                System.out.println("Given branch id is not in the system.");

                rs2.close();
                closeConnection();
                statement.close();
                return;
            }

            String selectQuery3 = "SELECT * FROM PRODUCTS WHERE PID = " + sqlpid;
            ResultSet rs3 = statement.executeQuery(selectQuery3);

            //If the given pid is not in the system
            int count3 = 0;
            while(rs3.next()){
                count3++;
            }
            if(count3 == 0){
                System.out.println();
                System.out.println("Product is not added to given branch!");
                System.out.println("Given product id is not in the system.");

                rs3.close();
                closeConnection();
                statement.close();
                return;
            }

            String selectQuery = "SELECT * FROM STOCKS WHERE BID = " + sqlbid + " AND PID = " + sqlpid;
            ResultSet rs = statement.executeQuery(selectQuery);

            //If the branch has already that product, it increases the quantity of the given product for the given branch
            int count2 = 0;
            while(rs.next()){
                if(rs.getString("BID").equals(bid) && rs.getString("PID").equals(pid)){
                    String updateQuery = "UPDATE STOCKS SET SQUANTITY = SQUANTITY + " + quantity + " WHERE BID = " + bid + " AND PID = " + pid ;
                    statement.executeUpdate(updateQuery);
                    System.out.println("Product quantity is incremented to given branch.");
                    count2++;
                    break;
                }
            }

            //If the given product is new for the given branch, it inserts the product into given branch stocks
            if(count2 == 0){
                String insertQuery = "INSERT INTO STOCKS(BID,PID,SQUANTITY) VALUES("+ sqlbid + ", "+ sqlpid +", "+ quantity +")";
                statement.executeUpdate(insertQuery);
                System.out.println("Product is added to given branch.");
            }

            rs.close();
            rs2.close();
            rs3.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(11) Search customer by their phone number
    public static void searchCustomerByPhone(String phoneNum){

        String sqlphoneNum = "'"+phoneNum+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT * FROM CUSTOMERS WHERE PHONENUMBER =" + sqlphoneNum;
            ResultSet rs = statement.executeQuery(selectQuery);

            int counter = 0;
            System.out.println("cid:cname:surname:caddress:phonenumber");
            System.out.println();

            //If the given phonenumber is not in the system
            while (rs.next()) {
                System.out.println(rs.getString("CID") + " : " + rs.getString("CNAME") + " : " + rs.getString("SURNAME") + " : " + rs.getString("CADDRESS")+ " : " + rs.getString("PHONENUMBER"));
                counter++;
            }
            if(counter == 0){
                System.out.println("There is no any customer with given phone number.");
            }

            rs.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //(12) Remove a user from system
    public static void deleteCustomer(String id){

        String sqlid = "'"+id+"'";

        try {
            establishConnection();
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT CID FROM CUSTOMERS WHERE CID = " + sqlid ;
            ResultSet rs = statement.executeQuery(selectQuery);

            int count = 0;

            while (rs.next()){
                count++;
            }

            //If customer id is not in the system
            if(count == 0){
                System.out.println("There is no customer with the given customer id.");
                rs.close();
                closeConnection();
                statement.close();
                return;
            }

            String deleteQuery = "DELETE FROM PURCHASES WHERE CID = " + sqlid;
            String deleteQuery2 = "DELETE FROM CUSTOMERS WHERE CID = " + sqlid;
            statement.executeUpdate(deleteQuery);
            statement.executeUpdate(deleteQuery2);

            System.out.println();
            System.out.println("Given customer is deleted from the system.");
            rs.close();
            closeConnection();
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
