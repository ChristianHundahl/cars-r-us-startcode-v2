package kea.sem3.jwtdemo.configuration;

import kea.sem3.jwtdemo.entity.*;
import kea.sem3.jwtdemo.repositories.CarRepository;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import kea.sem3.jwtdemo.repositories.ReservationRepository;
import kea.sem3.jwtdemo.security.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.Month;

@Controller
@Profile("!test")
public class MakeTestData implements ApplicationRunner {


    UserRepository userRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;
    ReservationRepository reservationRepository;

    public MakeTestData(UserRepository userRepository, MemberRepository memberRepository, CarRepository carRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.memberRepository = memberRepository;
        this.carRepository = carRepository;
        this.reservationRepository = reservationRepository;
    }

  public void makePlainUsers() {
    BaseUser user = new BaseUser("user", "user@a.dk", "test12");
    user.addRole(Role.USER);
    BaseUser admin = new BaseUser("admin", "admin@a.dk", "test12");
    admin.addRole(Role.ADMIN);
    BaseUser both = new BaseUser("user_admin", "both@a.dk", "test12");
    both.addRole(Role.USER);
    both.addRole(Role.ADMIN);

    userRepository.save(user);
    userRepository.save(admin);
    userRepository.save(both);

    Member m1 = memberRepository.save(new Member("kw", "kw@a.dk", "test12", "Kurt", "Wonnegut", "Lyngbyvje 34", "Lyngby", "2800", Role.USER));
    Member m2 = memberRepository.save(new Member("hw", "hw@a.dk", "test12", "Hanne", "Wonnegut", "Lyngbyvje 34", "Lyngby", "2800", Role.USER));
    Member m3 = memberRepository.save(new Member("jj", "jj@a.com", "test12", "John", "Johnson", "Nørrebrogade 34,2", "Nørrebro", "2800", Role.USER));
    Member m4 = memberRepository.save(new Member("janne", "janne@a.dk", "test12", "Janne", "Peterson", "Vedersøvej 34 34", "Vanløse", "2720", Role.USER));
    Member m5 = memberRepository.save(new Member("Sophi", "sophi@a.dk", "test12", "Sophia", "Johnson", "Vedersøvej 32", "Vanløse", "2720", Role.USER));
    Member m6 = memberRepository.save(new Member("mia", "mia@a.dk", "test12", "Mia", "Hansen 134", "Rødovrevej 123", "Rødovre", "2610", Role.USER));

    Car carVolvo = carRepository.save(new Car("Volvo", "C40", 560, 10));
    Car carV70 = carRepository.save(new Car("Volvo", "V70", 500, 10));
    carRepository.save(new Car("Volvo", "V49", 400, 10));
    Car vitara1 = carRepository.save(new Car("Suzuki", "Vitara", 500, 14));
    carRepository.save(new Car("Suzuki", "Vitara", 500, 14));
    carRepository.save(new Car("Suzuki", "S-Cross", 500, 14));
    Car xceed = carRepository.save(new Car("Kia", "XCeed", 500, 18));
    Car proCeed = carRepository.save(new Car("Kia", "ProCeed", 700, 16));
    Car modelT = carRepository.save(new Car("Ford", "Model T", 700, 16));

    //Create a Reservation
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 1), carVolvo, m1));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 2), carVolvo, m1));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 3), carVolvo, m1));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 4), carVolvo, m1));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 5), carVolvo, m1));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 4), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 5), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 4), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 5), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 4), vitara1, m3));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 3, 5), vitara1, m3));

    reservationRepository.save(new Reservation(LocalDate.of(2022, 6, 15), proCeed, m5));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 6, 16), proCeed, m5));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 6, 17), proCeed, m5));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 6, 18), proCeed, m5));


    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 6), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 7), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 8), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 9), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 10), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 11), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 12), carV70, m2));
    reservationRepository.save(new Reservation(LocalDate.of(2022, 5, 13), carV70, m2));


    System.out.println(carVolvo.getReservations().size());

    System.out.println("########################################################################################");
    System.out.println("########################################################################################");
    System.out.println("#################################### WARNING ! #########################################");
    System.out.println("## This part breaks a fundamental security rule -> NEVER ship code with default users ##");
    System.out.println("########################################################################################");
    System.out.println("########################  REMOVE BEFORE DEPLOYMENT  ####################################");
    System.out.println("########################################################################################");
    System.out.println("########################################################################################");
    System.out.println("Created TEST Users");

  }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        userRepository.deleteAll();

        makePlainUsers();


    }
}
