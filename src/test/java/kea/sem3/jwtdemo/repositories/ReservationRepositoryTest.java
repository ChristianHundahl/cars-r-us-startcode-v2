package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.entity.Reservation;
import kea.sem3.jwtdemo.entity.Role;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationRepositoryTest {

  @Autowired
  CarRepository carRepository;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  ReservationRepository reservationRepository;

  //Store som id's for the test methods
  static Car carRav4,carV40;
  static LocalDate reservationDate = LocalDate.of(2022, 3, 1);
  static Member m1;


  @BeforeAll
  static void setUp(@Autowired CarRepository carRepository,
                    @Autowired MemberRepository memberRepository,
                    @Autowired ReservationRepository reservationRepository) {
    reservationRepository.deleteAll();
    carRepository.deleteAll();
    memberRepository.deleteAll();
    carRav4 = carRepository.save(new Car("Toyota","RAV4",900,10));
    carV40 = carRepository.save(new Car("Volvo","V40",700,10));
    //Two Cars with no reservations
    carRepository.save(new Car("Suzuki", "Vitara", 500, 14));
    carRepository.save(new Car("Suzuki", "Vitara", 500, 14));

    m1 = memberRepository.save(new Member("kw", "kw@a.dk", "test12", "Kurt", "Wonnegut", "Lyngbyvej 34", "Lyngby", "2800"));
    reservationRepository.save(new Reservation(reservationDate, carRav4, m1));
    reservationRepository.save(new Reservation(reservationDate, carV40, m1));
    reservationRepository.save(new Reservation(LocalDate.of(2022,3,2), carRav4, m1));
  }
  @AfterAll
  static void tearDown(@Autowired CarRepository carRepository,
                    @Autowired MemberRepository memberRepository,
                    @Autowired ReservationRepository reservationRepository) {
    reservationRepository.deleteAll();
    carRepository.deleteAll();
    memberRepository.deleteAll();

  }

  @Test
  public void aTest(){
    assertTrue(true);
  }

  @Test
  public void testFindReservedCarByIdAndReservationDate(){
    Reservation reservation = reservationRepository.findReservationByReservedCar_IdAndRentalDate(carRav4.getId(), reservationDate);
    assertEquals(carRav4.getId(), reservation.getReservedCar().getId());
    assertEquals(m1.getUsername(), reservation.getReservedTo().getUsername());

    assertNotNull(reservation);
  }

  @Test
  public void testFindNonReservedCars(){
    List<Car> cars = reservationRepository.getCarsWithNoReservations();
    assertEquals(2, cars.size());
  }
}