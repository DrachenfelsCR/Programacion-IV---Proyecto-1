var url="http://localhost:8080/Proyecto2-Progra4/pags/";

sala = {
            idSala: ""
             
        };
 

async function loadCrearTanda(){
        let request = new Request(url+'crearTanda.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#crearTandaDialog #errorDiv"));return;}
            content = await response.text();
           

            $('body').append(content); 
             
            $("#abrirTanda").on("click",getName);
             
             $("#abrirTanda").on("click",getPeliculas);
            $("#crearTanda").click(crearTanda); 
           
        })();     
  }
  
  function crearTanda(){
        
        if (!crearValidarTanda()) return;

                    Tanda ={
                  idTanda:$("#id-Tanda").val(),
                  idPelicula:$("#id-Peliculita").val(),
                  idSala: $("#id-salita").val(),
                  idPrecio:$("#id-precio").val(),
                  idHora:$("#id-hora").val(),
                  idFecha:$("#id-Fecha").val(),
                  estado: true,
                  idCliente:"",
                  idAsiento:""
              };
        
        let request = new Request('http://localhost:8080/Proyecto2-Progra4/api/crearTanda/registrarTanda',{method: 'POST', headers: { 'Content-Type': 'application/json'}, body: JSON.stringify(Tanda)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessageCrearTanda(response.status,$("#crearTandaDialog #errorDiv"));return;}
            
            
            $('#crearTandaDialog').modal('hide');   
            document.location = url+'pagPrincipal.html';
            
               
                
                                      
        })(); 
    
    }


  var listaSala = new Array();
  var ArrayPeliculas = new Array();
        
   

     const getName=async () => {
    const arrayDatos = await fetch('http://localhost:8080/Proyecto2-Progra4/api/crearTanda/recuperarSalas').then(res => res.json());
      let array = new Array();
 
    for ( i = 0; i < arrayDatos.length; i++) {
         array.push(arrayDatos[i]);
    }
     listaSala = [...array];
     salas(listaSala);
 };
  
const getPeliculas=async () => {
    const arrayDatos = await fetch('http://localhost:8080/Proyecto2-Progra4/api/RecuperarPeliculas/movies').then(res => res.json());
      let array = new Array();
 
    for ( i = 0; i < arrayDatos.length; i++) {
         array.push(arrayDatos[i]);
    }
     ArrayPeliculas = [...array];
    await Peliculas(ArrayPeliculas);
 };
 
 
 
 const Peliculas = function(listaPeliculas){
     let html =`

    <option selected>Selecione la Pelicula</option>

                       `;
     for (let i = 0; i < listaPeliculas.length; i++) {
         if (listaPeliculas[i].cartelera) {
                    
        html+=` <option value="${listaPeliculas[i].movieName}">${listaPeliculas[i].movieName}</option>`;
         }
    }
     
     html+=`</select>`;
      document.getElementById("id-Peliculita").innerHTML = html;
     
 };
const salas = function (listaSala) {
   
        
    
   let html = `
 
   
                        <option selected>Seleccione el ID Sala</option>`;
                       
                           for (let i = 0; i < listaSala.length; i++) {
         
                        html+= `<option value="${listaSala[i].idSala}">${listaSala[i].idSala}</option>
                                `;
        }   
        html+=`</select>
    `;
    
    const el = document.querySelector(".faker");
   
   document.getElementById("id-salita").innerHTML = html;
  
};
 
 

function crearValidarTanda(){
        $("#crearTandaForm").addClass("was-validated");
        return $("#crearTandaForm").get(0).checkValidity(); 
    }


  function errorMessageCrearTanda(status,place){  
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
 

 
  $(loadCrearTanda);
 