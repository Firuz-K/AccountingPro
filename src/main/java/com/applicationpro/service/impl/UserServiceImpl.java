package com.applicationpro.service.impl;

import com.applicationpro.dto.UserDTO;
import com.applicationpro.entity.User;
import com.applicationpro.enums.UserStatus;
import com.applicationpro.repository.UserRepository;
import com.applicationpro.service.UserService;
import com.applicationpro.util.MapperUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
    public UserDTO findByUserName(String username) {
       User user = userRepository.findByEmail(username);
        return mapperUtil.convert(user, new UserDTO());
    }

    @Override
    public UserDTO findByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        return mapperUtil.convert(user, new UserDTO());

    }


    @Override
    public void save(UserDTO userDTO) {
        userDTO.setEnabled(true);
        userDTO.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(mapperUtil.convert(userDTO, new User()));
    }

    @Override
    public void update(UserDTO userDTO) {
        //Find current user
        User user = userRepository.findByEmail(userDTO.getEmail());
        //Map updated user dto to entity object
        User convertedUser = mapperUtil.convert(userDTO, new User());
        //set id to converted object
        convertedUser.setId(user.getId());
        //save updated user
        userRepository.save(convertedUser);

    }

    @Override
    public void delete(String username) {
        User user = userRepository.findByEmail(username);

            user.setIsDeleted(true);
            userRepository.save(user);

    }


}
