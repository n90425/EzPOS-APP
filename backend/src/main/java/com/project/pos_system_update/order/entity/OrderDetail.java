package com.project.pos_system_update.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orderDetail")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"businessId", "orderId", "ordDetNo"})
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @Id
    private String businessId;
    @Id
    private Integer orderId;
    @Id
    private Integer ordDetNo;

    private Integer menuId;
    private String menuName;
    private Integer ordAddNo;
    private Integer unitPrice;
    private Integer quantity;
    private Integer totalAmount;
    private LocalDateTime itemOrderTime;

    @ManyToOne
    @JoinColumn(name="orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    private Order order;

//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name= "businessId", referencedColumnName = "businessId", insertable = false, updatable = false),
//            @JoinColumn(name= "menuId", referencedColumnName = "menuId", insertable = false, updatable = false)
//    })
//    private Menu menu;
}
