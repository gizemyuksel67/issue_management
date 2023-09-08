package com.gizem.issue_management.api;

import com.gizem.issue_management.dto.IssueDTO;
import com.gizem.issue_management.entity.IssueStatus;
import com.gizem.issue_management.service.impl.IssueServiceImpl;
import com.gizem.issue_management.util.ApiPaths;
import com.gizem.issue_management.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL, description = "Issue APIs")
@CrossOrigin
public class IssueController {

    private final IssueServiceImpl issueServiceImpl;

    public IssueController(IssueServiceImpl issueServiceImpl) {
        this.issueServiceImpl = issueServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get By Pagination Operation", response = IssueDTO.class)
    public ResponseEntity<TPage<IssueDTO>> getAllByPagination(Pageable pageable) {
        TPage<IssueDTO> data = issueServiceImpl.getAllPageable(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation", response = IssueDTO.class)
    public ResponseEntity<IssueDTO> getById(@PathVariable(value = "id", required = true) Long id) {
        IssueDTO issue = issueServiceImpl.getById(id);
        return ResponseEntity.ok(issue);
    }

  /*  @GetMapping("/detail/{id}")
    public ResponseEntity<IssueDetailDTO> getByIdWithDetails(@PathVariable(value = "id", required = true) Long id) {
        IssueDetailDto detailDto = issueServiceImpl.getByIdWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }*/

    @PostMapping
    @ApiOperation(value = "Create Operation", response = IssueDTO.class)
    public ResponseEntity<IssueDTO> createProject(@Valid @RequestBody IssueDTO issue) {
        return ResponseEntity.ok(issueServiceImpl.save(issue));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update Operation", response = IssueDTO.class)
    public ResponseEntity<IssueDTO> updateProject(@PathVariable(value = "id", required = true) Long id, @Valid @RequestBody IssueDTO issue) {
        return ResponseEntity.ok(issueServiceImpl.update(id,issue));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Operation", response = Boolean.class)
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id", required = true) Long id) {
        return ResponseEntity.ok(issueServiceImpl.delete(id));
    }

    @GetMapping("/statuses")
    @ApiOperation(value = "Get All Issue Statuses Operation", response = String.class, responseContainer = "List")
    public ResponseEntity<List<IssueStatus>> getAll() {
        return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
    }
}