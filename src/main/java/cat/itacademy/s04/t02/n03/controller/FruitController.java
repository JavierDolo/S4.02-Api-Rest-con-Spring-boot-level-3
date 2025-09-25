package cat.itacademy.s04.t02.n03.controller;

import cat.itacademy.s04.t02.n03.dto.FruitRequest;
import cat.itacademy.s04.t02.n03.dto.FruitResponse;
import cat.itacademy.s04.t02.n03.service.FruitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/fruits")
@Validated
public class FruitController {

    private final FruitService service;

    public FruitController(FruitService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FruitResponse> create(@Valid @RequestBody FruitRequest dto) {
        FruitResponse created = service.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.id())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping
    public Page<FruitResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public FruitResponse findById(
            @PathVariable
            @Pattern(regexp = "^[a-fA-F0-9]{24}$", message = "id must be a 24-char hex ObjectId")
            String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public FruitResponse update(
            @PathVariable
            @Pattern(regexp = "^[a-fA-F0-9]{24}$") String id,
            @Valid @RequestBody FruitRequest dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable
            @Pattern(regexp = "^[a-fA-F0-9]{24}$") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
