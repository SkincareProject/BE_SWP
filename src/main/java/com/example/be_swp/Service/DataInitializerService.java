package com.example.be_swp.Service;

import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class DataInitializerService {

    private final RolesRepository _rolesRepository;
    private final UsersRepository _usersRepository;
    private final WorkScheduleRepository _workScheduleRepository;
    private final PaymentMethodRepository _paymentMethodRepository;
    private final PaymentRepository _paymentRepository;
    private final ExpertRepository _expertRepository;
    private final AppointmentRepository _appointmentRepository;
    private final ServicesRepository _servicesRepository;
    private final ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;
    private final QuizzesRepository _quizzesRepository;
    private final QuestionsRepository _questionsRepository;
    private final AnswersRepository _answersRepository;
    private final QuizResultsRepository _quizResultsRepository;
    private final ServiceRatingsRepository _serviceRatingsRepository;
    private final ExpertRatingsRepository _expertRatingsRepository;


    public DataInitializerService(RolesRepository _rolesRepository, UsersRepository _usersRepository, WorkScheduleRepository _workScheduleRepository, PaymentMethodRepository _paymentMethodRepository, PaymentRepository _paymentRepository, ExpertRepository _expertRepository, AppointmentRepository _appointmentRepository, ServicesRepository _servicesRepository, ExpertOccupiedTimeRepository _expertOccupiedTimeRepository, QuizzesRepository _quizzesRepository, QuestionsRepository _questionsRepository, AnswersRepository _answersRepository, QuizResultsRepository _quizResultsRepository, ServiceRatingsRepository _serviceRatingsRepository, ExpertRatingsRepository _expertRatingsRepository) {
        this._rolesRepository = _rolesRepository;
        this._usersRepository = _usersRepository;
        this._workScheduleRepository = _workScheduleRepository;
        this._paymentMethodRepository = _paymentMethodRepository;
        this._paymentRepository = _paymentRepository;
        this._expertRepository = _expertRepository;
        this._appointmentRepository = _appointmentRepository;
        this._servicesRepository = _servicesRepository;
        this._expertOccupiedTimeRepository = _expertOccupiedTimeRepository;
        this._quizzesRepository = _quizzesRepository;
        this._questionsRepository = _questionsRepository;
        this._answersRepository = _answersRepository;
        this._quizResultsRepository = _quizResultsRepository;
        this._serviceRatingsRepository = _serviceRatingsRepository;
        this._expertRatingsRepository = _expertRatingsRepository;
    }

    @PostConstruct
    public void init(){

        if (_rolesRepository.findAll().isEmpty()) {

            // Roles
            Roles rolesAdmin = new Roles();
            Roles rolesCustomer = new Roles();
            Roles rolesStaff = new Roles();
            Roles rolesExpert = new Roles();

            rolesAdmin.setName("ADMIN");
            rolesAdmin.setDescription("ROLE ADMIN");

            rolesCustomer.setName("CUSTOMER");
            rolesCustomer.setDescription("ROLE CUSTOMER");

            rolesStaff.setName("STAFF");
            rolesStaff.setDescription("ROLE STAFF");

            rolesExpert.setName("EXPERT");
            rolesExpert.setDescription("ROLE EXPERT");

            //Payment Methods

            PaymentMethods cash = new PaymentMethods("Cash", "Pay with cash", 1, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods payPal = new PaymentMethods("Paypal", "Pay with paypal", 0, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods visa = new PaymentMethods("Visa", "Pay with visa", 0, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods masterCard = new PaymentMethods("Master Card", "Pay with master card", 0, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods creditCard = new PaymentMethods("Credit Card", "Pay with credit card", 0, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods zaloPay = new PaymentMethods("Zalo Pay", "Pay with zalo pay", 1, LocalDateTime.now(), LocalDateTime.now());

            _paymentMethodRepository.save(cash);
            _paymentMethodRepository.save(payPal);
            _paymentMethodRepository.save(visa);
            _paymentMethodRepository.save(masterCard);
            _paymentMethodRepository.save(creditCard);
            _paymentMethodRepository.save(zaloPay);

            // Users

            Users usersAdmin = new Users("admin", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Admin", "admin@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersStaff = new Users("staff", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Staff", "staff@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert1 = new Users("expert1", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert One", "expert1@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert2 = new Users("expert2", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Two", "expert2@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert3 = new Users("expert3", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Three", "expert3@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert4 = new Users("expert4", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Four", "expert4@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert5 = new Users("expert5", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Five", "expert5@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert6 = new Users("expert6", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Six", "expert6@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert7 = new Users("expert7", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Seven", "expert7@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert8 = new Users("expert8", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Eight", "expert8@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert9 = new Users("expert8", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Nine", "expert9@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert10 = new Users("expert10", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Ten", "expert10@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersCustomer = new Users("customer", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Customer", "customer@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersCustomer1 = new Users("customer1", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Customer One", "customer@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersHello = new Users("hello", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "Hello", "hello@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());
            //Map User and Role
            usersAdmin.setRoles(rolesAdmin);
            List<Users> adminList = new ArrayList<>();
            adminList.add(usersAdmin);

            usersStaff.setRoles(rolesStaff);
            List<Users> staffList = new ArrayList<>();
            staffList.add(usersStaff);

            usersExpert1.setRoles(rolesExpert);
            usersExpert2.setRoles(rolesExpert);
            usersExpert3.setRoles(rolesExpert);
            usersExpert4.setRoles(rolesExpert);
            usersExpert5.setRoles(rolesExpert);
            usersExpert6.setRoles(rolesExpert);
            usersExpert7.setRoles(rolesExpert);
            usersExpert8.setRoles(rolesExpert);
            usersExpert9.setRoles(rolesExpert);
            usersExpert10.setRoles(rolesExpert);
            List<Users> expertList = new ArrayList<>();
            expertList.add(usersExpert1);
            expertList.add(usersExpert2);
            expertList.add(usersExpert3);
            expertList.add(usersExpert4);
            expertList.add(usersExpert5);
            expertList.add(usersExpert6);
            expertList.add(usersExpert7);
            expertList.add(usersExpert8);
            expertList.add(usersExpert9);
            expertList.add(usersExpert10);

            usersCustomer.setRoles(rolesCustomer);
            usersCustomer1.setRoles(rolesCustomer);
            usersHello.setRoles(rolesCustomer);
            List<Users> customerList = new ArrayList<>();
            customerList.add(usersCustomer);
            customerList.add(usersCustomer1);
            customerList.add(usersHello);

            rolesAdmin.setUsers(adminList);
            rolesStaff.setUsers(staffList);
            rolesExpert.setUsers(expertList);
            rolesCustomer.setUsers(customerList);

            _rolesRepository.save(rolesAdmin);
            _rolesRepository.save(rolesCustomer);
            _rolesRepository.save(rolesStaff);
            _rolesRepository.save(rolesExpert);

            //Experts
            
            Experts expert1 = new Experts("Facial Treatments", 6, "This is John Expert One", "https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert2 = new Experts("Facial Treatments", 5, "This is John Expert Two", "https://t4.ftcdn.net/jpg/07/07/89/33/360_F_707893394_5DEhlBjWOmse1nyu0rC9T7ZRvsAFDkYC.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert3 = new Experts("Back Treatments", 7, "This is John Expert Three", "https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert4 = new Experts("Back Treatments", 5, "This is John Expert Four", "https://t4.ftcdn.net/jpg/07/07/89/33/360_F_707893394_5DEhlBjWOmse1nyu0rC9T7ZRvsAFDkYC.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert5 = new Experts("Legs & Arms Treatments", 8, "This is John Expert Five", "https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert6 = new Experts("Legs & Arms Treatments", 10, "This is John Expert Six", "https://t4.ftcdn.net/jpg/07/07/89/33/360_F_707893394_5DEhlBjWOmse1nyu0rC9T7ZRvsAFDkYC.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert7 = new Experts("Hands & Feet Treatments", 3, "This is John Expert Seven", "https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert8 = new Experts("Hands & Feet Treatments", 4, "This is John Expert Eight", "https://t4.ftcdn.net/jpg/07/07/89/33/360_F_707893394_5DEhlBjWOmse1nyu0rC9T7ZRvsAFDkYC.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert9 = new Experts("Full Body Treatments", 9, "This is John Expert Nine", "https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            Experts expert10 = new Experts("Full Body Treatments", 2, "This is John Expert Ten", "https://t4.ftcdn.net/jpg/07/07/89/33/360_F_707893394_5DEhlBjWOmse1nyu0rC9T7ZRvsAFDkYC.jpg", 1, LocalDateTime.now(), LocalDateTime.now());

            //Map User and Expert

            expert1.setUsers(usersExpert1);
            usersExpert1.setExperts(expert1);
            _expertRepository.save(expert1);

            expert2.setUsers(usersExpert2);
            usersExpert2.setExperts(expert2);
            _expertRepository.save(expert2);

            expert3.setUsers(usersExpert3);
            usersExpert3.setExperts(expert3);
            _expertRepository.save(expert3);

            expert4.setUsers(usersExpert4);
            usersExpert4.setExperts(expert4);
            _expertRepository.save(expert4);

            expert5.setUsers(usersExpert5);
            usersExpert5.setExperts(expert5);
            _expertRepository.save(expert5);

            expert6.setUsers(usersExpert6);
            usersExpert6.setExperts(expert6);
            _expertRepository.save(expert6);

            expert7.setUsers(usersExpert7);
            usersExpert7.setExperts(expert7);
            _expertRepository.save(expert7);

            expert8.setUsers(usersExpert8);
            usersExpert8.setExperts(expert8);
            _expertRepository.save(expert8);

            expert9.setUsers(usersExpert9);
            usersExpert9.setExperts(expert9);
            _expertRepository.save(expert9);

            expert10.setUsers(usersExpert10);
            usersExpert10.setExperts(expert10);
            _expertRepository.save(expert10);

            //Work Schedule

            List<WorkSchedule> workScheduleList1 = new ArrayList<>();
            List<WorkSchedule> workScheduleList2 = new ArrayList<>();
            List<WorkSchedule> workScheduleList3 = new ArrayList<>();
            List<WorkSchedule> workScheduleList4 = new ArrayList<>();
            List<WorkSchedule> workScheduleList5 = new ArrayList<>();
            List<WorkSchedule> workScheduleList6 = new ArrayList<>();
            List<WorkSchedule> workScheduleList7 = new ArrayList<>();
            List<WorkSchedule> workScheduleList8 = new ArrayList<>();
            List<WorkSchedule> workScheduleList9 = new ArrayList<>();
            List<WorkSchedule> workScheduleList10 = new ArrayList<>();

            for (int i = 0; i < 7; i++){
                WorkSchedule scheduleMorning1 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning2 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning3 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning4 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning5 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning6 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning7 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning8 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning9 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                WorkSchedule scheduleMorning10 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
                workScheduleList1.add(scheduleMorning1);
                workScheduleList2.add(scheduleMorning2);
                workScheduleList3.add(scheduleMorning3);
                workScheduleList4.add(scheduleMorning4);
                workScheduleList5.add(scheduleMorning5);
                workScheduleList6.add(scheduleMorning6);
                workScheduleList7.add(scheduleMorning7);
                workScheduleList8.add(scheduleMorning8);
                workScheduleList9.add(scheduleMorning9);
                workScheduleList10.add(scheduleMorning10);
            }

            //Map Work Schedule with Expert

            for (WorkSchedule workSchedule : workScheduleList1) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert1);
            }

            for (WorkSchedule workSchedule : workScheduleList2) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert2);
            }

            for (WorkSchedule workSchedule : workScheduleList3) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert3);
            }

            for (WorkSchedule workSchedule : workScheduleList4) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert4);
            }

            for (WorkSchedule workSchedule : workScheduleList5) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert5);
            }

            for (WorkSchedule workSchedule : workScheduleList6) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert6);
            }

            for (WorkSchedule workSchedule : workScheduleList7) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert7);
            }

            for (WorkSchedule workSchedule : workScheduleList8) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert8);
            }

            for (WorkSchedule workSchedule : workScheduleList9) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert9);
            }

            for (WorkSchedule workSchedule : workScheduleList10) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
                    workSchedule.setStatus(3);
                }
                workSchedule.setExperts(expert10);
            }

            expert1.setWorkScheduleList(workScheduleList1);
            expert2.setWorkScheduleList(workScheduleList2);
            expert3.setWorkScheduleList(workScheduleList3);
            expert4.setWorkScheduleList(workScheduleList4);
            expert5.setWorkScheduleList(workScheduleList5);
            expert6.setWorkScheduleList(workScheduleList6);
            expert7.setWorkScheduleList(workScheduleList7);
            expert8.setWorkScheduleList(workScheduleList8);
            expert9.setWorkScheduleList(workScheduleList9);
            expert10.setWorkScheduleList(workScheduleList10);


            for (WorkSchedule workSchedule : workScheduleList1) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList2) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList3) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList4) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList5) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList6) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList7) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList8) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList9) {
                _workScheduleRepository.save(workSchedule);
            }

            for (WorkSchedule workSchedule : workScheduleList10) {
                _workScheduleRepository.save(workSchedule);
            }

            //Service

            Services regularFacial = new Services("Regular Facial", 100000, "A classic facial including cleansing, exfoliation, steam, and massage to maintain healthy skin", 60, 1, "Face Treatment", "Normal Skin" , "https://img.skininc.com/files/base/allured/all/image/2023/09/AdobeStock_578418396.64fb63a284980.png?auto=format%2Ccompress&q=70&rect=0%2C347%2C5295%2C2979&w=700" , LocalDateTime.now(), LocalDateTime.now());
            Services backCleansingFacial = new Services("Back Cleansing Facial", 150000, "Deep cleansing, exfoliation, and hydration to keep back skin smooth and clear", 60, 1, "Back Treatment", "Normal Skin" , "https://havispadanang.vn/wp-content/uploads/2024/03/Back-treatment-havi-spa.jpg" , LocalDateTime.now(), LocalDateTime.now());
            Services backAcneTreatment = new Services("Back Acne Treatment", 150000, "A deep pore cleansing treatment for acne-prone skin on the back", 60, 1, "Back Treatment", "Oily Skin" , "https://havispadanang.vn/wp-content/uploads/2024/03/Back-treatment-havi-spa.jpg" , LocalDateTime.now(), LocalDateTime.now());
            Services exfoliatingScrubForOilControl = new Services("Exfoliating Scrub for Oil Control", 150000, "Removes dead skin and excess oil for smoother skin", 60, 1, "Legs & Arms Treatment", "Oily Skin", "https://media.istockphoto.com/id/1169634751/photo/close-up-on-a-woman-applying-cream-on-her-legs.jpg?s=612x612&w=0&k=20&c=fV6iWihoE1zpbyy4jI7Ecb5HqOheL2ES_LVm_-40Wgk=" , LocalDateTime.now(), LocalDateTime.now());
            Services paraffinWaxTreatment = new Services("Paraffin Wax Treatment", 150000, "Softens and deeply moisturizes hands and feet", 60, 1, "Hands & Feet Treatment", "Dry Skin" , "https://ipalclinic.com/wp-content/uploads/2023/12/hand-and-foot-care.jpg" , LocalDateTime.now(), LocalDateTime.now());
            Services steamTherapy = new Services("Steam Therapy", 150000, "Opens pores and allows better absorption of moisture", 60, 1, "Full Body Treatment", "Dry Skin" , "https://www.royalwellnesscentre.ca/uploads/files/Images/face%20and%20body/aroma-therapy.jpg" , LocalDateTime.now(), LocalDateTime.now());
            Services hydrafacialBodyTreatment = new Services("Hydrafacial Body Treatment", 150000, "Uses water-based serums and suction for deep cleansing and", 60, 1, "Full Body Treatment", "Combination SKin" , "https://celestolite.com/wp-content/uploads/Celestolite-8-Body-Skin-Care-Tips-Legs.jpg" , LocalDateTime.now(), LocalDateTime.now());
            Services customizedFacial = new Services("Customized Facial", 150000, "A tailored treatment that balances oily and dry areas", 60, 1, "Face Treatment", "Combination skin" , "https://img.skininc.com/files/base/allured/all/image/2023/09/AdobeStock_578418396.64fb63a284980.png?auto=format%2Ccompress&q=70&rect=0%2C347%2C5295%2C2979&w=700" , LocalDateTime.now(), LocalDateTime.now());
            Services calmingFacial = new Services("Calming Facial", 150000, "Reduces inflammation and soothes sensitive skin", 60, 1, "Face Treatment", "Sensitive skin" , "https://img.skininc.com/files/base/allured/all/image/2023/09/AdobeStock_578418396.64fb63a284980.png?auto=format%2Ccompress&q=70&rect=0%2C347%2C5295%2C2979&w=700" , LocalDateTime.now(), LocalDateTime.now());
            Services gentleHydratingBackFacial = new Services("Gentle Hydrating Back Facial", 150000, "Uses mild, fragrance-free products to cleanse and nourish.", 60, 1, "Back Treatment", "Sensitive skin" , "https://havispadanang.vn/wp-content/uploads/2024/03/Back-treatment-havi-spa.jpg" , LocalDateTime.now(), LocalDateTime.now());

            _servicesRepository.save(regularFacial);
            _servicesRepository.save(backCleansingFacial);
            _servicesRepository.save(backAcneTreatment);
            _servicesRepository.save(exfoliatingScrubForOilControl);
            _servicesRepository.save(paraffinWaxTreatment);
            _servicesRepository.save(steamTherapy);
            _servicesRepository.save(hydrafacialBodyTreatment);
            _servicesRepository.save(customizedFacial);
            _servicesRepository.save(calmingFacial);
            _servicesRepository.save(gentleHydratingBackFacial);


            //Appointment
            LocalDate today = LocalDate.now();
            LocalTime startTime = LocalTime.of(8, 0);
            LocalTime endTime = startTime.plusMinutes(regularFacial.getDuration());

            Appointments appointments = new Appointments();

            if (LocalDateTime.now().isAfter(LocalDateTime.of(today, endTime))) {
                appointments.setStatus(4);
            } else {
                appointments.setStatus(1);
            }

            appointments.setTotal(regularFacial.getPrice());
            appointments.setStart_at(LocalDateTime.of(today, startTime));
            appointments.setEnd_at(LocalDateTime.of(today, endTime));
            appointments.setCreated_at(LocalDateTime.now());
            appointments.setUpdated_at(LocalDateTime.now());

            appointments.setServices(regularFacial);
            appointments.setUsers(usersCustomer);
            appointments.setExperts(expert1);

            List<Appointments> appointmentsList = new ArrayList<>();
            appointmentsList.add(appointments);

            regularFacial.setAppointmentsList(appointmentsList);
            usersCustomer.setAppointmentsList(appointmentsList);
            expert1.setAppointmentsList(appointmentsList);

            _appointmentRepository.save(appointments);

            // Done Appointment

            Appointments appointmentsDone = new Appointments();

            appointmentsDone.setTotal(backCleansingFacial.getPrice());
            appointmentsDone.setStart_at(LocalDateTime.of(today.minusDays(1), startTime));
            appointmentsDone.setEnd_at(LocalDateTime.of(today.minusDays(1), endTime));
            appointmentsDone.setStatus(4);
            appointmentsDone.setCreated_at(LocalDateTime.now());
            appointmentsDone.setUpdated_at(LocalDateTime.now());

            appointmentsDone.setServices(backCleansingFacial);
            appointmentsDone.setUsers(usersCustomer);
            appointmentsDone.setExperts(expert2);

            List<Appointments> appointmentsListDone = new ArrayList<>();
            appointmentsListDone.add(appointments);

            backCleansingFacial.setAppointmentsList(appointmentsListDone);
            usersCustomer.getAppointmentsList().add(appointmentsDone);
            expert2.setAppointmentsList(appointmentsListDone);

            _appointmentRepository.save(appointmentsDone);

            // Random Appointment


            Random random = new Random();

            LocalDate randomDate = LocalDate.now().plusDays(random.nextInt(1,7));
            LocalTime randomStartTime = LocalTime.of(random.nextInt(13,17),0,0);
            LocalTime randomEndTime = randomStartTime.plusHours(1);

            Appointments randomAppointment = new Appointments();
            if(LocalDateTime.now().isAfter(LocalDateTime.of(randomDate,randomEndTime))){
                randomAppointment.setStatus(4);
            }else{
                randomAppointment.setStatus(1);
            }

            randomAppointment.setStart_at(LocalDateTime.of(randomDate,randomStartTime));
            randomAppointment.setEnd_at(LocalDateTime.of(randomDate,randomEndTime));
            randomAppointment.setTotal(regularFacial.getPrice());
            randomAppointment.setCreated_at(LocalDateTime.now());
            randomAppointment.setUpdated_at(LocalDateTime.now());
            randomAppointment.setServices(regularFacial);
            randomAppointment.setExperts(expert1);
            randomAppointment.setUsers(usersCustomer1);

            List<Appointments> randomAppointmentsList = new ArrayList<>();

            randomAppointmentsList.add(randomAppointment);

            usersCustomer1.setAppointmentsList(randomAppointmentsList);
            expert1.getAppointmentsList().add(randomAppointment);
            regularFacial.getAppointmentsList().add(randomAppointment);

            _appointmentRepository.save(randomAppointment);
            // Occupied Time

            List<ExpertOccupiedTimes> expertOccupiedTimesList = new ArrayList<>();

            ExpertOccupiedTimes expertOccupiedTimes = new ExpertOccupiedTimes();

            expertOccupiedTimes.setExperts(expert1);
            expertOccupiedTimes.setStartAt(appointments.getStart_at().toLocalTime());
            expertOccupiedTimes.setEndAt(appointments.getEnd_at().toLocalTime());
            expertOccupiedTimes.setDate(appointments.getStart_at().toLocalDate());
            expertOccupiedTimes.setCreated_at(LocalDateTime.now());
            expertOccupiedTimes.setCreated_at(LocalDateTime.now());
            expertOccupiedTimes.setUpdated_at(LocalDateTime.now());

            if (LocalDateTime.of(expertOccupiedTimes.getDate(),expertOccupiedTimes.getStartAt()).isAfter(LocalDateTime.now())){
                expertOccupiedTimes.setStatus(1);
            }else if(LocalDateTime.of(expertOccupiedTimes.getDate(),expertOccupiedTimes.getStartAt()).isBefore(LocalDateTime.now()) && LocalDateTime.of(expertOccupiedTimes.getDate(),expertOccupiedTimes.getEndAt()).isAfter(LocalDateTime.now())){
                expertOccupiedTimes.setStatus(3);
            }else{
                expertOccupiedTimes.setStatus(4);
            }

            expertOccupiedTimesList.add(expertOccupiedTimes);
            expert1.setExpertOccupiedTimesList(expertOccupiedTimesList);

            _expertOccupiedTimeRepository.save(expertOccupiedTimes);
            //Occupied Time Done

            List<ExpertOccupiedTimes> expertOccupiedTimesListDone = new ArrayList<>();

            ExpertOccupiedTimes expertOccupiedTimesDone = new ExpertOccupiedTimes();

            expertOccupiedTimesDone.setExperts(expert2);
            expertOccupiedTimesDone.setStartAt(appointmentsDone.getStart_at().toLocalTime());
            expertOccupiedTimesDone.setEndAt(appointmentsDone.getEnd_at().toLocalTime());
            expertOccupiedTimesDone.setDate(appointmentsDone.getStart_at().toLocalDate());
            expertOccupiedTimesDone.setCreated_at(LocalDateTime.now());
            expertOccupiedTimesDone.setUpdated_at(LocalDateTime.now());
            expertOccupiedTimesDone.setStatus(4);

            expertOccupiedTimesListDone.add(expertOccupiedTimes);
            expert2.setExpertOccupiedTimesList(expertOccupiedTimesListDone);

            _expertOccupiedTimeRepository.save(expertOccupiedTimesDone);

            //Occupied Time Random
            ExpertOccupiedTimes randomExpertOccupiedTimes = new ExpertOccupiedTimes();

            randomExpertOccupiedTimes.setExperts(expert1);
            randomExpertOccupiedTimes.setStartAt(randomAppointment.getStart_at().toLocalTime());
            randomExpertOccupiedTimes.setEndAt(randomAppointment.getEnd_at().toLocalTime());
            randomExpertOccupiedTimes.setDate(randomAppointment.getStart_at().toLocalDate());
            randomExpertOccupiedTimes.setCreated_at(LocalDateTime.now());
            randomExpertOccupiedTimes.setUpdated_at(LocalDateTime.now());

            if (LocalDateTime.of(randomExpertOccupiedTimes.getDate(),randomExpertOccupiedTimes.getStartAt()).isAfter(LocalDateTime.now())){
                randomExpertOccupiedTimes.setStatus(1);
            }else if(LocalDateTime.of(randomExpertOccupiedTimes.getDate(),randomExpertOccupiedTimes.getStartAt()).isBefore(LocalDateTime.now()) && LocalDateTime.of(randomExpertOccupiedTimes.getDate(),randomExpertOccupiedTimes.getEndAt()).isAfter(LocalDateTime.now())){
                randomExpertOccupiedTimes.setStatus(3);
            }else{
                randomExpertOccupiedTimes.setStatus(4);
            }

            expert1.getExpertOccupiedTimesList().add(randomExpertOccupiedTimes);

            _expertOccupiedTimeRepository.save(randomExpertOccupiedTimes);

            //Payment

            Payments payments = new Payments(4, appointments.getTotal(), LocalDateTime.now(), LocalDateTime.now());

            if (LocalDateTime.now().isAfter(LocalDateTime.of(today, endTime))) {
                payments.setStatus(4);
            } else {
                payments.setStatus(2);
            }

            payments.setZpTransId(0);

            payments.setAppointments(appointments);
            appointments.setPayments(payments);

            List<Payments> paymentsList = new ArrayList<>();
            paymentsList.add(payments);

            payments.setPaymentMethods(payPal);
            payPal.setPayments(paymentsList);

            _paymentRepository.save(payments);

            //Payment Done

            Payments paymentsDone = new Payments(4, appointmentsDone.getTotal(), LocalDateTime.now(), LocalDateTime.now());

            paymentsDone.setZpTransId(0);

            paymentsDone.setAppointments(appointmentsDone);
            appointmentsDone.setPayments(paymentsDone);

            List<Payments> paymentsListDone = new ArrayList<>();
            paymentsListDone.add(paymentsDone);

            paymentsDone.setPaymentMethods(cash);
            cash.setPayments(paymentsListDone);

            _paymentRepository.save(paymentsDone);

            //Service Ratings

            ServiceRatings serviceRatingsDone = new ServiceRatings();

            serviceRatingsDone.setAppointments(appointmentsDone);
            serviceRatingsDone.setUsers(usersCustomer);
            serviceRatingsDone.setServices(backCleansingFacial);
            serviceRatingsDone.setRating(5);
            serviceRatingsDone.setFeedback("Good Service!");
            serviceRatingsDone.setStatus(4);
            serviceRatingsDone.setUpdated_at(LocalDateTime.now());
            serviceRatingsDone.setCreated_at(LocalDateTime.now());

            List<ServiceRatings> serviceRatingsListDone = new ArrayList<>();
            serviceRatingsListDone.add(serviceRatingsDone);

            appointmentsDone.setServiceRatings(serviceRatingsDone);
            usersCustomer.setServiceRatingsList(serviceRatingsListDone);
            backCleansingFacial.setServiceRatingsList(serviceRatingsListDone);

            _serviceRatingsRepository.save(serviceRatingsDone);

            //Expert Ratings

            ExpertRatings expertRatingsDone = new ExpertRatings();
            expertRatingsDone.setAppointments(appointmentsDone);
            expertRatingsDone.setUsers(usersCustomer);
            expertRatingsDone.setExperts(expert2);
            expertRatingsDone.setRating(5);
            expertRatingsDone.setFeedback("Friendly");
            expertRatingsDone.setStatus(1);
            expertRatingsDone.setCreated_at(LocalDateTime.now());
            expertRatingsDone.setUpdated_at(LocalDateTime.now());

            List<ExpertRatings> expertRatingsListDone = new ArrayList<>();
            expertRatingsListDone.add(expertRatingsDone);

            appointmentsDone.setExpertRatings(expertRatingsDone);
            usersCustomer.setExpertRatingsList(expertRatingsListDone);
            expert2.setExpertRatingsList(expertRatingsListDone);

            _expertRatingsRepository.save(expertRatingsDone);

            //Quizzes

            Quizzes quizzes = new Quizzes();
            quizzes.setName("Skin Quiz");
            quizzes.setQuestionsList(new ArrayList<>());
            quizzes.setQuizResultsList(new ArrayList<>());
            quizzes.setCreateAt(LocalDateTime.now());
            quizzes.setUpdateAt(LocalDateTime.now());

            List<Questions> questionsList = new ArrayList<>();

            //Quest1
            Questions question1 = new Questions();

            question1.setTitle("After washing your face, how does your skin feel?");
            question1.setCreatedAt(LocalDateTime.now());
            question1.setUpdatedAt(LocalDateTime.now());
            question1.setAnswersList(new ArrayList<>());

            List<Answers> question1AnswersList = new ArrayList<>(Arrays.asList(
                    new Answers("Smooth, Not Dry.",question1,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Tight and Dry.",question1,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Oily.",question1,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("The T-zone is oily, but the cheeks are dry.",question1,LocalDateTime.now(),LocalDateTime.now())
            ));
            question1.setAnswersList(question1AnswersList);
            questionsList.add(question1);
            question1.setQuizzes(quizzes);

            //Quest2
            Questions question2 = new Questions();

            question2.setTitle("When during the day does your skin usually get shiny/oily?");
            question2.setCreatedAt(LocalDateTime.now());
            question2.setUpdatedAt(LocalDateTime.now());
            question2.setAnswersList(new ArrayList<>());

            List<Answers> question2AnswersList = new ArrayList<>(Arrays.asList(
                    new Answers("Almost never gets oily.", question2,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Never oily throughout the day, might be slightly dry.", question2,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Always oily, especially in the forehead, nose, and chin areas.", question2,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Only oily in the T-zone, other areas are normal or dry.", question2,LocalDateTime.now(),LocalDateTime.now())
            ));
            question2.setAnswersList(question2AnswersList);
            questionsList.add(question2);
            question2.setQuizzes(quizzes);

            //Quest3
            Questions question3 = new Questions();

            question3.setTitle("How does your skin react when the weather changes?");
            question3.setCreatedAt(LocalDateTime.now());
            question3.setUpdatedAt(LocalDateTime.now());
            question3.setAnswersList(new ArrayList<>());

            List<Answers> question3AnswersList = new ArrayList<>(Arrays.asList(
                    new Answers("Little change, skin remains stable.", question3,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Very dry, easily flakes.", question3,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Prone to breakouts, gets oilier in hot weather.", question3,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Becomes more sensitive, T-zone gets oily, cheeks get dry.", question3,LocalDateTime.now(),LocalDateTime.now())
            ));
            question3.setAnswersList(question3AnswersList);
            questionsList.add(question3);
            question3.setQuizzes(quizzes);

            //Quest4
            Questions question4 = new Questions();

            question4.setTitle("How would you describe your pores?");
            question4.setCreatedAt(LocalDateTime.now());
            question4.setUpdatedAt(LocalDateTime.now());
            question4.setAnswersList(new ArrayList<>());

            List<Answers> question4AnswersList = new ArrayList<>(Arrays.asList(
                    new Answers("Small pores, difficult to see.", question4,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Very small or not clearly visible.", question4,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Large, easily visible.", question4,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Large in the T-zone, but small on the cheeks.", question4,LocalDateTime.now(),LocalDateTime.now())
            ));
            question4.setAnswersList(question4AnswersList);
            questionsList.add(question4);
            question4.setQuizzes(quizzes);

            //Quest5
            Questions question5 = new Questions();

            question5.setTitle("How does your skin typically feel after applying moisturizer?");
            question5.setCreatedAt(LocalDateTime.now());
            question5.setUpdatedAt(LocalDateTime.now());
            question5.setAnswersList(new ArrayList<>());

            List<Answers> question5AnswersList = new ArrayList<>(Arrays.asList(
                    new Answers("Feels comfortable, neither too shiny nor dry.", question5,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Skin absorbs moisture immediately, but still feels dry.", question5,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Usually feels greasy or sticky.", question5,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("T-zone becomes slightly shiny, other areas feel comfortable.", question5,LocalDateTime.now(),LocalDateTime.now())
            ));
            question5.setAnswersList(question5AnswersList);
            questionsList.add(question5);
            question5.setQuizzes(quizzes);

            //Quest6
            Questions question6 = new Questions();

            question6.setTitle("Do you experience problems with acne or skin irritation?");
            question6.setCreatedAt(LocalDateTime.now());
            question6.setUpdatedAt(LocalDateTime.now());
            question6.setAnswersList(new ArrayList<>());

            List<Answers> question6AnswersList = new ArrayList<>(Arrays.asList(
                    new Answers("Rarely occurs.", question6,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Skin is easily irritated, itchy, or flaky.", question6,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Frequently get acne, clogged pores.", question6,LocalDateTime.now(),LocalDateTime.now()),
                    new Answers("Acne appears mainly in the T-zone.", question6,LocalDateTime.now(),LocalDateTime.now())
            ));
            question6.setAnswersList(question6AnswersList);
            questionsList.add(question6);
            question6.setQuizzes(quizzes);

            quizzes.setQuestionsList(questionsList);

            _quizzesRepository.save(quizzes);

            QuizResults quizResults1 = new QuizResults();
            quizResults1.setQuizzes(quizzes);
            quizResults1.setUsers(usersCustomer);
            quizResults1.setFinalResult("Dry Skin");
            quizResults1.setUpdatedAt(LocalDateTime.now());
            quizResults1.setCreatedAt(LocalDateTime.now());

            List<QuizResults> quizResultsList = new ArrayList<>();
            quizResultsList.add(quizResults1);

            quizzes.setQuizResultsList(quizResultsList);

            _quizResultsRepository.save(quizResults1);

        }
    }

}
