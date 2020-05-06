/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMIServer;

import Models.Sensor;
import Models.User;
import java.rmi.Remote;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author GRP
 */
public interface Service extends Remote{
    
    User getUser(String id) throws Exception ;
    
    
    
    boolean addSensor(Sensor sensor) throws Exception;
    
    boolean updateSensor(Sensor cus)throws Exception;
    
    boolean deleteSensor(String Id)throws Exception;
    
    Sensor getSensor(String Id)throws Exception;
    
    ArrayList<Sensor> getSensorList()throws Exception;
    
    ArrayList<Sensor> AlertSensorList()throws Exception;
    
   
    
    
    

    
    
}
