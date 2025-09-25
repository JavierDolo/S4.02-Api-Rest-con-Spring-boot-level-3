package cat.itacademy.s04.t02.n03.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fruits")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class Fruit {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;

    private int quantityKilos;
}
