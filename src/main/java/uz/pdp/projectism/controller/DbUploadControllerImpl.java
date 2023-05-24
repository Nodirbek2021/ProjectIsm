package uz.pdp.projectism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.service.DbUploadService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DbUploadControllerImpl implements DbUploadController{
    private final DbUploadService dbUploadService;

    @Override
    public ApiResponse<?> uploadData(MultipartFile file) throws IOException {
        return dbUploadService.uploadData(file) ;
    }
}
