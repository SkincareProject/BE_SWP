package com.example.be_swp.Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello, this is Dummy Controller";
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
