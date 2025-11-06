package com.SkyLightLabs.api.Service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.SkyLightLabs.api.Model.Article;
import com.SkyLightLabs.api.Repo.ArticleRepo;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepo db;
    
    public List<Article> getAll(){
        return db.findAll();
        
        // Sort sort = null;
        // sort.
        // db.findAll()
    }
    public Article getById(int id){
        // Optional<Article> o = null;
        // o.get();
        Article article = db.findById(id).get();
        if(article == null) return null;
        else return article;
    }
    public boolean add(List<Article> articles){
        for(Article article : articles){
            if(db.existsById(article.getId())) return false;
            article.setPublishDate(new Date(System.currentTimeMillis()));
            article.setUpdateDate(new Date(System.currentTimeMillis()));
        }
        db.saveAll(articles);
        return true;
    }
    public boolean update(Article article){
        if(!db.existsById(article.getId())) return false;
        article.setUpdateDate(new Date(System.currentTimeMillis()));
        db.save(article);
        return true;
    }
    public boolean delete(int id){
        if(!db.existsById(id)) return false;
        db.deleteById(id);
        return true;
    }
    public List<Article> searchByKeyword(int mode,String keyword){
        return switch(mode){
            case 0 -> db.search(keyword);
            case 1 -> db.searchByTitle(keyword);
            case 2 -> db.searchByAuthor(keyword);
            default -> null;
        };
    }
    public List<Article> searchByDate(int mode,Date d1,Date d2){
        return switch (mode) {
            case 1 -> db.findByPublishDateBetween(d1, d2);
            case 2 -> db.findByUpdateDateBetween(d1, d2);
            default -> null;
        };
    }
}
