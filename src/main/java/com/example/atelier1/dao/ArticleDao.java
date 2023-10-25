package com.example.atelier1.dao;

import com.example.atelier1.model.Article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArticleDao implements Dao{
    static private List<Article> listeArticles= new ArrayList<>();

    public ArticleDao() {
    }

    static public List<Article> getAll() {
        return listeArticles;
    }

    static public void addArticle(Article article) {
        listeArticles.add(article);
    }

    public void updateArticle(Article article)
    {
        for(Article art : listeArticles)
        {
            if(art.getId() == article.getId())
                art.setDesignation(article.getDesignation());
        }

    }

    public void deleteArticle(int id)
    {
        Iterator<Article> iterator = listeArticles.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return;
            }
        }
    }

    public void initialize()
    {
        listeArticles.add(new Article(1,"lait"));
        listeArticles.add(new Article(2,"pain"));
    }

    public Article getArticleById(int id){
        for (Article art : this.listeArticles) {
            if(art.getId() == id)
                return art;
        }
        return null;
    }
}
