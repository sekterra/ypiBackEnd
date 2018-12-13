package com.tommy.ypi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "menus")
public class Menu extends AuditModel {
	@Id
	@GeneratedValue(generator = "menu_generator")
	@SequenceGenerator(
					name = "menu_generator",
					sequenceName = "menu_sequence",
					initialValue = 1000
	)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 100)
	private String name;

	@Size(max = 500)
	private String path;

	private Integer orderNo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
}
