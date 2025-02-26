package com.gridnine.testing.FlightFilterRulesImplementation;

import com.gridnine.testing.TestClasses.Flight;
import com.gridnine.testing.FlightFilterRules.RuleGroundedTimeMoreThanTwoHours;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


public class GroundedTimeMoreThanTwoHours implements RuleGroundedTimeMoreThanTwoHours {

    @Override
    public List<Flight> excludeGroundedTimeMoreThanTwoHours(List<Flight> flightList) {
        List<Flight> filteredFlights = flightList.stream().filter(flight -> flight.getSegments().size() >= 2)
                .filter(flights -> {
                    long hourCounter = 0;
                    for (int i = 0; i < flights.getSegments().size() - 1; i++) {
                        hourCounter = flights.getSegments().get(i).getArrivalDate().until(flights.getSegments().get(i + 1).getDepartureDate(), ChronoUnit.HOURS);
                    }
                    return hourCounter < 2;
                })
                .collect(Collectors.toList());
        return filteredFlights;
    }
}
