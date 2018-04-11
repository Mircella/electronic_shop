package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.feedback.Feedback;
import kz.mircella.mircella_electronic_shop.feedback.FeedbackService;
import kz.mircella.mircella_electronic_shop.order.OrderService;
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
    private ProductService productService;
    private ProductCategoryService productCategoryService;
    private OrderService orderService;

    @GetMapping(value = "/product/{id}")
    public String productPage(@PathVariable("id") String id, Model model) {
        Product product = productService.getProductById(Long.parseLong(id));
        model.addAttribute("product", productService.getProductById(Long.parseLong(id)));
        model.addAttribute("categories", productCategoryService.getAllProductCategories());
        model.addAttribute("feedbacks", feedbackService.getFeedbacksByProduct(product));
        return "product";
    }

    @PostMapping(value = "/product/{id}")
    public String feedbackPage(@PathVariable("id") String id, Feedback feedback, Principal principal) {
        feedbackService.createFeedbackDetails(principal != null ?
                        principal.getName() :
                        "Anonymous",
                feedback.getText(),
                Long.parseLong(id),
                null);
        return "redirect:/product/" + id;
    }

    @GetMapping(value = "/cart/{id}")
    public String cartPage(@PathVariable("id") String id, Principal principal, Model model) {
        orderService.saveOrder(principal.getName(), id);
        model.addAttribute("categories", productCategoryService.getAllProductCategories());
        model.addAttribute("order", orderService.getOrder(principal.getName()));
        return "cart";
    }

    @GetMapping(value = "/cart-remove/{title}")
    public String cartRemovePage(@PathVariable("title") String title, Principal principal, Model model) {
        model.addAttribute("categories", productCategoryService.getAllProductCategories());
        model.addAttribute("order", orderService.getOrder(principal.getName()));
        return "cart";
    }

}
