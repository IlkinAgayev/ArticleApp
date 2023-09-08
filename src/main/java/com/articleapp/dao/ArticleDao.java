package com.articleapp.dao;

import com.articleapp.model.Article;

import java.util.List;

public interface ArticleDao {
    void insertArticle(Article article);
    void updateArticle(Article article);
    void deleteArticle(int id);
    List<Article> selectAllArticles();
    List<Article> selectMyAllArticles(String username);
    Article getArticle(int id);

}
