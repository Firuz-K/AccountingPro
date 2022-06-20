package com.applicationpro.dto;

import com.applicationpro.enums.UserStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private String phone;
    private CompanyDTO company;
    private RoleDTO role;
   // private UserStatus userStatus;

}
