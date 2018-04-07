package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.feedback.Feedback;
import kz.mircella.mircella_electronic_shop.feedback.FeedbackDetails;
import kz.mircella.mircella_electronic_shop.feedback.FeedbackService;
import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.product.ProductService;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategoryService;
import kz.mircella.mircella_electronic_shop.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductPageController {

    private FeedbackService feedbackService;
    private UserService userService;
    private ProductService productService;
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/product/{id}")
    public String productPage(@PathVariable("id") String id, Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        Product product = productService.getProductById(Long.parseLong(id));
        List<Feedback> feedbacks = feedbackService.getFeedbacksByProduct(product);
        model.addAttribute("product", product);
        model.addAttribute("categories", productCategories);
        model.addAttribute("feedbacks", feedbacks);
        return "product";
    }

    @PostMapping(value = "/product/{id}")
    public String feedbackPage(@PathVariable("id") String id, Model model, Feedback feedback, Principal principal) {
        String name = principal != null ? principal.getName() : "Anonymous";
        String text = feedback.getText();
        FeedbackDetails feedbackDetails = feedbackService.createFeedbackDetails(name, text, Long.parseLong(id), null);
        return "redirect:/product/" + id;
    }

    @GetMapping(value = "/cart")
    public String cartPage(Model model) {
        model.addAttribute("review", "Review");
        return "cart";
    }

}
