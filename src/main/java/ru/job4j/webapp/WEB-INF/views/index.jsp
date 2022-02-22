<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
          crossorigin="anonymous">

    <title>Главная</title>

</head>
<body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
            <div class="container">
                <a class="navbar-brand" href="#">
                    Car Accident
                </a>
                <div class="row">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" href="<c:url value='/'/>">Главная</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/create'/>">Добавить инцидент</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container pt-3">
            <div class="row">
                    <table class="table table-bordered border-dark text-center">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Наименование</th>
                            <th scope="col">Тип</th>
                            <th scope="col">Статья</th>
                            <th scope="col">Описание</th>
                            <th scope="col">Адрес</th>
                            <th scope="col">Редактировать</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${accindents}" var="accindent">
                            <tr>
                                <td>
                                    <c:out value="${accindent.id}"/>
                                </td>
                                <td>
                                    <c:out value="${accindent.name}"/>
                                </td>
                                <td>
                                    <c:out value="${accindent.accidentType.name}"/>
                                </td>
                                <td>
                                    <c:forEach items="${accindent.rules}" var="rule">
                                        <c:out value="${rule.name}"/>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:out value="${accindent.text}"/>
                                </td>
                                <td>
                                    <c:out value="${accindent.address}"/>
                                </td>
                                <td>
                                    <a class="btn btn-outline-dark" href="<c:url value='/update?id=${accindent.id}'/>">редактировать</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
            </div>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
                crossorigin="anonymous"></script>
</body>
</html>
