/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.Sensor;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.Provider.Service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author GRP
 */
public class SensorClientServiceImpl implements SensorClientServices{
    
    //HTTPURL Connection Object
    HttpURLConnection conn;
    
    //Database Connection Object

  

    @Override
    //Check if Sensor is in InActive State
    public boolean checkStatusInActive(String ID) throws SQLException {
       
        //hold the status
        boolean availability =false;
        
        try {
            
            //Sensor Object
            Sensor sensor = getSensor(ID);
            
            //hold sensor initial status
            String status = sensor.getStatus();
            
            
            //check status
            if(status.equalsIgnoreCase("Inactive")){
                
                //set Sensor is open to Activate
                availability= true;
                
             //Check if sensor is already Activated   
            }else if(status.equalsIgnoreCase("active")){
                    JPanel pane5 = new JPanel();
                    JOptionPane.showMessageDialog(pane5, "Sensor Is Already Activated", "Activation Faild", JOptionPane.ERROR_MESSAGE);
                    availability= false;
            }
            
            
            
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(SensorClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            JPanel pane3 = new JPanel();
                    JOptionPane.showMessageDialog(pane3, "Sensor is Not in the System. Please Enter a Valid Sensor ID", "Activation Unsuccessfull", JOptionPane.ERROR_MESSAGE);
        }
        
        //return status
        return availability;
       
    }

    @Override
    
    //Update Status of Sensor To Active
    public boolean upadteStateActive(Sensor sensor) throws SQLException {
        
         //Method success status
         boolean stat=false;
        
        try {
           


            //Get Sensor ID
            String id = sensor.getId();
            
            //Set Status to Active
            sensor.setStatus("active");
            
            //crete object mapper
            ObjectMapper mapper = new ObjectMapper();
            
            //covert sensor object into JSON String
            String jsonString = mapper.writeValueAsString(sensor);

          
            //Connect endpoint
            URL url = new URL("http://localhost:8080/api/fireAlarmSystem/Update/"+id);
            
            //intiate connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //send put Request
            conn.setRequestMethod("PUT");
            
            // Set Request Property
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            //Set property to accept JSON
            conn.setRequestProperty("Accept", "application/json");
            
            //enable output Stream 
            conn.setDoOutput(true);

            
            String data = jsonString;
            
            //Write data in to output object
            
            OutputStream stream = conn.getOutputStream();
            byte[] input = data.getBytes("utf-8");
            stream.write(input, 0, input.length);

            //get Response Code
            int responseCode = conn.getResponseCode();

            //initate a reader
            Reader reader = null;
            
            
            //Check Responses
            if (responseCode >= 200 && responseCode <= 299) {

                
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                
                //update status state
                stat = true;
                
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                

                //update status state
                stat = false;
            }

        //catch exceptions
        } catch (MalformedURLException ex) {
            
        } catch (IOException ex) {
            
        }
        
        //return status
        return stat;
        
    }

    
    
    
    
    @Override
    public boolean upadteStateInActive(Sensor sensor) throws SQLException {
        
        //Method success status
         boolean stat=false;
        
        try {
           

            //set Properties
            String id = sensor.getId();
            sensor.setStatus("Inactive");
            sensor.setCo2_level("0");
            sensor.setSmoke_level("0");
            
            //write data to json using object mapper
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(sensor);

            System.out.println("Jason String: " + jsonString);
            
            //Connect endpoint
            URL url = new URL("http://localhost:8080/api/fireAlarmSystem/Update/"+id);
            
            //intiate connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            
            //send put PUT
            conn.setRequestMethod("PUT");

            // Set Request Property
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            //Set property to accept JSON
            conn.setRequestProperty("Accept", "application/json");

            //enable output Stream 
            conn.setDoOutput(true);

            //Write data to output objects
            String data = jsonString;
            OutputStream stream = conn.getOutputStream();
            byte[] input = data.getBytes("utf-8");
            stream.write(input, 0, input.length);


            //get Response Code
            int responseCode = conn.getResponseCode();

            //initaite reader
            Reader reader = null;

            //Check Responses
            if (responseCode >= 200 && responseCode <= 299) {

                
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                stat = true;
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                

                stat = false;
            }

        //catch exceptions
        } catch (MalformedURLException ex) {
            
        } catch (IOException ex) {
            
        }
        
        //return status
        return stat;
    }
    
    
    
     public Sensor getSensor(String Id) throws Exception {
        
        //sensor object
        Sensor sensor = null;
        
      

        //Connect endpoint
        URL url = new URL("http://localhost:8080/api/fireAlarmSystem/sensors/"+Id);
        
        //intiate connection
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        
        //send put GET
        con.setRequestMethod("GET");

        //Set property to accept JSON
        con.setRequestProperty("Accept", "application/json");


        //get Response Code
        int responseCode = con.getResponseCode();

        //initaite reader
        Reader reader = null;

        //Check Responses
        if (responseCode >= 200 && responseCode <= 299) {
            reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
        } else {
            reader = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
        }

// parsing the JSON response to a Java Object
        Gson gson = new Gson();
        try {
            sensor = gson.fromJson(reader, Sensor.class);

            
        } catch (Exception e) {
            System.out.println(e);
        }
        





	if(sensor!=null){
            
        return sensor;
        }
        else{
            return null;
        }
        
        
    }

    @Override
    public boolean upadteSensor(Sensor sensor,String CO2,String Smoke) throws SQLException {
        
        //Method success status
        boolean stat=false;
        
        try {
            
           
           

            //set Properties

            String id = sensor.getId();
            sensor.setCo2_level(CO2);
            sensor.setSmoke_level(Smoke);
            
            //write data to json using object mapper
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(sensor);

            System.out.println("Jason String: " + jsonString);

            //Connect endpoint
            URL url = new URL("http://localhost:8080/api/fireAlarmSystem/Update/"+id);
            
            //intiate connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //send put PUT
            conn.setRequestMethod("PUT");

            // Set Request Property
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            //Set property to accept JSON
            conn.setRequestProperty("Accept", "application/json");

            //enable output Stream 
            conn.setDoOutput(true);

            //write data into output object
            String data = jsonString;
            OutputStream stream = conn.getOutputStream();
            byte[] input = data.getBytes("utf-8");
            stream.write(input, 0, input.length);


            //get Response Code
            int responseCode = conn.getResponseCode();

            //initaite reader
            Reader reader = null;

            //Check Responses
            if (responseCode >= 200 && responseCode <= 299) {

                
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                stat = true;
            } else {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"));
                

                stat = false;
            }

            
        } catch (MalformedURLException ex) {
            
        } catch (IOException ex) {
            
        }
        
        //return status
        return stat;
        
    }
    
    
    
    @Override
    //Check if sensor is mannual Deactivated by Admin Panel
    public boolean checkTimerInActive(String ID) throws SQLException {
       
        //Sensor Inactivity status
        boolean availability =false;
        
        try {
            
            //Get Properties
            Sensor sensor = getSensor(ID);
            String status = sensor.getStatus();
            
            
            //check inactivity
            if(status.equalsIgnoreCase("Inactive")){
                
                availability= true;
            }else {
                    availability= false;
            }
            
            
            
            
            
         //catch exceptions   
            
        } catch (Exception ex) {
            System.out.println("Timer Active catcher occurs fails");
        }
        
        //return sensor status
        return availability;
       
    }
    
    
}
