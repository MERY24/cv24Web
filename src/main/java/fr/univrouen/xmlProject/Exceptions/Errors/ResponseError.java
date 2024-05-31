package fr.univrouen.xmlProject.Exceptions.Errors;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ResponseError {
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ResponseError(HttpStatus status, String message, String path) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
 //need to do this manually once it's final spring assumes it init so even with @Data it won't construct getters...
    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
