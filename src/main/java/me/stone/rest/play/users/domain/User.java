package me.stone.rest.play.users.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Getter
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_name", nullable = false, unique = true, length = 50)
	private String username;
	
	@Column(name = "first_name", nullable = false, length = 50)
	private String firstname;
	
	@Column(name = "last_name", nullable = false, length = 50)
	private String lastname;
	
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(name = "age", columnDefinition = "integer default 0")
	private Integer age;
	
	@Column(name = "role", nullable = false, length = 50)
	private String role;
	
	@Column(name = "ssn", nullable = false, unique = true, length = 50)
	private String ssn;

	@Builder
	public User(String username, String firstname, String lastname, String email, Integer age, String role,
			String ssn) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.age = age;
		this.role = role;
		this.ssn = ssn;
	}

}
