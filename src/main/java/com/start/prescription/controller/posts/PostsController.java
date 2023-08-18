package com.start.prescription.controller.posts;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PostsController {

    @GetMapping("/list")
    public String getList() {
        return null;
    }
}
