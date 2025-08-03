package com.project.pos_system_update.dining.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DiningId implements Serializable {
    private String businessId;
    private Integer tableNo;
}
