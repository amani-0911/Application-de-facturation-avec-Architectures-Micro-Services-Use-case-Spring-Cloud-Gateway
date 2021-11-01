package org.sid.billingservice.repository;

import org.sid.billingservice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoceRepository extends JpaRepository<Invoice,String> {
    List<Invoice> findBycustomerId(String clientId);
}
