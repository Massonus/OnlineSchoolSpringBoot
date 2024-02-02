<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Homework info</title>
</head>
<body>
<br>
<nav>
    <div class="nav">
        <br>
        <#list menu as menuItem>
            <a href="${menuItem.link}">${menuItem.label}</a>
        </#list>
    </div>
</nav>

<p> ${homework}

<h2>Edit the Homework</h2>
<form method="post" action="/homework/edit/${id}">
    <label for="task">Task:</label>
    <input type="text" id="task" name="task" required><br><br>

    <label for="lecture_id">Lecture id:</label>
    <input type="number" id="lecture_id" name="lectureId" required><br><br>

    <input type="submit" value="Send">
</form>

</body>
</html>