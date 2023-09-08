package com.gizem.issue_management.service;

import com.gizem.issue_management.dto.IssueDTO;
import com.gizem.issue_management.util.TPage;
import org.springframework.data.domain.Pageable;


public interface IssueService {
    IssueDTO save (IssueDTO issue) throws Exception;
    IssueDTO getById(Long Id);

    Boolean delete(Long issue);

    TPage<IssueDTO> getAllPageable(Pageable pageable);

    IssueDTO update(Long id, IssueDTO project);


}
