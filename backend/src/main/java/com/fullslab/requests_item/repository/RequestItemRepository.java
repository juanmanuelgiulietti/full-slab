package com.fullslab.requests_item.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullslab.requests_item.entity.RequestItem;

public interface RequestItemRepository extends JpaRepository<RequestItem, Long>{
    
}
