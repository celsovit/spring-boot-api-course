package com.crv.course.repositories;

import com.crv.course.entities.OrderItem;
import com.crv.course.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
