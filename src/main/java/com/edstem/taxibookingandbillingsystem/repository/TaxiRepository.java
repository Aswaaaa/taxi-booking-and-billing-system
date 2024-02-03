package com.edstem.taxibookingandbillingsystem.repository;

import com.edstem.taxibookingandbillingsystem.model.Taxi;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {
    List<Taxi> findByCurrentLocation(String pickupLocation);
}
