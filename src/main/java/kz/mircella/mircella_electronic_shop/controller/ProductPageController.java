package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.entity.Feedback;
import kz.mircella.mircella_electronic_shop.entity.Product;
import kz.mircella.mircella_electronic_shop.entity.user.User;
import kz.mircella.mircella_electronic_shop.service.FeedbackServiceImpl;
import kz.mircella.mircella_electronic_shop.service.ProductCategoryServiceImpl;
import kz.mircella.mircella_electronic_shop.service.ProductServiceImpl;
import kz.mircella.mircella_electronic_shop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class ProductPageController {

    @Autowired
    private FeedbackServiceImpl feedbackService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @GetMapping(value = "/product/{id}")
    public String productPage(@PathVariable("id") String id, Model model) {
        Product product = productService.getProductByIdWithFeedbacks(Long.parseLong(id));
        Set<Feedback> feedbacks = product.getFeedbacks();
        model.addAttribute("product", product);
        if(feedbacks!=null){
        model.addAttribute("feedbacks",feedbacks);}
        return "product";
    }

    @PostMapping(value = "/feedback/{id}")
    public String feedbackPage(@PathVariable("id") String id, Model model, Feedback feedback, Principal principal) {
        String name = principal!=null?principal.getName():"example2";
        User user = userService.getUserByName(name);
        String text = feedback.getText();
        Date date = new Date();
        feedback.setDate(date);
        feedback.setUser(user);
        feedback.setProduct(productService.getProductById(Long.parseLong(id)));
        feedbackService.saveFeedback(feedback);
        model.addAttribute("text",text);
        return "redirect:/product/"+id;
    }

    @GetMapping(value = "/cart")
    public String cartPage(Model model) {
        model.addAttribute("review", "Review");
        return "cart";
    }

}
