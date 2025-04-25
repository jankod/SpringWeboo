package hr.ja.weboo.db;// Primjer drugog entiteta: Address

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List; // Ako Address ima vezu natrag na User, nećemo je ovdje modelirati za jednostavnost

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

