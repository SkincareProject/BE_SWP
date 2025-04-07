package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.ServicesDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.ServiceRatings;
import com.example.be_swp.Models.Services;
import com.example.be_swp.Models.Users;
import com.example.be_swp.Repository.ServiceRatingsRepository;
import com.example.be_swp.Repository.ServicesRepository;
import com.example.be_swp.Repository.UsersRepository;
import com.example.be_swp.Service.ServicesService;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/services")
@AllArgsConstructor
public class ServiceController {

    private final UsersRepository usersRepository;
    private ServicesRepository servicesRepository;
    private ServiceRatingsRepository serviceRatingsRepository;
//    private Ser servicesService;

    @GetMapping("/findAll")
    public ApiResponse<?> findAll(){

        List <Services> services= servicesRepository.findAll();

        return  new ApiResponse<>(null, services, null);
    }

    @PostMapping("/createOrUpdate")
    public ApiResponse<?> createOrUpdate(@RequestBody Services services){
        if(services==null){
            return new ApiResponse<>("400", null,null);
        }
        Services toSave = null;



        if(services.getServiceId()!=null){
          toSave =servicesRepository.findById(services.getServiceId()).orElse(null);

        }else{
            toSave = new Services();

        }




        ///  update or create

//        toSave.setServiceId(services.getServiceId());
        toSave.setServiceName(services.getServiceName());
        toSave.setPrice(services.getPrice());
        toSave.setStatus(services.getStatus());
        toSave.setDescription(services.getDescription());
        toSave.setDuration(services.getDuration());
        toSave.setSkinType(services.getType());
        toSave.setImage(services.getImage());
        toSave=servicesRepository.save(toSave);


        return  new ApiResponse<>("200", toSave, "create or update success");

    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> delete(@PathVariable Long id){
        Services toDelete = null;
        Optional<Services> service =servicesRepository.findById(id);
        if(id==null || id==0) {
            return new ApiResponse<>("400", null,null);
        }
        servicesRepository.deleteById(id);
        return  new ApiResponse<>("200", toDelete, "delete success");

    }
    @GetMapping("/findById/{id}")
    public ApiResponse<?> findById(@PathVariable Long id){

        Services service =servicesRepository.findByServiceId(id);
        if(id==null || id==0) {
            return new ApiResponse<>("404", null,null);
        }
        return  new ApiResponse<>("200", service, "find success");

    }

    @GetMapping("/rating/{id}")
    public ApiResponse<?> findRating(@PathVariable Long id){

        if(id==null || id==0) {
            return new ApiResponse<>("404", null,null);

        }
        Services services   = servicesRepository.findByServiceId(id);

        List<?> serviceRatingsList = serviceRatingsRepository.findAllByServiceId(id)
                .stream().map(rating-> {
                    Users user =usersRepository.findById(rating.getUserId()).orElse(null);
                    if(user==null) {return null;}
                    HashMap<String, Object> object =new HashMap<>();
                    object.put("createdAt",rating.getCreatedAt());
                    object.put("serviceId",services.getServiceId());
                    object.put("userId",user.getId());
                    object.put("userName",user.getUsername());
                    object.put("rating",rating.getRating());
                    object.put("comment",rating.getFeedback());



                    return object;
                })
                .collect(Collectors.toList());



        return  new ApiResponse<>("200", serviceRatingsList, "find success");
    }


}
