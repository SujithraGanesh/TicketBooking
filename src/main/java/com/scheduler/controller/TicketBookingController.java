package com.scheduler.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.entity.ReceiptResponse;
import com.scheduler.entity.SeatInfo;
import com.scheduler.entity.TravelInfo;
import com.scheduler.exception.DetailsNotFoundException;
import com.scheduler.model.ErrorDetails;
import com.scheduler.model.bookTicketResponse;
import com.scheduler.service.TrainService;

@RestController
@RequestMapping("/api/v1")
public class TicketBookingController {

	@Autowired
	TrainService service;
	
	 @GetMapping("/users/{id}")
	    public ResponseEntity <TravelInfo> getUserReceipt(@PathVariable(value = "id") Long userId) throws Exception 
	    {
		 TravelInfo recResponse;
		try {
			recResponse = service.getReceiptInfo(userId);
			return ResponseEntity.ok().body(recResponse);
		} catch (DetailsNotFoundException e) {
			throw new DetailsNotFoundException(e.getMessage());
		}
	    }
	 
	 @PostMapping("/bookTicket")
	    public bookTicketResponse bookTicket(@Validated @RequestBody TravelInfo userAndStationDetails) throws Exception {
		 try {
	        return service.bookTicket(userAndStationDetails);
		 } catch (DetailsNotFoundException e) {
				throw new DetailsNotFoundException(e.getMessage());
			}catch(Exception e) {
				throw new Exception(e.getMessage());
			}
	    }
	 
	 @GetMapping("/seats/{id}")
	    public ResponseEntity <SeatInfo> getSeatInfo(@PathVariable(value = "id") Long userId) throws Exception 
	    {
		 SeatInfo recResponse;
		try {
			recResponse = service.getSeatInfo(userId);
			return ResponseEntity.ok().body(recResponse);
		} catch (DetailsNotFoundException e) {
			throw new DetailsNotFoundException(e.getMessage());
		}
	    }
	 
	 @DeleteMapping("/{id}")
	 public String removeTraveller(@PathVariable(value = "id") Integer userId) throws Exception 
	 {
		 try {
			 service.removeTraveller(userId);
			 return "Removed Sucessfully";
		 }catch (Exception e) {
				throw new Exception(e.getMessage());
			}
	 }
	 
	 
	 @PutMapping("/modifySeats/{id}")
	    public ResponseEntity <SeatInfo> modifySeatInfo(@PathVariable(value = "id") Long userId) throws Exception 
	    {
		 SeatInfo recResponse;
		try {
			recResponse = service.ModifyReservation(userId);
			return ResponseEntity.ok().body(recResponse);
		} catch (DetailsNotFoundException e) {
			throw new DetailsNotFoundException(e.getMessage());
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	    }
}
