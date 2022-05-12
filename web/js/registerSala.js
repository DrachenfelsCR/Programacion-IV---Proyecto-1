var url="http://localhost:8080/Proyecto2-Progra4/pags/";

    function registerSala(){
        
        if (!registerValidarSala()) return;
        Sala = {
            idSala: $("#id-sala").val()   
        };
       
        
        let request = new Request('http://localhost:8080/Proyecto2-Progra4/api/registrar',{method: 'POST', headers: { 'Content-Type': 'application/json'}, body: JSON.stringify(Sala)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessageReSala(response.status,$("#registerSalaDialog #errorDiv"));return;}
            
            
            $('#registerSalaDialog').modal('hide');   
            document.location = url+'pagPrincipal.html';
            
               
                
                                      
        })(); 
    
    }

    function registerValidarSala(){
        $("#RegisterSalaForm").addClass("was-validated");
        return $("#RegisterSalaForm").get(0).checkValidity(); 
    }


    function errorMessageReSala(status,place){  
        switch(status){
            case 404: error= "sala ya registrada"; break;
            case 403: case 405: error="Usuario no autorizado"; break;
            case 406: case 405: error="sala ya existe"; break;
        };            
        place.html('<div class="alert alert-danger fade show">' +
        '<button type="button" class="close" data-dismiss="alert">' +
        '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
        return;        
    }
  
    function loadRegisterSala(){
        let request = new Request(url+'registerSala.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#registerSalaDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content); 
            $("#registrarSala").click(registerSala); 
           
        })();     
  }
  
  $(loadRegisterSala);  

  