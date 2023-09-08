package com.gizem.issue_management.service.impl;

import com.gizem.issue_management.dto.UserDTO;
import com.gizem.issue_management.entity.User;
import com.gizem.issue_management.repository.UserRepository;
import com.gizem.issue_management.service.UserService;
import com.gizem.issue_management.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO save(UserDTO user) {
        User u = modelMapper.map(user, User.class);
        u = userRepository.save(u);
        user.setId(u.getId());
        return user;
    }

    @Override
    public UserDTO getById(Long id) {
        User u = userRepository.getOne(id);
        return modelMapper.map(u, UserDTO.class);
    }

    @Override
    public TPage<UserDTO> getAllPageable(Pageable pageable) {
        Page<User> data = userRepository.findAll(pageable);
        TPage<UserDTO> respnose = new TPage<UserDTO>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), UserDTO[].class)));
        return respnose;
    }

    public List<UserDTO> getAll() {
        List<User> data = userRepository.findAll();
        return Arrays.asList(modelMapper.map(data, UserDTO[].class));
    }


    @Override
    public UserDTO getByUsername(String username) {
        User u = userRepository.findByUsername(username);
        return modelMapper.map(u, UserDTO.class);

    }


}

