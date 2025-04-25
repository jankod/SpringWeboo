package hr.ja.weboo.db;

import java.io.Serializable; // Dobra praksa, iako ne nužno potrebna za samo Mapu

public interface InMemoryEntity<ID> extends Serializable {
    ID getId();
    void setId(ID id);
//    default void persist() {
//        InMemoryStorage.persist(this);
//    }
//
//    // Briše ovaj objekt iz memorijskog skladišta
//    default void delete() {
//        InMemoryStorage.delete(this);
//    }


}