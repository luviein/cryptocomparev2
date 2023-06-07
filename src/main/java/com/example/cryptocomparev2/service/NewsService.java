package com.example.cryptocomparev2.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.cryptocomparev2.model.NewsInfo;
import com.example.cryptocomparev2.repository.NewsRepository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class NewsService {


    @Autowired
    private NewsRepository repo;

    public List<NewsInfo> getArticles() throws IOException {

        // building the URI
        String newsUrl = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";
                

        System.out.println("NewsURL: " + newsUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res = template.getForEntity(newsUrl, String.class);

        // Access Data array in Json response. Best to separate as only used ONCE
        JsonReader jsonReader = Json.createReader(new StringReader(res.getBody()));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();

        JsonArray dataArray = jsonObject.getJsonArray("Data");

        List<NewsInfo> list = new LinkedList<>();
        for (int i = 0; i < dataArray.size(); i++) {
            new NewsInfo();

            list.add(NewsInfo.setJson(dataArray.get(i).toString()));

        }
        return list;

    }

    public void saveArticles(List<NewsInfo> articles) {
        for(NewsInfo article : articles){
            this.repo.save(article);
        }
    }

    public NewsInfo getArticlesById(String newsId) throws IOException {
       return this.repo.get(newsId);
    }

}
