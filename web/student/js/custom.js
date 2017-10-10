
function showDashboard() {
 
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
            }else{
                document.getElementById("page-wrapper").innerHTML = "<div id='status'></div>";
            }
        };
        xmlhttp.open("GET","student/getdashboard.jsp",true);
        xmlhttp.send();
    }

  function ReissueFromEnrlNo(){
      
        var bid_to_reissue = $("#bid_to_reissue").text();
        var bookname_to_reissue = $("#bookname_to_reissue").text();
        var authorname_to_reissue = $("#authorname_to_reissue").text();
        var enrl_to_reissue = $("#enrl_to_reissue").text();
        var date_to_reissue = $("#date_to_reissue").text();
        
        
   
        // Returns successful data submission message when the entered information is stored in database.
        if(bid_to_reissue.length==0||bookname_to_reissue.length==0||authorname_to_reissue.length==0||enrl_to_reissue.length==0||date_to_reissue.length==0)
        {
            alert("Please Fill All Fields");
        }
        else
        {
            // AJAX Code To Submit Form.
            $.ajax({
            type: "POST",
            url: "ReissueBookFromEnrlServlet",
            data: {
                        'bid' : bid_to_reissue,
                        'bookname' : bookname_to_reissue,
                        'authorname' : authorname_to_reissue,
                        'enrl_no' : enrl_to_reissue,
                        'issueddate' : date_to_reissue
                   },
            cache: false,
            success: function(result){
                alert(result.success+" And "+result.reissueddate);
            }
            });
        }     


    
}


function SearchBook() {
 
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
            }else{
                document.getElementById("page-wrapper").innerHTML = "Loading";
            }
        };
        xmlhttp.open("GET","student/searchBook.jsp",true);
        xmlhttp.send();
    }
    



  function SearchByBookname(){
      
        $("#SearchBookResult").empty();
        var bookname = $("#searchbookname").val();
        
        
   
        // Returns successful data submission message when the entered information is stored in database.
        if(bookname.length==0)
        {
            alert("Please Fill All Fields");
        }
        else
        {
            // AJAX Code To Submit Form.
            $.ajax({
            type: "POST",
            url: "SearchBookServlet",
            data: {
                        'bookname' : bookname
                        
                   },
            cache: false,
            success: function(result){
                
                var table = $("<table/>").addClass("table table-hover");
                var heading = $("<tr/>");
                    
                    heading.append($("<th/>").text("Book name"));
                    heading.append($("<th/>").text("Author name"));
                    heading.append($("<th/>").text("Availability"));
                table.append(heading); 
                
                $.each(result, function(rowIndex, r) {
                    
                        var row = $("<tr/>");
                        
                        row.append($("<td/>").text(r.bookname));
                        row.append($("<td/>").text(r.authorname));
                        if(r.issuedto === null){
                            row.append($("<td/>").text("Available"));
                        }
                        else{
                            row.append($("<td/>").text("Not Available"));
                        }
                            
                    
                    table.append(row);
                });
     
                $("#SearchBookResult").append(table);
                }
            });
        }     


    
}            