package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.UserRole;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable>{

}
