package com.ftn.sbnz.model.utils;

import com.ftn.sbnz.model.events.PotentialCorrectionEvent;
import com.ftn.sbnz.model.events.PotentialDiscoveryEvent;
import java.util.List;

public class CepHelper {


    public static boolean hasEnoughUsersForHotspot(List<PotentialDiscoveryEvent> events) {
        if (events == null || events.isEmpty()) {
            return false;
        }
        return events.stream()
                .map(PotentialDiscoveryEvent::getUserId)
                .distinct()
                .count() >= 3;
    }


    public static boolean hasEnoughUsersForCorrection(List<PotentialCorrectionEvent> events) {
        if (events == null || events.isEmpty()) {
            return false;
        }
        return events.stream()
                .map(PotentialCorrectionEvent::getUserId)
                .distinct()
                .count() >= 5;
    }
}