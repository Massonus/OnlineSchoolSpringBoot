<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/css/styles.css" rel="stylesheet">
    <title>Person List</title>
</head>
<body>
<h1>People List</h1>
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
        <th>First name</th>
        <th>Last name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Role</th>
        <th>delete</th>
    </tr>
    </thead>
    <tbody>
    <#list people as perosn>
        <tr>
            <td>${perosn.id!""}</td>
            <td>${perosn.firstName!""}</td>
            <td>
                <li><a href="/person/${perosn.id!""}">${perosn.lastName!""}</a></li>
            </td>
            <td>${perosn.email!""}</td>
            <td>${perosn.phone!""}</td>
            <td>${perosn.role!""}</td>
            <td><a href="/person/delete/${perosn.getId()}"><p>DELETE</p></a></td>
        </tr>
    </#list>
    </tbody>
</table>

<h2>Add Person</h2>
<form method="post" action="/addPerson">
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
                <input type="checkbox" name="lectureIdList" value=${lecture.id!""}> id: ${lecture.id!""}
            </label><br>
        </#list>
    </div>
    <br><br>

    <div class="choose-courses">
        <#list courses as course>
            <label>Course:
                <input type="checkbox" name="courseIdList" value=${course.id!""}> id: ${course.id!""}
            </label><br>
        </#list>
    </div>
    <br>

    <input type="submit" value="Send">
</form>
</body>
</html>