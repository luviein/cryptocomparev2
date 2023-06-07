package com.example.cryptocomparev2.model;

import java.io.ByteArrayInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class NewsInfo implements Serializable {
    private String id;
    private Long published_on;
    private String title;
    private String url;
    private String imageURL;
    private String body;
    private String tags;
    private String categories;

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public Long getPublished_on() {return published_on;}
    public void setPublished_on(Long published_on) {this.published_on = published_on;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}
    public String getImageURL() {return imageURL;}
    public void setImageURL(String imageURL) {this.imageURL = imageURL;}
    public String getBody() {return body;}
    public void setBody(String body) {this.body = body;}
    public String getTags() {return tags;}
    public void setTags(String tags) {this.tags = tags;}
    public String getCategories() {return categories;}
    public void setCategories(String categories) {this.categories = categories;}
    
    @Override
    public String toString() {
        return "NewsInfo [id=" + id + ", published_on=" + published_on + ", title=" + title + ", url=" + url
                + ", imageURL=" + imageURL + ", body=" + body + ", tags=" + tags + ", categories=" + categories + "]";
    }

    public NewsInfo() {
    }


    public static NewsInfo setJson(String json) throws IOException{
        var n = new NewsInfo();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            n.setId(o.getString("id"));
            n.setPublished_on(o.getJsonNumber("published_on").longValue());
            n.setTitle(o.getString("title"));
            n.setUrl(o.getString("url"));
            n.setImageURL((o.getString("imageurl")));
            n.setBody(o.getString("body"));
            n.setTags(o.getString("tags"));
            n.setCategories(o.getString("categories)"));

        }

        return n;
        
    }

    public JsonObject createJSON(){
        return Json.createObjectBuilder()
            .add("id", this.getId())
            .add("published_on", this.getPublished_on())
            .add("title", this.getTitle())
            .add("url", this.getUrl())
            .add("imageurl", this.getImageURL())
            .add("body", this.getBody())
            .add("tags", this.getTags())
            .add("categories", this.getCategories())
            .build();
    }

    
    
}
