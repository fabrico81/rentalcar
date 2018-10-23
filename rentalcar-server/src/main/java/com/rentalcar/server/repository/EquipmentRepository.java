package com.rentalcar.server.repository;

import com.rentalcar.server.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author faber
 */

public interface EquipmentRepository extends JpaRepository<Equipment,Integer> {
}
