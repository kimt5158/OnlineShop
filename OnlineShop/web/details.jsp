<%@page import="model.Product"%>
<%@page import="dal.DAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Home</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> 
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
                            <li><a href="#">My Account(<c:out value="${username }"></c:out>)</a></li>
                            </c:when>
                        </c:choose>
                    <li><a href="Controller?page=showcart">cart(<c:out value="${x}"/>)</a></li>
                </ul>
            </nav>
        </header>

        <div class="tiazon-content">
            <div class="container">
                <div class="row">
                    <div class="col-md-4"><!-- left -->
                        <div class="list-group"><!-- products -->
                            <a href="Controller?page=all-products" class="list-group-item" style="background:  #d6d4d3;">
                                All Products
                            </a>
                            <a href="Controller?page=mobiles" class="list-group-item">Mobiles</a>
                            <a href="Controller?page=laptops" class="list-group-item">Laptops</a>
                            <a href="Controller?page=clothing" class="list-group-item">Clothing</a>
                            <a href="Controller?page=home-decor" class="list-group-item">Home Decor</a>
                        </div> 
                    </div><!-- left -->
                    <%
                        DAO dao = new DAO();
                        Product pro = dao.fetchProduct(request.getParameter("id"));
                    %>
                    <div class="col-md-8"><!-- right -->
                        <h2 style="text-align: center;"><%= pro.getName()%></h2><br>
                        <div class="productIma">
                            <div class="bigIma"><img src="<%= pro.getImage() %>" alt=""></div>
                            <div class="smallIma"><img src="<%= pro.getImage() %>" alt=""></div>
                            <div class="smallIma"><img src="<%= pro.getImage() %>" alt=""></div>
                            <div class="smallIma"><img src="<%= pro.getImage() %>" alt=""></div>
                        </div>


                        <div class="productDetails">
                            <p class="detailsPrice"><%= pro.getPrice() %></p>
                            <br>
                            <b>MÔ TẢ SẢN PHẨM</b><br>
                            <br>
                            
                            <a class="btn btn-primary" href="Controller?page=addtocart&action=index&id=<%= pro.getId() %>">Add to Cart</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
