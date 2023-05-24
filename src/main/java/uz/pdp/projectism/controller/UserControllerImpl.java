package uz.pdp.projectism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.UserDTO;
import uz.pdp.projectism.service.UserService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ApiResponse<?> addUser(UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @Override
    public ApiResponse<?> getById(UUID id) {
        return userService.getById(id);
    }

    @Override
    public ApiResponse<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    public ApiResponse<?> editUser(UUID id, UserDTO userDTO) {
        return userService.editUser(id,userDTO);
    }

    @Override
    public ApiResponse<?> deleteUser(UUID id) {
        return userService.deleteUser(id);
    }

    @Override
    public ApiResponse<?> saveUser(UserDTO userDTO) {
//        return userService.saveUser(userDTO);
        return null;
    }
}
