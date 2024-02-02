<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Person info</title>
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

<h1>${person.getFirstName()}</h1>
<p> ${person}

<h2>Edit the Person</h2>
<form method="post" action="/person/edit/${id}">
    <label for="first_name">First name:</label>
    <input type="text" id="first_name" name="firstName" required><br><br>

    <label for="last_name">Last name:</label>
    <input type="text" id="last_name" name="lastName" required><br><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br><br>

    <label>Role:
        <input type="radio" name="role" value="STUDENT" checked>STUDENT
        <input type="radio" name="role" value="TEACHER">TEACHER
    </label><br><br>

    <div class="choose-lectures">
        <#list lectures as lecture>
            <label>Lecture:
                <input type="checkbox" name="lectureIdList" value=${lecture.id}> id: ${lecture.id}
            </label><br>
        </#list>
    </div>
    <br><br>

    <div class="choose-courses">
        <#list courses as course>
            <label>Course:
                <input type="checkbox" name="courseIdList" value=${course.id}> id: ${course.id}
            </label><br>
        </#list>
    </div>
    <br>

    <input type="submit" value="Send">
</form>

</body>
</html>