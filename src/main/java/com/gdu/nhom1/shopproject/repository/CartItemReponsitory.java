package com.gdu.nhom1.shopproject.repository;

import com.gdu.nhom1.shopproject.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemReponsitory extends JpaRepository<CartItem,Long> {
    @Override
    List<CartItem> findAll();
}
