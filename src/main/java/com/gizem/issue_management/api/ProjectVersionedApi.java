package com.gizem.issue_management.api;

import com.gizem.issue_management.dto.ProjectDTO;
import com.gizem.issue_management.service.impl.ProjectServiceImpl;
import com.gizem.issue_management.util.ApiPaths;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/versioning")
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@CrossOrigin
public class ProjectVersionedApi {

    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @GetMapping(value = "/{id}",params = "version=1")
    @ApiOperation(value = "Get By Id Operation V1", response = ProjectDTO.class)
    public ResponseEntity<ProjectDTO> getByIdV1(@PathVariable(value = "id", required = true) Long id) {
        ProjectDTO projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @GetMapping(value = "/{id}", params = "version=2")
    @ApiOperation(value = "Get By Id Operation V2", response = ProjectDTO.class)
    public ResponseEntity<ProjectDTO> getByIdV2(@PathVariable(value = "id", required = true) Long id) {
        ProjectDTO projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }
}