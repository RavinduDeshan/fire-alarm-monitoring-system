package net.codeJava.demofire;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class SensorController {
	
	
	//SMS Sender Properties

	String FROMNO="+12025195518";
	String TONO="+94714009185";
	
	String ACCOUNT_SID="ACf3aab5d2d322defcc4bc9b9882bae765";
	String AUTH_TOKEN="77c0878816f91875bf18d1b6da6e09b6";
	
	
	
	
	
	//EMAIL Reciever Properties
	final String EMAILRECIEVER="grpreceiver@gmail.com";
	String EMAILBODY;
	
	//array list to hold alert triggered sensors IDs
	ArrayList<Integer> temp =new ArrayList<Integer>();
	
	//holds the status of if a given id is in the "temp" array or not
	boolean stat=false;
	
	
          
	@Autowired
	private SensorService service;
	
	@GetMapping("/api/fireAlarmSystem/sensors")
	public List<Sensor> list() {
		return service.listAll();
		
	}
	
	@GetMapping("/api/fireAlarmSystem/sensors/{id}")
	public ResponseEntity<Sensor> get(@PathVariable Integer id) {
	try {
		Sensor sensor = service.get(id);
		return new ResponseEntity<Sensor>(sensor, HttpStatus.OK);
		
	}catch (NoSuchElementException e) {
		return new ResponseEntity<Sensor>(HttpStatus.NOT_FOUND);
		
		}
	}
	
	
	
	@PostMapping("/api/fireAlarmSystem/Add")
	public void add(@RequestBody Sensor sensor) {
		
		System.out.println("Sensor Recievedis :" + sensor);
		service.save(sensor);
	}
	
	
	// @PutMapping("/api/fireAlarmSystem/Update/{id}")
	// public ResponseEntity<?> update(@RequestBody Sensor sensor,@PathVariable Integer id) {
		
	// 	try {
			
			
			
	// 	//get sensor by ID	
	// 	Sensor existSensor = service.get(id);
		
	// 	//update Sensor
	// 	service.save(sensor);
		
	// 	//set initial status true		
	// 	stat=true;
		
		
	// 	//Check if the received sensor is a alerted(Smoke level or CO2 level >5) sensor
	// 	if(sensor.getCo2_level()>5 || sensor.getSmoke_level()>5) {
			
	// 		//check if temp array is empty
	// 			if (temp.isEmpty()) {

	// 				// if temp array is empty add the first sensor to temp array
	// 				temp.add(sensor.getId());
					
	// 			//Sms Message
	// 			String MESSAGE="Fire Alert Detected on the room no "+sensor.getLocation_roomNo() +"of the floor no " +  sensor.getLocation_floorNo()+ ". Detected CO2 Level is "+ sensor.getCo2_level()+" and Smoke level is "+sensor.getSmoke_level()+". Details are detected by Sensor ID "+ sensor.getId();
									
				
	// 			//Authorized Message API Account
	// 			 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
				
	// 			//Send SMS

	// 			 Message message = Message.creator(new PhoneNumber(TONO),new PhoneNumber(FROMNO), MESSAGE).create();

	// 			 //print message ID
	// 			 System.out.println(message.getSid());					
					
					

					
	// 				//Email Body
	// 				EMAILBODY="<h1 style=\"color:red;\">Fire Alert Detected</h1><h2>Sensor ID : "+sensor.getId().toString()+"<br> Floor No : "+ sensor.getLocation_floorNo()+"<br> Room No : "+ sensor.getLocation_roomNo()+"</h2><h2 style=\"color:red;\"> CO2 Level : "+sensor.getCo2_level()+"<br>Smoke Level : "+sensor.getSmoke_level()+"</h2>";
	// 				// send email
	// 				new EmailGenerator(EMAILRECIEVER, "Fire Alert", EMAILBODY);
	// 			} 
				
	// 			else {
					

	// 				for (int i : temp) {

						
	// 					if (sensor.getId() == i) {
	// 						stat = false;

	// 					}
						

	// 				}
					
					
					
	// 				if (stat) {
	// 					temp.add(sensor.getId());
					
	// 				// send SMS
	// 					String MESSAGE = "Fire Alert Detected on the room no " + sensor.getLocation_roomNo()
	// 							+ "of the floor no " + sensor.getLocation_floorNo() + ". Detected CO2 Level is "
	// 							+ sensor.getCo2_level() + " and Smoke level is " + sensor.getSmoke_level()
	// 							+ ". Details are detected by Sensor ID " + sensor.getId();					

	// 				//Authorized Message API Account
	// 				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	// 				//Send SMS
	// 				Message message = Message.creator(new PhoneNumber(TONO), new PhoneNumber(FROMNO), MESSAGE).create();

	// 				//Print Message ID
	// 				System.out.println(message.getSid());
	
						
						
						
	// 					//Email Body
	// 					EMAILBODY="<h1 style=\"color:red;\">Fire Alert Detected</h1><h2>Sensor ID : "+sensor.getId().toString()+"<br> Floor No : "+ sensor.getLocation_floorNo()+"<br> Room No : "+ sensor.getLocation_roomNo()+"</h2><h2 style=\"color:red;\"> CO2 Level : "+sensor.getCo2_level()+"<br>Smoke Level : "+sensor.getSmoke_level()+"</h2>";
						
	// 					// send email
	// 					new EmailGenerator(EMAILRECIEVER, "Fire Alert", EMAILBODY);
						

						
	// 				}

	// 			}
	// 		}
		
	// 	return new ResponseEntity<>(HttpStatus.OK);
		
		
	// }catch (NoSuchElementException e) {
		
	// 	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	// 	}
	// }
	
	
	@DeleteMapping("/api/fireAlarmSystem/Delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
