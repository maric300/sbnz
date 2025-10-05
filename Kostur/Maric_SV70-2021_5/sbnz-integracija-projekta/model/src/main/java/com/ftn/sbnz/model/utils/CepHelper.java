package com.ftn.sbnz.model.utils;

import com.ftn.sbnz.model.events.PotentialCorrectionEvent;
import com.ftn.sbnz.model.events.PotentialDiscoveryEvent;

import java.util.List;

public class CepHelper {

    public static boolean isValidHotspot(List<PotentialDiscoveryEvent> events) {
        if (events == null || events.isEmpty()) return false;
        if (events.stream().map(PotentialDiscoveryEvent::getMineralId).distinct().count() != 1) return false;
        if (events.stream().map(PotentialDiscoveryEvent::getLocation).distinct().count() != 1) return false;

        // Ova linija sada ispravno radi sa UUID-jevima
        return events.stream().map(PotentialDiscoveryEvent::getUserId).distinct().count() >= 3;
    }

    public static boolean isValidCorrectionPattern(List<PotentialCorrectionEvent> events) {
        if (events == null || events.isEmpty()) return false;
        if (events.stream().map(e -> e.getMineralId().toString() + e.getLocation() + e.getAttributeName() + e.getReportedValue()).distinct().count() != 1) return false;

        // Ova linija sada ispravno radi sa UUID-jevima
        return events.stream().map(PotentialCorrectionEvent::getUserId).distinct().count() >= 5;
    }
}