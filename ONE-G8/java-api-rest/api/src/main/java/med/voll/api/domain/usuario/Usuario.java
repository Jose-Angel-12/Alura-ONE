package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter //crea todos los getters
@NoArgsConstructor //crea un cosntructor por defecto
@AllArgsConstructor //crea un constructor con todos lo parametros
@EqualsAndHashCode(of = "id") //identifica si dos objetos son iguales si tienen el mismo id
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String  contrasena;
}
