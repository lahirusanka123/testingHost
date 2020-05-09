<%--

    Document   : index.jsp
    Created on : Mar 3, 2020, 11:37:47 PM
    Author     : Lahiru Sanka

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">  
        <link href="css/index.css" rel="stylesheet">
        
        <script src="jquery-2.1.0.js"></script>
        <script src="ajaxImageUpload.js"></script>

        <title>Home</title>
    </head>
    <body> 
        <%@include file="header.jsp" %>
        <div id="indexbody">

            <form id="UploadForm" method="POST" action="main_servlet" enctype="multipart/form-data">
                <br><p  class="text-center"> <b>Select Your Programming Language &nbsp;&nbsp;&nbsp;&nbsp; <input  type="radio"> Java &nbsp;&nbsp;<input type="radio"> C++ </b></p>

                <div class="progress" id="progressbar">
                    <div class="progress-bar progress-bar-striped progress-bar-animated" style="width:16.6%;height:20px"></div>
                </div>

                <textarea id="tarea" placeholder=" Past Your Code Here"></textarea>

                <hr id="uploadHr"> <span id="uploadHrText"> Or Upload Your Program file here </span> <hr id="uploadhrlRight">

                <div class="custom-file" style="width: 98.3%">
                    <input type="file" class="custom-file-input" id="customFile" name="codeFile"  accept=".java, .docx, .doc, .txt">
                    <label  class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <button value="Upload" id="btnSubmit"   type="submit" class="btn btn-primary  "><b>Submit And Calculate</b> </button> 
            </form>
        </div>

        <script>
            
           

            $(document).ready(function () {
                var url = window.location;
                $('ul.nav a[href="' + url + '"]').parent().addClass('active');
                $('ul.nav a').filter(function () {
                    return this.href === url;
                }).parent().addClass('active');


            });

            //to name of the file appear on select
            $(".custom-file-input").on("change", function () {
                var fileName = $(this).val().split("\\").pop();
                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
            });
            
             var pdfoptions = {
                        beforeSend: function() {
                           
                        },
                        uploadProgress: function(event, position, total, percentComplete) {

                        },
                        success: function(data, textStatus, jqXHR) {
                           
                              alert(data);
                         
                             

                        },
                        complete: function(response) {
                        

                        },
                        error: function() {
                            alert('error');
                        }
                    };


                    $("#UploadForm").ajaxForm(pdfoptions);

 
                    

        </script>
    </body>

</html>


