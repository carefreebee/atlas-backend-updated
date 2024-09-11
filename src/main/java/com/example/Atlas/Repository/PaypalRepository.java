package com.example.Atlas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Atlas.Entity.PaypalEntity;

@Repository
public interface PaypalRepository extends JpaRepository<PaypalEntity, Long> {
    Optional<PaypalEntity> findByPaymentId(String paymentId);
}
