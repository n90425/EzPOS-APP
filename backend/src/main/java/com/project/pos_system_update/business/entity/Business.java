package com.project.pos_system_update.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "business")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Business {
	
	@Id
	@Column(name = "businessId", length = 100)
	private String businessId;
	private String businessNum;
	@Column(name = "businessPwd")
	@JsonIgnore
	private String businessPwd;
	private String businessName;
	private String ownerName;
	private String businessAdd;
	private String businessPostCode;
	private String businessPhone;
	private String businessEmail;

}
