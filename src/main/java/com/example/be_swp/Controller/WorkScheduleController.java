package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.WorkScheduleDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.WorkScheduleService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workSchedule")
public class WorkScheduleController {

    private final WorkScheduleService _workScheduleService;

    public WorkScheduleController(WorkScheduleService workScheduleService){
        _workScheduleService = workScheduleService;
    }

    @GetMapping("/findAll")
    public ApiResponse<List<WorkScheduleDTO>> findAll(){
        List<WorkScheduleDTO> workScheduleDTOList = _workScheduleService.findAll();

        String status = "";
        String message = "";

        if (workScheduleDTOList.isEmpty()){
            status = "404";
            message = "Work Schedules Not Found!";
        }else {
            status = "200";
            message = "Get all work schedules successfully!";
        }

        return new ApiResponse<>(status,workScheduleDTOList,message);

    }

    @GetMapping("/findById/{id}")
    public ApiResponse<WorkScheduleDTO> findById(@PathVariable int id){
        WorkScheduleDTO workScheduleDTO = _workScheduleService.findById(id);

        String status = "";
        String message = "";

        if (workScheduleDTO.getWorkScheduleId() == -1){
            status = "404";
            message = "Work Schedule Not Found!";
        }else {
            status = "200";
            message = "Find WorkSchedule Successfully!";
        }

        workScheduleDTO.setWorkScheduleId(id);

        return new ApiResponse<>(status,workScheduleDTO,message);
    }

    @GetMapping("/findByExpert")
    public ApiResponse<List<WorkScheduleDTO>> findByExpert(@RequestParam String nameOrId){
        List<WorkScheduleDTO> workScheduleDTOList = _workScheduleService.findByExpertNameOrId(nameOrId);

        String status = "";
        String message = "";

        if (workScheduleDTOList.isEmpty()){
            status = "404";
            message = "No Work Schedule Found With " + nameOrId;
        }else {
            status = "200";
            message = "Get all work schedules with " + nameOrId + " successfully!";
        }

        return new ApiResponse<>(status,workScheduleDTOList,message);
    }

    @GetMapping("findAllToday")
    public ApiResponse<List<WorkScheduleDTO>> findAllToday(){

        List<WorkScheduleDTO> workScheduleDTOList = _workScheduleService.findAllToday();

        String status = "";
        String message = "";

        if (workScheduleDTOList.isEmpty()){
            status = "404";
            message = "Work Schedules Not Found!";
        }else {
            status = "200";
            message = "Get all work schedules for today successfully!";
        }

        return new ApiResponse<>(status,workScheduleDTOList,message);
    }

    @GetMapping("findAllTodayByExpertId/{expertId}")
    public ApiResponse<List<WorkScheduleDTO>> findAllTodayByExpertId(@PathVariable int expertId){

        List<WorkScheduleDTO> workScheduleDTOList = _workScheduleService.findAllTodayByExpertId(expertId);

        String status = "";
        String message = "";

        if (workScheduleDTOList.isEmpty()){
            status = "404";
            message = "Work Schedules Not Found!";
        }else {
            status = "200";
            message = "Get all work schedules for today with expert id: " + expertId + " successfully!";
        }

        return new ApiResponse<>(status,workScheduleDTOList,message);

    }

    @PostMapping("/add")
    public ApiResponse<WorkScheduleDTO> add(@RequestBody WorkScheduleDTO workScheduleDTO, @RequestParam int expertId){
        workScheduleDTO = _workScheduleService.add(workScheduleDTO,expertId);

        String status = "";
        String message = "";

        switch (workScheduleDTO.getWorkScheduleId()){
            case -1:
                status = "400";
                message = "Invalid Time!";
                break;
            case -2:
                status = "404";
                message = "No Such Expert Exist!";
                break;
            case -3:
                status = "400";
                message = "Overlap work schedule!";
                break;
            default:
                status = "200";
                message = "Add work schedule successfully!";
                break;
        }

        return new ApiResponse<>(status,workScheduleDTO,message);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<WorkScheduleDTO> update(@PathVariable int id){

        return new ApiResponse<>("Not Sure",new WorkScheduleDTO(),"What To Do Here");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<WorkScheduleDTO> delete(@PathVariable int id){
        WorkScheduleDTO workScheduleDTO = _workScheduleService.delete(id);

        String status = "";
        String message = "";

        if (workScheduleDTO.getWorkScheduleId() == -1){
            status = "404";
            message = "Work Schedule Not Found!";
        }else {
            status = "200";
            message = "Delete Successfully!";
        }

        workScheduleDTO.setWorkScheduleId(id);

        return new ApiResponse<>(status,workScheduleDTO,message);
    }

    @PostMapping("/checkIn/{id}")
    public ApiResponse<WorkScheduleDTO> checkInSchedule(@PathVariable int Id){

        return new ApiResponse<>();
    }

    @PostMapping("/checkOut/{id}")
    public ApiResponse<WorkScheduleDTO> checkOutSchedule(@PathVariable int Id){

        return new ApiResponse<>();
    }

}
