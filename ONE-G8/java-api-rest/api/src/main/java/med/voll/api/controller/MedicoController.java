package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroMedico datos, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(datos);
        repository.save(medico);
        //el codigo 201 se referencia con el createc la C del CRUD

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleMedico(medico));
    }

    //Pageable sirve para crear lista de paginas o paginar los datos
    //Page<DatosListaMedicos> devuelve los datos y una lista de datos
    //@PageableDefault cambia por defecto los valores de la pagina
    @GetMapping
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        //lsitar devuelve un 200 ok
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);
        return ResponseEntity.ok(page);
    }

    @Transactional //actualiza unicamente los datos necesario y los guarda en la base de datos
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        //debe devolver lo datos del medico ya actulizados
        var medico = repository.getReferenceById(datos.id()); //obtenemos el medico de la base de datos
        medico.actualizarInformaciones(datos);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional
    @DeleteMapping("/{id}") //@pathvariable para deciele que este id es el mismo que el del metodo
    public ResponseEntity eliminar(@PathVariable Long id) {
        //responseEntity nos devuleve metodos http diferentes
        var medico = repository.getReferenceById(id);
        medico.eliminar();
        //204 no conten para eliminar
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}") //@pathvariable para deciele que este id es el mismo que el del metodo
    public ResponseEntity detallar(@PathVariable Long id) {
        //responseEntity nos devuleve metodos http diferentes
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

}
