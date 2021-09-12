package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import net.javaguides.springboot.model.Caregiver;

public interface CaregiverService {
	List<Caregiver> getAllCaregivers();
	void saveCaregiver(Caregiver caregiver);
	Caregiver getCaregiverById(long id);
	void deleteCaregiverById(long id);
	Page<Caregiver> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}

