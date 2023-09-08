package com.gizem.issue_management.service;

import com.gizem.issue_management.dto.ProjectDTO;
import com.gizem.issue_management.entity.Project;
import com.gizem.issue_management.util.TPage;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    ProjectDTO save (ProjectDTO project) throws Exception;
    ProjectDTO getById(Long Id);

    ProjectDTO getByProjectCode(String projectCode);

    List<ProjectDTO> getByProjectCodeContains(String projectCode);

    TPage<ProjectDTO> getAllPageable(Pageable pageable);

    Boolean delete(Long Id);

    ProjectDTO update(Long id, ProjectDTO project);
}
