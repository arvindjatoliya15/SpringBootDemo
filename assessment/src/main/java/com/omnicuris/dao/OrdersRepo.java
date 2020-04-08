package com.omnicuris.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.omnicuris.model.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer>{

}
