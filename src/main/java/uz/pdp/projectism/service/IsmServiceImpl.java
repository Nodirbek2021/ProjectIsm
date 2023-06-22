package uz.pdp.projectism.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.projectism.entity.Ism;
import uz.pdp.projectism.entity.User;
import uz.pdp.projectism.exceptions.RestException;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.IsmDTO;
import uz.pdp.projectism.repository.IsmRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IsmServiceImpl implements IsmService {
    private final IsmRepository ismRepository;


    @Override
    public ApiResponse<?> getById(UUID id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        return ApiResponse.successResponse(ism,"found");
    }


    @Override
    public ApiResponse<?> editIsm(UUID id, IsmDTO ismDTO) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ism.setNameLat(ismDTO.getNameLat());
        ism.setNameCyr(ismDTO.getNameCyr());
        ism.setGender(ismDTO.isGender());
        ism.setDefinition(ismDTO.getDefinition());
        ism.setComingLang(ismDTO.getComingLang());
        ism.setLikeCount(ismDTO.getLikeCount());
        ism.setNamedPeople(ismDTO.getNamedPeople());
        Ism savedIsm = ismRepository.save(ism);
        savedIsm.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        Ism save = ismRepository.save(savedIsm);
        return ApiResponse.successResponse(save,"Edited!");
    }

    @Override
    public ApiResponse<?> deleteIsm(UUID id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ismRepository.delete(ism);
        return ApiResponse.successResponse("deleted");
    }

    @Override
    public ApiResponse<?> addIsm(IsmDTO ismDTO) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
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
        return ApiResponse.successResponse(save,"Saved");
    }

    @Override
    public ApiResponse<?> increaseLikeCount(UUID id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ism.setLikeCount(ism.getLikeCount()+1);
        Ism savedIsm = ismRepository.save(ism);
        return ApiResponse.successResponse("Liked!");
    }

    @Override
    public ApiResponse<?> decreaseLikeCount(UUID id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        Ism ism = ismRepository.findById(id).orElseThrow(() -> RestException.notFound("Ism not found!"));
        ism.setLikeCount(ism.getLikeCount()-1);
        Ism savedIsm = ismRepository.save(ism);
        return ApiResponse.successResponse("Unliked");
    }

    @Override
    public ApiResponse<?> getAllIsmlar(Date date) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        if (date == null){
            List<Ism> all = ismRepository.findAll();
        return ApiResponse.successResponse(all,"Here you are!");
        }
        List<Ism> byUpdatedAt = ismRepository.findAllByUpdatedAtAfter(date);
        return ApiResponse.successResponse(byUpdatedAt,"Here you are!");
    }


}
