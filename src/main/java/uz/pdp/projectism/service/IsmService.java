package uz.pdp.projectism.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;

import java.util.Date;
import java.util.UUID;

public interface IsmService {
    ApiResponse<?> getById( UUID id);

    ApiResponse<?>editIsm( UUID id,  IsmDTO ismDTO);

    ApiResponse<?>deleteIsm( UUID id);

    ApiResponse<?> addIsm(IsmDTO ismDTO);
    ApiResponse<?> increaseLikeCount(UUID id);
    ApiResponse<?> decreaseLikeCount(UUID id);



    ApiResponse<?> getAllIsmlar(Date date);
}
