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
        <fmt:message key="header.link.exhibitions"/>
    </title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body>
<div class="container w-75 p-5">

    <h1 class="text-center p-3 display-4">
        <fmt:message key="admin_panel.exhibition_events"/>
    </h1>

    <div class="container d-flex flex-column">
        <div class="d-flex justify-content-center">
            <form action="${pageContext.request.contextPath}/app/user/exhibition-event-list?filter=date"
                  class="form-inline">
                <div class="form-group mx-sm-3 mb-2">
                    <input type="date" name="date" class="form-control" id="inputDateFrom">
                </div>

                <button type="submit" class="btn btn-primary mb-2">
                    <fmt:message key="exhibition_list.find_button"/>
                </button>
            </form>
        </div>

        <div class="d-flex justify-content-center">
            <form action="${pageContext.request.contextPath}/app/user/exhibition-event-list?filter=ticketCost"
                  class="form-inline">
                <div class="form-group mx-sm-3 mb-2">
                    <input type="number" name="ticketCostFrom" class="form-control">
                </div>

                <div class="form-group mx-sm-3 mb-2">
                    <input type="number" name="ticketCostTo" class="form-control">
                </div>

                <button type="submit" class="btn btn-primary mb-2">
                    <fmt:message key="exhibition_list.find_button"/>
                </button>
            </form>
        </div>

        <div class="d-flex justify-content-center">
            <form action="${pageContext.request.contextPath}/app/user/exhibition-event-list?filter=exhibition"
                  class="form-inline">

                <div class="form-group mx-sm-3 mb-2">
                    <select name="exhibitionId" class="form-control">
                        <c:forEach var="exhibition" items="${requestScope.exhibitions}">
                            <c:choose>
                                <c:when test="${sessionScope.lang == 'ua'}">
                                    <option value="${exhibition.id}">
                                            ${exhibition.themeUkrainian}
                                    </option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${exhibition.id}">
                                            ${exhibition.themeEnglish}
                                    </option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary mb-2">
                    <fmt:message key="exhibition_list.find_button"/>
                </button>
            </form>
        </div>

    </div>

    <c:forEach var="exhibitionEvent" items="${requestScope.exhibitionEventPageable.items}">
        <div class="card" style="margin-top: 20px; margin-bottom: 20px;">
            <div class="container d-flex p-0">
                <div>
                    <img src="${exhibitionEvent.exhibition.imageUrl}"
                         style="height: 400px; width: 400px" alt="Exhibition image">
                </div>
                <div class="card-body">
                    <div class="d-flex justify-content-around">
                        <h5 class="card-title">
                            <fmt:message key="admin_panel.exhibition_event.table.theme"/>
                        </h5>
                        <div class="ml-auto">
                            <div class="d-flex justify-content-around">
                                <p class="font-weight-bold">
                                        ${exhibitionEvent.dateFrom}
                                </p>
                                <p class="font-weight-bold" style="margin-left: 50px">
                                        ${exhibitionEvent.dateTo}
                                </p>
                            </div>

                            <div class="d-flex justify-content-around">
                                <p class="font-weight-bold">
                                        ${exhibitionEvent.timeFrom}
                                </p>
                                <p class="font-weight-bold">
                                        ${exhibitionEvent.timeTo}
                                </p>
                            </div>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${sessionScope.lang == 'ua'}">
                            <p class="card-text">
                                    ${exhibitionEvent.exhibition.themeUkrainian}
                            </p>
                            <p class="card-text">
                                    ${exhibitionEvent.exhibition.descriptionUkrainian}
                            </p>
                        </c:when>
                        <c:otherwise>
                            <p class="card-text">
                                    ${exhibitionEvent.exhibition.themeEnglish}
                            </p>

                            <p class="card-text">
                                    ${exhibitionEvent.exhibition.descriptionEnglish}
                            </p>
                        </c:otherwise>
                    </c:choose>

                    <h5>
                        <fmt:message key="admin_panel.exhibition_event.table.halls"/>
                    </h5>
                    <ul class="list-group list-group-flush">
                        <c:forEach var="hall" items="${exhibitionEvent.halls}">
                            <li class="list-group-item p-1 d-flex justify-content-between">
                                <c:choose>
                                    <c:when test="${sessionScope.lang == 'ua'}">
                                        <p class="card-text">
                                                ${hall.nameUkrainian}
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="card-text">
                                                ${hall.nameEnglish}
                                        </p>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>
                    </ul>

                </div>
                <div class="d-flex align-items-end flex-column">
                    <a class="btn btn-primary mt-auto p-2 m-3"
                       href="${pageContext.request.contextPath}/app/user/purchase-successful?eventId=${exhibitionEvent.id}">
                        <fmt:message key="exhibitions.button.buy_ticket"/> ${exhibitionEvent.ticketCost} $
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>

</div>
<nav>
    <ul class="pagination justify-content-center">
        <c:forEach var="pageNumber" begin="1" end="${requestScope.pageAmount}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/app/user/exhibition-event-list?pageId=${pageNumber}">
                        ${pageNumber}
                </a>
            </li>
        </c:forEach>
    </ul>
</nav>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</html>
