package uz.pdp.projectism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;
import uz.pdp.projectism.service.IsmService;

import java.util.Date;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class IsmControllerImpl implements IsmController {

    private final IsmService ismService;

    @Override
    public ApiResponse<?> getById(UUID id) {
        return ismService.getById(id);
    }


    @Override
    public ApiResponse<?> editIsm(UUID id, IsmDTO ismDTO) {
        return ismService.editIsm(id,ismDTO);
    }

    @Override
    public ApiResponse<?> deleteIsm(UUID id) {
        return ismService.deleteIsm(id);
    }

    @Override
    public ApiResponse<?> addIsm(IsmDTO ismDTO) {
        return ismService.addIsm(ismDTO);
    }

    @Override
    public ApiResponse<?> increaseLikeCount(UUID id) {
        return ismService.increaseLikeCount(id);
    }

    @Override
    public ApiResponse<?> decreaseLikeCount(UUID id) {
        return ismService.decreaseLikeCount(id);
    }

    @Override
    public ApiResponse<?> getAllIsmlar(Date date) {
        return ismService.getAllIsmlar(date);
    }


}
