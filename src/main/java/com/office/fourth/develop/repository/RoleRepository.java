package com.office.fourth.develop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.office.fourth.develop.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

}
