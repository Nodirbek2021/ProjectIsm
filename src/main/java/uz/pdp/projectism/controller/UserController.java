package uz.pdp.projectism.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.UserDTO;

import java.util.UUID;

@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {
    String USER_CONTROLLER= AppConstants.BASE_PATH+"/user";

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?> addUser(@RequestBody UserDTO userDTO);

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?>getById(@PathVariable UUID id);

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?>getAllUsers();

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?>editUser(@PathVariable UUID id, @RequestBody UserDTO userDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?>deleteUser(@PathVariable UUID id);

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?> saveUser(@RequestBody UserDTO userDTO);

}
