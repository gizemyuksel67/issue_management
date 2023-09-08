package com.gizem.issue_management.service;

import com.gizem.issue_management.dto.UserDTO;
import com.gizem.issue_management.entity.Project;
import com.gizem.issue_management.entity.User;
import com.gizem.issue_management.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO save (UserDTO project) throws Exception;
    UserDTO getById(Long Id);

    TPage<UserDTO> getAllPageable(Pageable pageable);

    UserDTO getByUsername(String username);
}
