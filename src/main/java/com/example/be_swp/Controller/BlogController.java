package com.example.be_swp.Controller;

import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Blogs;
import com.example.be_swp.Repository.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1",method = {RequestMethod.GET, RequestMethod.POST})
public class BlogController {
    @Autowired // Add this to inject the repository
    private BlogsRepository repository;

    @GetMapping("/blogs")
    public ApiResponse<String> get(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @PostMapping("/createOrUpdate")
    public ApiResponse<?> createOrUpdate(@RequestBody Blogs data){


        Blogs newBlog=repository.findById(data.getAuthorId()).orElse(null);;


        System.out.println(newBlog);
        // Set the properties
        newBlog.setTitle(data.getTitle());
        newBlog.setContent(data.getContent());
        newBlog.setAuthorId(data.getAuthorId());

        // Save to repository
        newBlog = repository.save(newBlog);

        return new ApiResponse<>("200", newBlog, "Create Or Update success");
    }

    @PutMapping
    public ApiResponse<String> put(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @DeleteMapping
    public ApiResponse<String> delete(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

}
