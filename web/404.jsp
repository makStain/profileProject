<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Task Manager</title>
    <link rel="icon" href="img/icon.png" type="image/icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <style>
        .page-not-found {
            background-color: #3f51b5;
            height: 100vh;
        }
        .page-not-found h2 {
            font-size: 130px;
            color: #e91e63;
        }
        .page-not-found h3 {
            font-size: 42px;
        }
        .page-not-found .bg-light {
            width: 50%;
            padding: 50px;
            text-align: center;
            border-radius: 5px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        @media (max-width:  767px) {
            .page-not-found h2 {
                font-size: 100px;
            }
            .page-not-found h3 {
                font-size: 28px;
            }
            .page-not-found .bg-light {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<!-- Not Found Page HTML -->
<div class="page-not-found pt-5">
    <div class="bg-light shadow">
        <h2>4<i class="bi bi-bug">0</i>4</h2>
        <h3 class="mt-4">Opps! Page Not Found</h3>
        <p>Lorem Ipsum is simply dummy text of the printing and<br>typesetting industry.</p>
        <div class="mt-5">
            <button type="button" class="btn m-2 m-md-0 btn-primary"><i class="bi bi-house-door-fill"></i> Back Home</button>
            <button type="button" class="btn m-2 m-md-0 btn-success"><i class="bi bi-person-lines-fill"></i> Contact Us</button>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
