package hr.ja.weboo.model;// Primjer drugog entiteta: Address

import hr.ja.weboo.db.InMemoryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address implements InMemoryEntity<Long> {
    private Long id;
    private String street;
    private String city;

    // Ako postoji veza natrag na User, obično bi ovdje bio User user;
    // Ali to komplicira memorijsko upravljanje - za prototipiranje, User može samo držati listu Address ID-ova ili Address objekata

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

}

