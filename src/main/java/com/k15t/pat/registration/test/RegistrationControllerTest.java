package com.k15t.pat.registration.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.k15t.pat.ApplicationBootstrap;
import com.k15t.pat.registration.RegistrationResource;
import com.k15t.pat.registration.data.repository.RegistrationRepository;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationBootstrap.class,initializers = ConfigFileApplicationContextInitializer.class)

@WebIntegrationTest
public class RegistrationControllerTest {

//	@Autowired
//	private RegistrationRepository registrationRepo; 
//	@Autowired
//	private RegistrationResource registrationResource;
	private TestRestTemplate template;
	
	@Before
	public void setUp() {
		template = new TestRestTemplate("sa", "");
	}

	@Test
	public void testSave() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		
		Map<String, String> body = new HashMap<>();
		body.put("email", "hoda@hoda.com");
		body.put("name", "hoda");
		body.put("address", "13355 Berlin Am See Strasse");
		body.put("phoneNumber", "01005624932");
		
		
		HttpEntity<Map<String,String>> httpEntity = new HttpEntity<Map<String,String>>(body,headers);
		
		ResponseEntity<Map> responseEntity = template
		.postForEntity("http://localhost:8080/rest/registration",
				httpEntity, Map.class, 1);
		
	} 
	

}
