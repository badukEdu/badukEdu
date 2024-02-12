package org.choongang.edu.controllers;

import lombok.Data;

@Data
public class RequestEduData {
    private String name;
    private String dataType;
    private String contentType;
    private String serviceType;
    private String fileAddress;
    private String content;
    private String thumbnail;
}
