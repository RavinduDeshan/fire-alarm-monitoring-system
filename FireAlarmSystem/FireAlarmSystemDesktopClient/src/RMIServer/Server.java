/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIServer;

import firealarmsystem.ClientLogin;
import DB.dbConnection;
import Models.Sensor;
import Models.User;
import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author GRP
 */
public class Server extends UnicastRemoteObject implements Service {

    //Declare a Timer Oject
    Timer timer;
    //Declare HTTP URL Connectio object
    HttpURLConnection conn;
    //Declare an Object of authenticator class object
    Authenticator auth;
    //Create connection class by retrieving connection from dbConnection class object
    Connection con = dbConnection.getConnection();
    //REGISTRY PORT NUMBER
    final static int REGISTRY=1209;

    
    
    
    //Constructor
    public Server() throws RemoteException, SQLException {

        super();
        
        
        //CREATE USER TABLE in database if not Exist
        createUserTable();

    }

    /**
     * @param args the command line arguments
     */
    
    // Main Method of the Server
    public static void main(String[] args) {
        // TODO code application logic here

        
        //set Security Policy
        System.setProperty("java.security.policy", "file:allowall.policy");

        try {

            //Create a Server Object
            Server svr = new Server();
            
            //create Registry
            Registry registry = LocateRegistry.createRegistry(REGISTRY);
            
            //Bind the Server to the Registry
            registry.bind("SensorServer", svr);
            
            System.out.println("Service Started.....!");
            
            

        //Catch Exceptions 
        } catch (RemoteException re) {
            System.err.println(re.getMessage());
        } catch (AlreadyBoundException abe) {
            System.err.println(abe.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    
    
    @Override
    //get the user/admin details directly from database
    public User getUser(String id) throws Exception {

        //create a user object
        User user = null;

        try {

            //Sql query to take a user from ID from user table
            String sql = "SELECT * from user where userId ='" + id + "'";

            // create statement object
            Statement stm = con.createStatement();

            //set results of the sql queries to a result set object
            ResultSet rlt = stm.executeQuery(sql);

            //read each result in result set
            while (rlt.next()) {

                //get vlues from the one object of the result set
                String ID = rlt.getString("userId");
                String name = rlt.getString("name");
                String password = rlt.getString("password");

                
                //Create new user from retrieved data
                user = new User(ID, name, password);

            }

            //catch any SQL and other exceptions
        } catch (Exception ex) {
            Logger.getLogger(ClientLogin.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error is in SQL");
        }

        //return a user object
        return user;

    }

   

    @Override
    //add sensors to the database through REST API
    public boolean addSensor(Sensor sensor) throws SQLException {

        //hold the success of failure of the method execution; true if success, false if fails
        boolean stat = false;

        try {

            //Set initial CO2 level of the the new sensor to 0
            sensor.setCo2_level("0");
             //Set initial Smoke level of the the new sensor to 0
            sensor.setSmoke_level("0");
            
            
            //Create new object mapper Class
            ObjectMapper mapper = new ObjectMapper();
            
            //Set values of the Sensor Object in to a JSON String
            String jsonString = mapper.writeValueAsString(sensor);

            //Create URL object , targeting REST APIs Endpoint to Add Sensors
            URL url = new URL("http://localhost:8080/api/fireAlarmSystem/Add");
            
            // Create a HTTPURL Connection object and open a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // Set request method into POST
            conn.setRequestMethod("POST");
            
            // Set the Request Content Property
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            // Set the Request Property to accept JSON responses
            conn.setRequestProperty("Accept", "application/json");
            
            // Set a output stream
            conn.setDoOutput(true);
            
            // Creating the Data in the request body and writing it to output stream
            String data = jsonString;
            
            //Get Output Stream into an OutputStreamObject
            OutputStream stream = conn.getOutputStream();
            
            //Set get data format
            byte[] input = data.getBytes("utf-8");
            
            //Write inputs into OutputStream
            stream.write(input, 0, input.length);

            // Get response code
            int responseCode = conn.getResponseCode();
            
            // Read the response
            Reader reader = null;
            
            // check ResponseCode
            if (responseCode >= 200 && responseCode <= 299) {

               //read Buffer Data
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                //Set method execution success 
                stat = true;
                
            } else {
                
                //read Buffer Data
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
               

                //Set method execution Fail 
                stat = false;
            }

           

        //catch Exceptions
        } catch (IOException ex) {

            System.out.println("Json build error");
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        // return Method success state
        return stat;

    }

    @Override
    //Update Sensor Through REST API
    public boolean updateSensor(Sensor sensor) throws SQLException {
        
        //hold the success of failure of the method execution; true if success, false if fails
        boolean stat = false;

        try {
           
            //Hold Sensor ID
            String id = sensor.getId();
            
             //Create new object mapper Class
            ObjectMapper mapper = new ObjectMapper();
            
            //Set values of the Sensor Object in to a JSON String
            String jsonString = mapper.writeValueAsString(sensor);

           //Create URL object , targeting REST APIs Endpoint to Update Sensors
            URL url = new URL("http://localhost:8080/api/fireAlarmSystem/Update/" + id);
            // Create a HTTPURL Connection object and open a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // Set request method into PUT
            conn.setRequestMethod("PUT");
            
            // Set the Request Content Property
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            // Set the Request Property to accept JSON responses
            conn.setRequestProperty("Accept", "application/json");
            
            // Set a output stream
            conn.setDoOutput(true);
            
            // Creating the Data in the request body and writing it to output stream
            String data = jsonString;
            
            //Get Output Stream into an OutputStreamObject
            OutputStream stream = conn.getOutputStream();
            
            //Set get data format
            byte[] input = data.getBytes("utf-8");
            
            //Write inputs into OutputStream
            stream.write(input, 0, input.length);

            // Get response code
            int responseCode = conn.getResponseCode();
            
            // Read the response
            Reader reader = null;
            
            // check ResponseCode
            if (responseCode >= 200 && responseCode <= 299) {

               //read Buffer Data
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                //Set method execution success 
                stat = true;
                
            } else {
                
                //read Buffer Data
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
               

                //Set method execution Fail 
                stat = false;
            }

        //catch Exceptions
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        // return Method success state
        return stat;

    }

    @Override
    //Delete Sensor Through REST API
    public boolean deleteSensor(String Id) throws SQLException {

        try {
          
            //Create URL object , targeting REST APIs Endpoint to Delete Sensors
            URL url = new URL("http://localhost:8080/api/fireAlarmSystem/Delete/" + Id);

           // Create a HTTPURL Connection object and open a connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            // Set request method into DELETE
            conn.setRequestMethod("DELETE");
            
            // Set the Request Content Property
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            // Set the Request Property to accept JSON responses
            conn.setRequestProperty("Accept", "application/json");
            
            // Set a output stream
            conn.setDoOutput(true);

            //Get the Responsecode
            int responseCode = conn.getResponseCode();
            // Read the response
            Reader reader = null;
            // check ResponseCode
            if (responseCode >= 200 && responseCode <= 299) {
                
                //read Buffer Data
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            } else {
                
                //read Buffer Data
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
            }

        //Check Exceptions
        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

    @Override
    //Get Sensor Through REST API
    public Sensor getSensor(String Id) throws Exception {

        //Sensor Object
        Sensor sensor = null;

        //Create URL object , targeting REST APIs Endpoint to Get Sensor BY ID
        URL url = new URL("http://localhost:8080/api/fireAlarmSystem/sensors/" + Id);
        
        // Create a HTTPURL Connection object and open a connection
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
         // Set request method into GET
        con.setRequestMethod("GET");

        // Set the Request  Property to accept JSON
        con.setRequestProperty("Accept", "application/json");

        //Get the Responsecode
        int responseCode = con.getResponseCode();

        // Read the response
        Reader reader = null;

        //Check Responses
        if (responseCode >= 200 && responseCode <= 299) {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        } else {
            reader = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
        }

        //Create GSON Object
        Gson gson = new Gson();
        try {
            
            //Create Sensor object from JSON Response
            sensor = gson.fromJson(reader, Sensor.class);

        } catch (Exception e) {
            System.out.println(e);
        }

        // check if sensor is null
        if (sensor != null) {

            //if not null return sensor
            return sensor;
        } else {
            return null;
        }

    }

    @Override
    //Get All Sensors  Through REST API
    public ArrayList<Sensor> getSensorList() throws SQLException, IOException {

        //ArrayList
        ArrayList<Sensor> list = new ArrayList<>();

        //Create URL object , targeting REST APIs Endpoint to Get Sensors
        URL url = new URL("http://localhost:8080/api/fireAlarmSystem/sensors");
        // Create a HTTPURL Connection object and open a connection
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
         // Set request method into GET
        con.setRequestMethod("GET");

        // Set the Request  Property to accept JSON
        con.setRequestProperty("Accept", "application/json");

        //Get the Responsecode
        int responseCode = con.getResponseCode();

        // Read the response
        Reader reader = null;
        
        //Check Responses
        if (responseCode >= 200 && responseCode <= 299) {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        } else {
            reader = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
        }

        //Create GSON Object
        Gson gson = new Gson();
        try {
            
            //Create Sensor object Array from JSON Response
            Sensor[] temp = gson.fromJson(reader, Sensor[].class);

            for (int i = 0; i < temp.length; i++) {
                
                //add to ArrayList
                list.add(temp[i]);
            }

        //catch Exceptions
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //Return ArrayLists
        return list;

    }

    @Override
    //get Alert Triggered all Sensors Through REST API
    public ArrayList<Sensor> AlertSensorList() throws SQLException {

        //SEnsor object
        Sensor sensor = null;

        //ArrayList
        ArrayList<Sensor> list = new ArrayList<>();

        try {


            //Create URL object , targeting REST APIs Endpoint to Get Sensors
            URL url = new URL("http://localhost:8080/api/fireAlarmSystem/sensors");
            
            // Create a HTTPURL Connection object and open a connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
             // Set request method into GET
            con.setRequestMethod("GET");
            
            // Set the Request  Property to accept JSON
            con.setRequestProperty("Accept", "application/json");

            //Get the Responsecode
            int responseCode = con.getResponseCode();

            // Read the response
            Reader reader = null;
            
            
            //Check Responses
            if (responseCode >= 200 && responseCode <= 299) {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            } else {
                reader = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
            }

            //Create GSON Object
            Gson gson = new Gson();
            try {
                
                //Create Sensor object Array from JSON Response
                Sensor[] temp = gson.fromJson(reader, Sensor[].class);

                for (int i = 0; i < temp.length; i++) {

                    //Check if Sensor is Alerted
                    if (Integer.parseInt(temp[i].getCo2_level()) > 5 || Integer.parseInt(temp[i].getSmoke_level()) > 5) {
                        
                        //add to Arraylist
                        list.add(temp[i]);
                    }
                }

        //Catch Exceptions
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Return ArrayList
        return list;
    }

   

   

    //Create table USER and Add Data if not Exist
    private void createUserTable() throws SQLException {

        //Table Properties
        String TABLENAME = "user";
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + TABLENAME
                + "  (userId      INTEGER,"
                + "   Name     VARCHAR(50),"
                + "   Password VARCHAR(20))";

        //Get Database Connection
        Statement stmt = con.createStatement();
        
        //Execute Query
        stmt.execute(sqlCreate);

        String sqlIsEmpty = "SELECT * from " + TABLENAME;
        ResultSet rs = stmt.executeQuery(sqlIsEmpty);

        // checking if ResultSet is empty
        if (rs.next() == false) {

            //if ReultSet is Empty Insert Data to Table
            String sql = "INSERT INTO user VALUES (?,?,?)";

            //Get Database Connection
            PreparedStatement stm1 = con.prepareStatement(sql);

            int ID = 123;
            String NAME = "Steve";
            String PASS = "123";

            stm1.setObject(1, ID);
            stm1.setObject(2, NAME);
            stm1.setObject(3, PASS);

            //Execute Query
            boolean createStat = stm1.executeUpdate() > 0;

        }

    }
}
