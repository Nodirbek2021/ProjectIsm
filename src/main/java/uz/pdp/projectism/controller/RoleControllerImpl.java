package uz.pdp.projectism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.service.RoleService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {
    private final RoleService roleService;

    @Override
    public ApiResponse<?> getAll() {
        return roleService.getAll();
    }

    @Override
    public ApiResponse<?> getById(UUID id) {
        return roleService.getById(id);
    }
}
