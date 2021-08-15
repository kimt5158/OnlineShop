<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Sign up page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>

        <c:set var="x" value="0"></c:set>
        <c:forEach items="${cartlist }" var="i">
            <c:set var="x" value="${x+1 }"></c:set>
        </c:forEach>

        <header>
            <h1>
                KimShop
            </h1>
            <nav>
                <ul>
                    <li><a href="Controller?page=index">Home</a></li>
                        <c:choose>
                            <c:when test="${session == null}">
                            <li><a href="Controller?page=login">Login</a></li>
                            <li><a href="Controller?page=sign-up">Sign-up</a></li>
                            </c:when>
                            <c:when test="${session != null}">
                            <li><a href="Controller?page=logout" style="color: #F24638;">Logout</a></li>
                            <li><a href="#">My Account</a></li>
                            </c:when>
                        </c:choose>
                    <li><a href="Controller?page=showcart">cart(<c:out value="${x}"/>)</a></li>
                </ul>
            </nav>
        </header>

        <div class="signup-header">
            <h2>My Account</h2>
        </div>

        <form method="post" action="Controller">

            <input type="hidden" name="page" value="sign-up-form">

            <!-- Validations errors -->
            <font color="#F24638"><c:out value="${msg }"></c:out></font>

            <c:set var="user" value="${username }"></c:set>
            <c:forEach items="${userList }" var="user">
                <c:if test="${user.getName() == user }">


                    <div class="signup-group">
                        <label>Name</label>
                        <input type="text" name="name" value="<c:out value="${user.getName() }"></c:out>" required>
                        </div>
                        <div class="signup-group">
                            <label>Email</label>
                            <input type="email" name="email" value="<c:out value="${user.getEmail() }"></c:out>" required>
                        </div>
                        <div class="signup-group">
                            <label>Username</label>
                            <input type="text" name="username" value="<c:out value="${user.getUsername() }"></c:out>" required>
                        </div>

                        <div class="signup-group">
                            <label>Address</label>
                            <input type="text" name="address" value="<c:out value="${user.getAddress() }"></c:out>" required>
                        </div>
                        <div class="signup-group">
                            <label>Password</label>
                            <input type="password" name="password" value="<c:out value="${user.getPassword() }"></c:out>" required>
                        </div>
                        
                </c:if>
            </c:forEach>

            <div class="signup-group">
                <button type="submit" name="register" class="signup-btn">Register</button>
            </div>
            <p>
                Already have an account? <a href="Controller?page=login" style="color:#F24638;">Login!</a>
            </p>
        </form>
        <br><br><br>
        <footer>
            <div class="footer"> 
                This is footer
            </div>
        </footer>

    </body>
</html>