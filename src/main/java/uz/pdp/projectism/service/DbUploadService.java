package uz.pdp.projectism.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.projectism.payload.ApiResponse;

import java.io.IOException;
import java.util.Objects;

public interface DbUploadService {
    ApiResponse<?> uploadData(MultipartFile file) throws IOException;



}
