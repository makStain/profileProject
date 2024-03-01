<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kaz News</title>
    <%@include file="link.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container" style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-8 mx-auto">
            <%
                String success = request.getParameter("success");
                if (success != null){
            %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                News added successfully!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <%
                String error = request.getParameter("error");
                if (error != null){
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                Error on adding news!
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>
            <form action="/addNews" method="post">
                <div class="row">
                    <div class="col-12">
                        <label>TITLE </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <input type="text" class="form-control" name="title" placeholder="Title">
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>CONTENT </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <textarea class="form-control" name="content" rows="5" placeholder="Content"></textarea>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <label>CATEGORY </label>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-12">
                        <select class="form-select" aria-label="Default select example">
                            <option selected>All</option>
                            <option value="1">Events</option>
                            <option value="2">Sport</option>
                            <option value="3">Politics</option>
                            <option value="4">Technology</option>
                        </select>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col-12">
                        <button class="btn text-light" style="background-color: #1b2b4c">ADD NEWS</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
