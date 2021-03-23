/*
vamos a crear una funcion que se encargue que
a partir de una expresion regular se valide la entrada
de unicamente numeros
*/

function validarn(e){
    var teclado = (document.all)?e.keyCode:e.which;
    if(teclado == 8)return true;

    var patron = /[0-9\d .]/;

    var prueba = String.fromCharCode(teclado);
    return patron.test(prueba);
}


function interes(){
    var valor = document.formulario.cantidad.value;
    var result = parseInt(valor);
    var interes = result*0.02;
    var total = result+interes;

    document.formulario.sueldoti.value = "$"+total;
}

function borrar(){
    document.formulario.sueldoti.value = "";
    document.formulario.cantidad.value = "";
}