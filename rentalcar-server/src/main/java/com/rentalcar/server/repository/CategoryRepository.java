package com.rentalcar.server.repository;

import com.rentalcar.server.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author faber
 */

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
