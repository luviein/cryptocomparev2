package com.example.cryptocomparev2.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cryptocomparev2.model.NewsInfo;
import com.example.cryptocomparev2.service.NewsService;

@Controller
public class NewsController {
    
    @Autowired
    private NewsService newsSvc;


    @GetMapping(path="/")
    public String getLandingPage(Model m) throws IOException{

        List<NewsInfo> latestNews = newsSvc.getArticles();

        m.addAttribute("latestNews", latestNews);

        return "landingpage";
    }

    @PostMapping(path="/articles")
    public String processArticles(@RequestParam(name="selectedArticles") String[] articleIds, Model m) throws IOException{

        //converts selected IDs from array to list of string
        List<String> selectedIds = Arrays.asList(articleIds);
        //retrieve all articles to compare with selected IDs
        List<NewsInfo> allArticlesToCompare = newsSvc.getArticles();
        Iterator<NewsInfo> iterator = allArticlesToCompare.iterator();

        // remove IDs that are not saved
        while(iterator.hasNext()) {
            NewsInfo newsInfo = iterator.next();
            if(!selectedIds.contains(newsInfo.getId())){
                iterator.remove();
            }
        }


    }

}
