package com.gridnine.testing.FlightFilterRules;

import com.gridnine.testing.TestClasses.Flight;

import java.util.List;

public interface RuleGroundedTimeMoreThanTwoHours {
    public List<Flight> excludeGroundedTimeMoreThanTwoHours(List<Flight> flightList);
}
