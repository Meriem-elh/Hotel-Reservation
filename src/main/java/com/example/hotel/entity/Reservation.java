package com.example.hotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "`reservation1`")
@Data
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;


	@Column(name = "price")
	private int price;

	@Column(name = "num_of_rooms")
	private int rooms;

	@Column(name = "num_of_persons")
	private int persons;

	@Column(name = "num_of_children")
	private int children;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateDebut;

	@Column(name = "reservation_username")
	private String username;


}
