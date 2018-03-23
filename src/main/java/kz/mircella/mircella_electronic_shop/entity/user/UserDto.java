package kz.mircella.mircella_electronic_shop.entity.user;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @NotBlank private String userName;
    @NotBlank private String userPassword;
    @NotBlank private Long userCard;
    @NotBlank private String userEmail;
    @NotBlank private RoleEnum userRole;
}
