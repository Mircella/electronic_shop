package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.product.ProductService;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategoryService;
import kz.mircella.mircella_electronic_shop.util.message.MailBuilder;
import kz.mircella.mircella_electronic_shop.util.message.MailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
class MainController {
    private ProductCategoryService productCategoryService;
    private ProductService productService;

    @GetMapping(value = "/")
    String mainPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        List<List<Product>> products = productService.getProductsForMainPage(2);
        products.forEach(p -> {
            model.addAttribute("products" + (products.indexOf(p) + 1), p);
        });
        model.addAttribute("products5", productService.getProductsWithLowPrice(4));
        model.addAttribute("categories", productCategories);
        return "index";
    }

    @GetMapping(value = "/promotions")
    String promotionPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("promotion", "Promotion");
        model.addAttribute("categories", productCategories);
        return "promotions";
    }

    @GetMapping(value = "/contacts")
    String contactPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("promotion", "Promotion");
        model.addAttribute("categories", productCategories);
        return "contacts";
    }

    @GetMapping(value = "/reviews")
    String reviewPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("review", "Review");
        model.addAttribute("categories", productCategories);
        return "reviews";
    }
}
