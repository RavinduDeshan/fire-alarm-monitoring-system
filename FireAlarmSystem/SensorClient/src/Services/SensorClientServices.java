/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Sensor;
import java.sql.SQLException;

/**
 *
 * @author GRP
 */
public interface SensorClientServices {
    
    
    public boolean checkStatusInActive(String ID)throws SQLException;
    
    public boolean upadteStateActive(Sensor sensor)throws SQLException;
    
    public boolean upadteStateInActive(Sensor sensor)throws SQLException;
    
    public boolean upadteSensor(Sensor sensor,String CO2,String Smoke)throws SQLException;
    
    public Sensor getSensor(String Id) throws Exception;
    
    public boolean checkTimerInActive(String ID) throws SQLException;
        
   
    
}
