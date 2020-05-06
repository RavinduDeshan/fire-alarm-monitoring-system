package net.codeJava.demofire;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensor")
public class Sensor implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id")
	private Integer id;
	
	@Column(name = "location_floorNo")
	private Integer location_floorNo;
	
	@Column(name = "location_roomNo")
	private Integer location_roomNo;
	
	@Column(name = "co2_level")
	private Integer co2_level;
	
	@Column(name = "smoke_level")
	private Integer smoke_level;
	
	@Column(name = "status")
	private String  status;

	public Sensor() {
		super();
		
	}

	public Sensor(Integer id, Integer location_floorNo, Integer location_roomNo, Integer co2_level, Integer smoke_level,
			String status) {
		
		this.id = id;
		this.location_floorNo = location_floorNo;
		this.location_roomNo = location_roomNo;
		this.co2_level = co2_level;
		this.smoke_level = smoke_level;
		this.status = status;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLocation_floorNo() {
		return location_floorNo;
	}

	public void setLocation_floorNo(Integer location_floorNo) {
		this.location_floorNo = location_floorNo;
	}

	public Integer getLocation_roomNo() {
		return location_roomNo;
	}

	public void setLocation_roomNo(Integer location_roomNo) {
		this.location_roomNo = location_roomNo;
	}

	public Integer getCo2_level() {
		return co2_level;
	}

	public void setCo2_level(Integer co2_level) {
		this.co2_level = co2_level;
	}

	public Integer getSmoke_level() {
		return smoke_level;
	}

	public void setSmoke_level(Integer smoke_level) {
		this.smoke_level = smoke_level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
