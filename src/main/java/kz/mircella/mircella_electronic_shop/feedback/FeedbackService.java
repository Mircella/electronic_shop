package kz.mircella.mircella_electronic_shop.feedback;

import kz.mircella.mircella_electronic_shop.exception.server_exception.NotFoundException;
import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.product.ProductService;
import kz.mircella.mircella_electronic_shop.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class FeedbackService {

    private FeedbackRepository feedbackRepository;
    private ProductService productService;
    private UserService userService;

    private FeedbackMapper feedbackMapper;

    @Transactional
    private Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.saveAndFlush(feedback);
    }

    public List<Feedback> getFeedbacksByProduct(Product product) {
        List<Feedback> feedbacks = feedbackRepository.findFeedbacksByProduct(product);
        return feedbacks == null ? Collections.emptyList() : sortFeedbacks(feedbacks);
    }

    private List<Feedback> sortFeedbacks(List<Feedback> feedbacks) {
        return feedbacks.stream().sorted(new Comparator<Feedback>() {
            @Override
            public int compare(Feedback o1, Feedback o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        }).collect(Collectors.toList());
    }

    private Feedback createFeedback(String username, String text, long productId, Integer rating) {
        rating = rating == null ? 5 : rating;
        return new Feedback(text,
                new Date(),
                userService.getUserByName(username),
                rating,
                productService.getProductById(productId));
    }

    @Transactional
    public FeedbackDetails createFeedbackDetails(String username, String text, long productId, Integer rating) {
        Feedback feedback = createFeedback(username, text, productId, rating);
        Feedback saved = saveFeedback(feedback);
        validateFeedback(feedback);
        return feedbackMapper.mapToFeedbackDetails(saved);
    }

    private void validateFeedback(Feedback feedback) {
        if (feedback == null) {
            throw new NotFoundException("There is no such feedback");
        }
    }
}
