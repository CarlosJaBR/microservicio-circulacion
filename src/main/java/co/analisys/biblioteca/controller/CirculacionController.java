package co.analisys.biblioteca.controller;

import co.analisys.biblioteca.model.LibroId;
import co.analisys.biblioteca.model.Prestamo;
import co.analisys.biblioteca.model.PrestamoId;
import co.analisys.biblioteca.model.UsuarioId;
import co.analisys.biblioteca.service.CirculacionService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/circulacion")
public class CirculacionController {

    @Autowired
    private CirculacionService circulacionService;

    @Operation(
        summary = "Prestar libro",
        description = "Permite prestar un libro a un usuario."
    )
    @PostMapping("/prestar")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public void prestarLibro(
        @RequestParam String usuarioId,
        @RequestParam String libroId
    ) {
        circulacionService.prestarLibro(
            new UsuarioId(usuarioId),
            new LibroId(libroId)
        );
    }

    @Operation(
        summary = "Devolver libro",
        description = "Permite devolver un libro prestado."
    )
    @PostMapping("/devolver")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN)")
    public void devolverLibro(@RequestParam String prestamoId) {
        circulacionService.devolverLibro(new PrestamoId(prestamoId));
    }

    @Operation(
        summary = "Consultar todos los préstamos",
        description = "Este endpoint permite obtener una lista de todos los préstamos registrados en el sistema."
    )
    @GetMapping("/prestamos")
    @PreAuthorize("hasRole('ROLE_LIBRARIAN')")
    public List<Prestamo> obtenerTodosPrestamos() {
        return circulacionService.obtenerTodosPrestamos();
    }
}
