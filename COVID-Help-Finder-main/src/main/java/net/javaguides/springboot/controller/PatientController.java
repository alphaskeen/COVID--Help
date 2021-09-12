package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Patient;
import net.javaguides.springboot.model.Caregiver;

import net.javaguides.springboot.service.PatientService;
import net.javaguides.springboot.service.CaregiverService;


@Controller
public class PatientController {

	@Autowired
	private PatientService patientService;
	@Autowired
	private CaregiverService caregiverService;
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return "index";		
	}
	
	@GetMapping("/showNewPatientForm")
	public String showNewPatientForm(Model model) {
		// create model attribute to bind form data
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		return "new_patient";
	}
	@GetMapping("/showNewCaregiverForm")
	public String showNewCaregiverForm(Model model) {
		// create model attribute to bind form data
		Caregiver caregiver = new Caregiver();
		model.addAttribute("caregiver", caregiver);
		return "new_caregiver";
	}
	
	@PostMapping("/savePatient")
	public String savePatient(@ModelAttribute("patient") Patient patient) {
		// save employee to database
		patientService.savePatient(patient);
		return "redirect:/showCaregiverList";
	}
	@PostMapping("/saveCaregiver")
	public String saveCaregiver(@ModelAttribute("caregiver") Caregiver caregiver) {
		// save employee to database
		caregiverService.saveCaregiver(caregiver);
		return "redirect:/landingpage_caregiver";
	}
	@GetMapping("/landingpage_caregiver")
	public String landingpage_caregiver(Model model) {
		// save employee to database
		return "landingpage_caregiver";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Patient patient = patientService.getPatientById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("patient", patient);
		return "update_patient";
	}
	
	@GetMapping("/deletePatient/{id}")
	public String deletePatient(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.patientService.deletePatientById(id);
		return "redirect:/";
	}
	
	@GetMapping("/showCaregiverList")
	public String showCaregiverList(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Caregiver> page = caregiverService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Caregiver> listCaregiver = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCaregiver", listCaregiver);
		return "show_caregivers";
	}
}
