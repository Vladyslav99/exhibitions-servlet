<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="${sessionScope.lang}">
<head>
    <title>Header</title>
</head>
<div class="container-fluid bg-light">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Gallery</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/app/">
                            <fmt:message key="header.link.main"/>
                        </a>
                    </li>

                    <c:if test="${sessionScope.user.role.roleType == 'ADMIN' || sessionScope.user.role.roleType == 'CUSTOMER'}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="${pageContext.request.contextPath}/app/user/exhibition-event-list">
                                <fmt:message key="header.link.exhibitions"/>
                            </a>
                        </li>
                    </c:if>


                    <c:if test="${sessionScope.user.role.roleType == 'ADMIN'}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="adminDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <fmt:message key="header.link.admin_panel"/>
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/app/admin/add-exhibition-event">
                                    <fmt:message key="admin_panel.add_exhibition_event"/>
                                </a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/app/admin/exhibition-event-list">
                                    <fmt:message key="admin_panel.exhibition_events"/>
                                </a>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/app/admin/statistics">
                                    <fmt:message key="admin_panel.statistics.title"/>
                                </a>
                            </div>
                        </li>
                    </c:if>


                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="languageDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <fmt:message key="header.link.language"/>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                            <a class="dropdown-item" href="?sessionLocale=en">
                                <fmt:message key="language.english"/>
                            </a>

                            <a class="dropdown-item" href="?sessionLocale=ua">
                                <fmt:message key="language.ukrainian"/>
                            </a>
                        </div>
                    </li>


                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/app/login">
                                <fmt:message key="header.link.sign_in"/>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/app/registration">
                                <fmt:message key="header.link.sign_up"/>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.user != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/app/logout">
                                <fmt:message key="header.link.logout"/>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </nav>
    </div>
</div>
</html>