package com.project.pos_system_update.category.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryId implements Serializable {
	@Column(name = "businessId")
	private String businessId;
	@Column(name = "categoryId")
	private Integer categoryId;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryId)) return false;
        CategoryId that = (CategoryId) o;
        return Objects.equals(businessId, that.businessId) &&
               Objects.equals(categoryId, that.categoryId);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(businessId, categoryId);
	}
	
	@Override
	public String toString() {
		return "[" + businessId + "," + categoryId + "]";
	}
}
