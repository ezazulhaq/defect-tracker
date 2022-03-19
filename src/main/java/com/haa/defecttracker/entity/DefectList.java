package com.haa.defecttracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class DefectList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Please enter Defect ID")
    private Long bugId;

    private String description;

    @NotEmpty(message = "Please select Assigned To")
    private String assignedTo;

    @NotEmpty(message = "Please select Project")
    private String project;

    @NotEmpty(message = "Please select Status")
    private String status;

    public DefectList() {
    }

    public DefectList(Long bugId, String description, String assignedTo, String project, String status) {
        this.bugId = bugId;
        this.description = description;
        this.assignedTo = assignedTo;
        this.project = project;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBugId() {
        return bugId;
    }

    public void setBugId(Long bugId) {
        this.bugId = bugId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DefectList [assignedTo=" + assignedTo + ", bugId=" + bugId + ", description=" + description + ", id="
                + id + ", project=" + project + ", status=" + status + "]";
    }

}
