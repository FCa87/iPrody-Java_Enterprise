<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
    <title>Результат поиска</title>
    <style>
        body {
            font-family: sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            background-color: #f4f4f4;
            margin: 0;
        }

        .card {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            text-align: center;
            min-width: 300px;
        }

        .error {
            color: #d9534f;
            font-weight: bold;
        }

        .btn-back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #009879;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
    </style>
</head>
<body>

    <div class="card">
        <c:if test="${not empty foundUser}">
            <h2>Пользователь найден!</h2>
            <p><strong>ID:</strong> ${foundUser.id}</p>
            <p><strong>Имя:</strong> ${foundUser.name}</p>
            <p><strong>Email:</strong> ${foundUser.email}</p>
        </c:if>

        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>

        <a href="${pageContext.request.contextPath}/users" class="btn-back">Вернуться к списку</a>
    </div>

</body>
</html>