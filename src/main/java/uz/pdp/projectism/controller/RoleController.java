package uz.pdp.projectism.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.UserDTO;

import java.util.UUID;

@RequestMapping(RoleController.ROLE_CONTROLLER)
public interface RoleController {
    String ROLE_CONTROLLER= AppConstants.BASE_PATH+"/role";

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?> getAll();
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?> getById(@PathVariable UUID id);
}
