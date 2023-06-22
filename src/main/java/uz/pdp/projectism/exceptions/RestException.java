package uz.pdp.projectism.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class RestException extends HttpStatusCodeException {

    private RestException(String message, HttpStatus status) {
        super(status, message);
    }

    public static RestException notFound(String key){
        return new RestException("Not Found", HttpStatus.NOT_FOUND);
    }

    public static RestException restThrow(String key, HttpStatus status){
        return new RestException("Something went wrong;", status);
    }

    public static RestException unauthorized(String key){
        return new RestException("You don't have access to this page!", HttpStatus.UNAUTHORIZED);
    }

    public static RestException conflict(String key){
        return new RestException("Something went wrong!", HttpStatus.CONFLICT);
    }

    public static String forbidden() {
        throw new RestException("STOP!!!____Forbidden!", HttpStatus.FORBIDDEN);
    }
}
