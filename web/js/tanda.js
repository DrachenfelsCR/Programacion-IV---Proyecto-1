var url="http://localhost:8080/Proyecto2-Progra4/pags/";
var seats = [] ;
let cantFact = 0;  
loadFacturaCant();
let usuarioJson2 = sessionStorage.getItem('user');
let tandaAux2;
let tandaArrTandas = [];
var compraHTML = "<span>Metodo de Pago</span>";
        if (usuarioJson2==null){ compraHTML += `
<div class="input-group" style="margin-bottom: 25px">
    <div class="input-group-prepend "><span class="input-group-text"><i class="fa fa-user bigicon"></i></span></div>
    <input class="form-control" placeholder="Nombre del usuario" type="text" id="name-facturar" name="name-facturar" value="" required>
    <div class="invalid-feedback">Favor ingrese nombre del usuario</div>
</div>
<div class="input-group" style="margin-bottom: 25px">
    <div class="input-group-prepend "><span class="input-group-text"><i class="fa fa-user bigicon"></i></span></div>
    <input class="form-control" placeholder="Id del usuario" type="text" id="id-facturar" name="id-facturar" value="" required>
    <div class="invalid-feedback">Favor ingrese id del usuario</div>
</div>
<div class="input-group" style="margin-bottom: 25px">
    <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-key bigicon"></i></span></div>
    <input class="form-control" placeholder="Numero de tarjeta" type="text" id="card-facturar" name="card-facturar" value="" required>
    <div class="invalid-feedback">Favor ingrese el numero de tarjeta</div>
</div>`}
else{
    compraHTML += `<div class="input-group" style="margin-bottom: 25px">
    <div class="input-group-prepend"><span class="input-group-text"><i class="fa fa-key bigicon"></i></span></div>
    <input class="form-control" placeholder="Numero de tarjeta" type="text" id="card-facturar" name="card-facturar" value="" required>
    <div class="invalid-feedback">Favor ingrese el numero de tarjeta </div>
</div>
`;
}

//---------------------------------------------------------------------------------------------------------------------------------------------------------------


const movies = document.querySelector("#moviesContainer");



 async function loadPelisTandas(){   
   const response = await fetch('http://localhost:8080/Proyecto2-Progra4/api/RecuperarPeliculas/tandas');
    let listaPeliculas = await response.json();
    tandaArrTandas = listaPeliculas;
    await loadTandas(listaPeliculas);

 };
 
 async function loadMovieBySearch(){   
const searchBar =  document.querySelector(".searchMovie").value;
    if (searchBar === '') {
    await loadTandas(tandaArrTandas);
    }
    else{
const result = tandaArrTandas.filter(word => word.nombrePelicula.includes(searchBar));
    await loadTandas(result);
    }

 };


async function loadTandas(listaPeliculas){
    tamano = listaPeliculas.length;   
    let htmlMovies2= '';
    let previousName = "";
    for (var i = 0; i < listaPeliculas.length; i++) 
    {
        if (i > 0) {
            if (listaPeliculas[i].nombrePelicula != listaPeliculas[i-1].nombrePelicula) {
            htmlMovies2 +=`
           </div>  
    </div>
  </div>
    `;
        }
    }
        if (listaPeliculas[i].nombrePelicula === previousName) {
            htmlMovies2 += `<a  movie=${listaPeliculas[i].nombrePelicula} id='${listaPeliculas[i].idTanda}' class='nav-link tanda' href='#' data-toggle='modal' data-target='#tandaDialog'>${listaPeliculas[i].nombrePelicula} | Tanda: ${listaPeliculas[i].horita} - ₡${listaPeliculas[i].precio} |</a>`;
        }
        else{
        htmlMovies2 +=  `
        <div class="col">
        <div id="img-contenedor" class="card shadow-sm">
        <img src="http://localhost:8080/Proyecto2-Progra4/api/peliculas/${listaPeliculas[i].nombrePelicula}/imagen" class="img-thumbnail" id="img-movie" alt="...">
            <div class="card-body"  id="card-body-${listaPeliculas[i].nombrePelicula}">
        <a  movie=${listaPeliculas[i].nombrePelicula} id='${listaPeliculas[i].idTanda}' class='nav-link tanda' href='#' data-toggle='modal' data-target='#tandaDialog'>${listaPeliculas[i].nombrePelicula} | Tanda: ${listaPeliculas[i].horita} - ₡${listaPeliculas[i].precio} |</a>        
    `; 
       
       }
       
      previousName = listaPeliculas[i].nombrePelicula;  
    }
    
     htmlMovies2 +=`
           </div>  
    </div>
  </div>
    `;
    movies.innerHTML = htmlMovies2;
    $(".tanda").on("click", e => {getTandajs(e.target.getAttribute("id"));});
}







var tandaAct;

    function saveTandaAct(id, tandaAux){
        
        for(var i = 0; i < tandaAux.asientos.length; i++){
            $("#"+i).attr("tanda",id);
            if(tandaAux.asientos[i].estado == false){
                $("#"+i).addClass("occupied");
            }else{
                $("#"+i).removeClass("occupied");
            }
            if($("#"+i).hasClass("selected")){
                $("#"+i).removeClass("selected");
            }
        }
        seats = [];
        
        $("#tandaDialog").modal('show');
     
    }
    
    
 
    
//---------------------------------------------------------------------------------------------------------------------------------------------------------------

    function saveSeats(id){
        
        if(!$("#"+id).hasClass("occupied")){
            if($("#"+id).hasClass("selected")){
                $("#"+id).on("click",$("#"+id).removeClass("selected"));
                removeSeat(id);
            }else{  
            $("#"+id).on("click",$("#"+id).addClass("selected"));
                seats.push(id);
            }
        }
        
        document.getElementById("monto").innerHTML = tandaAux2.precio*seats.length; 
        
    };
    
    function removeSeat(id){
        for(var i = 0; i < seats.length; i++){
            if(seats[i] == id ){
                seats.splice(i,1);
            }
        }
    }
    


    function loadTanda(){
        let request = new Request(url+'tanda.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#tandaDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content); 
            $(".seat").on("click", e => {saveSeats(e.target.getAttribute("id"), e.target.getAttribute("tanda"));});
            $("#facturarBoton").on("click",resetModal);
            document.getElementById("monto").innerHTML = 0;                       
        })();     
  }
  
  $(loadTanda);  



    function facturar(){
        
        usuarioJson2 = sessionStorage.getItem('user');
        if (!facturarValidar()) return;
        if(usuarioJson2==null){
            fact = {
                id: cantFact,
                tanda: tandaAux2.idTanda,
                nameCliente: $("#name-facturar").val(),
                idCliente: $("#id-facturar").val(),
                butacas : seats
           
            };
        }else{
            let usuario2= JSON.parse(usuarioJson2);
            fact = {
            id: cantFact,
            tanda: tandaAux2.idTanda,
            nameCliente: usuario2.userName,
            idCliente: usuario2.id,
            butacas : seats
           
        };
        }
        
    
        let request = new Request('/Proyecto2-Progra4/api/facturar', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: JSON.stringify(fact)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessageFacturar(response.status,$("#facturarDialog #errorDiv"));setTimeout(hideFacturar,3000);return }
            afirmacionMessageFacturar(response.status,$("#facturarDialog #errorDiv"));

            setTimeout(hideFacturar ,3000);
                          
        })(); 
    
    }
    
    function hideFacturar(){
        $("#facturarDialog").modal('hide');
        document.location = url+'pagPrincipal.html';
    }
    

    function facturarValidar(){
        $("#facturarForm").addClass("was-validated");
        return $("#facturarForm").get(0).checkValidity(); 
    }
    
    function errorMessageFacturar(status,place){  
    switch(status){
        case 404: error= "Butacas ya compradas"; break;
        case 403: case 405: error="Usuario no autorizado"; break;
        case 406: case 405: error="Usuario ya existe"; break;
        case 500: error= "No se que error es"; break;
    };            
    place.html('<div class="alert alert-danger fade show">' +
    '<button type="button" class="close" data-dismiss="alert">' +
    '&times;</button><h4 class="alert-heading">Error!</h4>'+error+'</div>');
    return;        
    } 
    
    function afirmacionMessageFacturar(status,place){  
           
    place.html('<div class="alert alert-success fade show">' +
    '<button type="button" class="close" data-dismiss="alert">' +
    '&times;</button>Se hizo la compra con exito</div>');
    return;        
    } 
    
    
    function resetModal(){
        $("#tandaDialog").modal('hide');
        document.getElementById("div-facturar-msg").innerHTML = compraHTML;  

    }   

    

function loadFacturar(){
        let request = new Request(url+'facturar.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#facturarDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content);
            $("#facturar").on("click",facturar);
            
                                     
        })();     
  }
  
  
  $(loadFacturar);  
  
  
  
  async function getTandajs(idTanda){
    
   let response = await fetch('/Proyecto2-Progra4/api/crearTanda/recuperarTanda', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: idTanda});
 
        let tandaAux = await response.json();
        saveTandaAct(idTanda, tandaAux);
        tandaAux2 = tandaAux;

 };
 

  async function loadFacturaCant(){
   let cantidad = 0; 
   let response = await fetch('/Proyecto2-Progra4/api/facturar/recuperarFacturasCant');
   cantFact = await response.json();

 };
  
function loadSearchBar(){
        document.querySelector('.searchMovie').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        setTimeout(loadMovieBySearch, 250)
    }
});
  }

$(loadPelisTandas);  

$(loadSearchBar);  
