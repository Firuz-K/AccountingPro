package com.applicationpro.service;

import com.applicationpro.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> listAllRoles();
    RoleDTO findById(Long id);
}
