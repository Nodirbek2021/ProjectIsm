package uz.pdp.projectism.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;
import uz.pdp.projectism.payload.UserDTO;

import java.util.UUID;

@RequestMapping(IsmController.ISM_CONTROLLER)
public interface IsmController {
    String ISM_CONTROLLER= AppConstants.BASE_PATH+"/ism";

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR') or hasAnyAuthority('USER')")
    ApiResponse<?> getById(@PathVariable Long id);

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')or hasAnyAuthority('USER')")
    ApiResponse<?>getAllIsmlar();

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?>editIsm(@PathVariable Long id, @RequestBody IsmDTO ismDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?>deleteIsm(@PathVariable Long id);

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?> addIsm(@RequestBody IsmDTO ismDTO);

    @PatchMapping("/increase/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR') or hasAnyAuthority('USER')")
    ApiResponse<?> increaseLikeCount(@PathVariable Long id);

    @PatchMapping("/decrease/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR') or hasAnyAuthority('USER')")
    ApiResponse<?> decreaseLikeCount(@PathVariable Long id);



}
