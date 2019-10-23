package com.springlearn.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springlearn.location.entities.Location;
import com.springlearn.location.repos.LocationRepository;
import com.springlearn.location.service.LocationService;
import com.springlearn.location.util.EmailUtil;
import com.springlearn.location.util.ReportUtil;

@Controller
public class LocationController {

	@Autowired
	LocationService service;
	
	@Autowired
	LocationRepository repository;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	ReportUtil reportUtil;
	
	@Autowired
	ServletContext sc;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}

	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location savedLocation = service.saveLocation(location);
		String msg = "Location saved with id: " + savedLocation.getId();
		modelMap.addAttribute("msg", msg);
		//Send an email after creating a location
		emailUtil.sendEmail("aleandi077@gmail.com", "Created a new location",
				"Location details: \n" + savedLocation.toString());
		return "createLocation";
	}

	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = service.getAllLocations();
//		System.out.println(locations);
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	@RequestMapping("/deleteLocation")
	public String delLocation(@RequestParam("id") int id, ModelMap modelMap) {
		Location loc = new Location();
		loc.setId(id);
		service.deleteLocation(loc);
//		service.deleteLocation(service.getLocationById(id));

		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);

		return "displayLocations";
	}

	@RequestMapping("/showEdit")
	public String showUpdate(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = service.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "editLocation";
	}

	@RequestMapping("/editLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		service.updateLocation(location);

		List<Location> locations = service.getAllLocations();
		modelMap.addAttribute("locations", locations);

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
