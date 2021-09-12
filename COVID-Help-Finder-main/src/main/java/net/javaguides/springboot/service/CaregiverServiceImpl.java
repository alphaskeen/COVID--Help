package net.javaguides.springboot.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Caregiver;
import net.javaguides.springboot.repository.CaregiverRepository;

@Service
public class CaregiverServiceImpl implements CaregiverService {

	@Autowired
	private CaregiverRepository caregiverRepository;

	@Override
	public List<Caregiver> getAllCaregivers() {
		return caregiverRepository.findAll();
	}

	@Override
	public void saveCaregiver(Caregiver patient) {
		this.caregiverRepository.save(patient);
	}

	@Override
	public Caregiver getCaregiverById(long id) {
		Optional<Caregiver> optional = caregiverRepository.findById(id);
		Caregiver caregiver = null;
		if (optional.isPresent()) {
			caregiver = optional.get();
		} else {
			throw new RuntimeException(" Caregiver not found for id :: " + id);
		}
		return caregiver;
	}

	@Override
	public void deleteCaregiverById(long id) {
		this.caregiverRepository.deleteById(id);
	}

	@Override
	public Page<Caregiver> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.caregiverRepository.findAll(pageable);
	}
}



