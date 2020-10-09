<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>
        <fmt:message key="login.label.title"/>
    </title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body>
<div class="container-fluid">
    <div class="container login-registration-container w-50 p-5">

        <form action="${pageContext.request.contextPath}/app/login" method="post">
            <h1 class="text-center">
                <fmt:message key="login.label.title"/>
            </h1>

            <div class="form-group">
                <label for="inputLogin">
                    <fmt:message key="login.label.login"/>
                </label>
                <input name="username" type="text" class="form-control" id="inputLogin">
            </div>

            <div class="form-group">
                <label for="inputPassword">
                    <fmt:message key="login.label.password"/>
                </label>
                <input name="password" type="password" class="form-control" id="inputPassword">
            </div>

            <c:if test="${requestScope.wrongUsernamePassword != null}">
                <div class="alert alert-warning p-0" role="alert">
                    <fmt:message key="login.wrong_username_or_password"/>
                </div>
            </c:if>

            <c:if test="${requestScope.fieldsRequired != null}">
                <div class="alert alert-warning p-0" role="alert">
                    <fmt:message key="login.fields_required"/>
                </div>
            </c:if>

            <c:if test="${requestScope.loggedUser != null}">
                <div class="alert alert-warning p-0" role="alert">
                    <fmt:message key="login.already_logged"/>
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary">
                <fmt:message key="login.button.sign_in"/>
            </button>
        </form>
        <div class="d-flex justify-content-center p-3">
            <div class="col-5">
                <p class="text-center">
                    <fmt:message key="login.label.have_no_account"/>
                </p>
            </div>
            <div class="col-5">
                <a href="${pageContext.request.contextPath}/app/registration">
                    <fmt:message key="login.link.sign_up"/>
                </a>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</html>
