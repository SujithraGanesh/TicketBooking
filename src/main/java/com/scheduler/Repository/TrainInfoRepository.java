package com.scheduler.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.entity.Train;

public interface TrainInfoRepository extends JpaRepository<Train, Long>{

	@Query(value="select t.* from train t where t.seats not in (select seat_no from seat) limit 1",nativeQuery = true)
	Train fetchAvailableSeats();
}
