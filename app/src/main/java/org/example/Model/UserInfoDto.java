package org.example.Model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.example.entities.UsersInfo;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto extends UsersInfo {
    private String userName;
    private String lastName;
    private String email;
    private Long phoneNumber;
}
