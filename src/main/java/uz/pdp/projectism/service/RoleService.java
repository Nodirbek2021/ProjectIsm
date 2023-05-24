package uz.pdp.projectism.service;

import uz.pdp.projectism.payload.ApiResponse;

import java.util.UUID;

public interface RoleService {
    ApiResponse<?> getAll();
    ApiResponse<?> getById(UUID id);
}
