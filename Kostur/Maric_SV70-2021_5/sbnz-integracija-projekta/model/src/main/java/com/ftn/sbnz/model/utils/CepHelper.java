package com.ftn.sbnz.model.utils;

import com.ftn.sbnz.model.events.PotentialDiscoveryEvent;

import java.util.List;

public class CepHelper {

    /**
     * Proverava da li lista događaja zadovoljava uslove za novi hotspot.
     * @param events Lista događaja iz CEP prozora.
     * @return true ako su svi uslovi zadovoljeni, inače false.
     */
    public static boolean isValidHotspot(List<PotentialDiscoveryEvent> events) {
        if (events == null || events.isEmpty()) {
            return false;
        }

        // Uslov 1: Svi dogadjaji moraju biti za isti mineral
        boolean sameMineral = events.stream()
                .map(PotentialDiscoveryEvent::getMineralId)
                .distinct()
                .count() == 1;
        if (!sameMineral) return false;

        // Uslov 2: Svi dogadjaji moraju biti za istu lokaciju
        boolean sameLocation = events.stream()
                .map(PotentialDiscoveryEvent::getLocation)
                .distinct()
                .count() == 1;
        if (!sameLocation) return false;
        // Uslov 3: Dogadjaji moraju poticati od najmanje 3 različita korisnika
        boolean enoughUsers = events.stream()
                .map(PotentialDiscoveryEvent::getUserId)
                .distinct()
                .count() >= 3;

        return enoughUsers;
    }
}
