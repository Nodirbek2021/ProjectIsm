package uz.pdp.projectism.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;
import uz.pdp.projectism.service.IsmService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class IsmControllerImpl implements IsmController {

    private final IsmService ismService;

    @Override
    public ApiResponse<?> getById(Long id) {
        return ismService.getById(id);
    }

    @Override
    public ApiResponse<?> getAllIsmlar() {
        return ismService.getAllIsmlar();
    }

    @Override
    public ApiResponse<?> editIsm(Long id, IsmDTO ismDTO) {
        return ismService.editIsm(id,ismDTO);
    }

    @Override
    public ApiResponse<?> deleteIsm(Long id) {
        return ismService.deleteIsm(id);
    }

    @Override
    public ApiResponse<?> addIsm(IsmDTO ismDTO) {
        return ismService.addIsm(ismDTO);
    }

    @Override
    public ApiResponse<?> increaseLikeCount(Long id) {
        return ismService.increaseLikeCount(id);
    }

    @Override
    public ApiResponse<?> decreaseLikeCount(Long id) {
        return ismService.decreaseLikeCount(id);
    }
}
