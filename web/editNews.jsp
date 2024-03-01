<%@ page import="model.News" %>
<%@ page import="model.NewsCategories" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kaz News</title>
    <%@include file="link.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-6 offset-3">
            <%
                News news = (News) request.getAttribute("news");
                if (news != null){
            %>
            <%
                String success = request.getParameter("success");
                if (success != null){
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                Edited successfully!
            </div>
            <%
                }
            %>
            <%
                String error = request.getParameter("error");
                if (error != null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Something went wrong!
            </div>
            <%
                }
            %>
            <form action="/editNews" method="post">
                <input type="hidden" name="id" value="<%=news.getId()%>">
                <div class="form-group">
                    <label>Title: </label>
                    <input type="text" name="title" class="form-control" value="<%=news.getTitle()%>">
                </div>
                <div class="form-group">
                    <label>Content: </label>
                    <input type="text" name="content" class="form-control" value="<%=news.getContent()%>">
                </div>
                <div class="form-group">
                    <label>Category: </label>
                    <select id="category" name="category" required class="form-control">
                        <%
                            ArrayList<NewsCategories> categories = (ArrayList<NewsCategories>) request.getAttribute("categories");
                            if(categories != null) {
                                for (NewsCategories category : categories) {
                        %>
                        <option value="<%= category.getId() %>"><%= category.getName() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
            </form>

            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
