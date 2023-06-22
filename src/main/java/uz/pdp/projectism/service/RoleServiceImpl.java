package uz.pdp.projectism.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.projectism.entity.Role;
import uz.pdp.projectism.entity.User;
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
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        List<Role> all = roleRepository.findAll();
        return ApiResponse.successResponse(all,"Done!");
    }

    @Override
    public ApiResponse<?> getById(UUID id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        Role role = roleRepository.findById(id).orElseThrow(() -> RestException.notFound("Role id not found."));
        return ApiResponse.successResponse(role,"Done!");
    }
}
