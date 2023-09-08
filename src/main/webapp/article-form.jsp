<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Article Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="card mt-4">
                <div class="card-body">
                    <c:if test="${article != null}">
                    <form action="${pageContext.request.contextPath}/user/article/actions?action=update&id=<c:out value='${article.id}'/>" method="post">
                        </c:if>
                        <c:if test="${article == null}">
                        <form action="${pageContext.request.contextPath}/user/article/actions?action=insert" method="post">
                            </c:if>
                            <caption>
                                <h2>
                                    <c:if test="${article != null}">
                                        Edit Article
                                    </c:if>
                                    <c:if test="${article == null}">
                                        Add New Article
                                    </c:if>
                                </h2>
                            </caption>

                            <fieldset class="form-group">
                                <b><label>Title</label></b>
                                <input type="text" value="<c:out value='${article.title}'/>" class="form-control"
                                       name="articleTitle" required="required" minlength="5">
                            </fieldset>

                            <fieldset class="form-group">
                                <b><label>Article</label></b>
                                <textarea class="form-control"
                                          name="articleText" minlength="5" style="height: 200px"><c:out value='${article.articleText}' /></textarea>
                            </fieldset>

                                <button type="submit" class="btn btn-success">Submit</button>
                        </form>
                </div>
            </div>
        </div>
        <div class="col-3"></div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
