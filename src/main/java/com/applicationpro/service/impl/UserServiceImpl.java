package com.applicationpro.service.impl;

import com.applicationpro.dto.UserDTO;
import com.applicationpro.entity.User;
import com.applicationpro.repository.UserRepository;
import com.applicationpro.service.UserService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAll() {

        List<User> list = userRepository.findAll();
        return list.stream().map(
                user -> mapperUtil.convert(user,new UserDTO())).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream().map(
                user -> mapperUtil.convert(user,new UserDTO())).collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).get();

        return mapperUtil.convert(user, new UserDTO());

    }

    @Override
    public void save(UserDTO userDTO) {
        userDTO.setEnabled(true);
        userRepository.save(mapperUtil.convert(userDTO, new User()));
    }

    @Override
    public void update(UserDTO userDTO) {

    }

    @Override
    public void delete(UserDTO userDTO) {

    }
}
