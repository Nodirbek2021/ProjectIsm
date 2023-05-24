package uz.pdp.projectism.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.projectism.entity.Role;
import uz.pdp.projectism.exceptions.RestException;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.repository.RoleRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    public ApiResponse<?> getAll() {
        List<Role> all = roleRepository.findAll();
        return ApiResponse.successResponse(all,"Done!");
    }

    @Override
    public ApiResponse<?> getById(UUID id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> RestException.notFound("Role id not found."));
        return ApiResponse.successResponse(role,"Done!");
    }
}
