package com.example.be_swp.Repository;

import com.example.be_swp.Models.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

@Repository
public interface BlogsRepository  extends JpaRepository<Blogs,Integer> ,JpaSpecificationExecutor<Blogs> {

}
