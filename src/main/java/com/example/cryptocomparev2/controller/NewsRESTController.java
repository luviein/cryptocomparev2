package com.example.cryptocomparev2.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cryptocomparev2.model.NewsInfo;
import com.example.cryptocomparev2.service.NewsService;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path="/news")
public class NewsRESTController {
    
    @Autowired
    private NewsService newsSvc;

    @GetMapping(path="{newsId}")
    public ResponseEntity<String> getNewsById(@PathVariable String newsId) throws IOException{
        NewsInfo n = newsSvc.getArticlesById(newsId);
        if(n == null || !(n.getId()).equals(newsId)){
            JsonObject error = Json.createObjectBuilder()
                .add("message", "News ID: %s not found"
                .formatted(newsId))
                .build();
            
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error.toString());
        }

        return ResponseEntity.ok(n.createJSON().toString());
    }

}
