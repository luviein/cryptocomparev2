package com.example.cryptocomparev2.repository;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import com.example.cryptocomparev2.model.NewsInfo;


@Repository
public class NewsRepository {
    
    @Autowired
    private RedisTemplate<String,Object> template;


    public void save(NewsInfo newsInfo){
        template.opsForValue().set(newsInfo.getId(), newsInfo.createJSON().toString());
    }

    public NewsInfo get(String newsId) throws IOException{
        String json = (String) template.opsForValue().get(newsId);
        return NewsInfo.setJson(json);
    }
}
