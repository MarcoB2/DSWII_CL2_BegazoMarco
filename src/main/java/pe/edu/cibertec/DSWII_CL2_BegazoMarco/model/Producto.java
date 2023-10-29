package pe.edu.cibertec.DSWII_CL2_BegazoMarco.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idpro;
    @Column(name = "nombrepro")
    private String nombrepro;
    @Column(name = "descripcionpro")
    private String descripcionpro;
    @Column(name = "cantidadpro")
    private Integer cantidadpro;
    @Column(name = "fecvenpro")
    private Date fecvenpro;
}
