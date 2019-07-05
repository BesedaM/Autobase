<%--@elvariable id="errorRegisterPassword" type="java.lang.String"--%>
<%--@elvariable id="errorRegisterLogin" type="java.lang.String"--%>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.05.2019
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer register</title>
    <meta http-equiv="Content-Type" content="text/html">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>

<form name="customerRegister" action="controller" method="POST">
    <input type="hidden" name="command" value="customer_register"/>
    <fieldset>
        <legend>Registration form</legend>
        <table class="registration">
            <tr>
                <td><label for="login">Enter login : </label></td>
                <td><input id="login" name="login" type="text" pattern="^[\w]{8,}$" required/></td>
            </tr>
            <tr>
                <td colspan="2"><p class="error-message">${errorRegisterLogin}</p></td>
            </tr>
            <tr>
                <td>
                    <label for="passw1">Enter password : </label>
                </td>
                <td>
                    <input id="passw1" name="password" type="password" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="passw2">Confirm password : </label>
                </td>
                <td>
                    <input id="passw2" name="password_confirm" type="password" required/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p class="error-message">${errorRegisterPassword}</p>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset>
        <legend>Company</legend>
        <table class="registration">
            <tr>
                <td>
                    <label for="company">Company name: </label>
                </td>
                <td>
                    <input id="company" name="company_name" type="text"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <p class="information">*Leave blank if you are an individual customer</p>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset>
        <legend>Contacts</legend>
        <table class="registration">
            <tr>
                <td>
                    <label for="name">Name: </label>
                </td>
                <td>
                    <input id="name" name="name" type="text" pattern="(^[a-zA-Z-]{3,}$)|(^[А-Яа-я-]{3,}$)" required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="surname">Surname: </label>
                </td>
                <td>
                    <input id="surname" name="surname" type="text" pattern="(^[a-zA-Z-]{3,}$)|(^[А-Яа-я-]{3,}$)"
                           required/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="phone">Phone: </label>
                </td>
                <td>
                    <input id="phone" name="phone" type="text" placeholder="375(29)123-45-67"
                           pattern="^375\(((29)|(33)|(44)|(25))\)[0-9]{3}-[0-9]{2}-[0-9]{2}(\s){0,}" required>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="email">Email:</label>
                </td>
                <td>
                    <input id="email" name="email" type="email" placeholder="email@example.com"
                           pattern="([^\r\n\t\f\v\@]){2,}\@{1}[\w-]{2,}\.{1}[a-z]{2,}" required/>
                </td>
            </tr>
        </table>
    </fieldset>
    <br/>
    <input type="submit" value="Register"/>
    <input type="reset" value="Reset"/>
</form>
<br/>

<form name="LoginRedirect" method="get" action="${urlPrefix}/controller">
    <input type="hidden" name="command" value="login_page">
    <input type="submit" value="Back to login page"/>
</form>
</body>
</html>

