package com.gridnine.testing.FlightFilterRules;

import com.gridnine.testing.TestClasses.Flight;

import java.util.List;

public interface RuleDepartureBeforeCurrentMoment {
    List<Flight> excludeDepartureBeforeCurrentMoment(List<Flight> flightsList);
}