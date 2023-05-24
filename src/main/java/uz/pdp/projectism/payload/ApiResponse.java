package uz.pdp.projectism.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> implements Serializable {
    public ApiResponse(T data) {
        this.data = data;
    }

    private boolean succes;
    private String message;
    private T data;

    public ApiResponse(boolean succes, T data) {
        this.succes = succes;
        this.data = data;
    }

    public ApiResponse(boolean succes, String message) {
        this.succes = succes;
        this.message = message;
    }

    public ApiResponse(boolean succes, String message, T data) {
        this.succes = succes;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> successResponse(T data, String message) {
        return new ApiResponse<>(true, message, data);
    }
    public static <T> ApiResponse<T> successResponse(T data) {
        return new ApiResponse<>(true, data);
    }

    public static <T> ApiResponse<T> successResponse(String message) {
        return new ApiResponse<>(true, message);
    }

    public static <T> ApiResponse<T> errorResponse(String message) {
        return new ApiResponse<>(false, message);
    }

}
