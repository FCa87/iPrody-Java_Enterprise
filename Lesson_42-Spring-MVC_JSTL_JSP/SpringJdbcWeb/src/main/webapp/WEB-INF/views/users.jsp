<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
    <title>Список пользователей</title>
    <style>
        body {
            font-family: sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 2.5em;
            text-shadow: 1px 1px 2px rgba(0,0,0,0.1);
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

        caption {
            padding: 10px;
            font-weight: bold;
            caption-side: top;
            color: #333;
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


        .action-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            width: 80%;
            margin-top: 15px;
        }

        .left-zone {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .right-zone {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .search-group {
            display: flex;
            align-items: center;
            gap: 1cm;
        }


        .search-input {
            padding: 10px 15px;
            font-size: 0.9em;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
            width: 140px;
            transition: border-color 0.2s, box-shadow 0.2s;
        }

        .search-input:focus {
            border-color: #009879;
            box-shadow: 0 0 5px rgba(0, 152, 121, 0.3);
        }

        .btn {
            display: inline-block;
            background-color: #009879;
            color: white;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 4px;
            font-weight: bold;
            font-size: 0.9em;
            border: none;
            cursor: pointer;
            white-space: nowrap;
            transition: background-color 0.2s, transform 0.1s;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .btn:hover {
            background-color: #007a61;
        }

        .btn:active {
            transform: scale(0.98);
        }
    </style>
</head>
<body>

    <h1>Список пользователей</h1>

    <table>
        <caption>Зарегистрированные пользователи</caption>
        <thead>
            <tr>
                <th>ID</th>
                <th>Имя</th>
                <th>Email</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.email}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


    <div class="action-row">

        <div class="left-zone">
            <a href="users/add" class="btn">Добавить</a>
        </div>

        <div class="right-zone">
            <form action="users/search-redirect" method="get" class="search-group" style="margin: 0;">
                <button type="submit" class="btn">Поиск по Id</button>
                <input type="text" name="id" placeholder="Введите Id..." class="search-input" required>
            </form>
        </div>

    </div>

</body>
</html>