<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Material List</title>
</head>
<body>
<h1>Additional Material List</h1>
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
        <th>Type</th>
        <th>Task</th>
        <th>Lecture id</th>
        <th>delete</th>
    </tr>
    </thead>
    <tbody>
    <#list materials as material>
        <tr>
            <td>${material.id}</td>
            <td>${material.resourceType}</td>
            <td>
                <li><a href="/material/${material.id}">${material.task}</a></li>
            </td>
            <td>${material.lecture.getId()}</td>
            <td><a href="/material/delete/${material.getId()}"><p>DELETE</p></a></td>
        </tr>
    </#list>
    </tbody>
</table>


<h2>Add Material</h2>
<form method="post" action="/addMaterial">
    <label for="task">Task:</label>
    <input type="text" id="task" name="task" required><br><br>

    <label>Type:
        <input type="radio" name="type" value="URL" checked>URL
        <input type="radio" name="type" value="VIDEO">VIDEO
        <input type="radio" name="type" value="BOOK">BOOK
    </label><br><br>

    <label for="lecture_id">Lecture id:</label>
    <input type="number" id="lecture_id" name="lectureId" required><br><br>

    <input type="submit" value="Send">
</form>
</body>
</html>