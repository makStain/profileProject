<%@ page import="model.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kaz News</title>
    <%@include file="link.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<br>
<div class="container" style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-12">
            <%
                ArrayList<News> news = (ArrayList<News>) request.getAttribute("news");
                if (news != null){
                    for (News news1 : news) {
            %>
            <div class="row mt-3">
                <div class="col-11 mx-auto p-3" style="background-color: lightgrey;">
                    <h2><%=news1.getTitle()%></h2>
                    <p class="mt-2"><%=news1.getContent()%></p>
                    <p class="mt-2">
                        Category <strong><%=news1.getCategoryID()%></strong>
                        at <strong><%=news1.getPostDate()%></strong>
                    </p>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
