package uz.pdp.projectism.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.projectism.entity.Ism;
import uz.pdp.projectism.exceptions.RestException;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;
import uz.pdp.projectism.repository.IsmRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IsmServiceImpl implements IsmService {
    private final IsmRepository ismRepository;


    @Override
    public ApiResponse<?> getById(Long id) {
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        return ApiResponse.successResponse(ism,"found");
    }

    @Override
    public ApiResponse<?> getAllIsmlar() {
        List<Ism> allIsmlar = ismRepository.findAll();
        return ApiResponse.successResponse(allIsmlar,"Done!");
    }

    @Override
    public ApiResponse<?> editIsm(Long id, IsmDTO ismDTO) {
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ism.setNameLat(ismDTO.getNameLat());
        ism.setNameCyr(ismDTO.getNameCyr());
        ism.setGender(ismDTO.isGender());
        ism.setDefinition(ismDTO.getDefinition());
        ism.setComingLang(ismDTO.getComingLang());
        ism.setLikeCount(ismDTO.getLikeCount());
        ism.setNamedPeople(ismDTO.getNamedPeople());
        ismRepository.save(ism);
        return ApiResponse.successResponse(ism,"Edited!");
    }

    @Override
    public ApiResponse<?> deleteIsm(Long id) {
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ismRepository.delete(ism);
        return ApiResponse.successResponse("deleted");
    }

    @Override
    public ApiResponse<?> addIsm(IsmDTO ismDTO) {
        Ism ism=new Ism(
                ismDTO.getNameLat(),
                ismDTO.getNameCyr(),
                ismDTO.isGender(),
                ismDTO.getDefinition(),
                ismDTO.getNamedPeople(),
                ismDTO.getLikeCount(),
                ismDTO.getComingLang()
        );
        Ism save = ismRepository.save(ism);
        return ApiResponse.successResponse(ism,"Saved");
    }

    @Override
    public ApiResponse<?> increaseLikeCount(Long id) {
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ism.setLikeCount(ism.getLikeCount()+1);
        Ism savedIsm = ismRepository.save(ism);
        return ApiResponse.successResponse(savedIsm,"Liked!");
    }

    @Override
    public ApiResponse<?> decreaseLikeCount(Long id) {
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ism.setLikeCount(ism.getLikeCount()-1);
        Ism savedIsm = ismRepository.save(ism);
        return ApiResponse.successResponse(savedIsm,"Unliked");
    }
}
