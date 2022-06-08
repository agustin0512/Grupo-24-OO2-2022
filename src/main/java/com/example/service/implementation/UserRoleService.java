package com.example.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.UserRole;
import com.example.repo.IUserRoleRepository;
import com.example.service.IUserRoleService;

@Service
public class UserRoleService implements IUserRoleService{
	
	@Autowired
	private IUserRoleRepository repo;

	@Override
	@Transactional( readOnly = true )
	public List<UserRole> traer() {
		return (List<UserRole>) repo.findAll();
	}

	@Override
	@Transactional( readOnly = true )
	public UserRole traer(int id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void guardar(UserRole userRole) {
		repo.save(userRole);
		
	}

	@Override
	public UserRole traer(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
