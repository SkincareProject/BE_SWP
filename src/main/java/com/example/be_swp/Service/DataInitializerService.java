package com.example.be_swp.Service;

import com.example.be_swp.Models.Customers;
import com.example.be_swp.Models.Roles;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.CustomersRepository;
import com.example.be_swp.Repository.RolesRepository;
import com.example.be_swp.Repository.UsersRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataInitializerService {

    private final RolesRepository _rolesRepository;
    private final UsersRepository _usersRepository;
    private final CustomersRepository _customerRepository;

    public DataInitializerService (RolesRepository repository, UsersRepository usersRepository, CustomersRepository customerRepository){

        _rolesRepository = repository;
        _usersRepository = usersRepository;
        _customerRepository = customerRepository;
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

        // Users

        Users usersAdmin = new Users("admin","123","admin","admin@gmail.com","0123456789");
        usersAdmin.setRoles(admin);
        Users usersStaff = new Users("staff","123","staff","staff@gmail.com","0123456789");
        usersStaff.setRoles(staff);
        Users usersEsthetician = new Users("esthetician","123","esthe","esthetician@gmail.com","0123456789");
        usersEsthetician.setRoles(esthetician);
        Users usersCustomer = new Users("customer","123","customer","customer@gmail.com","0123456789");
        usersCustomer.setRoles(customer);
        Users usersCustomer1 = new Users("customer1","123","customer1","customer@gmail.com","0123456789");
        usersCustomer1.setRoles(customer);

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
//        _usersRepository.save(usersCustomer1);
//        _usersRepository.save(usersStaff);
//        _usersRepository.save(usersEsthetician);
        Customers customers = new Customers(usersCustomer.getId(),usersCustomer.getUsername(),usersCustomer.getEmail());
        Customers customers1 = new Customers(usersCustomer1.getId(),usersCustomer1.getUsername(),usersCustomer1.getEmail());

        _customerRepository.save(customers1);
        _customerRepository.save(customers);

    }

}
