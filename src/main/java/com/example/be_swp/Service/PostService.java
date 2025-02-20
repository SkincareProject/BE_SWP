package com.example.be_swp.Service;

import com.example.be_swp.Repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository _postRepository;

    public PostService(PostRepository _postRepository) {
        this._postRepository = _postRepository;
    }
}
