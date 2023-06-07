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

    private List<NewsInfo> allArticlesToCompare;


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
        allArticlesToCompare = newsSvc.getArticles();
        Iterator<NewsInfo> iterator = allArticlesToCompare.iterator();

        // remove IDs that are not saved
        while(iterator.hasNext()) {
            NewsInfo newsInfo = iterator.next();
            if(!selectedIds.contains(newsInfo.getId())){
                iterator.remove();
            }
        }

        //saves filtered articles to redis
        newsSvc.saveArticles(allArticlesToCompare);
        //generates latest articles again after saving
        List<NewsInfo> articlesAfterSaving = newsSvc.getArticles();
        m.addAttribute("latestNews", articlesAfterSaving);

        return "landingpage";
    }

    @GetMapping(path="/saved")
    public String seeSaved(Model m){
        m.addAttribute("see", allArticlesToCompare);
        System.out.println("------------->" + allArticlesToCompare);
        return "saved";
    }

}
