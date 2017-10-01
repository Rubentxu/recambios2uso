package cesar.cabrera.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Marca.
 */
@Document(collection = "marca")
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @NotNull
    @Field("nombre")
    private String nombre;

    @NotNull
    @Field("modelo")
    private String modelo;

    @NotNull
    @Field("tipo_motor")
    private String tipoMotor;

    @Field("descripcion")
    private String descripcion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Marca nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public Marca modelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public Marca tipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
        return this;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Marca descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Marca marca = (Marca) o;
        if (marca.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), marca.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Marca{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", tipoMotor='" + getTipoMotor() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
