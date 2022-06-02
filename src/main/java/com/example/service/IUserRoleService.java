package com.example.service;

import java.util.List;

import com.example.entities.UserRole;

public interface IUserRoleService {
	public List<UserRole> traer();
	public UserRole traer(Long id);
	public void guardar(UserRole userRole);
}
