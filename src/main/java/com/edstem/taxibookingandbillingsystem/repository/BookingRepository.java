package com.edstem.taxibookingandbillingsystem.repository;

import com.edstem.taxibookingandbillingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {

}