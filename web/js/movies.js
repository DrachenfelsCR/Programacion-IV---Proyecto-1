/*const movies = document.querySelector("#moviesContainer");
const searchBar = document.querySelector(".movieSearch");



async function getArrPeliculas()
{   
const response = await fetch('http://localhost:8080/Proyecto2-Progra4/api/RecuperarPeliculas');
let listaPeliculas = await response.json();
loadTandas(listaPeliculas);

}




function loadTandas(listaPeliculas){
    
    tamano = listaPeliculas.length;   
    let htmlMovies2= '';
    for (var i = 0; i < listaPeliculas.length; i++) 
    {
        htmlMovies2 +=  `
        <div class="col">
        <div id="img-contenedor" class="card shadow-sm">
        <img src="http://localhost:8080/Proyecto2-Progra4/api/peliculas/${listaPeliculas[i].movieName}/imagen" class="img-thumbnail" id="img-movie" alt="...">
            <div class="card-body"  id="card-body">
        <a  movie=${listaPeliculas[i].movieName} id='tanda1' class='nav-link tanda' href='#' data-toggle='modal' data-target='#tandaDialog'>Tanda de las 8</a>
    `;
    htmlMovies2 +=`
           </div>  
    </div>
  </div>
    `;
        
    }
    movies.innerHTML = htmlMovies2;
}


getArrPeliculas();

*/
 