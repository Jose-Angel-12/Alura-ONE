package com.aluracursos.screenmatch.controller;

import com.aluracursos.screenmatch.dto.SerieDTO;

import com.aluracursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerielController {

    @Autowired
    private SerieService servicio;

    //para mapear la serie que sera enviada ala web los datos enviados
    //los datos enviados solo seran los que yo quiero mostrar
    @GetMapping()
    public List<SerieDTO> obtenerTodasLasSeries(){
        return servicio.obtenerTodasLasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5(){
        return servicio.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientos(){
        return servicio.obtenerLanzamientosMasRecientes();
    }
    //como es un parametros dinamico el id hay que hacerlo asi
    //el @PathVariable es para indicarle que esa variable va en la url
    @GetMapping("/{id}")
    public SerieDTO obtenerPorId(@PathVariable Long id){
        return servicio.obtenerPorId(id);
    }
    //no funciono
//    @GetMapping("/inicio")
//    public String muestraMensaje(){
//        return "probando live reloadin para que compile y se ejecute la aplicacion automaticamente";
//    }

}
