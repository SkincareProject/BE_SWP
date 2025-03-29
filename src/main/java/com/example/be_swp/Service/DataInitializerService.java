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

//    private final RolesRepository _rolesRepository;
//    private final UsersRepository _usersRepository;
//    private final WorkScheduleRepository _workScheduleRepository;
//    private final PaymentMethodRepository _paymentMethodRepository;
//    private final PaymentRepository _paymentRepository;
//    private final ExpertRepository _expertRepository;
//    private final AppointmentRepository _appointmentRepository;
//    private final ServicesRepository _servicesRepository;
//    private final ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;
//    private final QuizzesRepository _quizzesRepository;
//    private final QuestionsRepository _questionsRepository;
//    private final AnswersRepository _answersRepository;
//    private final QuizResultsRepository _quizResultsRepository;
//    private final ServiceRatingsRepository _serviceRatingsRepository;
//    private final ExpertRatingsRepository _expertRatingsRepository;
//
//
//    public DataInitializerService(RolesRepository _rolesRepository, UsersRepository _usersRepository, WorkScheduleRepository _workScheduleRepository, PaymentMethodRepository _paymentMethodRepository, PaymentRepository _paymentRepository, ExpertRepository _expertRepository, AppointmentRepository _appointmentRepository, ServicesRepository _servicesRepository, ExpertOccupiedTimeRepository _expertOccupiedTimeRepository, QuizzesRepository _quizzesRepository, QuestionsRepository _questionsRepository, AnswersRepository _answersRepository, QuizResultsRepository _quizResultsRepository, ServiceRatingsRepository _serviceRatingsRepository, ExpertRatingsRepository _expertRatingsRepository) {
//        this._rolesRepository = _rolesRepository;
//        this._usersRepository = _usersRepository;
//        this._workScheduleRepository = _workScheduleRepository;
//        this._paymentMethodRepository = _paymentMethodRepository;
//        this._paymentRepository = _paymentRepository;
//        this._expertRepository = _expertRepository;
//        this._appointmentRepository = _appointmentRepository;
//        this._servicesRepository = _servicesRepository;
//        this._expertOccupiedTimeRepository = _expertOccupiedTimeRepository;
//        this._quizzesRepository = _quizzesRepository;
//        this._questionsRepository = _questionsRepository;
//        this._answersRepository = _answersRepository;
//        this._quizResultsRepository = _quizResultsRepository;
//        this._serviceRatingsRepository = _serviceRatingsRepository;
//        this._expertRatingsRepository = _expertRatingsRepository;
//    }
//
//    @PostConstruct
//    public void init(){
//
//        if (_rolesRepository.findAll().isEmpty()) {
//
//            // Roles
//            Roles rolesAdmin = new Roles();
//            Roles rolesCustomer = new Roles();
//            Roles rolesStaff = new Roles();
//            Roles rolesExpert = new Roles();
//
//            rolesAdmin.setName("ADMIN");
//            rolesAdmin.setDescription("ROLE ADMIN");
//
//            rolesCustomer.setName("CUSTOMER");
//            rolesCustomer.setDescription("ROLE CUSTOMER");
//
//            rolesStaff.setName("STAFF");
//            rolesStaff.setDescription("ROLE STAFF");
//
//            rolesExpert.setName("EXPERT");
//            rolesExpert.setDescription("ROLE EXPERT");
//
//            //Payment Methods
//
//            PaymentMethods cash = new PaymentMethods("Cash", "Pay with cash", 1, LocalDateTime.now(), LocalDateTime.now());
//
//            PaymentMethods payPal = new PaymentMethods("Paypal", "Pay with paypal", 0, LocalDateTime.now(), LocalDateTime.now());
//
//            PaymentMethods visa = new PaymentMethods("Visa", "Pay with visa", 0, LocalDateTime.now(), LocalDateTime.now());
//
//            PaymentMethods masterCard = new PaymentMethods("Master Card", "Pay with master card", 0, LocalDateTime.now(), LocalDateTime.now());
//
//            PaymentMethods creditCard = new PaymentMethods("Credit Card", "Pay with credit card", 0, LocalDateTime.now(), LocalDateTime.now());
//
//            PaymentMethods zaloPay = new PaymentMethods("Zalo Pay", "Pay with zalo pay", 1, LocalDateTime.now(), LocalDateTime.now());
//
//            _paymentMethodRepository.save(cash);
//            _paymentMethodRepository.save(payPal);
//            _paymentMethodRepository.save(visa);
//            _paymentMethodRepository.save(masterCard);
//            _paymentMethodRepository.save(creditCard);
//            _paymentMethodRepository.save(zaloPay);
//
//            // Users
//
//            Users usersAdmin = new Users("admin", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Admin", "admin@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());
//
//            Users usersStaff = new Users("staff", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Staff", "staff@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());
//
//            Users usersExpert = new Users("expert", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert One", "expert@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());
//
//            Users usersExpert2 = new Users("expert2", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Expert Two", "expert2@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());
//
//            Users usersCustomer = new Users("customer", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Customer", "customer@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());
//
//            Users usersCustomer1 = new Users("customer1", "$2a$10$svTTSTx1vX3sg5M5FBr18ef86ev.eVkNdOwuKlAk5bedbRqwJuXKG", "John Customer One", "customer@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());
//
//            //Map User and Role
//            usersAdmin.setRoles(rolesAdmin);
//            List<Users> adminList = new ArrayList<>();
//            adminList.add(usersAdmin);
//
//            usersStaff.setRoles(rolesStaff);
//            List<Users> staffList = new ArrayList<>();
//            staffList.add(usersStaff);
//
//            usersExpert.setRoles(rolesExpert);
//            usersExpert2.setRoles(rolesExpert);
//            List<Users> expertList = new ArrayList<>();
//            expertList.add(usersExpert);
//            expertList.add(usersExpert2);
//
//            usersCustomer.setRoles(rolesCustomer);
//            usersCustomer1.setRoles(rolesCustomer);
//            List<Users> customerList = new ArrayList<>();
//            customerList.add(usersCustomer);
//            customerList.add(usersCustomer1);
//
//            rolesAdmin.setUsers(adminList);
//            rolesStaff.setUsers(staffList);
//            rolesExpert.setUsers(expertList);
//            rolesCustomer.setUsers(customerList);
//
//            _rolesRepository.save(rolesAdmin);
//            _rolesRepository.save(rolesCustomer);
//            _rolesRepository.save(rolesStaff);
//            _rolesRepository.save(rolesExpert);
//
//            //Experts
//
//            Experts expert = new Experts("Facial Treatments", 6, "This is John Expert One, John can make your face more beautiful.", "https://img.freepik.com/free-photo/beautiful-young-female-doctor-looking-camera-office_1301-7807.jpg", 1, LocalDateTime.now(), LocalDateTime.now());
//
//            Experts expert2 = new Experts("Massage", 5, "This is John Expert Two, John can make your fatigue go away.", "https://t4.ftcdn.net/jpg/07/07/89/33/360_F_707893394_5DEhlBjWOmse1nyu0rC9T7ZRvsAFDkYC.jpg", 1, LocalDateTime.now(), LocalDateTime.now());
//
//            //Map User and Expert
//
//            expert.setUsers(usersExpert);
//            usersExpert.setExperts(expert);
//            _expertRepository.save(expert);
//
//            expert2.setUsers(usersExpert2);
//            usersExpert2.setExperts(expert2);
//            _expertRepository.save(expert2);
//
//            //Work Schedule
//
//            List<WorkSchedule> workScheduleList = new ArrayList<>();
//            List<WorkSchedule> workScheduleList2 = new ArrayList<>();
//
//            for (int i = 0; i < 7; i++){
//                WorkSchedule scheduleMorning = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(11,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
//                WorkSchedule scheduleEvening = new WorkSchedule(LocalTime.of(13,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
//                WorkSchedule scheduleMorning2 = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(11,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
//                WorkSchedule scheduleEvening2 = new WorkSchedule(LocalTime.of(13,0),LocalTime.of(17,0), LocalDate.now().plusDays(i),1,LocalDateTime.now(),LocalDateTime.now());
//                workScheduleList.add(scheduleMorning);
//                workScheduleList.add(scheduleEvening);
//                workScheduleList2.add(scheduleMorning2);
//                workScheduleList2.add(scheduleEvening2);
//            }
//
//            //Map Work Schedule with Expert
//
//            for (WorkSchedule workSchedule : workScheduleList) {
//                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
//                    workSchedule.setStatus(4);
//                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
//                    workSchedule.setStatus(3);
//                }
//                workSchedule.setExperts(expert);
//            }
//
//            for (WorkSchedule workSchedule : workScheduleList2) {
//                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
//                    workSchedule.setStatus(4);
//                }else if(workSchedule.getWork_date().equals(LocalDate.now()) && workSchedule.getEnd_at().isAfter(LocalTime.now()) && workSchedule.getStart_at().isBefore(LocalTime.now())){
//                    workSchedule.setStatus(3);
//                }
//                workSchedule.setExperts(expert2);
//            }
//
//            expert.setWorkScheduleList(workScheduleList);
//            expert2.setWorkScheduleList(workScheduleList2);
//
//
//            for (WorkSchedule workSchedule : workScheduleList) {
//                _workScheduleRepository.save(workSchedule);
//            }
//
//            for (WorkSchedule workSchedule : workScheduleList2) {
//                _workScheduleRepository.save(workSchedule);
//            }
//
//            //Service
//
//            Services facialService = new Services("All Your Facial Treatments", 100000, "All of skincare for yo face", 60, 1, "Facial Treatment", "Dry skin" , LocalDateTime.now(), LocalDateTime.now(),null);
//            Services massageService = new Services("All Your Skin Treatments", 150000, "Make all of your fatigue go away", 60, 1, "Massage Treatment", "Dry skin" , LocalDateTime.now(), LocalDateTime.now(),null);
//
//            _servicesRepository.save(facialService);
//            _servicesRepository.save(massageService);
//
//            //Appointment
////            LocalDate today = LocalDate.now();
////            LocalTime startTime = LocalTime.of(8, 0);
////            LocalTime endTime = startTime.plusMinutes(facialService.getDuration());
//
//            Appointments appointments = new Appointments();
//
////            if (LocalDateTime.now().isAfter(LocalDateTime.of(today, endTime))) {
////                appointments.setStatus(4);
////            } else {
////                appointments.setStatus(1);
////            }
//
//            appointments.setTotal(facialService.getPrice());
////            appointments.setStartAt(startTime);
////            appointments.setEndAt(LocalDateTime.of(today, endTime));
//            appointments.setCreated_at(LocalDateTime.now());
//            appointments.setUpdated_at(LocalDateTime.now());
//
//            appointments.setServices(facialService);
//            appointments.setUsers(usersCustomer);
//            appointments.setExperts(expert);
//
//            List<Appointments> appointmentsList = new ArrayList<>();
//            appointmentsList.add(appointments);
//
//            facialService.setAppointmentsList(appointmentsList);
//            usersCustomer.setAppointmentsList(appointmentsList);
//            expert.setAppointmentsList(appointmentsList);
//
//            _appointmentRepository.save(appointments);
//
//            // Done Appointment
//
//            Appointments appointmentsDone = new Appointments();
//
//            appointmentsDone.setTotal(massageService.getPrice());
////            appointmentsDone.setStart_at(LocalDateTime.of(today.minusDays(1), startTime));
////            appointmentsDone.setEnd_at(LocalDateTime.of(today.minusDays(1), endTime));
//            appointmentsDone.setStatus(4);
//            appointmentsDone.setCreated_at(LocalDateTime.now());
//            appointmentsDone.setUpdated_at(LocalDateTime.now());
//
//            appointmentsDone.setServices(massageService);
//            appointmentsDone.setUsers(usersCustomer);
//            appointmentsDone.setExperts(expert2);
//
//            List<Appointments> appointmentsListDone = new ArrayList<>();
//            appointmentsListDone.add(appointments);
//
//            massageService.setAppointmentsList(appointmentsListDone);
//            usersCustomer.getAppointmentsList().add(appointmentsDone);
//            expert2.setAppointmentsList(appointmentsListDone);
//
//            _appointmentRepository.save(appointmentsDone);
//
//            // Random Appointment
//
//
//            Random random = new Random();
//
//            LocalDate randomDate = LocalDate.now().plusDays(random.nextInt(1,7));
//            LocalTime randomStartTime = LocalTime.of(random.nextInt(13,17),0,0);
//            LocalTime randomEndTime = randomStartTime.plusHours(1);
//
//            Appointments randomAppointment = new Appointments();
//            if(LocalDateTime.now().isAfter(LocalDateTime.of(randomDate,randomEndTime))){
//                randomAppointment.setStatus(4);
//            }else{
//                randomAppointment.setStatus(1);
//            }
//
////            randomAppointment.setStart_at(LocalDateTime.of(randomDate,randomStartTime));
////            randomAppointment.setEnd_at(LocalDateTime.of(randomDate,randomEndTime));
//            randomAppointment.setTotal(facialService.getPrice());
//            randomAppointment.setCreated_at(LocalDateTime.now());
//            randomAppointment.setUpdated_at(LocalDateTime.now());
////            randomAppointment.setCustomerId(facialService);
////            randomAppointment.setExperts(expert);
////            randomAppointment.setUsers(usersCustomer1);
//
//            List<Appointments> randomAppointmentsList = new ArrayList<>();
//
//            randomAppointmentsList.add(randomAppointment);
//
//            usersCustomer1.setAppointmentsList(randomAppointmentsList);
//            expert.getAppointmentsList().add(randomAppointment);
//            facialService.getAppointmentsList().add(randomAppointment);
//
//            _appointmentRepository.save(randomAppointment);
//            // Occupied Time
//
//            List<ExpertOccupiedTimes> expertOccupiedTimesList = new ArrayList<>();
//
//            ExpertOccupiedTimes expertOccupiedTimes = new ExpertOccupiedTimes();
//
//            expertOccupiedTimes.setExperts(expert);
////            expertOccupiedTimes.setStartAt(appointments.getStartAt().toLocalTime());
////            expertOccupiedTimes.setEndAt(appointments.getEnd_at().toLocalTime());
////            expertOccupiedTimes.setDate(appointments.getStart_at().toLocalDate());
//            expertOccupiedTimes.setCreated_at(LocalDateTime.now());
//            expertOccupiedTimes.setCreated_at(LocalDateTime.now());
//            expertOccupiedTimes.setUpdated_at(LocalDateTime.now());
//
//            if (LocalDateTime.of(expertOccupiedTimes.getDate(),expertOccupiedTimes.getStartAt()).isAfter(LocalDateTime.now())){
//                expertOccupiedTimes.setStatus(1);
//            }else if(LocalDateTime.of(expertOccupiedTimes.getDate(),expertOccupiedTimes.getStartAt()).isBefore(LocalDateTime.now()) && LocalDateTime.of(expertOccupiedTimes.getDate(),expertOccupiedTimes.getEndAt()).isAfter(LocalDateTime.now())){
//                expertOccupiedTimes.setStatus(3);
//            }else{
//                expertOccupiedTimes.setStatus(4);
//            }
//
//            expertOccupiedTimesList.add(expertOccupiedTimes);
//            expert.setExpertOccupiedTimesList(expertOccupiedTimesList);
//
//            _expertOccupiedTimeRepository.save(expertOccupiedTimes);
//            //Occupied Time Done
//
//            List<ExpertOccupiedTimes> expertOccupiedTimesListDone = new ArrayList<>();
//
//            ExpertOccupiedTimes expertOccupiedTimesDone = new ExpertOccupiedTimes();
//
//            expertOccupiedTimesDone.setExperts(expert2);
////            expertOccupiedTimesDone.setStartAt(appointmentsDone.getStart_at().toLocalTime());
////            expertOccupiedTimesDone.setEndAt(appointmentsDone.getEnd_at().toLocalTime());
////            expertOccupiedTimesDone.setDate(appointmentsDone.getStart_at().toLocalDate());
//            expertOccupiedTimesDone.setCreated_at(LocalDateTime.now());
//            expertOccupiedTimesDone.setUpdated_at(LocalDateTime.now());
//            expertOccupiedTimesDone.setStatus(4);
//
//            expertOccupiedTimesListDone.add(expertOccupiedTimes);
//            expert2.setExpertOccupiedTimesList(expertOccupiedTimesListDone);
//
//            _expertOccupiedTimeRepository.save(expertOccupiedTimesDone);
//
//            //Occupied Time Random
//            ExpertOccupiedTimes randomExpertOccupiedTimes = new ExpertOccupiedTimes();
//
//            randomExpertOccupiedTimes.setExperts(expert);
////            randomExpertOccupiedTimes.setStartAt(randomAppointment.getStart_at().toLocalTime());
////            randomExpertOccupiedTimes.setEndAt(randomAppointment.getEnd_at().toLocalTime());
////            randomExpertOccupiedTimes.setDate(randomAppointment.getStart_at().toLocalDate());
//            randomExpertOccupiedTimes.setCreated_at(LocalDateTime.now());
//            randomExpertOccupiedTimes.setUpdated_at(LocalDateTime.now());
//
//            if (LocalDateTime.of(randomExpertOccupiedTimes.getDate(),randomExpertOccupiedTimes.getStartAt()).isAfter(LocalDateTime.now())){
//                randomExpertOccupiedTimes.setStatus(1);
//            }else if(LocalDateTime.of(randomExpertOccupiedTimes.getDate(),randomExpertOccupiedTimes.getStartAt()).isBefore(LocalDateTime.now()) && LocalDateTime.of(randomExpertOccupiedTimes.getDate(),randomExpertOccupiedTimes.getEndAt()).isAfter(LocalDateTime.now())){
//                randomExpertOccupiedTimes.setStatus(3);
//            }else{
//                randomExpertOccupiedTimes.setStatus(4);
//            }
//
//            expert.getExpertOccupiedTimesList().add(randomExpertOccupiedTimes);
//
//            _expertOccupiedTimeRepository.save(randomExpertOccupiedTimes);
//
//            //Payment
//
//            Payments payments = new Payments(4, appointments.getTotal(), LocalDateTime.now(), LocalDateTime.now());
//
////            if (LocalDateTime.now().isAfter(LocalDateTime.of(today, endTime))) {
////                payments.setStatus(4);
////            } else {
////                payments.setStatus(2);
////            }
//
//            payments.setZpTransId(0);
//
//            payments.setAppointments(appointments);
//            appointments.setPayments(payments);
//
//            List<Payments> paymentsList = new ArrayList<>();
//            paymentsList.add(payments);
//
//            payments.setPaymentMethods(payPal);
//            payPal.setPayments(paymentsList);
//
//            _paymentRepository.save(payments);
//
//            //Payment Done
//
//            Payments paymentsDone = new Payments(4, appointmentsDone.getTotal(), LocalDateTime.now(), LocalDateTime.now());
//
//            paymentsDone.setZpTransId(0);
//
//            paymentsDone.setAppointments(appointmentsDone);
//            appointmentsDone.setPayments(paymentsDone);
//
//            List<Payments> paymentsListDone = new ArrayList<>();
//            paymentsListDone.add(paymentsDone);
//
//            paymentsDone.setPaymentMethods(cash);
//            cash.setPayments(paymentsListDone);
//
//            _paymentRepository.save(paymentsDone);
//
//            //Service Ratings
//
//            ServiceRatings serviceRatingsDone = new ServiceRatings();
//
//            serviceRatingsDone.setAppointments(appointmentsDone);
//            serviceRatingsDone.setUsers(usersCustomer);
//            serviceRatingsDone.setServices(massageService);
//            serviceRatingsDone.setRating(5);
//            serviceRatingsDone.setFeedback("Good Service!");
//            serviceRatingsDone.setStatus(4);
//            serviceRatingsDone.setUpdated_at(LocalDateTime.now());
//            serviceRatingsDone.setCreated_at(LocalDateTime.now());
//
//            List<ServiceRatings> serviceRatingsListDone = new ArrayList<>();
//            serviceRatingsListDone.add(serviceRatingsDone);
//
//            appointmentsDone.setServiceRatings(serviceRatingsDone);
//            usersCustomer.setServiceRatingsList(serviceRatingsListDone);
//            massageService.setServiceRatingsList(serviceRatingsListDone);
//
//            _serviceRatingsRepository.save(serviceRatingsDone);
//
//            //Expert Ratings
//
//            ExpertRatings expertRatingsDone = new ExpertRatings();
//            expertRatingsDone.setAppointments(appointmentsDone);
//            expertRatingsDone.setUsers(usersCustomer);
//            expertRatingsDone.setExperts(expert2);
//            expertRatingsDone.setRating(5L);
//            expertRatingsDone.setFeedback("Friendly");
//            expertRatingsDone.setStatus(1);
//            expertRatingsDone.setCreated_at(LocalDateTime.now());
//            expertRatingsDone.setUpdated_at(LocalDateTime.now());
//
//            List<ExpertRatings> expertRatingsListDone = new ArrayList<>();
//            expertRatingsListDone.add(expertRatingsDone);
//
//            appointmentsDone.setExpertRatings(expertRatingsDone);
//            usersCustomer.setExpertRatingsList(expertRatingsListDone);
//            expert2.setExpertRatingsList(expertRatingsListDone);
//
//            _expertRatingsRepository.save(expertRatingsDone);
//
//            //Quizzes
//
//            Quizzes quizzes = new Quizzes();
//            quizzes.setName("Skin Quiz");
//            quizzes.setQuestionsList(new ArrayList<>());
//            quizzes.setQuizResultsList(new ArrayList<>());
//            quizzes.setCreateAt(LocalDateTime.now());
//            quizzes.setUpdateAt(LocalDateTime.now());
//
//            List<Questions> questionsList = new ArrayList<>();
//
//            //Quest1
//            Questions question1 = new Questions();
//
//            question1.setTitle("After washing your face, how does your skin feel?");
//            question1.setCreatedAt(LocalDateTime.now());
//            question1.setUpdatedAt(LocalDateTime.now());
//            question1.setAnswersList(new ArrayList<>());
//
//            List<Answers> question1AnswersList = new ArrayList<>(Arrays.asList(
//                    new Answers("Smooth, Not Dry.",question1,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Tight and Dry.",question1,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Oily.",question1,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("The T-zone is oily, but the cheeks are dry.",question1,LocalDateTime.now(),LocalDateTime.now())
//            ));
//            question1.setAnswersList(question1AnswersList);
//            questionsList.add(question1);
//            question1.setQuizzes(quizzes);
//
//            //Quest2
//            Questions question2 = new Questions();
//
//            question2.setTitle("When during the day does your skin usually get shiny/oily?");
//            question2.setCreatedAt(LocalDateTime.now());
//            question2.setUpdatedAt(LocalDateTime.now());
//            question2.setAnswersList(new ArrayList<>());
//
//            List<Answers> question2AnswersList = new ArrayList<>(Arrays.asList(
//                    new Answers("Almost never gets oily.", question2,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Never oily throughout the day, might be slightly dry.", question2,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Always oily, especially in the forehead, nose, and chin areas.", question2,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Only oily in the T-zone, other areas are normal or dry.", question2,LocalDateTime.now(),LocalDateTime.now())
//            ));
//            question2.setAnswersList(question2AnswersList);
//            questionsList.add(question2);
//            question2.setQuizzes(quizzes);
//
//            //Quest3
//            Questions question3 = new Questions();
//
//            question3.setTitle("How does your skin react when the weather changes?");
//            question3.setCreatedAt(LocalDateTime.now());
//            question3.setUpdatedAt(LocalDateTime.now());
//            question3.setAnswersList(new ArrayList<>());
//
//            List<Answers> question3AnswersList = new ArrayList<>(Arrays.asList(
//                    new Answers("Little change, skin remains stable.", question3,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Very dry, easily flakes.", question3,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Prone to breakouts, gets oilier in hot weather.", question3,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Becomes more sensitive, T-zone gets oily, cheeks get dry.", question3,LocalDateTime.now(),LocalDateTime.now())
//            ));
//            question3.setAnswersList(question3AnswersList);
//            questionsList.add(question3);
//            question3.setQuizzes(quizzes);
//
//            //Quest4
//            Questions question4 = new Questions();
//
//            question4.setTitle("How would you describe your pores?");
//            question4.setCreatedAt(LocalDateTime.now());
//            question4.setUpdatedAt(LocalDateTime.now());
//            question4.setAnswersList(new ArrayList<>());
//
//            List<Answers> question4AnswersList = new ArrayList<>(Arrays.asList(
//                    new Answers("Small pores, difficult to see.", question4,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Very small or not clearly visible.", question4,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Large, easily visible.", question4,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Large in the T-zone, but small on the cheeks.", question4,LocalDateTime.now(),LocalDateTime.now())
//            ));
//            question4.setAnswersList(question4AnswersList);
//            questionsList.add(question4);
//            question4.setQuizzes(quizzes);
//
//            //Quest5
//            Questions question5 = new Questions();
//
//            question5.setTitle("How does your skin typically feel after applying moisturizer?");
//            question5.setCreatedAt(LocalDateTime.now());
//            question5.setUpdatedAt(LocalDateTime.now());
//            question5.setAnswersList(new ArrayList<>());
//
//            List<Answers> question5AnswersList = new ArrayList<>(Arrays.asList(
//                    new Answers("Feels comfortable, neither too shiny nor dry.", question5,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Skin absorbs moisture immediately, but still feels dry.", question5,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Usually feels greasy or sticky.", question5,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("T-zone becomes slightly shiny, other areas feel comfortable.", question5,LocalDateTime.now(),LocalDateTime.now())
//            ));
//            question5.setAnswersList(question5AnswersList);
//            questionsList.add(question5);
//            question5.setQuizzes(quizzes);
//
//            //Quest6
//            Questions question6 = new Questions();
//
//            question6.setTitle("Do you experience problems with acne or skin irritation?");
//            question6.setCreatedAt(LocalDateTime.now());
//            question6.setUpdatedAt(LocalDateTime.now());
//            question6.setAnswersList(new ArrayList<>());
//
//            List<Answers> question6AnswersList = new ArrayList<>(Arrays.asList(
//                    new Answers("Rarely occurs.", question6,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Skin is easily irritated, itchy, or flaky.", question6,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Frequently get acne, clogged pores.", question6,LocalDateTime.now(),LocalDateTime.now()),
//                    new Answers("Acne appears mainly in the T-zone.", question6,LocalDateTime.now(),LocalDateTime.now())
//            ));
//            question6.setAnswersList(question6AnswersList);
//            questionsList.add(question6);
//            question6.setQuizzes(quizzes);
//
//            quizzes.setQuestionsList(questionsList);
//
//            _quizzesRepository.save(quizzes);
//
//            QuizResults quizResults1 = new QuizResults();
//            quizResults1.setQuizzes(quizzes);
//            quizResults1.setUsers(usersCustomer);
//            quizResults1.setFinalResult("Dry Skin");
//            quizResults1.setUpdatedAt(LocalDateTime.now());
//            quizResults1.setCreatedAt(LocalDateTime.now());
//
//            List<QuizResults> quizResultsList = new ArrayList<>();
//            quizResultsList.add(quizResults1);
//
//            quizzes.setQuizResultsList(quizResultsList);
//
//            _quizResultsRepository.save(quizResults1);
//
//        }
//    }

}
