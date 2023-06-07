package com.example.cryptocomparev2.service;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.cryptocomparev2.model.NewsInfo;

@Service
public class NewsService {

    @Value("${cryto.compare.url}")
    private String cryptoCompareUrl;
    @Value("${crypto.compare.key}")
    private String cryptoCompareKey;
    
    public List<NewsInfo>  getArticles() throws IOException{
        
        //building the URI
        String newsUrl = UriComponentsBuilder.fromUriString(cryptoCompareUrl)
            .queryParam("api_key", cryptoCompareKey)
            .toUriString();

        System.out.println("NewsURL: " + newsUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> res = template.getForEntity(newsUrl, String.class);
        NewsInfo n = NewsInfo.setJson(res.getBody());
        
        if(n != null){
            List<NewsInfo> list = new LinkedList<>();
            list.add(n);
            return list;
        }
        return Collections.emptyList();
    }
    
}
