package org.choongang.admin.edu.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestEduData {

    private Long num;
    private String mode = "add";
    private String gid = UUID.randomUUID().toString();

    @NotBlank
    private String name;

    private String dataType;
    private String contentType;
    private String serviceType;
    private String fileAddress;

    @NotBlank
    private String content;
//    private String thumbnail;
}
