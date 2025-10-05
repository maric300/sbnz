package com.ftn.sbnz.service.repositories;

import com.ftn.sbnz.model.models.SeasonalTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ISeasonalTipRepository extends JpaRepository<SeasonalTip, UUID> {
    boolean existsByMineralIdAndLocation(UUID mineralId, String location);
}