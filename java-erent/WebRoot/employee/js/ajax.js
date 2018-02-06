function AJAX(type,url,callback){
    var request = false;
    try {
        request = new XMLHttpRequest();
    } catch (trymicrosoft) {
     try {
        request = new ActiveXObject("Msxml2.XMLHTTP");
     } catch (othermicrosoft) {
       try {
            request = new ActiveXObject("Microsoft.XMLHTTP");
       } catch (failed) {
            request = false;
       }  
     }
    }
    if (!request)    return;
    
    request.open(type, url, true);
    request.setRequestHeader("CONTENT-TYPE","application/x-www-form-urlencoded");
//    request.setRequestHeader("Content-Length",data.length);

    request.onreadystatechange = function(){
        if(request.readyState == 4) {
            if(request.status == 200) {
                callback(request.responseText);
            } 
         }
    };
    request.send();
}