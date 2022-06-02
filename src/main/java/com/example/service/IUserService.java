package com.example.service;

import java.util.List;

import com.example.entities.User;

public interface IUserService {
	public List<User> traer();
	public boolean remove(int id);
	public User traer(int id);
	public void guardar(User user);
}
