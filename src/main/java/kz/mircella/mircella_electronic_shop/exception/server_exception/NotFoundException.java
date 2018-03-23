package kz.mircella.mircella_electronic_shop.exception.server_exception;

public class NotFoundException extends AbstractServerException {

    private static final long serialVersionUID = 1428627857023340354L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Object... messageParams) {
        super(message, messageParams);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
