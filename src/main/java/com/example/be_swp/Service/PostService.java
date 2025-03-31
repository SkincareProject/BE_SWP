package com.example.be_swp.Service;

import com.example.be_swp.DTOs.PostDTO;
import com.example.be_swp.Models.Experts;
import com.example.be_swp.Models.Posts;
import com.example.be_swp.Repository.ExpertRepository;
import com.example.be_swp.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

//    private final PostRepository _postRepository;
//    private final ExpertRepository _expertRepository;
//
//    public PostService(PostRepository _postRepository, ExpertRepository expertRepository) {
//        this._postRepository = _postRepository;
//        this._expertRepository = expertRepository;
//    }
//
//    public List<PostDTO> findAll(){
//        List<PostDTO> postDTOList = new ArrayList<>();
////        List<Posts> postsList = _postRepository.findAll();
////        if (!postsList.isEmpty()) {
////            for (Posts post : postsList) {
////                PostDTO postDTO = new PostDTO(post.getPostId(),post.getExperts().getExpertId(),post.getTitle()
////                        ,post.getDescription(),post.getContent());
////                postDTOList.add(postDTO);
////            }
////        }
//        return postDTOList;
//    }
//
//    public Optional<Posts> findById(int id){
//        return _postRepository.findById(id);
//    }
//
//    public PostDTO add(PostDTO postDTO){
//        Experts experts = _expertRepository.findById(postDTO.getExpertsId()).get();
//        Posts newPost = new Posts();
//        newPost.setPostId(postDTO.getPostId());
//        newPost.setExperts(experts );
//        newPost.setTitle(postDTO.getTitle());
//        newPost.setDescription(postDTO.getDescription());
//        newPost.setContent(postDTO.getContent());
//        _postRepository.save(newPost);
//        return postDTO;
//    }
//
//    public PostDTO update(int id,PostDTO postDTO){
//        Optional<Posts> post = _postRepository.findById(id);
//        if(post.isPresent()){
//            Experts experts = _expertRepository.findById(postDTO.getExpertsId()).get();
//            Posts updatedPost = post.get();
//            updatedPost.setPostId(postDTO.getPostId());
//            updatedPost.setExperts(experts );
//            updatedPost.setTitle(postDTO.getTitle());
//            updatedPost.setDescription(postDTO.getDescription());
//            updatedPost.setContent(postDTO.getContent());
//            _postRepository.save(updatedPost);
//        }
//        return postDTO;
//    }
//
//    public PostDTO delete(int id){
//        Optional<Posts> post = _postRepository.findById(id);
//        PostDTO postDTO = new PostDTO();
//        if(post.isPresent()){
//            postDTO.setPostId(post.get().getPostId());
//            postDTO.setExpertsId(post.get().getExperts().getExpertId());
//            postDTO.setTitle(post.get().getTitle());
//            postDTO.setDescription(post.get().getDescription());
//            postDTO.setContent(post.get().getContent());
//            _postRepository.delete(post.get());
//        } else{
//            postDTO.setPostId(0);
//        }
//        return postDTO;
//
//    }
}
