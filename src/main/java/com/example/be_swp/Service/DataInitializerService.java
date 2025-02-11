package com.example.be_swp.Service;

import com.example.be_swp.Models.Roles;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final ServiceRepository _serviceRepository;

    public DataInitializerService(RolesRepository _rolesRepository, UsersRepository _usersRepository, WorkScheduleRepository _workScheduleRepository, PaymentMethodRepository _paymentMethodRepository, PaymentRepository _paymentRepository, ExpertRepository _expertRepository, AppointmentRepository _appointmentRepository, ServiceRepository _serviceRepository) {
        this._rolesRepository = _rolesRepository;
        this._usersRepository = _usersRepository;
        this._workScheduleRepository = _workScheduleRepository;
        this._paymentMethodRepository = _paymentMethodRepository;
        this._paymentRepository = _paymentRepository;
        this._expertRepository = _expertRepository;
        this._appointmentRepository = _appointmentRepository;
        this._serviceRepository = _serviceRepository;
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

        // Users

        Users usersAdmin = new Users("admin","123","admin","admin@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );
        usersAdmin.setRoles(rolesAdmin);
        Users usersStaff = new Users("staff","123","staff","staff@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );
        usersStaff.setRoles(rolesStaff);
        Users usersEsthetician = new Users("expert","123","expert","expert@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );
        usersEsthetician.setRoles(rolesExpert);
        Users usersCustomer = new Users("customer","123","customer","customer@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );
        usersCustomer.setRoles(rolesCustomer);
        Users usersCustomer1 = new Users("customer1","123","customer1","customer@gmail.com","0123456789",true, LocalDateTime.now(),LocalDateTime.now() );
        usersCustomer1.setRoles(rolesCustomer);

        List<Users> adminList = new ArrayList<>();
        adminList.add(usersAdmin);

        List<Users> staffList = new ArrayList<>();
        staffList.add(usersStaff);

        List<Users> estheticianList = new ArrayList<>();
        estheticianList.add(usersEsthetician);

        List<Users> customerList = new ArrayList<>();
        customerList.add(usersCustomer);
        customerList.add(usersCustomer1);

        rolesAdmin.setUsers(adminList);
        rolesStaff.setUsers(staffList);
        rolesExpert.setUsers(estheticianList);
        rolesCustomer.setUsers(customerList);

        _rolesRepository.save(rolesAdmin);
        _rolesRepository.save(rolesCustomer);
        _rolesRepository.save(rolesStaff);
        _rolesRepository.save(rolesExpert);

//        _usersRepository.save(usersAdmin);
//        _usersRepository.save(usersCustomer);
//        _usersRepository.save(usersCustomer1);
//        _usersRepository.save(usersStaff);
//        _usersRepository.save(usersEsthetician);

    }

}
