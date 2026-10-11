<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<html>
<head>
    <title>Добавить пользователя</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif; /* */
            background-color: #f4f7f6; /* Light gray background */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background: #fff;
            padding: 40px;
            border-radius: 8px; /* Softer corners */
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
            width: 100%;
            max-width: 400px;
            box-sizing: border-box;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
            text-align: center;
        }

        .form-group {
            margin-bottom: 20px;
            width: 100%;
        }

        .form-group label {
            display: block; /* Makes label stack above input */
            margin-bottom: 8px;
            color: #555;
            font-weight: bold;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* Ensures padding doesn't affect width */
            font-size: 16px;
            transition: border-color 0.3s ease; /* Smooth transition for focus */
        }

        .form-group input:focus {
            outline: none;
            border-color: #007bff; /* Highlight color on focus */
        }

        .submit-btn {
            width: 100%;
            padding: 12px;
            background-color: #007bff; /* Primary action color */
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            text-transform: uppercase;
        }

        .submit-btn:hover {
            background-color: #0056b3; /* Darker shade on hover */
        }
    </style>
</head>
<body>
    <form action="add" method="POST" modelAttribute="user">
        <h2>Добавить пользователя</h2>

        <div class="form-group">
            <label for="name">Имя</label>
            <input type="text" id="name" name="name" required placeholder="Enter username">
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required placeholder="Enter email">
        </div>

        <button type="submit" class="submit-btn">Добавить</button>
    </form>


</body>
</html>