package com.bharath.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bharath.location.entities.Location;
import com.bharath.location.repos.LocationRepository;

@RestController
@RequestMapping("/locations")
public class LocationRESTController {
	
	@Autowired
	private LocationRepository repository;

	@GetMapping
	public List<Location> getLocations() {
		return repository.findAll();
	}
	
	@PostMapping
	public Location createLocation(@RequestBody Location location) {
		return repository.save(location);
	}
	
	@PutMapping
	public Location updateLocation(@RequestBody Location location) {
		return repository.save(location);
	}
	
	@DeleteMapping("/{id}")
	public void deleteLocation(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	@GetMapping("/{id}")
	public Location getlocation(@PathVariable int id) {
		return repository.findById(id).get();
	}
}
