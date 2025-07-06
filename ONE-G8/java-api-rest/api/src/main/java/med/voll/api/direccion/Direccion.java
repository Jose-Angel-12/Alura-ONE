package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //crea todos los getters
@NoArgsConstructor //crea un cosntructor por defecto
@AllArgsConstructor //crea un constructor con todos lo parametros
@Embeddable
public class Direccion {

    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String estado;

    public Direccion(DatosDireccion datos) {
        this.calle = datos.calle();
        this.numero = datos.numero();
        this.complemento = datos.complemento();
        this.barrio = datos.barrio();
        this.codigo_postal = datos.codigo_postal();
        this.ciudad = datos.ciudad();
        this.estado = datos.estado();
    }

    public void actualizarDireccion(DatosDireccion direccion) {
       if(direccion.calle() != null) {
           this.calle = direccion.calle();
       }
        if(direccion.numero() != null) {
            this.numero = direccion.numero();
        }
        if(direccion.complemento() != null) {
            this.complemento = direccion.complemento();
        }
        if(direccion.barrio() != null) {
            this.barrio = direccion.barrio();
        }
        if(direccion.codigo_postal() != null) {
            this.codigo_postal = direccion.codigo_postal();
        }
        if(direccion.ciudad() != null) {
            this.ciudad = direccion.ciudad();
        }
        if(direccion.estado() != null) {
            this.estado = direccion.estado();
        }
    }
}
