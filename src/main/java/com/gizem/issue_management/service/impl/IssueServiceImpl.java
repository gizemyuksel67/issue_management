
package com.gizem.issue_management.service.impl;

        import com.gizem.issue_management.dto.IssueDTO;
        import com.gizem.issue_management.entity.Issue;
        import com.gizem.issue_management.entity.IssueStatus;
        import com.gizem.issue_management.repository.IssueRepository;
        import com.gizem.issue_management.repository.ProjectRepository;
        import com.gizem.issue_management.repository.UserRepository;
        import com.gizem.issue_management.service.IssueService;
        import com.gizem.issue_management.util.TPage;
        import org.modelmapper.ModelMapper;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import org.springframework.stereotype.Service;

        import java.util.Arrays;
        import java.util.Date;
        import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, ProjectRepository projectRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.userRepository =userRepository;
        this.projectRepository=projectRepository;
    }

    @Override
    public IssueDTO save(IssueDTO issue) {
        // Bussiness Logic
        issue.setDate(new Date());
        issue.setIssueStatus(IssueStatus.OPEN);


        Issue issueEntity = modelMapper.map(issue, Issue.class);

        issueEntity.setProject(projectRepository.getOne(issue.getProjectId()));
        issueEntity = issueRepository.save(issueEntity);

        issue.setId(issueEntity.getId());
        return issue;
    }


    @Override
    public IssueDTO getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        return modelMapper.map(issue, IssueDTO.class);
    }

    @Override
    public TPage<IssueDTO> getAllPageable(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage<IssueDTO> respnose = new TPage<IssueDTO>();
        respnose.setStat(data, Arrays.asList(modelMapper.map(data.getContent(), IssueDTO[].class)));
        return respnose;
    }

    public List<IssueDTO> getAll() {
        List<Issue> data = issueRepository.findAll();
        return Arrays.asList(modelMapper.map(data, IssueDTO[].class));
    }

    @Override
    public IssueDTO update(Long id, IssueDTO project) {
        return null;
    }

    public Boolean delete(Long issueId) {
        issueRepository.deleteById(issueId);
        return true;
    }









}