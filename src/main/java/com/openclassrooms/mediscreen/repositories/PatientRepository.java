package com.openclassrooms.mediscreen.repositories;

import com.openclassrooms.mediscreen.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    @Query("select p from Patient p where p.firstName like :kw")
    List<Patient> searchPatient(@Param("kw") String keyword);
}
