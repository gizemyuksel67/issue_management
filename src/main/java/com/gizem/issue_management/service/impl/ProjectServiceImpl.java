package com.gizem.issue_management.service.impl;

import com.gizem.issue_management.dto.ProjectDTO;
import com.gizem.issue_management.entity.Project;
import com.gizem.issue_management.entity.User;
import com.gizem.issue_management.repository.ProjectRepository;
import com.gizem.issue_management.repository.UserRepository;
import com.gizem.issue_management.service.ProjectService;
import com.gizem.issue_management.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,UserRepository userRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userRepository= userRepository;
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {

        Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());

        if (projectCheck != null)
            throw new IllegalArgumentException("Project Code Already Exist");

        Project p = modelMapper.map(project, Project.class);
        User user = userRepository.getOne(project.getManagerId());
        p.setManager(user);

        p = projectRepository.save(p);
        project.setId(p.getId());
        return project;
    }

    @Override
    public ProjectDTO getById(Long id) {
        Project p = projectRepository.getOne(id);
        return modelMapper.map(p, ProjectDTO.class);
    }

    @Override
    public ProjectDTO getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<ProjectDTO> getByProjectCodeContains(String projectCode) {
        return null;
    }


    @Override
    public TPage<ProjectDTO> getAllPageable(Pageable pageable) {
        Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDTO> respnose = new TPage<ProjectDTO>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), ProjectDTO[].class)));
        return respnose;
    }


    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
        return true;
    }

    @Override
    public ProjectDTO update(Long id, ProjectDTO project) {
        Project projectDb = projectRepository.getOne(id);
        if (projectDb == null)
            throw new IllegalArgumentException("Project Does Not Exist ID:" + id);

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(project.getProjectCode(), id);
        if (projectCheck != null)
            throw new IllegalArgumentException("Project Code Already Exist");

        projectDb.setProjectCode(project.getProjectCode());
        projectDb.setProjectName(project.getProjectName());

        projectRepository.save(projectDb);
        return modelMapper.map(projectDb, ProjectDTO.class);
    }

    public List<ProjectDTO> getAll() {
        List<Project> data = projectRepository.findAll();
        return Arrays.asList(modelMapper.map(data, ProjectDTO[].class));
    }
}