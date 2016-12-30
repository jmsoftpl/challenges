package go.euro.challenges.bus.route.controller.exception;

import java.io.Serializable;

public class RestError implements Serializable{

    private String error;

    public RestError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
