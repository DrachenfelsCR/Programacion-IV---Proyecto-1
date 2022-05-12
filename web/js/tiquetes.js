/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var url="http://localhost:8080/Proyecto2-Progra4/pags/";
let contador = 0;
let tiquetesArr = [];
function insertHtmlFactura(facturaRec){
    const infoFac = document.querySelector('#facturaRecuperada');
    let html = ` 
    <div>
    <li id="infoFactu" class="list-group-item d-flex justify-content-between align-items-center value='${facturaRec[0].id}'">
    Factura: ${facturaRec[0].id} , Asientos: 
    <span class="badge bg-primary rounded-pill">${facturaRec.length}</span>
  </li> 
</div>
<div>
    <button type="button" id="imprimirTiquetes" class="btn btn-primary btn-lg btn-block ">Imprimir</button>
</div>`;
    infoFac.innerHTML = html;
    
}

function resetHtmlFactura(){
    const infoFac = document.querySelector('#facturaRecuperada');
    let html = ` 
    <div>
    <li class="list-group-item d-flex justify-content-between align-items-center">
    </li> 
</div>`;
    infoFac.innerHTML = html;  
}

function resetModalTiquetes(){
    document.querySelector('#tiquetesID').addEventListener('click',function(){
        resetHtmlFactura();
    });
}

function clickImprimir(){
    document.getElementById('imprimirTiquetes').addEventListener('click', function () {
        const f = document.querySelector('#infoFactu').value;       
        console.log(f);
        let request = new Request('http://localhost:8080/Proyecto2-Progra4/api/facturar/imprimirPdf',{method: 'POST', headers: { 'Content-Type': 'application/json'}, body: JSON.stringify(tiquetesArr)});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessageCrearTanda(response.status,$("#tiquetesDialog #errorDiv"));return;}
            $('#tiquetesDialog').modal('hide');   
            document.location = url+'pagPrincipal.html';                         
        })();
});
 }
 
async function loadFacturaTiquetes(id){
   let response = await fetch('/Proyecto2-Progra4/api/facturar/recuperarFactura', {method: 'POST', headers: { 'Content-Type': 'application/json'},body: id});
   let facturaRec = await response.json();
   tiquetesArr = facturaRec;
   insertHtmlFactura(facturaRec);
   $("#imprimirTiquetes").on("click", clickImprimir);
 };
 

 
function searchButtonClick(){
        const buscarTique = document.querySelector('#buscarTiquetes').addEventListener('click', function () {
             const info = document.querySelector('#facturaTiquetes').value;
             loadFacturaTiquetes(info);            
});  
 }
  
function loadTiquetes(){
        let request = new Request(url+'tiquetes.html', {method: 'GET'});
        (async ()=>{
            const response = await fetch(request);
            if (!response.ok) {errorMessage(response.status,$("#tiquetesDialog #errorDiv"));return;}
            content = await response.text();
            $('body').append(content); 
            $("#tiquetesDialog").click(searchButtonClick);
             resetModalTiquetes();
        })();     
         
  }
  
$(loadTiquetes)

