package com.gizem.issue_management.dto;

import com.gizem.issue_management.entity.IssueStatus;
import com.gizem.issue_management.entity.Project;
import com.gizem.issue_management.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Issue Data Transfer Object")
public class IssueDTO {

    @ApiModelProperty(required = true,value = "ID")
    private Long id;
    @ApiModelProperty(required = true,value = "Description")
    private String description;
    @ApiModelProperty(required = true,value = "Issue Details")
    private String detail;
    @ApiModelProperty(required = true,value = "Date")
    private Date date;
    @ApiModelProperty(required = true,value = "Issue Status")
    private IssueStatus issueStatus;
    @ApiModelProperty(required = true,value = "Assignee")
    private UserDTO assignee;
    @ApiModelProperty(required = true,value = "Project")
    private ProjectDTO project;
    private Long projectId;

}


