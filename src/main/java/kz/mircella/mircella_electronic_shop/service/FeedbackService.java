package kz.mircella.mircella_electronic_shop.service;

import kz.mircella.mircella_electronic_shop.entity.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService {
    List<Feedback>getAllFeedbacks();
    Feedback getFeedbackById(long id);
    void deleteFeedbackById(long id);
    Feedback saveFeedback(Feedback feedback);
}
