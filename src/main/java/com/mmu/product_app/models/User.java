package com.mmu.product_app.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * The User class represents a user in the application.
 * It implements the UserDetails interface provided by Spring Security.
 * This class is used for authentication and authorization purposes.
 */
@Data
@Builder
@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

	/**
	 * The unique identifier for the user.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * The email address of the user.
	 * It is nullable but must be unique.
	 */
	@Column(nullable = false, unique = true)
	private String email;

	/**
	 * The password of the user.
	 * It is non-null and must be provided.
	 */
	@Column(nullable = false)
	@Nonnull
	private String password;

	/**
	 * The role of the user.
	 * It is an enumerated value representing the user's role in the application.
	 */
	@Enumerated(EnumType.STRING)
	private Role role;

	/**
	 * The first name of the user.
	 */
	@Column()
	private String firstName;

	/**
	 * The last name of the user.
	 */
	@Column()
	private String lastName;

	/**
	 * Returns the authorities granted to the user.
	 * In this case, it returns a list containing a single authority based on the user's role.
	 *
	 * @return the authorities granted to the user
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	/**
	 * Returns the username of the user.
	 * In this case, it returns the email address of the user.
	 *
	 * @return the username of the user
	 */
	@Override
	public String getUsername() {
		return email;
	}

	/**
	 * Returns whether the user's account has expired.
	 * In this case, it always returns true.
	 *
	 * @return true if the user's account is not expired, false otherwise
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * Returns whether the user's account is locked.
	 * In this case, it always returns true.
	 *
	 * @return true if the user's account is not locked, false otherwise
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * Returns whether the user's credentials (password) have expired.
	 * In this case, it always returns true.
	 *
	 * @return true if the user's credentials are not expired, false otherwise
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * Returns whether the user is enabled.
	 * In this case, it always returns true.
	 *
	 * @return true if the user is enabled, false otherwise
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}