<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.atelier1.model.Article" %>
<html>
<head>
    <title>Articles List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Optional JavaScript; choose one of the two! -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="container mt-5">

<div class="card mb-4 shadow-sm">
    <div class="card-header bg-info text-white">
        <h3>Create or Update Product</h3>
    </div>
    <div class="card-body">
        <%
            Article art =(Article) request.getAttribute("artToEdit");
        %>
        <form action="${pageContext.request.contextPath}/articles" method="Post">
            <div class="form-group">
                <label for="id">ID:</label>
                <input type="text" id="id" name="id" class="form-control" value="<%=art != null ? art.getId() : ""%>">
            </div>
            <div class="form-group">
                <label for="designation">Designation:</label>
                <input type="text" id="designation" name="designation" class="form-control" value="<%=art != null ? art.getDesignation() : ""%>">
            </div>
            <input type="submit" name="action" class="btn btn-primary" value="<%= art != null ? "Update" : "Add" %> Article">
        </form>
    </div>
</div>

<div class="card shadow-sm">
    <div class="card-header bg-primary text-white">
        <h3>Articles List</h3>
    </div>
    <div class="card-body">
        <%
            List<Article> articles = (List<Article>) request.getAttribute("articles");
            if(articles != null && !articles.isEmpty()) {
        %>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Designation</th>
                <th>Operation</th>
            </tr>
            </thead>
            <tbody>
            <% for (Article article : articles) { %>
            <tr>
                <td><%= article.getId() %></td>
                <td><%= article.getDesignation() %></td>
                <td>
                    <a href="${pageContext.request.contextPath}/articles?id=<%=article.getId()%>&action=edit" class="btn btn-sm btn-warning">Edit</a>
                    <a href="${pageContext.request.contextPath}/articles?id=<%=article.getId()%>&action=Delete" class="btn btn-sm btn-danger">Delete</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <p class="alert alert-info">No articles found.</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
