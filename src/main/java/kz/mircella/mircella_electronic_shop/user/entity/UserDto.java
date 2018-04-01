package kz.mircella.mircella_electronic_shop.user.entity;

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
    @NotBlank private String photoPath;
}
