package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.ResponseWrapper;
import com.cydeo.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @RolesAllowed("MANAGER")
    public ResponseEntity<ResponseWrapper> getAllProject() {
        List<ProjectDTO> list = projectService.listAllProjects();
        return ResponseEntity.ok(ResponseWrapper.builder()
                .success(true)
                .message("All Projects successfully retrieved")
                .data(list)
                .code(200).build());
    }
    @GetMapping("/{code}")
    @RolesAllowed("MANAGER")
    public ResponseEntity<ResponseWrapper> getProjectByCode(@PathVariable("code") String code){
        ProjectDTO projectDTO=projectService.getByProjectCode(code);
        return ResponseEntity.ok(new ResponseWrapper("Project successfully is retrieved",projectDTO,HttpStatus.OK));
    }
    @PostMapping
    @RolesAllowed("MANAGER")
    public ResponseEntity<ResponseWrapper> createProject(@RequestBody ProjectDTO project){
        projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Project is successfully created",HttpStatus.CREATED));
    }
    @PutMapping
    @RolesAllowed("MANAGER")
    public ResponseEntity<ResponseWrapper> updateProject(@RequestBody ProjectDTO project){
        projectService.update(project);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully updated", HttpStatus.OK));
    }
    @DeleteMapping("/{projectCode}")
    @RolesAllowed("MANAGER")
    public ResponseEntity<ResponseWrapper> deleteProject(@PathVariable("projectCode") String projectCode){
        projectService.delete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully deleted", HttpStatus.OK));
    }
    @GetMapping("/manager/project-status")
    @RolesAllowed("MANAGER")
    public ResponseEntity<ResponseWrapper> getProjectByManager(){
        List<ProjectDTO> projectList = projectService.listAllProjectDetails();
        return ResponseEntity.ok(new ResponseWrapper("Projects are successfully retrieved",projectList, HttpStatus.OK));
    }
    @PutMapping("/manager/project/{projectCode}")
    @RolesAllowed("MANAGER")
    public ResponseEntity<ResponseWrapper> managerCompleteProject(@PathVariable("projectCode")String projectCode){
        projectService.complete(projectCode);
        return ResponseEntity.ok(new ResponseWrapper("Project is successfully completed", HttpStatus.OK));

    }
}

