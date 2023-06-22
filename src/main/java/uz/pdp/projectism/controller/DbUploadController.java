package uz.pdp.projectism.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;

import java.io.IOException;

@RequestMapping(DbUploadController.UPLOAD)
@Tag(name ="Upload")
public interface DbUploadController {
    String UPLOAD = AppConstants.BASE_PATH+"/dbEdit";

    @PostMapping("/upload")
    @Operation(summary = "Upload Data to DB")
    @PreAuthorize("hasAnyAuthority('USER') or hasAnyAuthority('DIRECTOR')")
    ApiResponse<?> uploadData(@RequestParam("file") MultipartFile file) throws IOException;


}
