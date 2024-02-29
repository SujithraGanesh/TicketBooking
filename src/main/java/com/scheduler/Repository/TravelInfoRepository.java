package com.scheduler.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.scheduler.entity.TravelInfo;

@Repository
public interface TravelInfoRepository extends JpaRepository<TravelInfo, Long> {

	@Query("select t from TravelInfo t join user u where u.id=:userId")
	TravelInfo findByUserId(@Param("userId") long userId);
	

}
