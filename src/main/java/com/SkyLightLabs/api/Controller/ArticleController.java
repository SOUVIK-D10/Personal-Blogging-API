package com.SkyLightLabs.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.SkyLightLabs.api.DTO.DateDTO;
import com.SkyLightLabs.api.Model.Article;
import com.SkyLightLabs.api.Service.ArticleService;
import com.SkyLightLabs.api.StanderdConstants.SearchFilter;

@RestController
@RequestMapping("/article_api")
public class ArticleController {
    @Autowired
    private ArticleService service;
    
    
    @GetMapping("/search")
    private ResponseEntity<?> search(@RequestParam(required=false,defaultValue = "") String keyword,
    @RequestParam(required = false,defaultValue = SearchFilter.none) String filter,
    @RequestBody(required = false) DateDTO dates){
        System.out.println(keyword+","+filter);
        try{
            switch(filter){
                case SearchFilter.none:
                return new ResponseEntity<>(service.searchByKeyword(0,keyword),HttpStatus.OK);
                // break;
                case SearchFilter.title:
                return new ResponseEntity<>(service.searchByKeyword(1,keyword),HttpStatus.OK);
                // break;
                case SearchFilter.author:
                System.out.println("here -> ");
                return new ResponseEntity<>(service.searchByKeyword(2,keyword),HttpStatus.OK);
                // break;
                case SearchFilter.PublishDate:
                return new ResponseEntity<>(service.searchByDate(1,dates.getStartDate(),dates.getEndDate()),HttpStatus.OK);
                // break;
                case SearchFilter.UpdateDate:
                return new ResponseEntity<>(service.searchByDate(2,dates.getStartDate(),dates.getEndDate()),HttpStatus.OK);
                // break;
                default: return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // return null;
    }

    @GetMapping("/article")
    public ResponseEntity<?> getData(@RequestParam(required = false,defaultValue = "0") String id){
        try {
            int uid = Integer.parseInt(id);
            if(uid==0) return new ResponseEntity<>(service.getAll(),HttpStatus.OK);
            else{
                Article article = service.getById(uid);
                if(article==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                else return new ResponseEntity<>(article,HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return null;
    }
    
    @PostMapping("/article")
    public ResponseEntity<?> add(@RequestBody List<Article> articles){
        if(service.add(articles)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
    }
    @PutMapping("/article")
    public ResponseEntity<?> update(@RequestBody Article articles){
        if(service.update(articles)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/article")
    public ResponseEntity<?> delete(@RequestParam(required = true) int id){
        if(service.delete(id)) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
