package com.k15t.pat.registration.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.k15t.pat.registration.RegisterationModel;

@Service
public interface RegistrationRepository extends CrudRepository<RegisterationModel, Long> {


}
