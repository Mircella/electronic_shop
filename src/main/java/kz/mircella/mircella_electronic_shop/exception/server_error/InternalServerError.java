package kz.mircella.mircella_electronic_shop.exception.server_error;

import lombok.Value;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;

@Value
@SuppressWarnings("PMD.FinalFieldCouldBeStatic")
public class InternalServerError implements ServerError {

    int status = HTTP_INTERNAL_ERROR;
    String code = "IE";
    String message;
    String stackTrace;

}
