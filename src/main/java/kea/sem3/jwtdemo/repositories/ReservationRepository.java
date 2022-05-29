package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    //Reservation findReservationByReservedCar_IdAndRentalDate(int id, LocalDate date);
    //add a method declaration to the ReservationRepository that, given ID for a car and a LocalDate will allow
    //us to find a reservation, if created for the given car and date
    //Write corresponding tests to verify the behaviour both for an existing Reservation and for a "free" date
    Reservation findReservationByReservedCar_IdAndRentalDate(int id, LocalDate date);//Match id and LocalDateTime from Reservation.java

    @Query("select c from Car c where c.reservations.size=0")//Asserts Car c number of reservations = 0
    List<Car> getCarsWithNoReservations();
}