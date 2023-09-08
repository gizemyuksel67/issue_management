package com.gizem.issue_management.api;

import com.gizem.issue_management.dto.ProjectDTO;
import com.gizem.issue_management.service.impl.ProjectServiceImpl;
import com.gizem.issue_management.util.ApiPaths;
import com.gizem.issue_management.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value = ApiPaths.ProjectCtrl.CTRL, description = "Project APIs")
@CrossOrigin
public class ProjectController
{
    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl) {
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = ProjectDTO.class)
    public ResponseEntity<TPage<ProjectDTO>> getAllByPagination(Pageable pageable) {
        TPage<ProjectDTO> data = projectServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    @ApiOperation(value = "Get All Operation", response = ProjectDTO.class , responseContainer = "List")
    public ResponseEntity<List<ProjectDTO>> getAll() {
        List<ProjectDTO> data = projectServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = ProjectDTO.class)
    public ResponseEntity<ProjectDTO> getById(@PathVariable(value = "id", required = true) Long id) {
        log.info("ProjectController-> GetByID ");
        log.debug("ProjectController-> GetByID -> PARAM:" + id);
        ProjectDTO projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value = "Create Operation", response = ProjectDTO.class)
    public ResponseEntity<ProjectDTO> createProject( @Valid @RequestBody ProjectDTO project) {
        return ResponseEntity.ok(projectServiceImpl.save(project));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = ProjectDTO.class)
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody ProjectDTO project) {
        return ResponseEntity.ok(projectServiceImpl.update(id, project));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(projectServiceImpl.delete(id));
    }





}
