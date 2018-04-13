package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.product.Product;
import kz.mircella.mircella_electronic_shop.product.ProductService;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class CatalogController {

    private ProductCategoryService productCategoryService;
    private ProductService productService;

    @GetMapping(value = "/catalog/{category_id}")
    public String categoryPage(@PathVariable("category_id") String categoryId, Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        ProductCategory category = productCategoryService.getProductCategoryById(Long.parseLong(categoryId));
        model.addAttribute("category", "Category " + category.getTitle());
        model.addAttribute("products", productService.getProductsByProductCategory(category));
        model.addAttribute("categories", productCategories);
        return "category";
    }

    @GetMapping(value = "/search")
    public String searchPage(@PathVariable(required = false) String title, Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("categories", productCategories);
        return "search";
    }

    @PostMapping(value = "/search")
    public String searchResultsPage(@ModelAttribute("product") Product product, Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        model.addAttribute("categories", productCategories);
        List<Product> products = productService.getProductByNameOrDescription(product.getTitle());
        model.addAttribute("products", products);
        return "search";
    }

}
