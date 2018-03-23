package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.entity.Product;
import kz.mircella.mircella_electronic_shop.entity.ProductCategory;
import kz.mircella.mircella_electronic_shop.service.ProductCategoryServiceImpl;
import kz.mircella.mircella_electronic_shop.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CatalogController {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Autowired
    private ProductServiceImpl productService;

    @PostMapping(value = "/search")
    public String productSearch(Model model, final Product product) {
        String search = product.getTitle();
        List<Product> products = productService.getProductByNameOrDescription(search);
        model.addAttribute("products",products);
        return "search";
    }

    @GetMapping(value = "/")
    public String mainPage(Model model) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        for(int i=0;i<productCategories.size();i++){
            List<Product>products = productService
                    .getProductsWithProductCategory(productCategories
                            .get(i)
                            .getTitle())
                    .stream()
                    .limit(2)
                    .collect(Collectors.toList());
            model.addAttribute("products"+(i+1),products);
        }
        List<Product>products = productService.getProductsWithLowPrice(4);
        model.addAttribute("products5",products);
        model.addAttribute("categories",productCategories);
        return "index";
    }

    @GetMapping(value = "/catalog/{category_id}")
    public String categoryPage(@PathVariable("category_id") String categoryId, Model model) {
        ProductCategory category = productCategoryService.getProductCategoryById(Long.parseLong(categoryId));
        String title = category.getTitle();
        List<Product>products = productService.getProductsByProductCategory(category);
        List<String>productTitles = products.stream().map(p->p.getTitle()).collect(Collectors.toList());
        model.addAttribute("category","Category "+title);
        model.addAttribute("products",products);
        return "category";
    }

    @GetMapping(value = "/promotions")
    public String promotionPage(Model model) {
        model.addAttribute("promotion","Promotion");
        return "promotions";
    }

}
