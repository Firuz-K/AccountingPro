package com.applicationpro.service;

import com.applicationpro.dto.UserDTO;
import java.util.List;

public interface UserService {
    List<UserDTO> listAll();
    List<UserDTO> findAll();
    UserDTO findUserById(Long id);
    void save(UserDTO userDTO);
    void update(UserDTO userDTO);
    void delete(UserDTO userDTO);

}
