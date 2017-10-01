package cesar.cabrera.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import cesar.cabrera.domain.enumeration.Tipo;

/**
 * A Recambio.
 */
@Document(collection = "recambio")
public class Recambio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @NotNull
    @Size(min = 3, max = 50)
    @Field("referencia")
    private String referencia;

    @NotNull
    @Field("nombre")
    private String nombre;

    @NotNull
    @Field("tipo")
    private Tipo tipo;

    @Field("descripcion")
    private String descripcion;

    @NotNull
    @Field("precio")
    private Double precio;

    @Field("reclamo_publicitario")
    private String reclamoPublicitario;

    @NotNull
    @Field("disponibilidad")
    private Boolean disponibilidad;

    @NotNull
    @Field("exposicion")
    private Boolean exposicion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public Recambio referencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public Recambio nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Recambio tipo(Tipo tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Recambio descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public Recambio precio(Double precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getReclamoPublicitario() {
        return reclamoPublicitario;
    }

    public Recambio reclamoPublicitario(String reclamoPublicitario) {
        this.reclamoPublicitario = reclamoPublicitario;
        return this;
    }

    public void setReclamoPublicitario(String reclamoPublicitario) {
        this.reclamoPublicitario = reclamoPublicitario;
    }

    public Boolean isDisponibilidad() {
        return disponibilidad;
    }

    public Recambio disponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
        return this;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Boolean isExposicion() {
        return exposicion;
    }

    public Recambio exposicion(Boolean exposicion) {
        this.exposicion = exposicion;
        return this;
    }

    public void setExposicion(Boolean exposicion) {
        this.exposicion = exposicion;
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
        Recambio recambio = (Recambio) o;
        if (recambio.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), recambio.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Recambio{" +
            "id=" + getId() +
            ", referencia='" + getReferencia() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", precio='" + getPrecio() + "'" +
            ", reclamoPublicitario='" + getReclamoPublicitario() + "'" +
            ", disponibilidad='" + isDisponibilidad() + "'" +
            ", exposicion='" + isExposicion() + "'" +
            "}";
    }
}
