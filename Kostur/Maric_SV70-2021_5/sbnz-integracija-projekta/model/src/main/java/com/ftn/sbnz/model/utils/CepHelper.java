package com.ftn.sbnz.model.utils;

import com.ftn.sbnz.model.events.PotentialCorrectionEvent;
import com.ftn.sbnz.model.events.PotentialDiscoveryEvent;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static boolean isSeasonalSpike(Map<String, Object> analysisData) {
        if (analysisData == null || analysisData.isEmpty()) {
            return false;
        }

        // Uslov 1: Obrazac se ponovio najmanje dve uzastopne godine.
        Set<Integer> years = (Set<Integer>) analysisData.get("years");
        if (years == null || years.size() < 2) {
            return false;
        }

        // Uslov 2: Mora postojati bar neki prosek van sezone za poređenje.
        Double offPeakAvg = (Double) analysisData.get("offPeakAvg");
        if (offPeakAvg == null || offPeakAvg <= 0) {
            return false;
        }

        // Uslov 3: Prosečan broj pronalazaka u sezoni mora biti 250% veći.
        Double peakAvg = (Double) analysisData.get("peakAvg");
        if (peakAvg == null || peakAvg <= (offPeakAvg * 2.5)) {
            return false;
        }

        return true;
    }
}