package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Caregiver;
@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, Long >{

}
