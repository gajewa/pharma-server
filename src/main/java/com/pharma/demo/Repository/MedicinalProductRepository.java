package com.pharma.demo.Repository;

import com.pharma.demo.Entity.MedicinalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicinalProductRepository extends JpaRepository<MedicinalProduct, Long> {
}
