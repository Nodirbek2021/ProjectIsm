package uz.pdp.projectism.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;

import java.util.UUID;

public interface IsmService {
    ApiResponse<?> getById( Long id);


    ApiResponse<?>getAllIsmlar();

    ApiResponse<?>editIsm( Long id,  IsmDTO ismDTO);

    ApiResponse<?>deleteIsm( Long id);

    ApiResponse<?> addIsm(IsmDTO ismDTO);
    ApiResponse<?> increaseLikeCount(Long id);
    ApiResponse<?> decreaseLikeCount(Long id);


}
