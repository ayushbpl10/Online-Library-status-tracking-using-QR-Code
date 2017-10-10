
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
                document.getElementById("page-wrapper").innerHTML = "Error";
            }
        };
        xmlhttp.open("GET","admin/getdashboard.jsp",true);
        xmlhttp.send();
    }
function AddBook() {
 
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
                document.getElementById("page-wrapper").innerHTML = "Error";
            }
        };
        xmlhttp.open("GET","admin/addBook.jsp",true);
        xmlhttp.send();
    }
    function RemoveBook() {
 
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
                document.getElementById("page-wrapper").innerHTML = "Error";
            }
        };
        xmlhttp.open("GET","admin/removeBook.jsp",true);
        xmlhttp.send();
    }
    

function AddBookToDb(){
    
        var bookname = $("#bookname").val();
        var authorname = $("#authorname").val();
   
        // Returns successful data submission message when the entered information is stored in database.
        if(bookname.length==0||authorname.length==0)
        {
            alert("Please Fill All Fields");
        }
        else
        {
            // AJAX Code To Submit Form.
            $.ajax({
            type: "POST",
            url: "AddBookServlet",
            data: {
                        'bookname' : bookname,
                        'authorname' : authorname
                   },
            cache: false,
            success: function(result){
                alert(result.success);
                alert(result.image);
                var base64_string = result.image;
                $('<div/>', {
                    html: 'The QR Code for new book : '
                }).appendTo('#Result');
                $('<button/>', {
                    class: 'btn btn-success',
                    onclick: 'printImg()',
                    text: 'Print',
                    html : 'Print'
                }).appendTo('#Result');
                
                $("<img>", {
                    "src": "data:image/png;base64," + base64_string,
                    "width": "250px", 
                    "height": "250px",
                    "id" : "mainImg"
                }).appendTo("#QRCode");
                
                $('#mainform').hide();
            }
            });
        }
    }
  function RemoveBookFromDb(){
    
        var bookname = $("#bookname").val();
        var authorname = $("#authorname").val();
   
        // Returns successful data submission message when the entered information is stored in database.
        if(bookname.length==0||authorname.length==0)
        {
            alert("Please Fill All Fields");
        }
        else
        {
            // AJAX Code To Submit Form.
            $.ajax({
            type: "POST",
            url: "RemoveBookServlet",
            data: {
                        'bookname' : bookname,
                        'authorname' : authorname
                   },
            cache: false,
            success: function(result){
                alert(result.success);
            }
            });
        }     


    
}

  function IssueToEnrlNo(){
      
        var bid_to_issue = $("#bid_to_issue").text();
        var bookname_to_issue = $("#bookname_to_issue").text();
        var authorname_to_issue = $("#authorname_to_issue").text();
        var enrl_to_issue = $("#enrl_to_issue").val();
        
        
        
        
   
        // Returns successful data submission message when the entered information is stored in database.
        if(bid_to_issue.length==0||bookname_to_issue.length==0||authorname_to_issue.length==0||enrl_to_issue.length==0)
        {
            alert("Please Fill All Fields");
        }
        else
        {
            if(enrl_to_issue.length <= 11){
                alert("Please provide the correct Enrollment Number.");
            }
            else{
                // AJAX Code To Submit Form.
                    $.ajax({
                    type: "POST",
                    url: "IssueBookServlet",
                    data: {
                                'bid' : bid_to_issue,
                                'bookname' : bookname_to_issue,
                                'authorname' : authorname_to_issue,
                                'enrl_no' : enrl_to_issue
                           },
                    cache: false,
                    success: function(result){
                        alert(result.success);
                    }
                    });
                }     


        }
            
    }            
    


  function ReturnFromEnrlNo(){
      
        var bid_to_return = $("#bid_to_return").text();
        var bookname_to_return = $("#bookname_to_return").text();
        var authorname_to_return = $("#authorname_to_return").text();
        var enrl_to_return = $("#enrl_to_return").text();
        
        
   
        // Returns successful data submission message when the entered information is stored in database.
        if(bid_to_return.length==0||bookname_to_return.length==0||authorname_to_return.length==0||enrl_to_return.length==0)
        {
            alert("Please Fill All Fields");
        }
        else
        {
            // AJAX Code To Submit Form.
            $.ajax({
            type: "POST",
            url: "ReturnBookFromEnrlServlet",
            data: {
                        'bid' : bid_to_return,
                        'bookname' : bookname_to_return,
                        'authorname' : authorname_to_return,
                        'enrl_no' : enrl_to_return
                   },
            cache: false,
            success: function(result){
                alert(result.success);
            }
            });
        }     


    
}






function logout(id){
    $.ajax({
    url: './offline.php',
    type: 'post',
    dataType: 'json',
    data: {
       'id': id
     },
    // complete: function(xhr, status) {
    //   console.log('Offline. Result: ' + status + ' : ' + xhr.responseText);
      
    //  },
    success: function(result) {

  
        console.log("success"+result.success);
        
        if(result.success == 'false')
          {
              console.log('\n Could not logout. \n');
              return false;
          }
          else
          {
              console.log('\n Logged out Successfully \n');
              return true;
          }
    
  
      },
    error: function(xhr, desc, err) {
        console.log(xhr + "\n" + err + "\n" + desc);
        return false;
      }

  });

    
}


function SourcetoPrint(source) {
		return "<html><head><script>function step1(){\n" +
				"setTimeout('step2()', 10);}\n" +
				"function step2(){window.print();window.close()}\n" +
				"</scri" + "pt></head><body onload='step1()'>\n" +
				"<img height='100px' width='100px' src='" + source + "' /></body></html>";
	}
        
function printImg() {
        var source = document.getElementById("mainImg").src;
        Pagelink = "about:blank";
        var pwa = window.open(Pagelink, "_new");
        pwa.document.open();
        pwa.document.write(SourcetoPrint(source));
        pwa.document.close();
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

    
