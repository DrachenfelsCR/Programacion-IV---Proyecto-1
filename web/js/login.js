var url="http://localhost:8080/Proyecto2-Progra4/pags/";

     function login(){
        console.log("HOLA");
        if (!loginValidar()) return;
        usuario = {
            id: $("#id-login").val(),
            clave: $("#clave-login").val()
        };
        
        let request = new Request('/Proyecto2-Progra4/api/login', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(usuario)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            usuario = await response.json();
            sessionStorage.setItem('user', JSON.stringify(usuario));
            $('#loginDialog').modal('hide');            
           switch(usuario.rol){
               case 'ADM': document.location = url+"pagPrincipal.html"; break;
               case 'CLI': document.location = url+"pagPrincipal.html"; break;
           }                           
        })(); 
    
    }

    function loginValidar(){
        $("#loginForm").addClass("was-validated");
        return $("#loginForm").get(0).checkValidity(); 
    }

    function logout(){
        let request = new Request('/Proyecto2-Progra4/api/login', {method: 'DELETE', headers: { }});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            sessionStorage.removeItem('user');
            document.location = url+"pagPrincipal.html";                         
        })();          
    }

    function errorMessage(status,place){  
        switch(status){
            case 404: error= "Registro no encontrado"; break;
            case 403: case 405: error="Usuario no autorizado"; break;
            case 406: case 405: error="Usuario ya existe"; break;
        };            
        place.html('<div class="alert alert-danger fade show">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
        return;        
    }  
  
    function loadLogin(){
        let request = new Request(url+'login.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#loginDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content); 
            $("#login").click(login);
            $("#logout").click(logout);                          
        })();     
  }
  
  $(loadLogin);  