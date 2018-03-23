package kz.mircella.mircella_electronic_shop.exception.server_error;

import lombok.Value;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

@Value
@SuppressWarnings("PMD.FinalFieldCouldBeStatic")
public class BadRequest implements ServerError {

    int status = HTTP_BAD_REQUEST;
    String code = "BR";
    String message;

}
