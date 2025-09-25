package cat.itacademy.s04.t02.n03.config;

import cat.itacademy.s04.t02.n03.model.Fruit;
import cat.itacademy.s04.t02.n03.repository.FruitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seed(FruitRepository repo) {
        return args -> {
            if (!repo.existsByName("apple")) {
                repo.save(new Fruit(null, "apple", 10));
            }
            if (!repo.existsByName("banana")) {
                repo.save(new Fruit(null, "banana", 20));
            }
        };
    }
}
