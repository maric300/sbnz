package com.ftn.sbnz.model.utils;

import com.ftn.sbnz.model.events.PotentialCorrectionEvent;
import com.ftn.sbnz.model.events.PotentialDiscoveryEvent;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CepHelper {

    public record SeasonalSpikeResult(boolean isSpike, String seasonName) {}

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

    public static SeasonalSpikeResult findSeasonalSpike(Map<String, Object> analysisData) {
        if (analysisData == null || !analysisData.containsKey("monthlyCounts") || !analysisData.containsKey("years")) {
            return new SeasonalSpikeResult(false, null);
        }

        Map<Integer, Double> monthlyCounts = (Map<Integer, Double>) analysisData.get("monthlyCounts");
        Set<Integer> years = (Set<Integer>) analysisData.get("years");

        if (years.size() < 2) {
            return new SeasonalSpikeResult(false, null);
        }

        // Računamo ukupan broj pronalazaka i ukupan broj meseci za prosek
        double totalFindings = monthlyCounts.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalMonths = 12.0 * years.size();
        if (totalFindings == 0 || totalMonths == 0) {
            return new SeasonalSpikeResult(false, null);
        }

        // Računamo ukupan mesečni prosek
        double overallAverage = totalFindings / totalMonths;
        if (overallAverage == 0) {
            return new SeasonalSpikeResult(false, null);
        }

        // Definišemo sezone i njihove proseke
        double springAvg = (monthlyCounts.get(2) + monthlyCounts.get(3) + monthlyCounts.get(4)) / (3.0 * years.size());
        double summerAvg = (monthlyCounts.get(5) + monthlyCounts.get(6) + monthlyCounts.get(7)) / (3.0 * years.size());
        double autumnAvg = (monthlyCounts.get(8) + monthlyCounts.get(9) + monthlyCounts.get(10)) / (3.0 * years.size());
        double winterAvg = (monthlyCounts.get(11) + monthlyCounts.get(0) + monthlyCounts.get(1)) / (3.0 * years.size());

        // Proveravamo da li neka sezona ima prosek 250% veći od ukupnog proseka
        if (springAvg > overallAverage * 2.5) return new SeasonalSpikeResult(true, "proleće");
        if (summerAvg > overallAverage * 2.5) return new SeasonalSpikeResult(true, "leto");
        if (autumnAvg > overallAverage * 2.5) return new SeasonalSpikeResult(true, "jesen");
        if (winterAvg > overallAverage * 2.5) return new SeasonalSpikeResult(true, "zimu");

        // Ako nijedna sezona ne odskače, vraćamo negativan rezultat.
        return new SeasonalSpikeResult(false, null);
    }
}