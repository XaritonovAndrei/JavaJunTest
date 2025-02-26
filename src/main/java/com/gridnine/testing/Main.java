package com.gridnine.testing;

import com.gridnine.testing.FlightFilterRulesImplementation.DepartureBeforeCurrentMoment;
import com.gridnine.testing.FlightFilterRulesImplementation.ArrivalSegmentsBeforeDeparture;
import com.gridnine.testing.FlightFilterRulesImplementation.GroundedTimeMoreThanTwoHours;
import com.gridnine.testing.TestClasses.Flight;
import com.gridnine.testing.TestClasses.FlightBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Список всех рейсов
        List<Flight> flightsList = FlightBuilder.createFlights();

        // Выводит в консоль список всех рейсов
        System.out.println("Полный список рейсов:");
        for (Flight element : FlightBuilder.createFlights()) {
            System.out.println(element);
        }
        System.out.println("-".repeat(100));


        LocalDateTime currentTime = LocalDateTime.now();

        DepartureBeforeCurrentMoment departureBeforeCurrentMoment = new DepartureBeforeCurrentMoment();
        ArrivalSegmentsBeforeDeparture arrivalSegmentsBeforeDeparture = new ArrivalSegmentsBeforeDeparture();
        GroundedTimeMoreThanTwoHours groundedTimeMoreThanTwoHours = new GroundedTimeMoreThanTwoHours();


        // Вызов фильтра рейсов, исключающий те, где вылет до текущего момента времени, принимает список всех рейсов
        System.out.printf("Рейсы за исключением тех, где вылет до текущего момента времени (%tT):\n", currentTime);
        for (Flight element : departureBeforeCurrentMoment.excludeDepartureBeforeCurrentMoment(flightsList)) {
            System.out.println(element);
        }
        System.out.println("-".repeat(100));


        // Вызов фильтра рейсов, исключающего те, где есть сегменты с датой прилёта раньше даты вылета
        System.out.println("Рейсы, за исключением тех, где есть сегменты с датой прилёта раньше даты вылета:");
        for (Flight element : arrivalSegmentsBeforeDeparture.excludeSegmentsThatHaveArrivalBeforeDeparture(flightsList)) {
            System.out.println(element);
        }
        System.out.println("-".repeat(100));


        // Вызов фильтра рейсов с пересадками, исключающего те, где время между сегментами больше двух часов
        System.out.println("Рейсы с пересадками не дольше двух часов:");
        for (Flight element : groundedTimeMoreThanTwoHours.excludeGroundedTimeMoreThanTwoHours(flightsList)) {
            System.out.println(element);
        }
        System.out.println("-".repeat(100));
    }
}