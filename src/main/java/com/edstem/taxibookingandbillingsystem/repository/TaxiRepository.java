package com.edstem.taxibookingandbillingsystem.repository;

import com.edstem.taxibookingandbillingsystem.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepository extends JpaRepository<Taxi,Long> {
}
