package com.example.be_swp.Controller;

import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Blogs;
import com.example.be_swp.Repository.BlogsRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BlogController {
   private BlogsRepository repository;

    @GetMapping("/blogs")
    public ApiResponse<String> get(){

        return new ApiResponse<>("This is","Dummy","Api");
    }

    @PostMapping("/createOrUpdate")
    public ApiResponse<?> post(@RequestBody Blogs data){
        Blogs newBlog =null;


        newBlog.setTitle(data.getTitle());
        newBlog.setContent(data.getContent());
//        newBlog.setAuthorId(data.getAuthorId());

        newBlog=repository.save(newBlog);

        return new ApiResponse<>("200",newBlog,"Create Or Update success");
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
