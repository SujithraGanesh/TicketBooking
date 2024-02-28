package com.scheduler.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scheduler.entity.SeatInfo;

public interface SeatBookingRepo extends JpaRepository<SeatInfo, Long>{

	@Query("select t from SeatInfo t where  t.userId=:userId")
	SeatInfo findSeatByUser(@Param("userId") long userId);
}
