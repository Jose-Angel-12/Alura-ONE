package com.aluracursos.screenmatch.service;

public interface IConvierteDatos {
    //este es un metodo generico que me puede o no retornar algun valor del cual aun no se cual es
    <T> T obtenerDatos(String json, Class<T> clase);
}
