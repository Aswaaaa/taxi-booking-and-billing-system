package com.edstem.taxibookingandbillingsystem.repository;

import com.edstem.taxibookingandbillingsystem.model.Taxi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepository extends JpaRepository<Taxi, Long> {
    List<Taxi> findByCurrentLocation(String pickupLocation);

    //    Taxi findByDriversName(TaxiRequest taxiRequest);

}
