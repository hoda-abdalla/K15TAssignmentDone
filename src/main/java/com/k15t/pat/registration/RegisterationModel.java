package com.k15t.pat.registration;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "REGISTRATION")
public class RegisterationModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7603691335920724501L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull(message = "Name cannot be null")
	@Pattern(regexp="[a-zA-Z]{3,20}")
	@Column(unique=true)
	private String name;
	
	@Pattern(regexp="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")
	@Column(unique=true)
	private String email;
	
	@NotNull(message = "Name cannot be null")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	private String password;
	
	@Pattern(regexp="^[0-9\\-\\+\\s\\(\\)]*$")
	private String phoneNumber;
	
	private String address;

}
