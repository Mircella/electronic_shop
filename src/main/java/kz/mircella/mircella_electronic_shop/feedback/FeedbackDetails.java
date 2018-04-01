package kz.mircella.mircella_electronic_shop.feedback;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FeedbackDetails {
    @NotBlank private String text;
    @NotBlank private String username;
    @NotBlank private Date date;
    @NotBlank private int rating;
}
