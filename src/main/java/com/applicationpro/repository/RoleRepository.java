package com.applicationpro.repository;

import com.applicationpro.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByDescription(String description);
}
