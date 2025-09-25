package cat.itacademy.s04.t02.n03.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FruitRequest(
        @NotBlank @Size(max = 100) String name,
        @Min(value = 0, message = "quantityKilos must be >= 0") int quantityKilos
) {}
