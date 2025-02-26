package com.gridnine.testing.FlightFilterRulesImplementation;

import com.gridnine.testing.TestClasses.Flight;
import com.gridnine.testing.FlightFilterRules.RuleDepartureBeforeCurrentMoment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class DepartureBeforeCurrentMoment implements RuleDepartureBeforeCurrentMoment {

    LocalDateTime currentTime = LocalDateTime.now();

    @Override
    public List<Flight> excludeDepartureBeforeCurrentMoment(List<Flight> flightsList) {
        List<Flight> filteredFlights = flightsList.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .anyMatch(segment -> segment.getDepartureDate().isAfter(currentTime)))
                .collect(Collectors.toList());
        return filteredFlights;
    }
}

