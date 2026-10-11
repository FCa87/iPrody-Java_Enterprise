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

        caption {
            padding: 10px;
            font-weight: bold;
            caption-side: top;
            color: #333;
            font-size: 1.25em;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 0 0 20px 0;
            font-size: 0.9em;
            min-width: 400px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
            background-color: #fff;
        }

        thead tr {
            background-color: #009879;
            color: #ffffff;
            text-align: left;
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #dddddd;
        }

        tbody tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }

        tbody tr:hover {
            background-color: #eeeeee;
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
            <p><strong></strong></p>
            <c:if test="${not empty foundUser.addresses}">
                <table>
                        <caption>Адреса пользователя</caption>
                        <thead>
                            <tr>
                                <th>№</th>
                                <th>Street</th>
                                <th>City</th>
                                <th>Postal Code</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="address" items="${foundUser.addresses}" varStatus="status">
                                <tr>
                                    <td><c:out value="${status.count}"/></td>
                                    <td><c:out value="${address.street}"/></td>
                                    <td><c:out value="${address.city}"/></td>
                                    <td><c:out value="${address.postalCode}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                </table>
            </c:if>
            <p><strong></strong></p>
            <c:if test="${not empty foundUser.authorities}">
                <table>
                        <caption>Роли пользователя</caption>
                        <thead>
                            <tr>
                                <th>№</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="authority" items="${foundUser.authorities}" varStatus="status">
                                <tr>
                                    <td><c:out value="${status.count}"/></td>
                                    <td><c:out value="${authority.authority}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                </table>
            </c:if>

        </c:if>

        <c:if test="${not empty errorMessage}">
            <p class="error">${errorMessage}</p>
        </c:if>

        <a href="${pageContext.request.contextPath}/users" class="btn-back">Вернуться к списку</a>
    </div>

</body>
</html>