<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Lecture List</title>
</head>
<body>
<h1>Lecture List</h1>
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
        <th>Subject</th>
        <th>Description</th>
        <th>lecture date</th>
        <th>course id</th>
        <th>person id</th>
        <th>delete</th>
    </tr>
    </thead>
    <tbody>
    <#list lectures as lecture>
        <tr>
            <td>${lecture.id!""}</td>
            <td>
                <li><a href="/lecture/${lecture.id!""}">${lecture.subject!""}</a></li>
            </td>
            <td>${lecture.description!""}</td>
            <td>${lecture.lectureDate!""}</td>
            <td>${lecture.course.getId()!""}</td>
            <td>${lecture.person.getId()!""}</td>
            <td><a href="/lecture/delete/${lecture.getId()}"><p>DELETE</p></a></td>
        </tr>
    </#list>
    </tbody>
</table>

<h2>Add Lecture</h2>
<form method="post" action="/addLecture">
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