 var url="http://localhost:8080/Proyecto2-Progra4/pags/";

    function register(){
        if (!registerValidar()) return;
        usuario = {
            userName: $("#name-register").val(),
            id: $("#id-register").val(),
            clave: $("#clave-register").val(),
            rol: "CLI"
        };
        
        let request = new Request('/Proyecto2-Progra4/api/register', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessageRe(response.status,$("#registerDialog #errorDiv"));return;}
            usuario = await response.json();
            sessionStorage.setItem('user', JSON.stringify(usuario));
            $('#loginDialog').modal('hide');            
           switch(usuario.rol){
               case 'ADM': document.location = url+"pagPrincipal.html"; break;
               case 'CLI': document.location = url+"pagPrincipal.html"; break;
           }                           
        })(); 
    
    }

    function registerValidar(){
        $("#registerForm").addClass("was-validated");
        return $("#registerForm").get(0).checkValidity(); 
    }


    function errorMessageRe(status,place){  
        switch(status){
            case 404: error= "Usuario ya registrado"; break;
            case 403: case 405: error="Usuario no autorizado"; break;
            case 406: case 405: error="Usuario ya existe"; break;
        };            
        place.html('<div class="alert alert-danger fade show">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
        return;        
    }
  
    function loadRegister(){
        let request = new Request(url+'register.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#registerDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content); 
            $("#register").click(register);
                                     
        })();     
  }
  
  $(loadRegister);  

  