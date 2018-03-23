package kz.mircella.mircella_electronic_shop.exception.server_exception;

abstract class AbstractServerException extends RuntimeException {

    private static final long serialVersionUID = 8847218180665472888L;

    AbstractServerException(String message) {
        super(message);
    }

    AbstractServerException(String message, Object... messageParams) {
        super(String.format(message, messageParams));
    }

    AbstractServerException(String message, Throwable cause) {
        super(message, cause);
    }

}
