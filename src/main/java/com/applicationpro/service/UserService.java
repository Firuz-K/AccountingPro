package com.applicationpro.service;

import com.applicationpro.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> listAll();
    List<UserDTO> findAll();
    UserDTO findByUserName(String username);
    UserDTO findByID(Long id);
    void save(UserDTO userDTO);
    void update(UserDTO userDTO);
    void delete(String username);

}
