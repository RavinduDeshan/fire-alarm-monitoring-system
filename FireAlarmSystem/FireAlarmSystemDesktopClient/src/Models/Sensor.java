/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
/**
 *
 * @author GRP
 */
public class Sensor implements Serializable{
    
    
    
    private String id;
    private String location_floorNo;
    private String location_roomNo;
    private String co2_level;
    private String smoke_level;
    private String status;

    public Sensor(String id, String location_floorNo, String location_roomNo, String status) {
        this.id = id;
        this.location_floorNo = location_floorNo;
        this.location_roomNo = location_roomNo;
        this.status = status;
    }
    
    public Sensor(String location_floorNo, String location_roomNo, String status) {
        
        this.location_floorNo = location_floorNo;
        this.location_roomNo = location_roomNo;
        this.status = status;
    }

    public Sensor(String id, String location_floorNo, String location_roomNo, String co2_level, String smoke, String status) {
        this.id = id;
        this.location_floorNo = location_floorNo;
        this.location_roomNo = location_roomNo;
        this.co2_level = co2_level;
        this.smoke_level = smoke;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation_floorNo() {
        return location_floorNo;
    }

    public void setLocation_floorNo(String location_floorNo) {
        this.location_floorNo = location_floorNo;
    }

    public String getLocation_roomNo() {
        return location_roomNo;
    }

    public void setLocation_roomNo(String location_roomNo) {
        this.location_roomNo = location_roomNo;
    }

    public String getCo2_level() {
        return co2_level;
    }

    public void setCo2_level(String co2_level) {
        this.co2_level = co2_level;
    }

    public String getSmoke_level() {
        return smoke_level;
    }

    public void setSmoke_level(String smoke_level) {
        this.smoke_level = smoke_level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    

   
    
    
    
    
    
    
    
}


