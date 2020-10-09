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
        <fmt:message key="admin_panel.exhibition_events"/>
    </title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body>
<div class="container-fluid">

    <h1 class="text-center p-3 display-4">
        <fmt:message key="admin_panel.statistics.title"/>
    </h1>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="row">
                <fmt:message key="admin_panel.exhibition_event.table.id"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.theme"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.date_from"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.date_to"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.time_from"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.time_to"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.ticket_cost"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.sold_places"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.max_places"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.status"/>
            </th>
            <th>
                <fmt:message key="admin_panel.exhibition_event.table.halls"/>
            </th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="exhibitionEvent" items="${requestScope.exhibitionEventPageable.items}">
            <tr>
                <th>${exhibitionEvent.id}</th>
                <td>
                    <p class="font-weight-normal">
                            ${exhibitionEvent.exhibition.themeEnglish}
                    </p>
                    <p class="font-weight-normal">
                            ${exhibitionEvent.exhibition.themeUkrainian}
                    </p>
                </td>
                <td>${exhibitionEvent.dateFrom}</td>
                <td>${exhibitionEvent.dateTo}</td>
                <td>${exhibitionEvent.timeFrom}</td>
                <td>${exhibitionEvent.timeTo}</td>
                <td>${exhibitionEvent.ticketCost}</td>
                <td>${exhibitionEvent.soldPlaces}</td>
                <td>${exhibitionEvent.maxAvailablePlaces}</td>
                <td>
                        ${exhibitionEvent.status}
                </td>
                <td>
                    <ul class="list-group list-group-flush">
                        <c:forEach var="hall" items="${exhibitionEvent.halls}">
                            <li class="list-group-item" style="padding: 0">
                                    ${hall.nameEnglish} - ${hall.nameUkrainian}
                            </li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <nav>
        <ul class="pagination justify-content-center">
            <c:forEach var="pageNumber" begin="1" end="${requestScope.pageAmount}">
                <li class="page-item">
                    <a class="page-link"
                       href="${pageContext.request.contextPath}/app/admin/statistics?pageId=${pageNumber}">
                            ${pageNumber}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </nav>
</div>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</html>
