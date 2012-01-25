/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pasosServer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Antonio
 */
@Entity
@Table(name = "PROTEGIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Protegido.findAll", query = "SELECT p FROM Protegido p"),
    @NamedQuery(name = "Protegido.findByIdProtegido", query = "SELECT p FROM Protegido p WHERE p.idProtegido = :idProtegido"),
    @NamedQuery(name = "Protegido.findByNombre", query = "SELECT p FROM Protegido p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Protegido.findByTelefonoMovil", query = "SELECT p FROM Protegido p WHERE p.telefonoMovil = :telefonoMovil"),
    @NamedQuery(name = "Protegido.findByApellidos", query = "SELECT p FROM Protegido p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Protegido.findByFechaNacimiento", query = "SELECT p FROM Protegido p WHERE p.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Protegido.findByLongitud", query = "SELECT p FROM Protegido p WHERE p.longitud = :longitud"),
    @NamedQuery(name = "Protegido.findByLatitud", query = "SELECT p FROM Protegido p WHERE p.latitud = :latitud"),
    @NamedQuery(name = "Protegido.findByImei", query = "SELECT p FROM Protegido p WHERE p.imei = :imei")})
public class Protegido implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROTEGIDO")
    private BigDecimal idProtegido;
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "TELEFONO_MOVIL")
    private int telefonoMovil;
    @Size(max = 30)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Lob
    @Column(name = "FOTO")
    private byte[] foto;
    @Column(name = "LONGITUD")
    private double longitud;
    @Column(name = "LATITUD")
    private double latitud;
    @Size(max = 18)
    @Column(name = "IMEI")
    private String imei;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProtegido")
    private Collection<Maltratador> maltratadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProtegido")
    private Collection<Alarma> alarmaCollection;
    @OneToMany(mappedBy = "idProtegido")
    private Collection<Contacto> contactoCollection;

    public Protegido() {
    }

    public Protegido(BigDecimal idProtegido) {
        this.idProtegido = idProtegido;
    }

    public BigDecimal getIdProtegido() {
        return idProtegido;
    }

    public void setIdProtegido(BigDecimal idProtegido) {
        this.idProtegido = idProtegido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setLatitud(BigInteger latitud) {
        this.setLatitud(latitud);
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @XmlTransient
    public Collection<Maltratador> getMaltratadorCollection() {
        return maltratadorCollection;
    }

    public void setMaltratadorCollection(Collection<Maltratador> maltratadorCollection) {
        this.maltratadorCollection = maltratadorCollection;
    }

    @XmlTransient
    public Collection<Alarma> getAlarmaCollection() {
        return alarmaCollection;
    }

    public void setAlarmaCollection(Collection<Alarma> alarmaCollection) {
        this.alarmaCollection = alarmaCollection;
    }

    @XmlTransient
    public Collection<Contacto> getContactoCollection() {
        return contactoCollection;
    }

    public void setContactoCollection(Collection<Contacto> contactoCollection) {
        this.contactoCollection = contactoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProtegido != null ? idProtegido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Protegido)) {
            return false;
        }
        Protegido other = (Protegido) object;
        if ((this.idProtegido == null && other.idProtegido != null) || (this.idProtegido != null && !this.idProtegido.equals(other.idProtegido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pasosServer.model.Protegido[ idProtegido=" + idProtegido + " ]";
    }

    /**
     * @param telefonoMovil the telefonoMovil to set
     */
    public void setTelefonoMovil(int telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the foto
     */
    public byte[] getFoto() {
        return foto;
    }

    /**
     * @param foto the foto to set
     */
    public void setFoto(byte[] foto) {
        this.setFoto(foto);
    }

    /**
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @return the latitud
     */
    public double getLatitud() {
        return latitud;
    }
    
}
