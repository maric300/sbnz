package com.ftn.sbnz.service.repositories;

import com.ftn.sbnz.model.models.MineralHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMineralHierarchyRepository extends JpaRepository<MineralHierarchy, UUID> {
}