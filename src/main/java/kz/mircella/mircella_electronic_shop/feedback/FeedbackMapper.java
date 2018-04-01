package kz.mircella.mircella_electronic_shop.feedback;

import org.springframework.stereotype.Component;

@Component
public class FeedbackMapper {

    public FeedbackDetails mapToFeedbackDetails(Feedback feedback){
        return FeedbackDetails.builder()
                .username(feedback.getUser().getUsername())
                .text(feedback.getText())
                .date(feedback.getDate())
                .rating(feedback.getRating())
                .build();
    }
}
