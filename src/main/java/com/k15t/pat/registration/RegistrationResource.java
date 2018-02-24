package com.k15t.pat.registration;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.k15t.pat.registration.data.repository.RegistrationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/registration")
@Component
public class RegistrationResource {
	
	@Autowired
	private RegistrationRepository registrationRepo; 
	
 
    // Extend the current resource to receive and store the data in memory.
    // Return a success information to the user including the entered information.
    // In case of the address split the information into a better format/structure
    // for better handling later on.
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({MediaType.APPLICATION_JSON})
    public Response save( RegisterationModel rBean) {
    	log.info(rBean.toString());
    	
    	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    	Set<ConstraintViolation<RegisterationModel>> validations = validator.validate( rBean);
    	if (!validations.isEmpty()) {    		
			String message = validations.stream()
					.map(input -> input.getPropertyPath() + ": " + input.getMessage() + ", ")
					.reduce("", String::concat);
			log.info(message);
			
			return Response.status(Response.Status.BAD_REQUEST).entity(message).type(MediaType.TEXT_PLAIN)
					.build();
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(rBean.getPassword());
    	rBean.setPassword(hashedPassword);

    	
    	registrationRepo.save(rBean);
    	log.info("saved successfully id :"+String.valueOf(rBean.getId()));
        return Response.ok().build();
    }
}
