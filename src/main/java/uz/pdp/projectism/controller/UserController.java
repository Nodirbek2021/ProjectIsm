package uz.pdp.projectism.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.UserDTO;

import java.util.UUID;

@RequestMapping(UserController.USER_CONTROLLER)
@Tag(name = "User")
public interface UserController {
    String USER_CONTROLLER= AppConstants.BASE_PATH+"/user";

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Create user")
    ApiResponse<?> addUser(@RequestBody UserDTO userDTO);

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','DIRECTOR')")
    @Operation(summary = "Get user by id.")
    ApiResponse<?>getById(@PathVariable UUID id);

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Get all users.")
    ApiResponse<?>getAllUsers();

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Edit users.")
    ApiResponse<?>editUser(@PathVariable UUID id, @RequestBody UserDTO userDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Delete users.   :) ")
    ApiResponse<?>deleteUser(@PathVariable UUID id);

}
