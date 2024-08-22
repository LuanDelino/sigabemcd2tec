package com.cd2tec.demo.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cd2tec.demo.domain.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {}
