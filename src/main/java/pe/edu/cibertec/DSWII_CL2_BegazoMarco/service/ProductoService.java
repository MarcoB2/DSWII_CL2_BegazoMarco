package pe.edu.cibertec.DSWII_CL2_BegazoMarco.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DSWII_CL2_BegazoMarco.model.Producto;
import pe.edu.cibertec.DSWII_CL2_BegazoMarco.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoService {
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }
    public Producto guardar(Producto producto){
        return productoRepository.save(producto);
    }
    public Optional<Producto> obtenerProductoPorId(Integer id){
        Optional<Producto> producto = productoRepository.findById(id);
        if(producto.isEmpty()){
            return Optional.empty();
        }else
            return producto;
    }
    public void eliminarProducto(Integer id){

        productoRepository.deleteById(id);
    }

    public List<Producto> buscarProNom(String nombre){

        return productoRepository.findBynombrepro(nombre);
    }

    public List<Producto> buscarPro(){
        return productoRepository.findProductosEntre10y100();
    }

    public List<Producto> buscarProAnio(Integer anio){
        return productoRepository.findProductosByYear2024(anio);
    }
}
