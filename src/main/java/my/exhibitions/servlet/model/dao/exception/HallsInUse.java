package my.exhibitions.servlet.model.dao.exception;

public class HallsInUse extends RuntimeException{

    public HallsInUse() {
    }

    public HallsInUse(String message) {
        super(message);
    }

    public HallsInUse(String message, Throwable cause) {
        super(message, cause);
    }

    public HallsInUse(Throwable cause) {
        super(cause);
    }

    public HallsInUse(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
