<%@ page import="model.Users" %>
<%
    Users currentUser = (Users) session.getAttribute("CURRENT_USER");
%>
<header style="width: 80%; margin: auto; background-color: #1b2b4c; margin-top: 1%;" class="shadow-lg p-3 mb-5 rounded">
    <nav class="navbar navbar-expand-lg navbar-dark nav-underline" <%--style="background-color: none; "--%>>
        <div class="container">
            <a class="navbar-brand text-light" href="/"><b>KAZ NEWS</b></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <%
                        if (currentUser != null){
                    %>
                    <li class="nav-item">
                        <a class="nav-link" href="/addNews">Add News</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle text-light" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false"><%=currentUser.getFullName()%></a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="/profile">Profile</a></li>
                            <li><a class="dropdown-item" href="/settings">Settings</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/logout">Logout</a></li>
                        </ul>
                    </li>
                    <%
                        } else {
                    %>
                    <li class="nav-item"><a class="nav-link text-light" href="#">Home</a></li>
                    <li class="nav-item"><a class="nav-link text-light" href="register.jsp">Register</a></li>
                    <li class="nav-item"><a class="nav-link text-light" href="login.jsp">Sign In</a></li>
<%--                    <li class="nav-item">--%>
<%--                        <label class="switch">--%>
<%--                            <input type="checkbox" id="night" />--%>
<%--                            <span class="slider"></span>--%>
<%--                        </label>--%>
<%--                    </li>--%>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
    </nav>
</header>
