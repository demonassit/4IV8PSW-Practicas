//vamos a obtener los elementos del html por medio de las clases

var piezas = document.getElementsByClassName('movil');

var tamWidth = [134, 192, 134, 163, 134, 163, 134, 192, 134];

var tamHeight = [163, 134, 163, 134, 192, 134, 163, 134, 163];

//enviar esos atributos para que se redimensionen las imagenes

for(var i = 0; i < piezas.length; i++){
    piezas[i].setAttribute("width", tamWidth[i]);
    piezas[i].setAttribute("height", tamHeight[i]);
    //posiciones de las piezas
    piezas[i].setAttribute("x", Math.floor((Math.random()*10)+1));
    piezas[i].setAttribute("y", Math.floor((Math.random()*409)+1));
    piezas[i].setAttribute("onmousedown", "seleccionarElemento(evt)");
}

var elementosSeleccionados = 0;
var currentX = 0;
var currentY = 0;
var currentPosX = 0;
var currentPosY = 0;

function seleccionarElemento(evt){

    //verificar los elementos seleccionar vs reordenamiento
    elementosSeleccionados = reordenar(evt);

    currentX = evt.clientX;
    currentY = evt.clientY;
    /*
    alert(currentX);
    alert(currentY);
    */

    currentPosX = parseFloat(elementosSeleccionados.getAttribute(currentX));
    currentPosY = parseFloat(elementosSeleccionados.getAttribute(currentY));

    elementosSeleccionados.setAttribute("onmousemove", "moverElemento(evt)");
}

function moverElemento(evt){
    var dx = evt.clientX - currentX;
    var dy = evt.clientY - currentY;

    //posicion
    currentPosX = currentPosX + dx;
    currentPosY = currentPosY + dy;

    elementosSeleccionados.setAttribute("x", currentPosX);
    elementosSeleccionados.setAttribute("y", currentPosY);
    

    currentX = evt.clientX;
    currentY = evt.clientY;

    //cuando queremos deseleccionar el elemento

    elementosSeleccionados.setAttribute("onmouseout", "deseleccionarElemento(evt)");
    elementosSeleccionados.setAttribute("onmouseup", "deseleccionarElemento(evt)");

    //funsion de iman
    iman();
}

function deseleccionarElemento(evt){
    //necesito una funcion que sepa que pieza estoy tomando
    testing();
    if(elementosSeleccionados != 0){
        elementosSeleccionados.removeAttribute("onmousemove");
        elementosSeleccionados.removeAttribute("onmouseout");
        elementosSeleccionados.removeAttribute("onmouseup");
        elementosSeleccionados = 0;
    }
}

var entorno = document.getElementById('entorno');

//vamos a ordenar los elementos

function reordenar(evt){
    var padre = evt.target.parentNode;
    var clone = padre.cloneNode(true);
    var id = padre.getAttribute("id");

    entorno.removeChild(document.getElementById(id));
    entorno.appendChild(clone);

    return entorno.lastChild.firstChild;

}


//que pasa con pikachu
var origX = [200, 304, 466, 200, 333, 437, 200, 304, 466];
var origY = [100, 100, 100, 233, 204, 233, 337, 366, 337];

//ahora si mi funcion de iman para cuando se coloque de forma erronea la pieza

function iman(){
    for(var i = 0; i < piezas.length; i++){
        if(Math.abs(currentPosX-origX[i])<15 && Math.abs(currentPosY-origY[i])<15){
            elementosSeleccionados.setAttribute("x", origX[i]);
            elementosSeleccionados.setAttribute("y", origY[i]);
        }
    }
}

//para ganar

var win = document.getElementById('win');


//ahora falta saber si estan en la posicion final del rompecabezas

function testing(){
    //vamos a saber si esta bien ubicada
    var bien_ubicada = 0;
    var padres = document.getElementsByClassName('padre');

    for(var i = 0; i < piezas.length; i++){
        var posx = parseFloat(padres[i].firstChild.getAttribute("x"));
        var posy = parseFloat(padres[i].firstChild.getAttribute("y"));

        ide = padres[i].getAttribute("id");

        if(origX[ide] == posx && origY[ide] == posy){
            bien_ubicada = bien_ubicada + 1;
        }
    }
    if(bien_ubicada == 9){
        //una funcion para ganar
        //que mande una alerta
        //que baile yo que se
        //fanfarrias
        win.play();
    }
}
