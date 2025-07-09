package med.voll.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

//    @Autowired
//    private PacienteRepository repository;
//
//    @PostMapping
//    @Transactional
//    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) {
//        repository.save(new Paciente(datos));
//    }
//    @PostMapping
//    @Transactional
//    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriBuilder) {
//        var paciente = new Paciente(datos);
//        repository.save(paciente);
//
//        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
//        return ResponseEntity.created(uri).body(new DatosDetallePaciente(paciente));
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<DatosListadoPaciente>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
//        var page = repository.findAllByAtivoTrue(paginacion).map(DatosListadoPaciente::new);
//        return ResponseEntity.ok(page);
//    }
//
//    @PutMapping
//    @Transactional
//    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
//        var paciente = repository.getReferenceById(datos.id());
//        paciente.actualizarInformacion(datos);
//
//        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity eliminar(@PathVariable Long id) {
//        var paciente = repository.getReferenceById(id);
//        paciente. desactivar();
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity detallar(@PathVariable Long id) {
//        var paciente = repository.getReferenceById(id);
//        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
//    }
}
