




        <!DOCTYPE html>
        <html lang="en">

        <head>


            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta name="description" content="">
            <meta name="author" content="">


            <title>e-Library Portal</title>

            <!-- Bootstrap Core CSS -->
            <link href="css/bootstrap.css" rel="stylesheet">

            <!-- Custom CSS -->
            <link href="css/sb-admin.css" rel="stylesheet">

            <!-- Custom Fonts -->
            <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
            <!-- Favicon -->
            
            
            <!-- jQuery -->
            <script src="js/jquery.js"></script>

            <!-- Custom JS -->
            <script src="student/js/custom.js"></script>

            <!-- Bootstrap Core JavaScript -->
            <script src="js/bootstrap.js"></script>

            <script type="text/javascript" src="js/instascan.min.js"></script>
            <script>
                
                function ReIssueBook() {
 
                                if (window.XMLHttpRequest) {
                                    // code for IE7+, Firefox, Chrome, Opera, Safari
                                    xmlhttp = new XMLHttpRequest();
                                } else {
                                    // code for IE6, IE5
                                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                                }
                                xmlhttp.onreadystatechange = function() {
                                    if (this.readyState == 4 && this.status == 200) {
                                        document.getElementById("page-wrapper").innerHTML = this.responseText;
                                        
                                        let scanner = new Instascan.Scanner({ video: document.getElementById('preview') });
                                        scanner.addListener('scan', function (content) {
                                          console.log(content);
                                          
                                          var details = content.split("/");
                                          var bid = details[0];
                                          var bookname = details[1];
                                          var authorname = details[2];
                                          
                                          $.ajax({
                                                    type: "POST",
                                                    url: "BookDetailsServlet",
                                                    data: {
                                                                'bid' : bid,
                                                                'bookname' : bookname,
                                                                'authorname' : authorname
                                                           },
                                                    cache: false,
                                                    success: function(result){
                                                        alert(result.success+" bid "+result.bid+" bname "+result.bookname+" author "+result.authorname+" issuedto "+result.issuedto+" issueddate "+result.issueddate);
                                                        
        
                                                                if(result.issuedto!=null){
                                                                        $.ajax({
                                                                        type: "POST",
                                                                        url: "student/reissueBookForm.jsp",
                                                                        data: {
                                                                                    'bid' : result.bid,
                                                                                    'bookname' : result.bookname,
                                                                                    'authorname' : result.authorname,
                                                                                    'issuedto' : result.issuedto,
                                                                                    'issueddate' : result.issueddate
                                                                               },
                                                                        cache: false,
                                                                        success: function(result){
                                                                            $("#BookReissue").replaceWith(result);
                                                                            scanner.stop();
                                                                            }
                                                                        });//ajax
                                                                }
                                                                else{
                                                                    alert("book not issued");
                                                                }
                                                                
                                                        }
                                                });//ajax
                                          
                                        });
                                        
                                        
                                        Instascan.Camera.getCameras().then(function (cameras) {
                                          if (cameras.length > 0) {
                                            scanner.start(cameras[0]);
                                          } else {
                                            console.error('No cameras found.');
                                            alert('No cameras found.');
                                          }
                                        }).catch(function (e) {
                                          console.error(e);
                                          alert('No cameras found.');
                                        });
                                        
                                        
                                    }else{
                                        document.getElementById("page-wrapper").innerHTML = "Error";
                                    }
                                };
                                xmlhttp.open("GET","student/reissueBook.jsp",true);
                                xmlhttp.send();
                            }
           
            </script>

        </head>

        <body>

                

              

            <div id="wrapper">

                <!-- Navigation -->
                <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
                    <!-- Brand and toggle get grouped for better mobile display -->
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="./">e-library Portal</a>
                    </div>
                    <!-- Top Menu Items -->
                    <ul class="nav navbar-right top-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="index.html"><i class="fa fa-fw fa-power-off"></i>Log Out</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                        <ul class="nav navbar-nav side-nav">
                            <li class="">
                                <a style="cursor: pointer;"><div onclick="showDashboard()"><i class="fa fa-fw fa-dashboard"></i>Dashboard</div></a>
                            </li>
                            <li class="">
                                <a style="cursor: pointer;"><div onclick="SearchBook()"><i class="fa fa-fw fa-book"></i>Search Book</div></a>
                            </li>
                             <li class="">
                                <a style="cursor: pointer;"><div onclick="ReIssueBook()"><i class="fa fa-fw fa-book"></i>ReIssue Book</div></a>
                            </li>
                        </ul>
                    </div>
                    <!-- /.navbar-collapse -->
                </nav>


    

