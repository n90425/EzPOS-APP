package com.project.pos_system_update.open.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="open")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Open {
    /**
     * 복합 키: (businessId, openDate 오픈일)
     */
    @EmbeddedId
    private OpenId id;

    private LocalDateTime openTime; /* 영업시작 날짜+시간 */
    private LocalDateTime closeTime; /* 영업마감 날짜+시간 */
    private Boolean isOpen; /* 영업상태 (오픈/마감) */
    private int totalOrders; /* 총 주문수 */
    private int totalSales; /* 총 매출액 */

    public static Open of(OpenId id, LocalDateTime openTime) {
        Open open = new Open();
        open.setId(id);
        open.setOpenTime(openTime);
        open.setIsOpen(true);
        open.setTotalOrders(0);
        open.setTotalSales(0);
        return open;
    }
}
