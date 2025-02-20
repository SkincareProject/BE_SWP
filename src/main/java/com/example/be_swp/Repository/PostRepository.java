package com.example.be_swp.Repository;

import com.example.be_swp.Models.Posts;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ListCrudRepository<Posts,Integer> {
}
