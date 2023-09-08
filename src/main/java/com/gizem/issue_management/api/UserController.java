package com.gizem.issue_management.api;

import com.gizem.issue_management.dto.UserDTO;
import com.gizem.issue_management.service.impl.UserServiceImpl;
import com.gizem.issue_management.util.ApiPaths;
import com.gizem.issue_management.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL, description = "User APIs")
@CrossOrigin
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = UserDTO.class)
    public ResponseEntity<TPage<UserDTO>> getAllByPagination(Pageable pageable) {
        TPage<UserDTO> data = userServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    @ApiOperation(value = "Get All By Operation", response = UserDTO.class)
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> data = userServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = UserDTO.class)
    public ResponseEntity<UserDTO> getById(@PathVariable(value = "id", required = true) Long id) {
        UserDTO user = userServiceImpl.getById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = UserDTO.class)
    public ResponseEntity<UserDTO> createProject( @RequestBody UserDTO user) {
        return ResponseEntity.ok(userServiceImpl.save(user));
    }
}