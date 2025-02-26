package com.gridnine.testing.FlightFilterRules;

import com.gridnine.testing.TestClasses.Flight;

import java.util.List;

public interface RuleArrivalSegmentsBeforeDeparture {
    List<Flight> excludeSegmentsThatHaveArrivalBeforeDeparture(List<Flight> flightsList);
}
