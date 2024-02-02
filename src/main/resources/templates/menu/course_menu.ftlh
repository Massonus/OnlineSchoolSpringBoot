<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Course List</title>
</head>
<body>
<h1>Course List</h1>
<br>
<nav>
    <div class="nav">
        <br>
        <#list menu as menuItem>
            <a href="${menuItem.link}">${menuItem.label}</a>
        </#list>
    </div>
</nav>
<h2>Select the button</h2>

<table border="1px">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>delete</th>
    </tr>
    </thead>
    <tbody>
    <#list courses as course>
        <tr>
            <td>${course.id!""}</td>
            <td>
                <li><a href="/course/${course.id!""}">${course.courseName!""}</a></li>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>