package com.multiplex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiplex.dtos.BookingDTO;
import com.multiplex.models.Booking;
import com.multiplex.repos.BookingRepository;

@Service
public class BookingService {

	@Autowired private BookingRepository repo;
	@Autowired private ShowsService sservice;
	@Autowired private SeatTypeService stservice;
	@Autowired private UserService uservice;
	
	public void save(BookingDTO dto) {
		Booking bk=new Booking();
		bk.setShowDate(dto.getBookDate());
		bk.setNoOfSeats(dto.getNoOfSeats());
		bk.setCost(dto.getCost());
		bk.setShow(sservice.findById(dto.getShowId()));
		bk.setSeatType(stservice.findById(dto.getSeatTypeId()));
		bk.setUser(uservice.findById(dto.getUserId()));
		repo.save(bk);
	}
	
	public List<Booking> listall(){
		return repo.findAll();
	}
	
	public List<Booking> alluserbooking(int id){
		return repo.findByUser(uservice.findById(id));
	}
	
	public Booking findById(int id) {
		return repo.getById(id);
	}
	
	public void delete(int id) {
		Booking bk=repo.getById(id);
		bk.setStatus("Cancelled");
		bk.setCancelCharges((float) (bk.getCost()*.20));
		bk.setCost(0);
		repo.save(bk);
	}
}
