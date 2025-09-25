package cat.itacademy.s04.t02.n03.service;

import cat.itacademy.s04.t02.n03.dto.FruitRequest;
import cat.itacademy.s04.t02.n03.dto.FruitResponse;
import cat.itacademy.s04.t02.n03.exception.NotFoundException;
import cat.itacademy.s04.t02.n03.model.Fruit;
import cat.itacademy.s04.t02.n03.repository.FruitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FruitService {

    private final FruitRepository repo;

    public FruitService(FruitRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public FruitResponse create(FruitRequest dto) {
        if (repo.existsByName(dto.name())) {
            throw new IllegalArgumentException("Fruit with that name already exists");
        }
        Fruit saved = repo.save(new Fruit(null, dto.name(), dto.quantityKilos()));
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<FruitResponse> findAll(Pageable pageable) {
        return repo.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public FruitResponse findById(String id) {
        Fruit fruit = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit not found"));
        return toResponse(fruit);
    }

    @Transactional
    public FruitResponse update(String id, FruitRequest dto) {
        Fruit fruit = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Fruit not found"));
        fruit.setName(dto.name());
        fruit.setQuantityKilos(dto.quantityKilos());
        return toResponse(repo.save(fruit));
    }

    @Transactional
    public void delete(String id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Fruit not found");
        }
        repo.deleteById(id);
    }

    private FruitResponse toResponse(Fruit f) {
        return new FruitResponse(f.getId(), f.getName(), f.getQuantityKilos());
    }
}
