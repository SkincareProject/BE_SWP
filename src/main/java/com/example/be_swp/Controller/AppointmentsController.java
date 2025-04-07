package com.example.be_swp.Controller;

import com.example.be_swp.Bean.AppointmentBean;
import com.example.be_swp.DTOs.Appointments.AppointmentsDTO;
import com.example.be_swp.Data.RatingData;
import com.example.be_swp.Data.UpdateStatusData;
import com.example.be_swp.Models.*;
import com.example.be_swp.Repository.*;
import com.example.be_swp.mapper.appointment.AppointmentMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
@AllArgsConstructor
public class AppointmentsController {

    @Autowired
    private AppointmentRepository _appointmentRepository;
    @Autowired
    private ExpertOccupiedTimeRepository _expertOccupiedTimeRepository;


    private AppointmentMapper _appointmentMapper;
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ServiceRatingsRepository serviceRatingsRepository;
    @Autowired
    private ExpertRatingsRepository expertRatingsRepository;

    @GetMapping("/getAll")
    public ApiResponse<?> getAllAppointments() {
        List<Appointments> appointmentsDtoList = _appointmentRepository.findAll();
        String status = "";
        String message = "";

        return new ApiResponse<>(status,appointmentsDtoList,message);
    }

    @PostMapping("/createOrUpdate")
    public ApiResponse<?> createOrUpdate(@RequestBody Appointments body) {
        // Check for expert availability


        List<ExpertOccupiedTimes> expertOccupiedTimesList = _expertOccupiedTimeRepository.findOccupiedTimes(
                body.getExpertId(), body.getBookingDate(), body.getStartAt()
        );

        if (expertOccupiedTimesList.size() > 0) {
            return new ApiResponse<>("409", null, "Expert has another appointment in this time");
        }


        // Check if the appointment exists
        Appointments toSave = null;

        if(body.getAppointmentId() == null){
            toSave=new Appointments();

        }else {
            Optional<Appointments> appointments = _appointmentRepository.findById(body.getAppointmentId());
            toSave = appointments.orElseGet(Appointments::new);

        }



        toSave.setServiceId(body.getServiceId());
        toSave.setUserId(body.getUserId());
        toSave.setStartAt(body.getStartAt());
        toSave.setStatus(0); // Assuming status is intentionally set to 0
        toSave.setExpertId(body.getExpertId());
        toSave.setEndAt(body.getEndAt());
        toSave.setBookingDate(body.getBookingDate());
        toSave.setTotal(body.getTotal());



        Appointments save = _appointmentRepository.save(toSave);
       //# start  expert occupied time region

        ExpertOccupiedTimes toSaveExpertOccupiedTimes= _expertOccupiedTimeRepository.findByAppointmentId(toSave.getAppointmentId());

        if(toSaveExpertOccupiedTimes==null){
            toSaveExpertOccupiedTimes = new ExpertOccupiedTimes();
        }
        toSaveExpertOccupiedTimes.setAppointmentId(save.getAppointmentId());
        toSaveExpertOccupiedTimes.setDate(toSave.getBookingDate());
        toSaveExpertOccupiedTimes.setStartAt(toSave.getStartAt());
        toSaveExpertOccupiedTimes.setEndAt(toSave.getEndAt());
        toSaveExpertOccupiedTimes.setExpertId(body.getExpertId());
        toSaveExpertOccupiedTimes.setStatus(0);


        try{
            ExpertOccupiedTimes tosavexxx=_expertOccupiedTimeRepository.save(toSaveExpertOccupiedTimes);
        }catch (Exception e){
            throw  new RuntimeException(e.getMessage());
        }

        //# end expert occupied time region


        return new ApiResponse<>("200", save, null);
    }

    @DeleteMapping("/delete")
    public ApiResponse<?> delete(@RequestBody Long appointmentId){
        Optional<Appointments> appointments = _appointmentRepository.findById(appointmentId);
        if(appointments.isEmpty()){
            return new ApiResponse<>("404", null, "appointment not found");
        }
        Appointments toDelete = appointments.get();
//        toDelete.setStatus(0);
        _appointmentRepository.delete(toDelete);
        return new ApiResponse<>("200", null, "delete success");


    }
    @PostMapping("/getUsersAppointment")
    public ApiResponse<?> getAppointmentById(@RequestParam Long userId){
        List<AppointmentsDTO> appointments=_appointmentRepository.findAllByUserId(userId).stream().map(appointments1 ->{
                    AppointmentsDTO appointDTO= _appointmentMapper.mapToDTO(appointments1);
                    return appointDTO;
                }
                ).toList();
        return   new ApiResponse<>("200", appointments, null);
    }

    @GetMapping("/getById/{id}")
    public ApiResponse<?> getById(@PathVariable Long id){
       Appointments  appointments = _appointmentRepository.findByAppointmentId(id);
        AppointmentsDTO appointmentsDTO=null;
       if(appointments!=null){
           appointmentsDTO=_appointmentMapper.mapToDTO(appointments);

       }
        return  new ApiResponse<>("200", appointmentsDTO, "success");
    }

    @PostMapping("/reschedule/{id}")
    public ApiResponse<?> reschedule(@PathVariable Long id ,@RequestBody AppointmentBean body){
        if(id == null){
            throw new NullPointerException("id is null");
        }
        Appointments appointments = _appointmentRepository.findByAppointmentId(id);
        if(appointments==null){
            throw new NullPointerException("appointment not found");
        }

        ExpertOccupiedTimes expertOccupiedTimes=_expertOccupiedTimeRepository.findByExpertOccupiedTimeId(body.getExpertScheduleId());

        if(expertOccupiedTimes==null){
            throw new NullPointerException("Error");

        }

        expertOccupiedTimes.setStartAt(body.getStartAt());
        expertOccupiedTimes.setEndAt(body.getEndAt());
        expertOccupiedTimes.setDate(body.getDate());

        appointments.setExpertId(body.getExpertScheduleId());
        appointments.setBookingDate(body.getDate());
        appointments.setStartAt(body.getStartAt());
        appointments.setEndAt(body.getEndAt());



        _appointmentRepository.save(appointments);
        _expertOccupiedTimeRepository.save(expertOccupiedTimes);

        return   new ApiResponse<>("200", appointments, null);



    }

    @PostMapping("/updateStatus")
    public ApiResponse<?> updateStatus(@RequestBody UpdateStatusData body){
        if(body == null|| body.getId()==null){
            throw new NullPointerException("id is null");
        }

        Appointments appointments = _appointmentRepository.findByAppointmentId(body.getId());
        if(appointments==null){
            throw new NullPointerException("appointment not found");
        }

        appointments.setStatus(body.getStatus());
        _appointmentRepository.save(appointments);

        return   new ApiResponse<>("200", appointments, null);


    }

    @PostMapping("/rating")
    public ApiResponse<?> rating(@RequestBody RatingData serviceRatings){

        Appointments appointments=_appointmentRepository.findByAppointmentId(serviceRatings.getAppointmentId());

        if(appointments==null){
            throw new NullPointerException("appointment not found");
        }

//        ExpertRatings expertRatings=new ExpertRatings();
//
//        expertRatings.setRating(serviceRatings.getExpertRating());
//        expertRatings.setFeedback(serviceRatings.getExpertFeedback());
//        expertRatings.setUserId(appointments.getUserId());
//        expertRatings.setExpertId(appointments.getExpertId());


        ServiceRatings service_rating=new ServiceRatings();
        service_rating.setRating(serviceRatings.getServiceRating());
        service_rating.setFeedback(serviceRatings.getServiceFeedback());
        service_rating.setUserId(appointments.getUserId());
        service_rating.setServiceId(appointments.getServiceId());



        serviceRatingsRepository.save(service_rating);
//        expertRatingsRepository.save(expertRatings);

        return   new ApiResponse<>("200", service_rating, null);

    }
}
