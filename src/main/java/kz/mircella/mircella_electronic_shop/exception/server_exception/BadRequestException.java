package kz.mircella.mircella_electronic_shop.exception.server_exception;

public class BadRequestException extends AbstractServerException {

    private static final long serialVersionUID = 8686016918294295789L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Object... messageParams) {
        super(message, messageParams);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
