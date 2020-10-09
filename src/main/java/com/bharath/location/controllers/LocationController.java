package com.bharath.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.location.entities.Location;
import com.bharath.location.repos.LocationRepository;
import com.bharath.location.service.LocationService;
import com.bharath.location.util.EmailUtil;
import com.bharath.location.util.ReportUtil;

@Controller
public class LocationController {
	
	@Autowired
	private LocationService locationService; 
	
	@Autowired
	private LocationRepository repository;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext sc;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}
	
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location savedLocation = locationService.saveLocation(location);
		String msg = "Location is saved. Id is " + savedLocation.getId();
		modelMap.addAttribute("msg", msg);
//		emailUtil.send("davidwei01@gmail.com", "Location is saved", "Location is successfullt saved and return to create location page");
		return "createLocation";
	}
	
	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}
	
	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = new Location();
		location.setId(id);
		locationService.deleteLocation(location);
		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}
	
	@RequestMapping("/showUpdate")
	public String editLocation(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = locationService.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "editLocation";
	}
	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		locationService.updateLocation(location);
		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		String path = sc.getRealPath("/");
		List<Object[]> data = repository.findTypeAndTypeCount();
		reportUtil.generatePieChart(path, data);
		return "report";
	}

}
