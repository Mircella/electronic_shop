package kz.mircella.mircella_electronic_shop.util.message;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Mail {

    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;

}
