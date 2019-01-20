package com.pharma.demo.Repository;

import com.pharma.demo.Entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    public Producer findByName(String name);
}
