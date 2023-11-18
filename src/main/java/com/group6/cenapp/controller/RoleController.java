package com.group6.cenapp.controller;

import com.group6.cenapp.response.ApiResponseHandler;
import com.group6.cenapp.service.RoleService;
import com.group6.cenapp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping()
    public ResponseEntity<List<Role>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoleById (@PathVariable int id){
        Optional<Role> roleSearch = roleService.getRoleById(id);
        if(roleSearch.isPresent())
            return ApiResponseHandler.generateResponse("Role data retrieved successfully", HttpStatus.OK, roleSearch.get());

        return ApiResponseHandler.generateResponseError("Role "+ id + " not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Role> addRole (@RequestBody Role role){
        return ResponseEntity.ok(roleService.saveRole(role));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateRole (@RequestBody Role role){
        ResponseEntity<String> response;
        if (roleService.getRoleById(role.getId()).isPresent()) {
            roleService.updateRole(role);
            response = ResponseEntity.ok("el Rol se actualizó correctamente");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("el Rol NO se ha encontrado");
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole (@PathVariable int id) throws Exception {
        roleService.deleteRoleById(id);
        return ResponseEntity.ok("el Rol se eliminó correctamente");
    }


}
