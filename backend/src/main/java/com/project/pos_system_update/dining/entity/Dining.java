package com.project.pos_system_update.dining.entity;

import jakarta.persistence.*;
import lombok.*;
import com.project.pos_system_update.order.entity.Order;

import java.math.BigDecimal;

@Entity
@Table(name="dining")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Dining {
    /**
     * 복합 키: (businessId, tableNo)
     */
    @EmbeddedId
    private DiningId id;

    @Column(name="xPosition", precision = 10, scale = 2)
    private BigDecimal xPosition;

    @Column(name="yPosition", precision = 10, scale = 2)
    private BigDecimal yPosition;

    @Column(name="tableColor")
    private String tableColor;

    /* 테이블의 가로세로 사이즈 */
    private BigDecimal width;
    private BigDecimal height;

    @Enumerated(EnumType.STRING)
    @Column(name="tableStatus", columnDefinition = "ENUM('EMPTY', 'FULL') DEFAULT 'EMPTY'")
    private status tableStatus = status.EMPTY;

    private String currentOrderNo;

    public enum status {
        EMPTY, FULL
    }

    /* 주문이 들어왔을때 */
    public void assignOrder(Order Order){
        this.currentOrderNo = Order.getOrderNo();
        this.tableStatus = status.FULL;
    }

    /* 결제가 완료되어 테이블을 비울때 */
    public void clearTable(){
        this.currentOrderNo = null;
        this.tableStatus = status.EMPTY;
    }

    public void updateFrom(Dining dto) {
        this.xPosition = dto.getXPosition();
        this.yPosition = dto.getYPosition();
        this.width = dto.getWidth();
        this.height = dto.getHeight();
        this.tableColor = dto.getTableColor();
    }
}
