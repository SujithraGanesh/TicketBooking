package com.scheduler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scheduler.Repository.SeatBookingRepo;
import com.scheduler.Repository.TrainInfoRepository;
import com.scheduler.Repository.TravelInfoRepository;
import com.scheduler.Repository.UserRepository;
import com.scheduler.entity.ReceiptResponse;
import com.scheduler.entity.SeatInfo;
import com.scheduler.entity.Train;
import com.scheduler.entity.TravelInfo;
import com.scheduler.exception.DetailsNotFoundException;
import com.scheduler.model.bookTicketResponse;

@Service
public class TrainService {

	@Autowired
	TravelInfoRepository receiptRepo;
	
	@Autowired
	SeatBookingRepo bookingRepo;
	
	@Autowired
	TrainInfoRepository trainInfoRepo;
	
	@Autowired
	SeatBookingRepo seatBookingRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public TravelInfo getReceiptInfo(long userId) throws Exception{
		try {
			TravelInfo travelInfo=  receiptRepo.findByUserId(userId);
			validate(travelInfo);
			return travelInfo;
		}catch (DetailsNotFoundException e) {
			throw e;
		}
		catch(Exception e) {
			throw e;
		}
		
	}
	
	private void validate(TravelInfo travelInfo) throws Exception {
		if(travelInfo==null) {
			throw new DetailsNotFoundException("Traveller not booked the ticket yet..");
		}
	}

	public bookTicketResponse bookTicket(TravelInfo userAndStationDetails) throws Exception {
		try {
		Train trainDet = new Train();
		trainDet = trainInfoRepo.fetchAvailableSeats();
		if(trainDet==null) {
			throw new DetailsNotFoundException("Sorry,Train tickets are not available");
		}
		
		TravelInfo bookingDetails = receiptRepo.save(userAndStationDetails);
		SeatInfo info = new SeatInfo();
		info.setUserId(userAndStationDetails.getUser().getId());
		info.setSeatNo(trainDet.getSeats());
		info.setSection(trainDet.getSection());
		bookingRepo.save(info);
		bookTicketResponse resposne = new bookTicketResponse();
		resposne.setFromStation(bookingDetails.getFromStation());
		resposne.setToStation(bookingDetails.getToStation());
		resposne.setPrice(bookingDetails.getPrice());
		resposne.setFirstName(bookingDetails.getUser().getFirstName());
		resposne.setTravellerId(bookingDetails.getUser().getId());
		return resposne;
		}catch(DetailsNotFoundException e) {
			throw e;
		}
		catch(Exception e){
			throw new Exception("Please try again later/Contact tech support");
		}
	}

	public SeatInfo getSeatInfo(Long userId) throws Exception {
		try {
			SeatInfo seats = seatBookingRepo.findSeatByUser(userId);
			
		if(seats==null) {
			throw new DetailsNotFoundException("No Ticket booked for the user");
		}
		return seats;
		}catch(Exception e){
			throw e;
		}
	}
	
	@Transactional
	public void removeTraveller(Integer userId) throws Exception {
		try {
		TravelInfo travelInfo=  receiptRepo.findByUserId(userId);
		SeatInfo seats = seatBookingRepo.findSeatByUser(userId);
		receiptRepo.delete(travelInfo);
		bookingRepo.delete(seats);
		userRepo.deleteById(userId);
		}catch(Exception e) {
			throw new Exception("Some technical error occured while removing the user");
		}
		
	}
	
	
	public SeatInfo ModifyReservation(Long userId) throws Exception {
		try {
		Train trainDet = new Train();
		trainDet = trainInfoRepo.fetchAvailableSeats();
		if(trainDet==null) {
			throw new DetailsNotFoundException("Sorry,Alternative seats are notr available in the Train");
		}
		
		SeatInfo info = bookingRepo.findSeatByUser(userId);
		if(info==null) {
			throw new DetailsNotFoundException("Cannot alter seats since ticket not booked yet");
		}
		info.setSeatNo(trainDet.getSeats());
		info.setSection(trainDet.getSection());
		info = bookingRepo.save(info);
		return info;
		}catch(DetailsNotFoundException e) {
			throw e;
		}
		catch(Exception e){
			throw new Exception("Please try again later/Contact tech support");
		}
	}
}
