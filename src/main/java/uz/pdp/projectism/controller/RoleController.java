package uz.pdp.projectism.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.UserDTO;

import java.util.UUID;

@RequestMapping(RoleController.ROLE_CONTROLLER)
@Tag(name = "Role")
public interface RoleController {
    String ROLE_CONTROLLER= AppConstants.BASE_PATH+"/role";

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Get All roles.")
    ApiResponse<?> getAll();
    @GetMapping("/{id}")
    @Operation(summary = "Get role by its id.")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?> getById(@PathVariable UUID id);
}
