package com.Ayush.Cryptik.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String email;
    private String roles;
    private Date createdAt;
}