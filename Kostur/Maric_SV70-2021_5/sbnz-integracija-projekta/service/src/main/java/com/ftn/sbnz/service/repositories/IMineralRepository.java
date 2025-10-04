package com.ftn.sbnz.service.repositories;

import com.ftn.sbnz.model.models.Mineral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMineralRepository extends JpaRepository<Mineral, UUID> {
}