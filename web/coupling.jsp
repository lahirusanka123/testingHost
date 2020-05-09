<%-- 
    Document   : coupling
    Created on : Mar 19, 2020, 5:54:11 PM
    Author     : Lahiru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="couplingbody">
        
            <h1>Hello World!</h1>
        
        </div>
        
        <script>
             $(document).ready(function () {
                var url = window.location;
                $('ul.nav a[href="' + url + '"]').parent().addClass('active');
                $('ul.nav a').filter(function () {
                    return this.href === url;
                }).parent().addClass('active');
            });
        </script>
    </body>
</html>
