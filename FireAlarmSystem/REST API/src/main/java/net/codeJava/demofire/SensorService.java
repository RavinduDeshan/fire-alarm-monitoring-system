package net.codeJava.demofire;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {
	
	@Autowired
	private SensorRepository repo;
	
	public List<Sensor> listAll(){
		return repo.findAll();
	}
	
	public void save(Sensor sensor) {
		repo.save(sensor);
	}
	
	public Sensor get(Integer id) {
		return repo.findById(id).get();
		
	}

	public void delete(Integer id){
		repo.deleteById(id);
	}
}
