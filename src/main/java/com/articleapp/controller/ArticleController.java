package com.articleapp.controller;

import com.articleapp.dao.ArticleDaoImpl;
import com.articleapp.model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArticleController", value ="/user/article/actions")
public class ArticleController extends HttpServlet {
    private ArticleDaoImpl articleDao;

    @Override
    public void init() throws ServletException {
        articleDao=new ArticleDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            request.getRequestDispatcher("/article-form.jsp").forward(request, response);
        } else {
            try {
                switch (action) {
                    case "new":
                        request.getRequestDispatcher("/article-form.jsp").forward(request, response);
                        break;
                    case "insert":
                        insertArticle(request, response);
                        break;
                    case "allarticle":
                        selectAllOfArticles(request,response);
                        break;
                    case "edit":
                        showEditArticle(request, response);
                        break;
                    case "update":
                        updateArticle(request, response);
                        break;
                    case "delete":
                        deleteArticle(request, response);
                        break;
                    default:
                        response.sendRedirect("login.jsp");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void insertArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = (String) request.getSession().getAttribute("username");
        String articleTitle = request.getParameter("articleTitle");
        String articleContext = request.getParameter("articleText");

        Article article = new Article();
        article.setArticleText(articleContext);
        article.setTitle(articleTitle);
        article.setUsername(username);

        articleDao.insertArticle(article);
        response.sendRedirect(request.getContextPath() + "/user/article");
    }

    private void showEditArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Article article = articleDao.getArticle(id);
        request.setAttribute("article", article);
        request.getRequestDispatcher("/article-form.jsp").forward(request, response);
    }

    private void updateArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String articleTitle = request.getParameter("articleTitle");
        String articleContext = request.getParameter("articleText");

        Article article = articleDao.getArticle(id);
        article.setTitle(articleTitle);
        article.setArticleText(articleContext);

        articleDao.updateArticle(article);
        response.sendRedirect(request.getContextPath() + "/user/article");
    }

    private void deleteArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        articleDao.deleteArticle(id);
        response.sendRedirect(request.getContextPath() + "/user/article");
    }

    private void selectAllOfArticles (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Article> articles = articleDao.selectAllArticles();
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("/article.jsp").forward(request, response);
    }


}
