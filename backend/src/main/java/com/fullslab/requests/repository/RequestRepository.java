package com.fullslab.requests.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullslab.requests.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long>{
    
}
