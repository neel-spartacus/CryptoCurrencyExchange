<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Zooplus Challenge - Sign Up The solution</title>
    <meta name="description" content="Zooplus Challenge solution">

    <link rel="stylesheet" href="style/site.css">
    <link rel="shortcut icon" href="/favicon.ico" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</head>
<body>
    <header class="pure-g">
        <div class="pure-u-1-2">
            <h1>Zooplus</h1>
            <h2>the solution to the challenge</h2>
        </div>
        <div class="pure-u-1-2">
            <form name="signin" action="signin" method="post">
                <input type="text" name="username" placeholder="Email">
                <input type="password" name="password" placeholder="Password">
                <button type="submit">Log In</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  <c:if test="${not empty error}"><div>Invalid Username or Password</div></c:if>
            </form>
        </div>
    </header>
    <main>
        <div class="pure-g">
            <div class="pure-u-1-8"></div>
            <div class="pure-u-1-2">
                <article>
                    <p>This is my solution to the Zooplus Challenge.</p>
                </article>
            </div>
            <div class="pure-u-1-4">
                <h3>Sign Up</h3>
                <form name="signup" action="signup" method="post" class="pure-form">
                    <fieldset class="pure-group">
                        <input type="text"  name="firstname"  class="pure-input-1" placeholder="First name">
                        <input type="text"  name="lastname"   class="pure-input-1" placeholder="Last name">
                        <input type="email" name="email"      class="pure-input-1" placeholder="Email">
                        <input type="date"  name="bday"       class="pure-input-1" placeholder="Birth day">
                    </fieldset>
                    <fieldset class="pure-group">
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
                        <input type="password" name="password" id="password"  class="pure-input-1" placeholder="Password" >
                        <input type="password" name="repassword" id="repassword" class="pure-input-1" placeholder="Re-enter password">
                        <span id='message'></span>

                        <!-- Address (street, zip, city & country)  -->
                    </fieldset>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button type="submit" class="pure-button pure-input-1 pure-button-primary">Sign Up</button>

                </form>
            </div>
            <div class="pure-u-1-8"></div>
        </div>
    </main>
    <footer>

    </footer>
</body>
</html>
