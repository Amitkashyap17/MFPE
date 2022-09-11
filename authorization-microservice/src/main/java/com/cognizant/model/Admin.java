package com.cognizant.model;

import javax.persistence.*;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="Admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column
	private String userName;

	@Column
	private String password;

}
