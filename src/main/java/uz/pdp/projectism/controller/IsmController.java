package uz.pdp.projectism.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;
import uz.pdp.projectism.payload.UserDTO;

import java.util.Date;
import java.util.UUID;

@RequestMapping(IsmController.ISM_CONTROLLER)
@Tag(name = "Ism")
public interface IsmController {
    String ISM_CONTROLLER= AppConstants.BASE_PATH+"/ism";

    @GetMapping("/{id}")
    @Operation(summary = "Get name by id")
//    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR') or hasAnyAuthority('USER') or hasAnyAuthority('NULL')")
    ApiResponse<?> getById(@PathVariable UUID id);

//    @GetMapping
//    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')or hasAnyAuthority('USER')")
//    @Operation(summary = "Get All names")
//    ApiResponse<?>getAllIsmlar();

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Edit names by its id.")
    ApiResponse<?>editIsm(@PathVariable UUID id, @RequestBody IsmDTO ismDTO);

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Delete names by its id.")
    ApiResponse<?>deleteIsm(@PathVariable UUID id);

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Add names.")
    ApiResponse<?> addIsm(@RequestBody IsmDTO ismDTO);

    @PatchMapping("/increase/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR') or hasAnyAuthority('USER')")
    @Operation(summary = "Increase Name like count.")
    ApiResponse<?> increaseLikeCount(@PathVariable UUID id);

    @PatchMapping("/decrease/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR') or hasAnyAuthority('USER')")
    @Operation(summary = "Decrease Name like count.")
    ApiResponse<?> decreaseLikeCount(@PathVariable UUID id);

    @GetMapping()
//    @PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('DIRECTOR')")
    @Operation(summary = "Get all name. Also with dates")
    ApiResponse<?>getAllIsmlar(@RequestParam(name = "from", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate);

}
