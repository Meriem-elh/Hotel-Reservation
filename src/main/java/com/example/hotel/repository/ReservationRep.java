package com.example.hotel.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel.entity.Employee;
import com.example.hotel.entity.Reservation;

@Repository
public interface ReservationRep extends JpaRepository<Reservation, Long>{
	void deleteById(Long id);
	void deleteByUsername(String username);
		public Reservation findByUsername(String username);
		
		List<Reservation> findAll();



}
