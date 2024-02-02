<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Lecture info</title>
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

<p> ${lecture}

<h2>Edit the Lecture</h2>
<form method="post" action="/lecture/edit/${id}">
    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" required><br><br>

    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required><br><br>

    <label for="teacher_id">Teacher id:</label>
    <input type="number" id="teacher_id" name="teacherId" required><br><br>

    <label for="course_id">Course id:</label>
    <input type="number" id="course_id" name="courseId" required><br><br>

    <input type="submit" value="Send">
</form>

</body>
</html>