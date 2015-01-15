function getJournal(){
    
    var xmlhttp;

    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    } else { // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status == 200) {
            var text = JSON.parse(xmlhttp.responseText);

            var output = "";
            for(var i = 0; i < text["journal"].length; i++){
                output += (text["journal"][i] + "<br />");
            }
            document.getElementById("journal").innerHTML = "<p>" + output + "</p>";
        }
    }

    xmlhttp.open("GET","/myDiary-Web/api/text-generation/get-journal",true);
    xmlhttp.send();
}

function getQuestion(){
    
    alert('Ionica !'); 
    
    var xmlhttp;

    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    } else { // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status == 200) {
            var text = JSON.parse(xmlhttp.responseText);

            //document.getElementById("textArea").value =  text["question"];
            
            $("#textArea").append(text["question"]).append("\n");
        
        }
    }

    xmlhttp.open("GET","/myDiary-Web/api/qa/get-initial-question",true);
    xmlhttp.send();
}

function sendResponse(){
    
    var xmlhttp;

    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    } else { // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status == 200) {
            var text = JSON.parse(xmlhttp.responseText);

            $("#textArea").append(text["response"]).append("\n");
        
        }
    }


    var parameters= { "question":  document.getElementById("focusedInput").value };

    xmlhttp.open("POST","/myDiary-Web/api/qa/get-response",true);
    xmlhttp.setRequestHeader("Content-type", "application/json");
    xmlhttp.send(JSON.stringify(parameters));
	}