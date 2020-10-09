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
        <fmt:message key="admin_panel.exhibition.add_new"/>
    </title>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<body>
<div class="container-fluid">
    <div class="container w-50 p-5">
        <form action="${pageContext.request.contextPath}/app/admin/add-exhibition-event" method="post">
            <h2 class="text-center p-3 display-4">
                <fmt:message key="admin_panel.exhibition_event.add_new"/>
            </h2>

            <div class="form-group">
                <label for="exhibitionSelect">
                    <fmt:message key="admin_panel.exhibition_event.label.choose_exhibition"/>
                </label>
                <select name="exhibitionId" id="exhibitionSelect" class="form-control">
                    <c:forEach var="exhibition" items="${requestScope.exhibitions}">
                        <option value="${exhibition.id}">
                                ${exhibition.themeEnglish} - ${exhibition.themeUkrainian}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <c:if test="${requestScope.exhibitionError != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="admin_panel.exhibition_event.add.exhibition_error"/>
                </div>
            </c:if>

            <div class="form-group">
                <label for="inputDateFrom">
                    <fmt:message key="admin_panel.exhibition_event.label.choose_date_from"/>
                </label>
                <input name="dateFrom" id="inputDateFrom" type="date" class="form-control">
            </div>

            <div class="form-group">
                <label for="inputDateTo">
                    <fmt:message key="admin_panel.exhibition_event.label.choose_date_to"/>
                </label>
                <input name="dateTo" id="inputDateTo" type="date" class="form-control">
            </div>

            <c:if test="${requestScope.dateError != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="admin_panel.exhibition_event.add.date_error"/>
                </div>
            </c:if>


            <div class="form-group">
                <label for="inputTimeFrom">
                    <fmt:message key="admin_panel.exhibition_event.label.choose_time_from"/>
                </label>
                <input name="timeFrom" id="inputTimeFrom" type="time" class="form-control">
            </div>


            <div class="form-group">
                <label for="inputTimeTo">
                    <fmt:message key="admin_panel.exhibition_event.label.choose_time_to"/>
                </label>
                <input name="timeTo" id="inputTimeTo" type="time" class="form-control">
            </div>

            <c:if test="${requestScope.timeError != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="admin_panel.exhibition_event.add.time_error"/>
                </div>
            </c:if>

            <div class="form-group">
                <label for="inputTicketCost">
                    <fmt:message key="admin_panel.exhibition_event.label.ticket_cost"/>
                </label>
                <input name="ticketCost" type="number" class="form-control" id="inputTicketCost">
            </div>

            <c:if test="${requestScope.ticketCostError != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="admin_panel.exhibition_event.add.ticket_cost_error"/>
                </div>
            </c:if>


            <div class="form-group">
                <label for="inputMaxAvailablePlaces">
                    <fmt:message key="admin_panel.exhibition_event.label.max_places"/>
                </label>
                <input name="maxAvailablePlaces" type="number" class="form-control" id="inputMaxAvailablePlaces">
            </div>

            <c:if test="${requestScope.maxPlaceAmountError != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="admin_panel.exhibition_event.add.max_place_amount_error"/>
                </div>
            </c:if>


            <div class="form-group">
                <label for="inputHalls">
                    <fmt:message key="admin_panel.exhibition_event.label.choose_halls"/>
                </label>
                <select name="hallsIds" multiple class="form-control" id="inputHalls">
                    <c:forEach var="hall" items="${requestScope.halls}">
                        <option value="${hall.id}">${hall.nameEnglish} - ${hall.nameUkrainian}</option>
                    </c:forEach>
                </select>
            </div>

            <c:if test="${requestScope.hallsInUseError != null}">
                <div class="alert alert-danger p-0" role="alert">
                    <fmt:message key="admin_panel.exhibition_event.add.halls_in_use_error"/>
                </div>
            </c:if>

            <button type="submit" class="btn btn-primary">
                <fmt:message key="admin_panel.exhibition_event.button.add"/>
            </button>

        </form>
    </div>
</div>
</body>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</html>
