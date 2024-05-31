package fr.univrouen.xmlProject.util;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponseBuilder {
        private HttpStatus status;
        private LocalDateTime timestamp;
        private String message;
        private String debugMessage;

        public ApiResponseBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiResponseBuilder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ApiResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiResponseBuilder debugMessage(String debugMessage) {
            this.debugMessage = debugMessage;
            return this;
        }

        public ApiResponse build() {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatus(status);
            apiResponse.setTimestamp(timestamp != null ? timestamp : LocalDateTime.now());
            apiResponse.setMessage(message);
            apiResponse.setDebugMessage(debugMessage);
            return apiResponse;
        }

}
