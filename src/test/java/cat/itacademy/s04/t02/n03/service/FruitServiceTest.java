package cat.itacademy.s04.t02.n03.service;

import cat.itacademy.s04.t02.n03.dto.FruitRequest;
import cat.itacademy.s04.t02.n03.model.Fruit;
import cat.itacademy.s04.t02.n03.repository.FruitRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FruitServiceTest {

    private final FruitRepository repo = Mockito.mock(FruitRepository.class);
    private final FruitService service = new FruitService(repo);

    @Test
    void createFruit_shouldSaveAndReturnResponse() {
        FruitRequest request = new FruitRequest("apple", 10);
        Fruit saved = new Fruit("1", "apple", 10);

        when(repo.existsByName("apple")).thenReturn(false);
        when(repo.save(any(Fruit.class))).thenReturn(saved);

        var response = service.create(request);

        assertThat(response.name()).isEqualTo("apple");
        assertThat(response.quantityKilos()).isEqualTo(10);
    }

    @Test
    void findById_shouldReturnFruitResponse() {
        when(repo.findById("1")).thenReturn(Optional.of(new Fruit("1", "banana", 5)));
        var response = service.findById("1");
        assertThat(response.name()).isEqualTo("banana");
    }
}
