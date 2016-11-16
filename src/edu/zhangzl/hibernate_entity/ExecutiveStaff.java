package edu.zhangzl.hibernate_entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ExecutiveStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "executive_staff", catalog = "course")
public class ExecutiveStaff implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String count;
	private String password;

	// Constructors

	/** default constructor */
	public ExecutiveStaff() {
	}

	/** full constructor */
	public ExecutiveStaff(String name, String count, String password) {
		this.name = name;
		this.count = count;
		this.password = password;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "count", nullable = false, length = 20)
	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Column(name = "password", nullable = false, length = 20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}