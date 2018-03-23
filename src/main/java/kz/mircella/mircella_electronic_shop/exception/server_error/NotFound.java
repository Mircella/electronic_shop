package kz.mircella.mircella_electronic_shop.exception.server_error;

import lombok.Value;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;

@Value
@SuppressWarnings("PMD.FinalFieldCouldBeStatic")
public class NotFound implements ServerError {

    int status = HTTP_NOT_FOUND;
    String code = "NF";
    String message;

}
