package com.example.be_swp.Service;

import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public DataInitializerService(RolesRepository _rolesRepository, UsersRepository _usersRepository, WorkScheduleRepository _workScheduleRepository, PaymentMethodRepository _paymentMethodRepository, PaymentRepository _paymentRepository, ExpertRepository _expertRepository, AppointmentRepository _appointmentRepository, ServicesRepository _serviceRepository) {
        this._rolesRepository = _rolesRepository;
        this._usersRepository = _usersRepository;
        this._workScheduleRepository = _workScheduleRepository;
        this._paymentMethodRepository = _paymentMethodRepository;
        this._paymentRepository = _paymentRepository;
        this._expertRepository = _expertRepository;
        this._appointmentRepository = _appointmentRepository;
        this._servicesRepository = _serviceRepository;
    }

    @PostConstruct
    public void init(){

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

        PaymentMethods cash = new PaymentMethods("Cash","Pay with cash",1,LocalDateTime.now(),LocalDateTime.now());

        PaymentMethods payPal = new PaymentMethods("Paypal","Pay with paypal",1,LocalDateTime.now(),LocalDateTime.now());

        PaymentMethods visa = new PaymentMethods("Visa","Pay with visa",1,LocalDateTime.now(),LocalDateTime.now());

        PaymentMethods masterCard = new PaymentMethods("Master Card","Pay with master card",1,LocalDateTime.now(),LocalDateTime.now());

        PaymentMethods creditCard = new PaymentMethods("Credit Card","Pay with credit card",1,LocalDateTime.now(),LocalDateTime.now());

        _paymentMethodRepository.save(cash);
        _paymentMethodRepository.save(payPal);
        _paymentMethodRepository.save(visa);
        _paymentMethodRepository.save(masterCard);
        _paymentMethodRepository.save(creditCard);

        // Users

        Users usersAdmin = new Users("admin","123","John Admin","admin@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );

        Users usersStaff = new Users("staff","123","John Staff","staff@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );

        Users usersExpert = new Users("expert","123","John Expert","expert@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );

        Users usersCustomer = new Users("customer","123","John Customer","customer@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );

        Users usersCustomer1 = new Users("customer1","123","John Customer One","customer@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );

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

        Experts expert = new Experts("Facial Treatments",6,"This is John Expert, John can make your face more beautiful.",1,LocalDateTime.now(),LocalDateTime.now());

        //Map User and Expert

        expert.setUsers(usersExpert);
        usersExpert.setExperts(expert);
        _expertRepository.save(expert);

        //Work Schedule

        WorkSchedule todayMorning = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(11,0), LocalDate.now(),1,LocalDateTime.now(),LocalDateTime.now());
        WorkSchedule todayEvening = new WorkSchedule(LocalTime.of(13,0),LocalTime.of(17,0), LocalDate.now(),1,LocalDateTime.now(),LocalDateTime.now());
        WorkSchedule tomorrowMorning = new WorkSchedule(LocalTime.of(7,0),LocalTime.of(11,0), LocalDate.now().plusDays(1),1,LocalDateTime.now(),LocalDateTime.now());
        WorkSchedule tomorrowEvening = new WorkSchedule(LocalTime.of(13,0),LocalTime.of(17,0), LocalDate.now().plusDays(1),1,LocalDateTime.now(),LocalDateTime.now());

        List<WorkSchedule> workScheduleList = new ArrayList<>();
        workScheduleList.add(todayMorning);
        workScheduleList.add(todayEvening);
        workScheduleList.add(tomorrowMorning);
        workScheduleList.add(tomorrowEvening);

        //Map Work Schedule with Expert

        for(WorkSchedule workSchedule: workScheduleList){
            if (workSchedule.getEnd_at().isBefore(LocalTime.now()) && (workSchedule.getWork_date().isEqual(LocalDate.now()) || workSchedule.getWork_date().isBefore(LocalDate.now()))){
                workSchedule.setStatus(4);
            }
            workSchedule.setExperts(expert);
        }

        expert.setWorkScheduleList(workScheduleList);


        for(WorkSchedule workSchedule: workScheduleList){
            _workScheduleRepository.save(workSchedule);
        }

        //Service

        Services facialService = new Services("All Your Facial Treatments",100,"All of skincare for yo face",60,1,"Facial Treatment",LocalDateTime.now(),LocalDateTime.now());

        _servicesRepository.save(facialService);

        //Appointment
        LocalDate today = LocalDate.now();
        LocalTime startTime = LocalTime.of(8,0);
        LocalTime endTime = startTime.plusMinutes(facialService.getDuration());

        Appointments appointments = new Appointments();

        if(LocalDateTime.now().isAfter(LocalDateTime.of(today,endTime))){
            appointments.setStatus(4);
        }else{
            appointments.setStatus(1);
        }

        appointments.setTotal(facialService.getPrice());
        appointments.setStart_at(LocalDateTime.of(today, startTime));
        appointments.setEnd_at(LocalDateTime.of(today,endTime));
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

        //Payment

        Payments payments = new Payments(4,appointments.getTotal(),LocalDateTime.now(),LocalDateTime.now());

        if(LocalDateTime.now().isAfter(LocalDateTime.of(today,endTime))){
            payments.setStatus(4);
        }else{
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
