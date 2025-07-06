package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController le decimos que es un controller del tipo Rest
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    //@RequestBody se usa para decirle que en esa request yo solo quiero el body
    //@Valid de jakarta verifica la validacion puestas en el DTO
    @Transactional //para hacer modificaciones en la base de datos
    @PostMapping //@PostMapping usado para decir que la pagina esta haciendo un post
    public void registrar(@RequestBody @Valid DatosRegistroMedico datos){
        repository.save(new Medico(datos));
    }
    //Pageable sirve para crear lista de paginas o paginar los datos
    //Page<DatosListaMedicos> devuelve los datos y una lista de datos
    //@PageableDefault cambia por defecto los valores de la pagina
    @GetMapping
    public Page<DatosListaMedico> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion){
        return repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
    }

    @Transactional //actualiza unicamente los datos necesario y los guarda en la base de datos
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        var medico = repository.getReferenceById(datos.id()); //obtenemos el medico de la base de datos
        medico.actualizarInformaciones(datos);
    }

    @Transactional
    @DeleteMapping("/{id}") //@pathvariable para deciele que este id es el mismo que el del metodo
    public void eliminar(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.eliminar();
    }

}
