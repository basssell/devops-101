package com.example.atelier1.controller;

import com.example.atelier1.dao.ArticleDao;
import com.example.atelier1.dao.DaoDB;
import com.example.atelier1.dao.DbConnection;
import com.example.atelier1.model.Article;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "articles" ,urlPatterns = "/articles")
public class ArticleController extends HttpServlet {

    private final ArticleDao monarticle = new ArticleDao();

    public void init() {
        DaoDB.initialize();
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String action = request.getParameter("action");
        if(action != null) {
            switch (action) {
                case "edit":
                    edit_article(request, response);
                    break;
                case "Delete":
                    delete_article(request, response);
                    break;
                case "Add Article":
                    create_article(request, response);
                    break;
                case "Update Article":
                    update_article(request, response);
                    break;
                default:
                    list(request, response);
                    break;
            }
            list(request, response);
        }else {
            list(request, response);
        }
    }




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Article> articles = DaoDB.getAll();
            request.setAttribute("articles", articles);
            request.getRequestDispatcher("WEB-INF/views/article.jsp").forward(request, response);
    }

    public void create_article(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            String designation = request.getParameter("designation");
            int id = Integer.parseInt(idStr);
            Article article = new Article(id, designation);
            DaoDB.addArticle(article);
            list(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void edit_article(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        Article art = DaoDB.getArticleById(id);
        request.setAttribute("artToEdit", art);
        list(request,response);
    }

    public void update_article(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            String designation = request.getParameter("designation");
            int id = Integer.parseInt(idStr);
            Article article = new Article(id, designation);
            DaoDB.updateArticle(article);
            list(request, response);
        } catch (NumberFormatException  e) {
            e.printStackTrace();
        }
    }

    public void delete_article(HttpServletRequest request, HttpServletResponse response) {
        try {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);
            DaoDB.deleteArticle(id);
            list(request,response);
        } catch (NumberFormatException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
