var validator = {

      regulation : {

           login : {regex : /^[a-z0-9]{4,15}$/,
                    error : "Incorrect login! Login must starts with eng letter and contain 4-15 symbols"},

        password : {regex : /^[a-zA-Zа-яА-Я0-9]{5,15}$/,
                    error : "Invalid password! Password must contain from 5 to 15 eng symbols"},

           email : {regex : /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,5}$/,
                    error : "Incorrect email! Email must be like 'example@example.com'"},

       firstname : {regex : /^[a-zA-Zа-яА-Я]{3,12}$/,
                    error : "Incorrect name! First name must contain 3-12 russian or english letters"},

        lastname : {regex : /^[a-zA-Zа-яА-Я]{3,20}$/,
                    error : "Incorrect name! Last name must contain 3-20 russian or english letters"}
      },

    validate : function validation(val, name){
       if(!val.match(this.regulation[name].regex)){
           return this.regulation[name].error;
       }
       else{
           return "";
       }
    }
}
