<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="link.jsp"%>
</head>
<body>
<%@include file="navbar.jsp"%>
<div class="container" style="min-height: 500px;">
    <div class="row mt-3">
        <div class="col-12">
            <h3 class="text-center">PROFILE PAGE&nbsp;<%=(currentUser!=null?currentUser.getFullName():"")%></h3>
            <%
                Users current_user = (Users) request.getSession().getAttribute("CURRENT_USER");
                boolean ONLINE = false;

                if (current_user != null){
                    ONLINE = true;
                }
            %>
            <h2 class="mb-4">
                User Profile: <%=(ONLINE ? currentUser.getFullName() : "Offline")%>
            </h2>
            <% if (ONLINE) { %>
            <div class="card">
                <div class="card-body">
                    <p class="card-text">User name: <%=current_user.getFullName() %></p>
                    <p class="card-text">Email: <%=current_user.getEmail() %></p>
                    <p class="card-text">Role: <%=current_user.getRoleID() == "1" ? "Admin" : "User" %></p>
                    <a href="/editUser?id=<%=current_user.getId()%>" class="btn btn-sm text-light" style="background-color: #1b2b4c; width: 50px">Edit</a>
                </div>
            </div>
            <% } else { %>
            <div class="alert alert-warning" role="alert">
                User is not found.
            </div>
            <% } %>
        </div>
    </div>
</div>
</body>
</html>
