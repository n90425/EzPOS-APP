package com.project.pos_system_update.order.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    private Integer businessId;
    private Integer orderId;
    private Integer ordDetNo;
}
