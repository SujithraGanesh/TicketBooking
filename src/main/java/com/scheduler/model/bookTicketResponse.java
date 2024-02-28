package com.scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class bookTicketResponse {

	 private String fromStation;
	 private String toStation;
	 private long price;
	 private String firstName;
	    private Integer TravellerId;
		public String getFromStation() {
			return fromStation;
		}
		public void setFromStation(String fromStation) {
			this.fromStation = fromStation;
		}
		public String getToStation() {
			return toStation;
		}
		public void setToStation(String toStation) {
			this.toStation = toStation;
		}
		public long getPrice() {
			return price;
		}
		public void setPrice(long price) {
			this.price = price;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public Integer getTravellerId() {
			return TravellerId;
		}
		public void setTravellerId(Integer travellerId) {
			TravellerId = travellerId;
		}
	    
	    
}
