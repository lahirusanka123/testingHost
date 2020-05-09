<%-- 
    Document   : control structure
    Created on : Mar 14, 2020, 4:04:36 PM
    Author     : Lahiru Sanka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/contralstructure.css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="contralstructDiv">

            <br><p  class="text-center"> <b>Code Complexity Measuring Of Your Program Due to Control Structure </b></p>

            <div class="progress" id="progressbar">
                <div class="progress-bar progress-bar-striped progress-bar-animated" style="width:83.3%;height:20px"></div>
            </div> 

            <div id="scrol">
                <table id="ctrlTable" class="table-bordered table-striped" >
                    <tr>
                        <th style="width: 65px;" >Line No</th>
                        <th>Program Statements</th>
                        <th style="width: 65px;">Wtcs</th>
                        <th style="width: 65px;">Nc</th>
                        <th style="width: 65px;">Ccspps</th>
                        <th style="width: 65px;">Ccs</th>
                    </tr>
                      

                </table>

            </div>
            <table id="abbreviationtbl" class="table-striped">
                <tr>
                    <th colspan="2" style="text-align: center">- Abbreviation -</th>

                </tr>
                <tr>
                    <td >Wtcs</td>
                    <td>= Weight due to control structure type </td>
                </tr>
                <tr>
                    <td>Nc</td>
                    <td>= Number of conditions in the control structure </td>
                </tr>
                <tr>
                    <td>Ccspps</td>
                    <td>= Control structure complexity of the previous program statement</td>
                </tr>
                <tr>
                    <td>Ccs</td>
                    <td>= Complexity of a program statement with a control structure </td>
                </tr>
            </table>
            <p id="tt"></p>

        </div>

        <script>

           

            $(document).ready(function () {
                var url = window.location;
                $('ul.nav a[href="' + url + '"]').parent().addClass('active');
                $('ul.nav a').filter(function () {
                    return this.href === url;
                }).parent().addClass('active');
            });

            var xmlHttp;
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                alert("Browser Doesnt Support Ajax!");
            }
            if (xmlHttp !== null) {
                xmlHttp.onreadystatechange = async function () {
                    if (xmlHttp.readyState === 4) {
                        var res = xmlHttp.responseText;
                         
                         
                         $("#ctrlTable").find('tbody').append(res);

                    }
                };
                xmlHttp.open("POST", "main_servlet", true);
                xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlHttp.send("RqData=FillForm&ctrl= ctrl"   );
            }


//            var lines = []; 
//            $('#my_textarea_selector').val().split("\n").each(function ()
//            {
//            if (parseInt($(this) != 'NaN')
//                lines[] = parseInt($(this));
//            }




        </script>
    </body>
</html>
