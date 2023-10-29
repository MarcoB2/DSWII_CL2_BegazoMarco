package pe.edu.cibertec.DSWII_CL2_BegazoMarco.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII_CL2_BegazoMarco.model.Producto;
import pe.edu.cibertec.DSWII_CL2_BegazoMarco.exception.ResourceNotFoundException;
import pe.edu.cibertec.DSWII_CL2_BegazoMarco.service.ProductoService;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/producto")

public class ProductoController {

    private ProductoService productoService;

    @GetMapping("")
    public ResponseEntity<List<Producto>> listarCategorias(){
        List<Producto> productoList = new ArrayList<>();
        productoService.listarProductos()
                .forEach(productoList::add);
        if(productoList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }

    @GetMapping("/{idpro}")
    public ResponseEntity<Producto> obtenerProducto(
            @PathVariable("idpro") Integer idpro){
        Producto producto = productoService.obtenerProductoPorId(idpro)
                .orElseThrow(() -> new ResourceNotFoundException("El producto con el Id Nro. "+
                        idpro + " no existe."));
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Producto> registrarProducto(
            @RequestBody Producto producto
    ){
        return new ResponseEntity<>(
                productoService.guardar(producto), HttpStatus.CREATED
        );
    }

    @PutMapping("/{idpro}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable("idpro") Integer idpro,
            @RequestBody Producto nuevoProducto
    ){
        Producto productoExistente = productoService
                .obtenerProductoPorId(idpro)
                .orElse(null);
        if (productoExistente != null) {
            productoExistente.setNombrepro(nuevoProducto.getNombrepro());
            productoExistente.getDescripcionpro(nuevoProducto.getDescripcionpro());
            productoExistente.setcantidadpro(nuevoProducto.getCantidadpro());
            productoExistente.setFecvenpro(nuevoProducto.getFecvenpro());
            Producto productoActualizado = productoService.guardar(productoExistente);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{idpro}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable("idpro") Integer idpro
    ){
        productoService.eliminarProducto(idpro);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/nombrepro/{nombrepro}")
    public ResponseEntity<List<Producto>> buscarProNom(
            @PathVariable("nombrepro") String nombre
    ){
        List<Producto> producto = productoService.buscarProductoPorNombre(nombre);
        if (producto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/cantidadpro")
    public ResponseEntity<List<Producto>> buscarPro(){
        List<Producto> producto = productoService.buscarProductoEntre10Y100();
        if (producto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/vencimiento/2024/{anio}")
    public ResponseEntity<List<Producto>> buscarProAnio(@PathVariable("anio") Integer anio){
        List<Producto> productoList = new ArrayList<>();
        productoService.buscarProductoPorAnio2024(anio)
                .forEach(productoList::add);
        if (productoList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productoList, HttpStatus.OK);
    }

}