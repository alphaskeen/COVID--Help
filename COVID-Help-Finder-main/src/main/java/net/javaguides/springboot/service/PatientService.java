package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Patient;

public interface PatientService {
	List<Patient> getAllPatients();
	void savePatient(Patient patient);
	Patient getPatientById(long id);
	void deletePatientById(long id);
	Page<Patient> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

