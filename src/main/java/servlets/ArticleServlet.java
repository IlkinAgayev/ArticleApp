package servlets;

import com.articleapp.dao.ArticleDaoImpl;
import com.articleapp.model.Article;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArticleServlet", value = "/user/article")
public class ArticleServlet extends HttpServlet {
    private ArticleDaoImpl articleDao;

    @Override
    public void init() throws ServletException {
        articleDao=new ArticleDaoImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username= (String) request.getSession().getAttribute("username");
        List<Article> articles = articleDao.selectMyAllArticles(username);
        request.setAttribute("articles",articles);
        request.getRequestDispatcher("/article.jsp").forward(request, response);

    }
}
