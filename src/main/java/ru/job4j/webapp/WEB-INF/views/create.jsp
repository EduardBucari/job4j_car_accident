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

    <title>Создать</title>

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
            </ul>
        </div>
    </div>
</nav>

<div class="container pt-3">
    <h4>Добавить инцидент</h4>
    <div class="col-4">
        <form action="<c:url value='/'/>" method='POST'>
            <div class="pt-2">
                <label for="name" class="form-label">Название</label>
                <input type="text" class="form-control" name="name" id="name">
            </div>
            <div class="pt-2">
                <label for="address" class="form-label">Описание</label>
                <input type="text" class="form-control" name="text" id="text">
            </div>
            <div class="pt-2">
                <label for="address" class="form-label">Адрес</label>
                <input type="text" class="form-control" name="address" id="address">
            </div>
            <div class="pt-2">
                <label for="accidentType" class="form-label">Тип нарушения</label>
                <select class="form-select" name="accidentType.id" id="accidentType">
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="pt-2">
                <label for="rules" class="form-label">Статьи</label>
                <select class="form-select" name="rIds" id="rules" multiple>
                    <c:forEach var="rule" items="${rules}" >
                        <option value="${rule.id}">${rule.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="pt-2">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </form>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</body>
</html>
