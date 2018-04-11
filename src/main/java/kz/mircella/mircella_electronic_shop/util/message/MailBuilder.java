package kz.mircella.mircella_electronic_shop.util.message;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MailBuilder {
    public static Mail buildConfirmationEmail(String nameTo, String email) {
        Map<String, Object> model = new HashMap<>();
        model.put("name", nameTo);
        model.put("from", "Electronic Shop");
        return Mail.builder()
                .from("noreply@electronicshop.com")
                .to(email)
                .subject("Confirmation letter from Electronic Shop")
                .model(model)
                .build();
    }
}
