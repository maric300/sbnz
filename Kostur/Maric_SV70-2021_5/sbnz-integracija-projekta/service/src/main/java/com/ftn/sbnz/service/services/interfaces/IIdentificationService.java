package com.ftn.sbnz.service.services.interfaces;

import com.ftn.sbnz.model.models.IdentificationResult;
import com.ftn.sbnz.model.models.Mineral;
import com.ftn.sbnz.model.models.Sample;

import java.util.List;

public interface IIdentificationService {
    List<IdentificationResult> identifyMineral(Sample sample);

    List<Mineral> getMineralDatabase();
}
