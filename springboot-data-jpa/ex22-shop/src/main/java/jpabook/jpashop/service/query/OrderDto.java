package jpabook.jpashop.service.query;


import jpabook.jpashop.domain.entity.embed.Address;
import jpabook.jpashop.domain.entity.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private Address address;
    private List<OrderItemDto> orderItems;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.address = order.getDelivery().getAddress();
        this.orderItems = order.getOrderItems().stream()
                .map(OrderItemDto::new)
                .collect(toList());
    }
}