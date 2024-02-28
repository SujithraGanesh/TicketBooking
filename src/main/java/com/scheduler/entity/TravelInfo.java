package com.scheduler.entity;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "travel_details")
public class TravelInfo {
 
	private long id;
    private String fromStation;
    private String toStation;
    private User user;
    private long price;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name = "from_stn", nullable = false)
	public String getFromStation() {
		return fromStation;
	}
    
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	
	@Column(name = "to_stn", nullable = false)
	public String getToStation() {
		return toStation;
	}
	
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "traveller_id")
  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
    
	
}
