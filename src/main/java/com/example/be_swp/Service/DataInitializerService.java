package com.example.be_swp.Service;

import com.example.be_swp.DTOs.ExpertOccupiedTimesDTO;
import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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

    public DataInitializerService(RolesRepository _rolesRepository, UsersRepository _usersRepository, WorkScheduleRepository _workScheduleRepository, PaymentMethodRepository _paymentMethodRepository, PaymentRepository _paymentRepository, ExpertRepository _expertRepository, AppointmentRepository _appointmentRepository, ServicesRepository _serviceRepository, ExpertOccupiedTimeRepository _expertOccupiedTimeRepository) {
        this._rolesRepository = _rolesRepository;
        this._usersRepository = _usersRepository;
        this._workScheduleRepository = _workScheduleRepository;
        this._paymentMethodRepository = _paymentMethodRepository;
        this._paymentRepository = _paymentRepository;
        this._expertRepository = _expertRepository;
        this._appointmentRepository = _appointmentRepository;
        this._servicesRepository = _serviceRepository;
        this._expertOccupiedTimeRepository = _expertOccupiedTimeRepository;
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

            PaymentMethods payPal = new PaymentMethods("Paypal", "Pay with paypal", 1, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods visa = new PaymentMethods("Visa", "Pay with visa", 1, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods masterCard = new PaymentMethods("Master Card", "Pay with master card", 1, LocalDateTime.now(), LocalDateTime.now());

            PaymentMethods creditCard = new PaymentMethods("Credit Card", "Pay with credit card", 1, LocalDateTime.now(), LocalDateTime.now());

            _paymentMethodRepository.save(cash);
            _paymentMethodRepository.save(payPal);
            _paymentMethodRepository.save(visa);
            _paymentMethodRepository.save(masterCard);
            _paymentMethodRepository.save(creditCard);

            // Users

            Users usersAdmin = new Users("admin", "123", "John Admin", "admin@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersStaff = new Users("staff", "123", "John Staff", "staff@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersExpert = new Users("expert", "123", "John Expert", "expert@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersCustomer = new Users("customer", "123", "John Customer", "customer@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            Users usersCustomer1 = new Users("customer1", "123", "John Customer One", "customer@gmail.com", "0123456789", true, LocalDateTime.now(), LocalDateTime.now());

            //Map User and Role
            usersAdmin.setRoles(rolesAdmin);
            List<Users> adminList = new ArrayList<>();
            adminList.add(usersAdmin);

            usersStaff.setRoles(rolesStaff);
            List<Users> staffList = new ArrayList<>();
            staffList.add(usersStaff);

            usersExpert.setRoles(rolesExpert);
            List<Users> expertList = new ArrayList<>();
            expertList.add(usersExpert);

            usersCustomer.setRoles(rolesCustomer);
            usersCustomer1.setRoles(rolesCustomer);
            List<Users> customerList = new ArrayList<>();
            customerList.add(usersCustomer);
            customerList.add(usersCustomer1);

            rolesAdmin.setUsers(adminList);
            rolesStaff.setUsers(staffList);
            rolesExpert.setUsers(expertList);

            rolesCustomer.setUsers(customerList);

            _rolesRepository.save(rolesAdmin);
            _rolesRepository.save(rolesCustomer);
            _rolesRepository.save(rolesStaff);
            _rolesRepository.save(rolesExpert);

            //Experts

            Experts expert = new Experts("Facial Treatments", 6, "This is John Expert, John can make your face more beautiful.", 1, LocalDateTime.now(), LocalDateTime.now());

            //Map User and Expert

            expert.setUsers(usersExpert);
            usersExpert.setExperts(expert);
            _expertRepository.save(expert);

            //Work Schedule

            WorkSchedule todayMorning = new WorkSchedule(LocalTime.of(7, 0), LocalTime.of(11, 0), LocalDate.now(), 1, LocalDateTime.now(), LocalDateTime.now());
            WorkSchedule todayEvening = new WorkSchedule(LocalTime.of(13, 0), LocalTime.of(17, 0), LocalDate.now(), 1, LocalDateTime.now(), LocalDateTime.now());
            WorkSchedule tomorrowMorning = new WorkSchedule(LocalTime.of(7, 0), LocalTime.of(11, 0), LocalDate.now().plusDays(1), 1, LocalDateTime.now(), LocalDateTime.now());
            WorkSchedule tomorrowEvening = new WorkSchedule(LocalTime.of(13, 0), LocalTime.of(17, 0), LocalDate.now().plusDays(1), 1, LocalDateTime.now(), LocalDateTime.now());

            List<WorkSchedule> workScheduleList = new ArrayList<>();
            workScheduleList.add(todayMorning);
            workScheduleList.add(todayEvening);
            workScheduleList.add(tomorrowMorning);
            workScheduleList.add(tomorrowEvening);

            //Map Work Schedule with Expert

            for (WorkSchedule workSchedule : workScheduleList) {
                if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))) {
                    workSchedule.setStatus(4);
                }
                workSchedule.setExperts(expert);
            }

            expert.setWorkScheduleList(workScheduleList);


            for (WorkSchedule workSchedule : workScheduleList) {
                _workScheduleRepository.save(workSchedule);
            }

            //Service

            Services facialService = new Services("All Your Facial Treatments", 100, "All of skincare for yo face", 60, 1, "Facial Treatment", LocalDateTime.now(), LocalDateTime.now());

            _servicesRepository.save(facialService);

            //Appointment
            LocalDate today = LocalDate.now();
            LocalTime startTime = LocalTime.of(8, 0);
            LocalTime endTime = startTime.plusMinutes(facialService.getDuration());

            Appointments appointments = new Appointments();

            if (LocalDateTime.now().isAfter(LocalDateTime.of(today, endTime))) {
                appointments.setStatus(4);
            } else {
                appointments.setStatus(1);
            }

            appointments.setTotal(facialService.getPrice());
            appointments.setStart_at(LocalDateTime.of(today, startTime));
            appointments.setEnd_at(LocalDateTime.of(today, endTime));
            appointments.setCreated_at(LocalDateTime.now());
            appointments.setUpdated_at(LocalDateTime.now());

            appointments.setServices(facialService);
            appointments.setUsers(usersCustomer);
            appointments.setExperts(expert);

            List<Appointments> appointmentsList = new ArrayList<>();
            appointmentsList.add(appointments);

            facialService.setAppointmentsList(appointmentsList);
            usersCustomer.setAppointmentsList(appointmentsList);
            expert.setAppointmentsList(appointmentsList);

            _appointmentRepository.save(appointments);

            // Random Appointment


            Random random = new Random();

            LocalDate randomDate = LocalDate.now().plusDays(random.nextInt(1,7));
            LocalTime randomStartTime = LocalTime.of(random.nextInt(13,17),0,0);
            LocalTime randomEndTime = randomStartTime.plusHours(1);

            Appointments randomAppointment = new Appointments();
            if(LocalDateTime.now().isAfter(LocalDateTime.of(today,endTime))){
                randomAppointment.setStatus(4);
            }else{
                randomAppointment.setStatus(1);
            }

            randomAppointment.setStart_at(LocalDateTime.of(randomDate,randomStartTime));
            randomAppointment.setEnd_at(LocalDateTime.of(randomDate,randomEndTime));
            randomAppointment.setTotal(facialService.getPrice());
            randomAppointment.setCreated_at(LocalDateTime.now());
            randomAppointment.setUpdated_at(LocalDateTime.now());
            randomAppointment.setServices(facialService);
            randomAppointment.setExperts(expert);
            randomAppointment.setUsers(usersCustomer1);

            List<Appointments> randomAppointmentsList = new ArrayList<>();

            randomAppointmentsList.add(randomAppointment);

            usersCustomer1.setAppointmentsList(randomAppointmentsList);
            expert.getAppointmentsList().add(randomAppointment);
            facialService.getAppointmentsList().add(randomAppointment);

            _appointmentRepository.save(randomAppointment);
            // Occupied Time

            List<ExpertOccupiedTimes> expertOccupiedTimesList = new ArrayList<>();

            ExpertOccupiedTimes expertOccupiedTimes = new ExpertOccupiedTimes();

            expertOccupiedTimes.setExperts(expert);
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
            expert.setExpertOccupiedTimesList(expertOccupiedTimesList);

            _expertOccupiedTimeRepository.save(expertOccupiedTimes);

            //Occupied Time Random
            ExpertOccupiedTimes randomExpertOccupiedTimes = new ExpertOccupiedTimes();

            randomExpertOccupiedTimes.setExperts(expert);
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

            expert.getExpertOccupiedTimesList().add(randomExpertOccupiedTimes);

            _expertOccupiedTimeRepository.save(randomExpertOccupiedTimes);

            //Payment

            Payments payments = new Payments(4, appointments.getTotal(), LocalDateTime.now(), LocalDateTime.now());

            if (LocalDateTime.now().isAfter(LocalDateTime.of(today, endTime))) {
                payments.setStatus(4);
            } else {
                payments.setStatus(2);
            }

            payments.setAppointments(appointments);
            appointments.setPayments(payments);

            List<Payments> paymentsList = new ArrayList<>();
            paymentsList.add(payments);

            payments.setPaymentMethods(payPal);
            payPal.setPayments(paymentsList);

            _paymentRepository.save(payments);

        }
    }

}
