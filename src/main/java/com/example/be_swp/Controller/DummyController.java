package com.example.be_swp.Controller;


import com.example.be_swp.Models.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    @GetMapping("/hello")
    public ApiResponse<String> hello(){
        return new ApiResponse<>("200","Ta","Message");
    }

    @GetMapping("/dateTime")
    public ApiResponse<LocalDateTime> dateTime(){
        return new ApiResponse<>("200",LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh")),"Message");
    }

    @PostMapping("/add")
    public String add(){
        return "This is Post in Dummy Controller";
    }

    @PutMapping("/update")
    public String update(){
        return "This is Put in Dummy Controller";
    }

    @DeleteMapping("/delete")
    public String delete(){
        return "Hello, this is Dummy Controller and Delete";
    }

}
