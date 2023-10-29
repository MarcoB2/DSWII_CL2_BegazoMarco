package pe.edu.cibertec.DSWII_CL2_BegazoMarco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import pe.edu.cibertec.DSWII_CL2_BegazoMarco.model.Producto;

import java.util.List;
import java.util.Optional;


@Repository
public interface  ProductoRepository extends JpaRepository<Producto, Integer>{
    Optional<Producto> findBynombrepro(String nombrepro);

    List<Producto> findBynombreproContainingIgnoreCase(String filtro);

    @Query("SELECT c FROM producto c WHERE c.nombrepro LIKE %:filtro%")
    List<Producto> filtrarCategoriasPorNombre(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM producto WHERE nombrepro LIKE %:filtro%",
            nativeQuery = true)
    List<Producto> filtrarCategoriasPorNombreSQL(@Param("filtro") String filtro);

}
