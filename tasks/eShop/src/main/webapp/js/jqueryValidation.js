$(document).ready(function(){
     $("#btnjquery").click( function(e){
        jqueryValidator.validate(e)
     })
})

var jqueryValidator = {
    validate : function(e){
       var elements = [$("#loginid"), $("#passwordid"), $("#emailid"), $("#firstnameid"), $("#lastnameid")];
       var valid = true;
       $.each(elements, function(index, element){
          var error = validator.validate(element.val(), element.attr("name"));
          $("#" + element.attr("name")+"error").text(error);
          if(error){
             valid = false;
          }
       });
       if(!valid){
         e.preventDefault();
       }
    }
}


