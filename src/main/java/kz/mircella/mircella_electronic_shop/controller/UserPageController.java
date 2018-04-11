package kz.mircella.mircella_electronic_shop.controller;

import kz.mircella.mircella_electronic_shop.product_category.ProductCategory;
import kz.mircella.mircella_electronic_shop.product_category.ProductCategoryService;
import kz.mircella.mircella_electronic_shop.user.entity.RoleEnum;
import kz.mircella.mircella_electronic_shop.user.entity.User;
import kz.mircella.mircella_electronic_shop.user.entity.UserDto;
import kz.mircella.mircella_electronic_shop.user.entity.UserMapper;
import kz.mircella.mircella_electronic_shop.user.UserService;
import kz.mircella.mircella_electronic_shop.util.WebUtil;
import kz.mircella.mircella_electronic_shop.util.message.MailBuilder;
import kz.mircella.mircella_electronic_shop.util.message.MailServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
class UserPageController {

    private UserService userService;
    private UserMapper userMapper;
    private ProductCategoryService productCategoryService;
    private MailServiceImpl emailService;

    @PostMapping(value = "/sign-up")
    String signUp(@Valid @ModelAttribute("user") User user, Model model) {
        String name = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();
        Long card = user.getCard();
        User saved = new User(name, email, password, "user.jpg", card, RoleEnum.ROLE_USER);
        userService.saveUser(saved);
        emailService.sendEmail(MailBuilder.buildConfirmationEmail(name, email));
        String message = "Confirmation letter was sent to your email";
        model.addAttribute("message",message);
        return "authorization";
    }

    @GetMapping(value = "/login")
    String login(Model model) {
        return "authorization";
    }

    @GetMapping(value = "/user-page")
    String userPage(Model model, Principal principal) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductCategories();
        org.springframework.security.core.userdetails.User loginedUser =
                (org.springframework.security.core.userdetails.User)
                        ((Authentication) principal).getPrincipal();
        String username = loginedUser.getUsername();
        User user = userService.getUserByName(username);
        UserDto userDto = userMapper.mapAppUserToUserUserDto(user);
        model.addAttribute("userDto", userDto);
        model.addAttribute("categories", productCategories);
        return "user";
    }

    @GetMapping(value = "/logout-successful")
    String logout(Model model) {
        return "redirect:/";
    }

    @GetMapping(value = "/denied")
    String denyAccess(Model model, Principal principal) {
        org.springframework.security.core.userdetails.User loginedUser;
        if (principal != null) {
            loginedUser = (org.springframework.security.core.userdetails.User)
                    ((Authentication) principal).getPrincipal();
            String username = loginedUser.getUsername();
            UserDto userDto = WebUtil.getSecurityUserInfo(username);
            model.addAttribute("userDto", userDto);
            model.addAttribute("userMessage", "You have no access to this page");
        }
        model.addAttribute("access", "Access is denied");
        return "error";
    }
}
