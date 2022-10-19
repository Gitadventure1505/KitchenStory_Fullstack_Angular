package com.in.Kitchenstory.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;



@NamedQuery(name = "User.findByEmail" , query = "select u from User u where u.email=:email")

@Data
@Entity
@Table(name = "user")
@DynamicInsert
@DynamicUpdate

public class User implements Serializable
{
		private static final long serialVersionUID = 1L;
		
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;
		
		@Column(name = "name")
		private String name;
		
		
		@Column(name = "contactNumber")
		private String contactNumber;
		
		@Column(name = "email")
		private String email;
		
		@Column(name = "password")
		private String password;
		
		@Column(name = "status")
		private String status;
		
		@Column(name = "role")
		private String role;
		
		
		

		public User() {
			super();
		}

		public User(Integer id, String name, String contactNumber, String email, String password, String status,
				String role) {
			super();
			this.id = id;
			this.name = name;
			this.contactNumber = contactNumber;
			this.email = email;
			this.password = password;
			this.status = status;
			this.role = role;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContactNumber() {
			return contactNumber;
		}

		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
		
		
		
		

		
		
}
