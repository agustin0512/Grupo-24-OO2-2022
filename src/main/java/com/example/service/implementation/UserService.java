package com.example.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repo.IUserRepository;
import com.example.service.IUserService;
import com.example.entities.UserRole;

@Service("userDetailsService")
public class UserService implements UserDetailsService, IUserService{
	
	//@Qualifier("userRepository")
	@Autowired
	private IUserRepository repo;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.entities.User user = repo.findByUsernameAndFetchURolEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getRol()));
	}
	
	private User buildUser(com.example.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(UserRole rol) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(rol.getRole()));
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}




	
	
	
	
	
	@Override
	@Transactional( readOnly = true )
	public List<com.example.entities.User> traer() {
		return (List<com.example.entities.User>) repo.findAll();
	}

	@Override
	@Transactional( readOnly = true )
	public com.example.entities.User traer(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void guardar(com.example.entities.User usuario) {
		repo.save(usuario);
	}

	public boolean remove(int id) {
		try {
			repo.deleteById(id);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}