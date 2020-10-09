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
        <fmt:message key="header.link.main"/>
    </title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body>
<div class="cover-container d-flex p-3 mx-auto flex-column">
    <div class="container p-5">
        <h1 class="cover-heading text-center">Виставки</h1>
        <p class="lead text-center">Існують ролі: користувач, авторизований користувач, адміністратор.</p>
        <p class="lead text-center">Адміністратор робить список експозицій (тема, зал, період та час роботи, вартість
            квитка), а також може відміняти експозиції, переглядати статистику відвідувань.</p>
        <p class="lead text-center">Користувач може переглядати експозиції за темою, вартістю квитка, а також
            фільтрувати за датою проведення.</p>
        <p class="lead text-center">Авторизований користувач може купити квиток на обрану експозицію. </p>


        <p class="lead text-center">
            <c:if test="${sessionScope.user == null}">
                <a href="${pageContext.request.contextPath}/app/login" class="btn btn-lg btn-primary">
                    <fmt:message key="login.button.sign_in"/>
                </a>
            </c:if>
        </p>
    </div>
</div>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</html>
