package com.start.prescription.controller;

import com.start.prescription.crawling.CrawlingData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/")
@Tag(name = "prescription" , description = "prescription API 명세서 예시")
public class SwaggerController {

    private CrawlingData crawlingData;

    public SwaggerController(CrawlingData crawlingData) {
        this.crawlingData = crawlingData;
    }

    @Operation(summary = "prescription swagger 테스트" , description = "swagger 테스트입니다.")
    @GetMapping("home/{id}")
    public ResponseEntity<String> swaggerTest(@Parameter(description = "ID" ,required = true, example = "hello") @PathVariable("id") String id) {
        return new ResponseEntity("hello " + id, HttpStatus.OK);
    }

    @GetMapping("crawling")
    public void crawling() {
        System.out.printf("logging~");
        crawlingData.process();
    }
}
