package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.entity.Feedback;
import kz.mircella.mircella_electronic_shop.entity.Product;
import kz.mircella.mircella_electronic_shop.service.FeedbackServiceImpl;
import kz.mircella.mircella_electronic_shop.service.ProductCategoryServiceImpl;
import kz.mircella.mircella_electronic_shop.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductPageController {

    @Autowired
    private FeedbackServiceImpl feedbackService;
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @GetMapping(value = "/product/{id}")
    public String productPage(@PathVariable("id") String id, Model model, @RequestParam(value = "text", required = false)String text) {
        Product product = productService.getProductById(Long.parseLong(id));
        model.addAttribute("product", product);
        model.addAttribute("text",text);
        return "product";
    }

    @PostMapping(value = "/feedback/{id}")
    public String feedbackPage(@PathVariable("id") String id, Model model, Feedback feedback) {
        String text = feedback.getText();
        model.addAttribute("text",text);
        return "redirect:/product/"+id;
    }

    @GetMapping(value = "/reviews")
    public String reviewPage(Model model) {
        model.addAttribute("review", "Review");
        return "reviews";
    }

}
