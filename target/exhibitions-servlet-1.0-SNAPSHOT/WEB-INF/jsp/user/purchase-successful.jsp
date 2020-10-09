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
        <fmt:message key="purchase_successful.title"/>
    </title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body>
<main role="main" class="container p-5">
    <div class="jumbotron">
        <h1>
            <fmt:message key="purchase_successful.title"/>
        </h1>
        <p>
            <fmt:message key="purchase_successful.description"/>
        </p>
        <a class="btn btn-lg btn-primary" href="${pageContext.request.contextPath}/app/" role="button">
            <fmt:message key="error.message.go_to_main"/>
        </a>
    </div>
</main>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</html>
