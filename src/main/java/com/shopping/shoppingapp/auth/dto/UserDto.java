package com.shopping.shoppingapp.auth.dto;

import com.shopping.shoppingapp.auth.entities.User;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class UserDto {
    private String id;
    private String name;
    private String email;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
