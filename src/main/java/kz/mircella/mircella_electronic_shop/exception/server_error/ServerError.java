package kz.mircella.mircella_electronic_shop.exception.server_error;

public interface ServerError {

    int getStatus();

    String getCode();

    String getMessage();

}
