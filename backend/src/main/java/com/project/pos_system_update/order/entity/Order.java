package com.project.pos_system_update.order.entity;

import com.project.pos_system_update.dining.entity.Dining;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private String orderNo;
    private String businessId;
    private Integer tableNo;
    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    @Column(name="orderPayStatus", columnDefinition = "ENUM('PAID', 'UNPAID') DEFAULT 'UNPAID'")
    private status orderPayStatus = status.unpaid;

    private double orderAmount;
    private double orderVat;

    /**
     * Order → Dining (N:1)
     * 외래 키: (businessId, tableNo)
     */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name= "businessId", referencedColumnName = "businessId", insertable = false, updatable = false),
            @JoinColumn(name= "tableNo", referencedColumnName = "tableNo", insertable = false, updatable = false)
    })
    private Dining dining;

    public enum status {
        paid, unpaid
    }

    /* 결제완료시 order 테이블 상태변경 */
    public void complatedPay() {
        this.orderPayStatus = status.paid;
    }

    /* 주문상세 */


}
