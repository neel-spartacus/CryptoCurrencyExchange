<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Zooplus Challenge - The solution</title>
    <meta name="description" content="Zooplus Challenge solution">
    <meta name="author" content="github.com/jomoespe">

    <link rel="stylesheet" href="style/site.css">
    <link rel="shortcut icon" href="/favicon.ico" />
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
    </script>
<script >

    var ip;
    $.getJSON("https://api.ipify.org?format=json", function(data) {
           // Setting text of element P with id gfg
           $("#client-ip").html(data.ip);
           ip=data.ip;
       });

 </script>

</head>
<body>
    <header class="pure-g">
        <div class="pure-u-3-4">
            <h1>Zooplus Challenge</h1>
            <h2>the solution to the challenge</h2>
        </div>
        <div class="pure-u-1-4">
            <form id="logout" name="logout" action="logout" method="post" >
                <label>Hi ${pageContext.request.userPrincipal.name}!</label>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button id="logout">logout</button>
                 <script>
              $(document).ready(function() {
                                $('#logout').click(function() {
                                         if (localStorage.selectVal) {
                                                   localStorage.clear();
                                          }
                                         })
                                     });
                 </script>
            </form>
        </div>
    </header>
    <main>
        <section id="form_container" class="pure-g">
            <div class="pure-u-1-8"></div>
            <div class="pure-u">
                <h3>get a current exchange rate</h3>
                <form action="rate" class="pure-form" name="rateForm">
                    <fieldset>
                    <select name="target" id="target" onchange="onChange()">
                        <c:forEach var="currency" items="${currencies}"><option value="${currency.currencyCode}"> ${currency.currencyCode} ${currency.displayName}</option>
                        </c:forEach>
                    </select>
                    <br></br>
                     <input type="text" id="ipaddress" name="clientIp" placeholder="IP">
                     <script>
                     $(document).ready(function() {
                      if (localStorage.selectVal) {
                           $('#target').val( localStorage.selectVal );
                       }
                         $('#submit').click(function() {
                              if(document.getElementById('ipaddress').value===''){
                                document.getElementById('ipaddress').value=ip;
                              };
                         })


                     });
                     $("#ipaddress").on("blur", function () {
                            if ($(this).val().trim().length == 0) {
                                $(this).val(ip);
                            }
                        });
                        //trigger blur once for the initial setup:
                        $("#ipaddress").trigger("blur");

                      function onChange(){
                           var currentVal=document.getElementById('target').value;
                           localStorage.setItem('selectVal', currentVal );
                        }
                     </script>
                    <button class="pure-button button-main" id="submit">Get rate</button>
                    </fieldset>
                    <c:if test="${empty error}"><label>Current Unit Price is: <strong>${rate.rate}</strong> ${rate.target}</label></c:if>
                    <c:if test="${not empty error}"><div>${error}</div></c:if>
                    

                </form>
            </div>
            <div class="pure-u-1-8"></div>                
        </section>

        <section id="searchs_container" class="pure-g">
            <div class="pure-u-3-24"></div>
            <div class="pure-u">
                <h3>the last queries</h3>
                <table class="pure-table pure-table-horizontal pure-table-striped">
                    <%--caption>Last searchs....</caption--%>
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Base</th>
                            <th>Target</th>
                            <th>Exchange rate</th>
                        </tr>
                    </thead>
                    <c:forEach var="exchange" items="${searchs}">
                        <tr>
                            <td>${exchange.rateDate}</td>
                            <td>${exchange.source}</td>
                            <td>${exchange.target}</td>
                            <td>${exchange.rate}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="pure-u-2-24"></div>                
        </section>
    </main>
    <footer>
    </footer>
</body>
</html>
