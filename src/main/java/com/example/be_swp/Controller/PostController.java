package com.example.be_swp.Controller;

import com.example.be_swp.DTOs.PostDTO;
import com.example.be_swp.Models.ApiResponse;
import com.example.be_swp.Service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController{
    private final PostService _postService;

    public PostController(PostService postService) {
        _postService = postService;
    }

    @GetMapping("/getAll")
    public ApiResponse<List<PostDTO>> getAll(){
        List<PostDTO> postDTOList = _postService.findAll();
        String status = "";
        String message = "";
        if (postDTOList.isEmpty()) {
            status = "404";
            message = "List Not Found!";
        } else {
            status = "200";
            message = "List Found!";
        }
        return new ApiResponse<>(status,postDTOList,message);
    }
    @PostMapping("/add")
    public ApiResponse<PostDTO> add(@RequestBody PostDTO postDTO){
        String status = "";
        String message = "";
        postDTO = _postService.add(postDTO);
        if (postDTO == null) {
            status = "404";
            message = "Post Not Found!";
        } else {
            status = "200";
            message = "Post Added!";
        }
        return new ApiResponse<>(status,postDTO,message);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<PostDTO> update(@RequestBody PostDTO postDTO, @PathVariable int id){
        String status = "";
        String message = "";
        postDTO = _postService.update(id,postDTO);
        if (postDTO == null) {
            status = "404";
            message = "Post Not Found!";
        } else {
            status = "200";
            message = "Post Updated!";
        }
        postDTO.setPostId(id);
        return new ApiResponse<>(status,postDTO,message);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<PostDTO> delete(@PathVariable int id){
        String status = "";
        String message = "";
        PostDTO postDTO = _postService.delete(id);
        if (id == 0){
            status = "404";
            message = "Post Not Found!";
        } else {
            status = "200";
            message = "Post Deleted!";
        }
        postDTO.setPostId(id);
        return new ApiResponse<>(status,postDTO,message);
    }
}
