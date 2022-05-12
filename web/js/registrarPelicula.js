url = "http://localhost:8080/Proyecto2-Progra4/pags/";
const url2 = "http://localhost:8080/Proyecto2-Progra4/";
var pelicula = {movieName:"Horse", cartelera:false};
const regisPeli = document.querySelector('#registrarPelicula');

function registerMovieAndImage(){    
    registerPelicula();
   
}

function ocultarModal(){
    
    $("#validacionImagen").addClass("invalid-feedback");
     $("#registerPeliculaDialog").modal("hide");
    document.location = url2 + "/pags/pagPrincipal.html";
}
function addImagen(Pelicula){
  var imagenData = new FormData();
  imagenData.append("movieName", Pelicula.movieName);
  imagenData.append("imagen", $("#imagen").get(0).files[0]); 
  const str = url2+'api/peliculas/'+Pelicula.movieName+"/imagen";
  let request = new Request(url2+'api/peliculas/'+Pelicula.movieName+"/imagen", {method: 'POST',body: imagenData});
  (async ()=>{
      const response = await fetch(request);
      if (!response.ok) {errorMessage(response.status,$("#add-modal #errorDiv"));return;}              
  })();    

}
  
function registerPelicula() {
 if (!registerValidarPelicula()) return;
 //const valorCartelera = $("#enCartelera").val();
 const valorCartelera = document.getElementById("enCartelera").checked;
  Pelicula = {
    movieName: $("#name-movie").val(),
    cartelera: valorCartelera
  };

 // let request = new Request("/Proyecto2-Progra4/api/RegistrarPelicula",
   const turl = url2+"api/peliculas/"+Pelicula.movieName+"/register";
   let request = new Request(url2+"api/peliculas/"+Pelicula.movieName+"/register",{
    method: "POST",
    headers: { "Content-Type": "application/json", Accept: "application/json" },
    body: JSON.stringify(Pelicula),
  });
  (async () => {
    const response = await fetch(request);
    if (!response.ok) {
      errorMessageReSala(response.status, $("#registerPeliculaDialog #errorDiv"));
      return;
    }
    
  })();
  addImagen(Pelicula);
  setTimeout(ocultarModal ,2000);
}

function registerValidarPelicula() {    
  //$("#RegisterPeliculaForm").addClass("was-validated");
  const inputImagen = document.querySelector("#imagen").value;
  const inputMovieName = document.querySelector("#name-movie").value;
   if(inputMovieName.length === 0 || inputImagen.length === 0){
        if (inputImagen.length === 0) {
            $("#validacionImagen").removeClass("invalid-feedback");
        }
       $("#RegisterPeliculaForm").addClass("was-validated");
       return false;
   }
  return true;
}

function errorMessageRePelicula(status, place) {
  switch (status) {
    case 404:
      error = "pelicula ya registrada";
      break;
    case 403:
    case 405:
      error = "Usuario no autorizado";
      break;
    case 406:
    case 405:
      error = "Pelicula ya existe";
      break;
  }
  place.html(
    '<div class="alert alert-danger fade show">' +
      '<button type="button" class="close" data-dismiss="alert">' +
      '&times;</button><h4 class="alert-heading">Error!</h4>' +
      error +
      "</div>"
  );
  return;
}

function loadRegisterPelicula() {
  let request = new Request(url + "registrarPelicula.html", { method: "GET" });
  (async () => {
    const response = await fetch(request);
    if (!response.ok) {
      errorMessage(response.status, $("#registerPeliculaDialog #errorDiv"));
      return;
    }
    content = await response.text();
    $("body").append(content);
    $("#registrarPelicula").click(registerMovieAndImage);
  })();
}



$(loadRegisterPelicula);
