package com.gridnine.testing.FlightFilterRulesImplementation;
import com.gridnine.testing.TestClasses.Flight;
import com.gridnine.testing.TestClasses.Segment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.AfterEach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@DisplayName("Проверка трёх правил исключения рейсов")
public class FlightFilterRulesImplementationTest {

    DepartureBeforeCurrentMoment departureBeforeCurrentMomentTest = new DepartureBeforeCurrentMoment();
    ArrivalSegmentsBeforeDeparture arrivalSegmentsBeforeDepartureTest = new ArrivalSegmentsBeforeDeparture();
    GroundedTimeMoreThanTwoHours groundedTimeMoreThanTwoHoursTest = new GroundedTimeMoreThanTwoHours();

    LocalDateTime timeNow = LocalDateTime.now();

    //Сегменты двух рейсов для метода excludeDepartureBeforeCurrentMoment
    Segment segmentOne = new Segment(timeNow.minusHours(5), timeNow.plusDays(3));
    Segment segmentTwo = new Segment(timeNow.plusHours(5), timeNow.plusDays(3));

    //Сегмент рейса для метода excludeSegmentsThatHaveArrivalBeforeDeparture
    Segment segmentThree = new Segment(timeNow, timeNow.minusHours(5));

    // Сегменты рейса для метода excludeGroundedTimeMoreThanTwoHours
    Segment segmentFourFirst = new Segment(timeNow, timeNow.plusHours(3));
    Segment segmentFourSecond = new Segment(timeNow.plusHours(4), timeNow.plusHours(8));
    Segment segmentFiveFirst = new Segment(timeNow, timeNow.plusHours(3));
    Segment segmentFiveSecond = new Segment(timeNow.plusHours(8), timeNow.plusHours(12));


    // Два рейса для проверки метода excludeDepartureBeforeCurrentMoment,
    //      исключающего рейс с вылетом до текущего момента;
    //          flightOne - вылет был 5 часов назад
    //          flightTwo - вылет будет через 5 часов
    Flight flightOne = new Flight(Collections.singletonList(segmentOne));
    Flight flightTwo = new Flight(Collections.singletonList(segmentTwo));

    // Рейс для проверки метода excludeSegmentsThatHaveArrivalBeforeDeparture,
    //      исключающего рейс, где прилёт раньше вылета
    //          flightOne - вылет был 5 часов назад, прилёт через 3 дня
    //          flightThree - вылет сейчас, прилёт был 5 часов назад
    Flight flightThree = new Flight(Collections.singletonList(segmentThree));

    // Два рейса для проверки метода excludeGroundedTimeMoreThanTwoHours,
    //      исключающего рейс, где время на земле дольше двух часов
    //          flightFour - время на земле меньше двух часов
    //          flightFive - время на земле больше двух часов
    Flight flightFour = new Flight(Arrays.asList(segmentFourFirst, segmentFourSecond));
    Flight flightFive = new Flight(Arrays.asList(segmentFiveFirst, segmentFiveSecond));


    List<Flight> expected = new ArrayList<>();
    List<Flight> actual = new ArrayList<>();


    @DisplayName("Проверка метода для исключения рейсов с вылетом до текущего момента (" + "excludeDepartureBeforeCurrentMoment)")
    @Test
    public void departureBeforeCurrentMomentTest() {

        expected.add(flightTwo);
        actual.add(flightOne);
        actual.add(flightTwo);

        List<Flight> actualResult = departureBeforeCurrentMomentTest
                .excludeDepartureBeforeCurrentMoment(actual);

        assertEquals(expected, actualResult);
        assertEquals(1, actualResult.size());

        System.out.println(actualResult); // чек
    }

    @DisplayName("Проверка метода для исключения рейсов с вылетом раньше прилёта (" + "excludeSegmentsThatHaveArrivalBeforeDeparture)")
    @Test
    public void arrivalSegmentsBeforeDepartureTest() {
        expected.add(flightOne);
        actual.add(flightOne);
        actual.add(flightThree);

        List<Flight> actualResult = arrivalSegmentsBeforeDepartureTest
                .excludeSegmentsThatHaveArrivalBeforeDeparture(actual);

        assertEquals(expected, actualResult);
        System.out.println(actualResult);
    }

    @DisplayName("Проверка метода для исключения рейсов, где время на земле меньше двух часов (" + "excludeGroundedTimeMoreThanTwoHours)")
    @Test
    public void groundedTimeMoreThanTwoHoursTest() {

        expected.add(flightFour);
        actual.add(flightFour);
        actual.add(flightFive);

        List<Flight> actualResult = groundedTimeMoreThanTwoHoursTest
                .excludeGroundedTimeMoreThanTwoHours(actual);

        assertEquals(expected, actualResult);
        System.out.println(actualResult);
    }

    @AfterEach
    void clearExpectedAndActual() {
        expected.clear();
        actual.clear();
    }
}




