package com.project.pos_system_update.open.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OpenId implements Serializable {
    private String businessId;
    private LocalDate openDate; /* 오픈일 */
}
