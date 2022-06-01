package com.example.service;

import java.util.List;

import com.example.entities.User;

public interface IUserService {
	public List<User> traer();
	public User traer(Long id);
	public void guardar(User user);
}
