package com.landgraf.scheduletask.infrastucture.business.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String email;
    private String password;
}

