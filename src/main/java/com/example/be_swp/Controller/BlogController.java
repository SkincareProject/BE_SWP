package com.example.be_swp.Controller;

import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Models.Blogs;
import com.example.be_swp.Repository.BlogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class BlogController {
    @Autowired // Add this to inject the repository
    private BlogsRepository repository;

    @GetMapping("/blogs")
    public ApiResponse<?> get(){
        List<Blogs> blogs = repository.findAll();


        return new ApiResponse<>("200",blogs,"OK");
    }

    @GetMapping("/blogs/{id}")
    public ApiResponse<Blogs> getById(@PathVariable int id) {
        Blogs blog = repository.findById(id).orElse(null);

        if (blog == null) {
            return new ApiResponse<>("404", null, "Blog not found");
        }

        return new ApiResponse<>("200", blog, "Success");
    }

    @PostMapping("/createOrUpdate")
    public ApiResponse<?> createOrUpdate(@RequestBody Blogs data){


        Blogs newBlog=repository.findById(data.getBlogId()).orElse(null);;

        if(newBlog==null){
            newBlog = new Blogs();
        }

        // Set the properties
        newBlog.setTitle(data.getTitle());
        newBlog.setContent(data.getContent());
        newBlog.setAuthorId(data.getAuthorId());
        newBlog.setImage(data.getImage());

        // Save to repository
        newBlog = repository.save(newBlog);

        return new ApiResponse<>("200", newBlog, "Create Or Update success");
    }



    @DeleteMapping("/deleteBlog")
    public ApiResponse<?> delete(@RequestParam("blogId")  int  blogId){
        Blogs deleteBlog=repository.findById(blogId).orElse(null);

        if(deleteBlog==null){
            return new ApiResponse<>("400",null,"Error not existing blog");

        }
        repository.delete(deleteBlog);


        return new ApiResponse<>("200",deleteBlog,"delete ok");
    }

}
