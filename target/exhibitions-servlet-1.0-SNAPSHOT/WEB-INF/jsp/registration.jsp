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
        <fmt:message key="registration.button.sign_up"/>
    </title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body>

<div class="container-fluid">
    <div class="container login-registration-container w-50 p-5">
        <form action="${pageContext.request.contextPath}/app/registration" method="post">
            <h1 class="text-center">
                <fmt:message key="registration.label.title"/>
            </h1>
            <div class="form-group">
                <label for="inputLogin">
                    <fmt:message key="registration.label.login"/>
                </label>
                <input name="username" type="text" class="form-control" id="inputLogin">
            </div>

            <c:if test="${requestScope.duplicatedUsername != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="registration.duplicated_username"/>
                </div>
            </c:if>

            <div class="form-group">
                <label for="inputEmail">
                    <fmt:message key="registration.label.email"/>
                </label>
                <input name="email" type="text" class="form-control" id="inputEmail">
            </div>

            <c:if test="${requestScope.wrongEmailFormat != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="registration.wrong_email_format"/>
                </div>
            </c:if>

            <div class="form-group">
                <label for="inputPassword">
                    <fmt:message key="registration.label.password"/>
                </label>
                <input name="password" type="password" class="form-control" id="inputPassword">
            </div>

            <c:if test="${requestScope.fieldsRequired != null}">
                <div class="alert alert-warning p-0" role="alert">
                    <fmt:message key="registration.fields_required"/>
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary">
                <fmt:message key="registration.button.sign_up"/>
            </button>
        </form>

        <div class="d-flex justify-content-center p-3">
            <div class="col-5">
                <p class="text-center">
                    <fmt:message key="registration.label.have_account"/>
                </p>
            </div>
            <div class="col-5">
                <a href="${pageContext.request.contextPath}/app/login">
                    <fmt:message key="registration.link.sign_in"/>
                </a>
            </div>
        </div>
    </div>
</div>

</body>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</html>