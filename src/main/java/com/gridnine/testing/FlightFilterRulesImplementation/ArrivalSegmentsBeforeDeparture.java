package com.gridnine.testing.FlightFilterRulesImplementation;

import com.gridnine.testing.TestClasses.Flight;
import com.gridnine.testing.FlightFilterRules.RuleArrivalSegmentsBeforeDeparture;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalSegmentsBeforeDeparture implements RuleArrivalSegmentsBeforeDeparture {

    @Override
    public List<Flight> excludeSegmentsThatHaveArrivalBeforeDeparture(List<Flight> flightsList) {
        List<Flight> filteredFlights = flightsList.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
        return filteredFlights;
    }
}
