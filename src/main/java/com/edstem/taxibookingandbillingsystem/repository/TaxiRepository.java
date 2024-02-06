package com.edstem.taxibookingandbillingsystem.repository;

import com.edstem.taxibookingandbillingsystem.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi, Long> {

}
