package com.gizem.issue_management.repository;

import com.gizem.issue_management.dto.ProjectDTO;
import com.gizem.issue_management.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

Project getByProjectCode(String projectCode);
List<Project> getByProjectCodeContains(String projectCode);

List<Project> findAll(Sort sort);

Project getByProjectCodeAndIdNot(String projectCode, Long id);
}
