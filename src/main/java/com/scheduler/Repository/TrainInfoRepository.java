package com.scheduler.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduler.entity.Train;

public interface TrainInfoRepository extends JpaRepository<Train, Long>{

	@Query(value="select t.* from train t except (select t.* from train t join seat s on s.seat_no=t.seats and s.section=t.section) limit 1",nativeQuery = true)
	Train fetchAvailableSeats();
}
