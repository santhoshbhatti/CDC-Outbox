package com.infinite.cdcoutbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infinite.cdcoutbox.entities.Outbox;
@Repository
public interface OutboxRepository extends JpaRepository<Outbox, Long> {

}
