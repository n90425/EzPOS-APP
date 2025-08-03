package com.project.pos_system_update.category.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Category {
	@EmbeddedId
    private CategoryId id;

    @Column(name = "categoryname")
    private String categoryname;
    
    @Column(name = "isvisible", nullable = false)
    private Boolean isVisible  = true;
    
    @Column(name = "parent", insertable = false, updatable = false)
    private Integer parentId;
    
    //상위 카테고리
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
    	@JoinColumn(name = "businessId", referencedColumnName = "businessId", insertable = false, updatable = false),
        @JoinColumn(name = "parent", referencedColumnName = "categoryId", insertable = false, updatable = false)
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Category parent;
    
    //하위 카테고리
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Category> children = new ArrayList<>();
    
    //생성자
    public Category(String businessId, Integer categoryId, String categoryname) {
    	this.id = new CategoryId(businessId, categoryId);
    	this.categoryname = categoryname;
    }
    
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryname='" + categoryname + '\'' +
                ", isVisible=" + isVisible +
                ", parent=" + (parent != null ? parent.getId() : "null") +
                ", childrenSize=" + (children != null ? children.size() : 0) +
                '}';
    }

}
