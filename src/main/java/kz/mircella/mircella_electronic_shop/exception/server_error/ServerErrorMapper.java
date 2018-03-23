package kz.mircella.mircella_electronic_shop.exception.server_error;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;

@Component
public class ServerErrorMapper {

    public InternalServerError mapToInternalServerError(Exception ex) {
        return new InternalServerError(ex.getMessage(), extractStackTrace(ex));
    }

    private String extractStackTrace(Throwable throwable) {
        StringWriter errorMsgWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(errorMsgWriter));
        return errorMsgWriter.toString();
    }

}
