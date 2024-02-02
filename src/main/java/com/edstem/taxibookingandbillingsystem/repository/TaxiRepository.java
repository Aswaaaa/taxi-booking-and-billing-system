package com.edstem.taxibookingandbillingsystem.repository;

import com.edstem.taxibookingandbillingsystem.contract.request.TaxiRequest;
import com.edstem.taxibookingandbillingsystem.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxiRepository extends JpaRepository<Taxi,Long> {
    List<Taxi> findByCurrentLocation(String pickupLocation);

//    Taxi findByDriversName(TaxiRequest taxiRequest);

}
