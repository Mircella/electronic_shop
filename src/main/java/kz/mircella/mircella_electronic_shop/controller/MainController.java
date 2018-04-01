package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.product.ProductService;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.IntStream;

@Controller
@AllArgsConstructor
public class MainController {
    private ProductCategoryService productCategoryService;
    private ProductService productService;

    @GetMapping(value = "/")
    public String mainPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        List<List<Product>> products = productService.getProductsForMainPage(2);
        products.forEach(p->{model.addAttribute("products"+(products.indexOf(p)+1),p);});
        model.addAttribute("products5", productService.getProductsWithLowPrice(4));
        model.addAttribute("categories", productCategories);
        return "index";
    }

    @GetMapping(value = "/promotions")
    public String promotionPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("promotion", "Promotion");
        model.addAttribute("categories", productCategories);
        return "promotions";
    }

    @GetMapping(value = "/contacts")
    public String contactPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("promotion", "Promotion");
        model.addAttribute("categories", productCategories);
        return "contacts";
    }
    @GetMapping(value = "/reviews")
    public String reviewPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("review", "Review");
        model.addAttribute("categories", productCategories);
        return "reviews";
    }
}
