var url="http://localhost:8080/Proyecto2-Progra4/pags/";





var menu=`
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <a class="navbar-brand" href="#"><img src="../Img/tidael3.jpeg" style="width: 150px; height: auto;" alt="Registro"></a>
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#menu" >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="nav-item"><p class="text-light fw-bold fs-4 mb-0" >Tidael's Cinema</p></div>
      <div class="collapse navbar-collapse" id="menu">
      
        <ul class="navbar-nav ml-auto" id="menuUl">

          <li class="nav-item">
            
              <input type="text" class="searchMovie" placeholder="Search..." name="searchMovie" >             

          </li>
          <li class="nav-item">
            <a class="nav-link" href="pagPrincipal.html">Home</a>
          </li>
         `;
          
            let usuarioJson4 = sessionStorage.getItem('user');
            
            if (usuarioJson4!=null){ 
                let usuario= JSON.parse(usuarioJson4);
                loadFacturaId(usuario.id);
                
                if (['ADM'].includes(usuario.rol)){
                    menu+=`
                             <li class='nav-item'>
                            <a class='nav-link' href='#' data-toggle='modal' data-target='#registerSalaDialog'>Registrar Sala</a>
                             </li>
                              <li class='nav-item'>
                            <a class='nav-link' href='#' data-toggle='modal' data-target='#crearTandaDialog' id="abrirTanda">Crear Tanda</a>
                             </li> 
                             <li class='nav-item'>
                            <a class='nav-link' href='#' data-toggle='modal' data-target='#registerPeliculaDialog'>Registrar Pelicula</a>
                             </li> 
                            <li class='nav-item'>
                            <a class='nav-link' href='#' data-toggle='modal' data-target='#tiquetesDialog' id='tiquetesID'>Tiquetes</a>
                             </li> `;   
        
                }
                
                menu+=`
                    <li class='nav-item dropdown'>
                      <a class='nav-link dropdown-toggle' data-toggle='dropdown' href='#'>Historial</a>
                      <div id="historial" class='dropdown-menu'>`;
                      

                menu+=`
                    <li class='nav-item dropdown'>
                      <a class='nav-link dropdown-toggle' data-toggle='dropdown' href=''> ${usuario.userName}</a>
                      <div class='dropdown-menu'>
                        <a class='dropdown-item' id='logout'>Salir</a>
                      </div>
                    </li>`;                
            }
            else{
              menu+=`
                <li class='nav-item'>
                    <a class='nav-link' href='#' data-toggle='modal' data-target='#registerDialog'>Registrarse</a>
                </li>   
                <li class='nav-item'>
                    <a class='nav-link' href='#' data-toggle='modal' data-target='#loginDialog'>Iniciar-Sesión</a>
                </li>`;           
            }
            menu+=`
        </ul>
      </div>
    </div>
  </nav>`;
  
  function loadMenu(){
    $('body').prepend(menu); 
  }
  
  $(loadMenu);  
  
  
  var datosFactura = "";
  
  function insertFacturaDatos(facturaRec){
    
    
    
    datosFactura = `
      <div>Id Factura: `;
    datosFactura +=  facturaRec[0].id;   
    datosFactura += `</div>
        <div>Nombre del Cliente: `;
    datosFactura +=  facturaRec[0].nameCliente;   
    datosFactura += `</div>
    <div>ID del Cliente: `;
    datosFactura +=  facturaRec[0].idCliente;   
    datosFactura += `</div>
    <div>Id tanda: `;
    datosFactura +=  facturaRec[0].tanda;   
    datosFactura += `</div>
    `;
      
      document.getElementById("div-facturaView-msg").innerHTML = datosFactura; 
      
      $("#facturaViewDialog").modal('show');
  }
  
  
  
      function loadFacturaView(){
        let request = new Request(url+'factura.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#facturaViewDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content);
            
            
            
        })();     
  }$(loadFacturaView);  
  
  let idFactActual = "";
  
  
  
 async function loadFactura(id){
    
   let response = await fetch('/Proyecto2-Progra4/api/facturar/recuperarFactura', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: id});
   let facturaRec = await response.json();
   insertFacturaDatos(facturaRec);

   
 };
 
 async function loadFacturaId(idCliente){
    
   let response = await fetch('/Proyecto2-Progra4/api/facturar/recuperarFacturasId', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: idCliente});
   let facturas= await response.json();
   insertFacturas(facturas);
   $(".factura").on("click", e => {loadFactura(e.target.getAttribute("id"));});

 };
 
 function insertFacturas(facturas){
    let htmlMovies2 = '';
    for(var i = 0; i < facturas.length; i++){
                        htmlMovies2+= `<a class='dropdown-item factura' href='#' data-toggle='modal' id="`
                        htmlMovies2+=facturas[i];
                        htmlMovies2+=`">`;
                        htmlMovies2+=facturas[i];
                        htmlMovies2+=`</a>`;
                      }
    document.getElementById("historial").innerHTML = htmlMovies2;
}

  const searchBar = document.querySelector(".searchMovie");
