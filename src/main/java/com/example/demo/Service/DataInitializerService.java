package com.example.demo.Service;

import com.example.demo.Models.Roles;
import com.example.demo.Models.Users;
import com.example.demo.Repository.RolesRepository;
import com.example.demo.Repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataInitializerService {

    private final RolesRepository _rolesRepository;
    private final UsersRepository _usersRepository;

    public DataInitializerService (RolesRepository repository, UsersRepository usersRepository){

        _rolesRepository = repository;
        _usersRepository = usersRepository;
    }


    @PostConstruct
    public void init(){

        // Roles
        Roles admin = new Roles();
        Roles customer = new Roles();
        Roles staff = new Roles();
        Roles esthetician = new Roles();

        admin.setName("ADMIN");
        admin.setDescription("ROLE ADMIN");

        customer.setName("CUSTOMER");
        customer.setDescription("ROLE CUSTOMER");

        staff.setName("STAFF");
        staff.setDescription("ROLE STAFF");

        esthetician.setName("ESTHETICIAN");
        esthetician.setDescription("ROLE ESTHETICIAN");

        _rolesRepository.save(admin);
        _rolesRepository.save(customer);
        _rolesRepository.save(staff);
        _rolesRepository.save(esthetician);

        // Users

        Users usersAdmin = new Users("admin","123","admin@gmail.com");
        usersAdmin.setRoles(admin);
        Users usersStaff = new Users("staff","123","staff@gmail.com");
        usersStaff.setRoles(staff);
        Users usersEsthetician = new Users("esthetician","123","esthetician@gmail.com");
        usersEsthetician.setRoles(esthetician);
        Users usersCustomer = new Users("customer","123","customer@gmail.com");
        usersCustomer.setRoles(customer);
        Users usersCustomer1 = new Users("customer1","123","customer@gmail.com");
        usersCustomer1.setRoles(customer);

        _usersRepository.save(usersAdmin);
        _usersRepository.save(usersCustomer);
        _usersRepository.save(usersCustomer1);
        _usersRepository.save(usersStaff);
        _usersRepository.save(usersEsthetician);


        List<Users> adminList = new ArrayList<>();
        adminList.add(usersAdmin);

        List<Users> staffList = new ArrayList<>();
        staffList.add(usersStaff);

        List<Users> estheticianList = new ArrayList<>();
        estheticianList.add(usersEsthetician);

        List<Users> customerList = new ArrayList<>();
        customerList.add(usersCustomer);
        customerList.add(usersCustomer1);

        admin.setUsers(adminList);
        staff.setUsers(staffList);
        esthetician.setUsers(estheticianList);
        customer.setUsers(customerList);

        _rolesRepository.save(admin);
        _rolesRepository.save(customer);
        _rolesRepository.save(staff);
        _rolesRepository.save(esthetician);

//        _usersRepository.save(usersAdmin);
//        _usersRepository.save(usersCustomer);
//        _usersRepository.save(usersStaff);
//        _usersRepository.save(usersEsthetician);
    }

}
