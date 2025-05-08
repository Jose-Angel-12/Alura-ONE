// variables
let numeroMaximoPosible = 50;
let numeroSecreto = Math.floor((Math.random() * numeroMaximoPosible) + 1);
let numeroUsuario = 0;
let intentos = 1;
let maximosIntentos = 6;
console.log(numeroSecreto);

while (numeroSecreto != numeroUsuario) {
    numeroUsuario = parseInt(prompt(`Ingresa un número entre 1 y ${numeroMaximoPosible}`));
    console.log(numeroUsuario);

    if (numeroSecreto == numeroUsuario) {
        alert(`Acertaste, el número es: ${numeroSecreto}, lo hiciste en ${intentos} ${intentos == 1 ? 'vez' : 'veces'}`);
    } else {
        if (numeroUsuario > numeroSecreto) {
            alert("El número secreto es menor");
        } else {
            alert("El número secreto es mayor");
        }
        intentos++;
        palabraVeces = 'veces';
        //alert('No acertaste');
        if (intentos > maximosIntentos) {
            alert(`llegaste al número máximo de ${maximosIntentos}`)
            break;
        }
    }
}
