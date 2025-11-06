package com.SkyLightLabs.api.Repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.SkyLightLabs.api.Model.Article;
@Repository
public interface ArticleRepo extends JpaRepository<Article,Integer>{
    @Query("SELECT p from Article p WHERE "+
    "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR "+
    "LOWER(p.author) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR "+
    "LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword ,'%')) OR "+
    "LOWER(p.tags) LIKE LOWER(CONCAT('%', :keyword ,'%'))")
    List<Article> search(String keyword);
    @Query("SELECT p from Article p WHERE "+
    "LOWER(p.author) LIKE LOWER(CONCAT('%', :keyword ,'%'))")
    List<Article> searchByAuthor(String keyword);
    @Query("SELECT p from Article p WHERE "+
    "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword ,'%'))")
    List<Article> searchByTitle(String keyword);
    // @Query("SELECT p from Article p WHERE "+
    // "p.pdate BETWEEN :d1 and :d2")
    // List<Article> searchByOriginalDateTime(Date d1,Date d2);
    List<Article> findByPublishDateBetween(Date d1, Date d2);

    // @Query("SELECT p from Article p WHERE "+
    // "p.udate BETWEEN :d1 and :d2")
    // List<Article> searchByUpdatedDateTime(Date d1,Date d2);
    List<Article> findByUpdateDateBetween(Date d1, Date d2);


}
