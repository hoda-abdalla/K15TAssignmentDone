package com.k15t.pat.captcha;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.k15t.pat.captcha.util.RecaptchaUtil;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class RecaptchaService {
	 
	  @Value("${google.recaptcha.secret}") 
	 private  String recaptchaSecret;
	  
	   
	  private static final String GOOGLE_RECAPTCHA_VERIFY_URL =
	    "https://www.google.com/recaptcha/api/siteverify";
	   
	  //@Autowired
	  //RestTemplate restTemplateBuilder;
	  
	 
	  public String verifyRecaptcha(String ip, 
	    String recaptchaResponse){
		  
	    Map<String, String> body = new HashMap<String, String>();
	    RestTemplate restTemplate = new RestTemplate();
	    body.put("secret", recaptchaSecret);
	    body.put("response", recaptchaResponse);
	    body.put("remoteip", ip); 
	    HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(body);
	    Map responseBody = restTemplate.postForObject(GOOGLE_RECAPTCHA_VERIFY_URL+
		          "?secret={secret}&response={response}&remoteip={remoteip}", request, Map.class);
	    
	    log.debug("Request body for recaptcha: {}", body);
	             
	    log.debug("Response from recaptcha: {}",  responseBody);
	       
	    boolean recaptchaSucess = (Boolean)responseBody.get("success");
	    if ( !recaptchaSucess) {
	      List<String> errorCodes =  (List)responseBody.get("error-codes");
	       
	      String errorMessage = errorCodes.stream()
	          .map(s -> RecaptchaUtil.RECAPTCHA_ERROR_CODE.get(s))
	          .collect(Collectors.joining(", "));
	           
	      return errorMessage;
	    }else {
	      return StringUtils.EMPTY;
	    }
	  }
	 }
