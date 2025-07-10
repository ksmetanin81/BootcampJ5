package com.colvir.repository;

import com.colvir.domain.PayMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayMethodRepository extends JpaRepository<PayMethod, Long> {
}
