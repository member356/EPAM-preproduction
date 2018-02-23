window.onload = function() {
    document.getElementById("btnjs").addEventListener("click", function(e){
        jsValidator.validate(e)
    })
}

var jsValidator = {
    validate : function(e) {
       var elements =[document.getElementById("loginid"), document.getElementById("passwordid"),
                      document.getElementById("emailid"), document.getElementById("firstnameid"), document.getElementById("lastnameid")];
       var valid = true;
       elements.forEach( function (element){
           var error = validator.validate(element.value, element.getAttribute("name"));
           document.getElementById(element.getAttribute("name") + "error").textContent = error;
           if(error){
               valid = false;
           }
       });
       if(!valid){
           e.preventDefault();
       }
    }
}