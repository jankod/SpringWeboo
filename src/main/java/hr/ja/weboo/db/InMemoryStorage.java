package hr.ja.weboo.db;

import jakarta.validation.*;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Jednostavno statičko skladište u memoriji
public class InMemoryStorage {

    // Mapa klase entiteta -> Mapa ID-a -> Instanca entiteta
    // Koristimo ConcurrentHashMap za osnovnu thread-sigurnost
    private static final Map<Class<?>, Map<Object, Object>> storage = new ConcurrentHashMap<>();

    // Mapa klase entiteta -> Brojač ID-ova (za Long ID-ove)
    private static final Map<Class<?>, AtomicLong> counters = new ConcurrentHashMap<>();

    // Dodajte statičku instancu Validatora
    private static final Validator validator;

    // Statički inicijalizacijski blok za postavljanje validatora
    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        // Napomena: U pravoj Spring aplikaciji s DI, validator bi se @Autowired-ao u Spring Bean
        // Ovdje ga ručno pokrećemo jer je InMemoryStorage statička klasa.
    }

    static <T> void validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }


    @SuppressWarnings("unchecked")
    static <T, ID> Map<ID, T> getMap(Class<T> entityClass) {
        return (Map<ID, T>) storage.computeIfAbsent(entityClass, k -> new ConcurrentHashMap<>());
    }

    static long nextId(Class<?> entityClass) {
        return counters.computeIfAbsent(entityClass, k -> new AtomicLong(0)).incrementAndGet();
    }

    // Generic method to persist an entity
    static <T extends InMemoryEntity<ID>, ID> void persist(T entity) {
        validate(entity);

        //persistOnly(entity);
        persistRecursive(entity, new HashSet<>());
    }

    @SuppressWarnings("unchecked")
    private static <T extends InMemoryEntity<ID>, ID> void persistOnly(T entity) {
        Map<ID, T> map = getMap((Class<T>) entity.getClass());
        if (entity.getId() == null) {
            // Pretpostavljamo da ID-ovi generirani ovdje moraju biti Long
            if (entity.getClass().getGenericInterfaces()[0].getTypeName().contains("Long")) { // Pojednostavljena provjera tipa ID-a
                entity.setId((ID) Long.valueOf(nextId(entity.getClass())));
            } else {
                // Ovdje biste trebali implementirati logiku za druge tipove ID-a (npr. UUID)
                throw new UnsupportedOperationException("Generiranje ID-a podržano samo za Long. Implementirajte za druge tipove.");
            }
        }
        map.put(entity.getId(), entity);
    }

    static <T extends InMemoryEntity<ID>, ID> boolean delete(T entity) {
        if (entity.getId() == null) {
            return false; // Ne može obrisati nešto bez ID-a
        }

        return deleteRecursive(entity, new HashSet<>()); // Koristi Set za praćenje posjećenih

//        Map<ID, T> map = getMap((Class<T>) entity.getClass());
//        return map.remove(entity.getId()) != null; // Vraća true ako je nešto uklonjeno
    }

    static <T extends InMemoryEntity<ID>, ID> T findById(Class<T> entityClass, ID id) {
        Map<ID, T> map = getMap(entityClass);
        return map.get(id);
    }

    static <T extends InMemoryEntity<ID>, ID> List<T> listAll(Class<T> entityClass) {
        Map<ID, T> map = getMap(entityClass);
        return map.values().stream().collect(Collectors.toList());
    }

    // Metoda za brisanje SVIH podataka (za resetiranje prototipa)
    static void clear() {
        storage.clear();
        counters.clear();
    }

    // Rekurzivna metoda za kaskadno spremanje/ažuriranje
    private static <T> void persistRecursive(T current, Set<Object> visited) {
        // Zaustavi rekurziju ako je objekt null ili je već posjećen (obrada ciklusa)
        if (current == null || visited.contains(current)) {
            return;
        }

        // Dodaj trenutni objekt u set posjećenih
        visited.add(current);

        // --- 1. Spremi (ili ažuriraj) TRENUTNU instancu entiteta (ako je entitet) ---
        // Provjeravamo je li objekt InMemoryEntity jer kaskadiramo samo entitete
        if (current instanceof InMemoryEntity) {
            InMemoryEntity entity = (InMemoryEntity) current;

            // Ako je novi entitet, generiraj ID
            if (entity.getId() == null) {
                // Oprez: Logika generiranja ID-a podržava samo Long
                if (entity.getClass().getGenericInterfaces().length > 0 &&
                    entity.getClass().getGenericInterfaces()[0].getTypeName().contains("Long")) {
                    entity.setId(nextId(entity.getClass())); // Generiraj i postavi ID
                } else {
                    // Ovdje biste trebali proširiti logiku za druge tipove ID-a (UUID i sl.)
                    throw new UnsupportedOperationException("ID generation for Long only. Extend logic for other types.");
                }
            }

            // Spremi trenutnu instancu entiteta u odgovarajuću Mapu
            Map<Object, InMemoryEntity> map = getMap((Class<InMemoryEntity>) entity.getClass());
            map.put(entity.getId(), entity);
        } else {
            // Ako objekt nije InMemoryEntity, nećemo ga spremati u Mapu
            // i nećemo kaskadirati DALJE od njega u ovom jednostavnom modelu
            return;
        }
        // ------------------------------------------------------------------------


        // --- 2. Prođi kroz polja entiteta i kaskadiraj na povezane entitete ---
        Class<?> currentClass = current.getClass();
        // Prolazimo kroz hijerarhiju klasa (uključujući roditeljske klase)
        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                try {
                    // Omogući pristup privatnim poljima
                    field.setAccessible(true);
                    // Dohvati vrijednost polja za trenutnu instancu
                    Object value = field.get(current);

                    // Preskoči ako je vrijednost null
                    if (value == null) continue;

                    if (value instanceof InMemoryEntity) {
                        // Ako polje referencira na drugu instancu InMemoryEntity (One-to-One, Many-to-One strana)
                        persistRecursive(value, visited); // Rekurzivno pozovi spremanje za taj entitet
                    } else if (value instanceof Collection) {
                        // Ako polje referencira na Kolekciju (List, Set, itd.)
                        Collection<?> collection = (Collection<?>) value;
                        for (Object item : collection) {
                            if (item instanceof InMemoryEntity) {
                                // Ako je element u kolekciji instanca InMemoryEntity (One-to-Many, Many-to-Many strana)
                                // Napomena: Ovo ne provjerava generički tip kolekcije, samo elemente unutar nje.
                                // Također ne obrađuje Map, Array ili ugniježđene kolekcije.
                                persistRecursive(item, visited); // Rekurzivno pozovi spremanje za element kolekcije
                            }
                            // Ovdje možete dodati provjere za druge tipove (npr. Map, Array) ako su relevantni za vaše veze
                        }
                    }
                    // Ignoriraj primitivne tipove, Stringove, datume i objekte koji nisu entiteti

                } catch (Exception e) {
                    // Ovdje se može uhvatiti IllegalAccessException, ReflectionException itd.
                    // Za prototip, printanje stack tracea može biti dovoljno za debugging
                    e.printStackTrace();
                    // Ovisno o želji, možete baciti RuntimeException ili samo logirati
                }
            }
            currentClass = currentClass.getSuperclass(); // Idi na roditeljsku klasu da obradi njena polja
        }
    }

    // Rekurzivna metoda za kaskadno brisanje
    private static <T> boolean deleteRecursive(T current, Set<Object> visited) {
        // Zaustavi rekurziju ako je objekt null ili je već posjećen
        if (current == null || visited.contains(current)) {
            return false; // Vraća false ako nije ništa obrisano u ovom koraku
        }
        visited.add(current);

        boolean deletedCurrent = false;
        // --- 1. Obriši TRENUTNU instancu entiteta iz Mape (ako je entitet) ---
        if (current instanceof InMemoryEntity) {
            InMemoryEntity entity = (InMemoryEntity) current;
            // Entitet mora imati ID da bi se mogao brisati
            if (entity.getId() != null) {
                Map<Object, InMemoryEntity> map = (Map<Object, InMemoryEntity>) getMap(entity.getClass());
                deletedCurrent = map.remove(entity.getId()) != null; // Briše iz Mape; true ako je nešto obrisano
            }
        } else {
            // Ne kaskadiramo brisanje s ne-entiteta
            return false;
        }
        // --------------------------------------------------------------------


        // --- 2. Prođi kroz polja entiteta i kaskadiraj brisanje ---
        Class<?> currentClass = current.getClass();
        while (currentClass != null && currentClass != Object.class) {
            for (Field field : currentClass.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(current);

                    if (value == null) continue;

                    if (value instanceof InMemoryEntity) {
                        // Ako polje referencira na drugu instancu InMemoryEntity
                        // Oprez: Logika brisanja kaskada je kompleksna u pravim ORM-ovima (orphanRemoval, itd.)
                        // Ova implementacija simulira CascadeType.REMOVE (briše povezane)
                        deleteRecursive(value, visited); // Rekurzivno pozovi brisanje za taj entitet
                    } else if (value instanceof Collection) {
                        // Ako polje referencira na Kolekciju
                        Collection<?> collection = (Collection<?>) value;
                        for (Object item : collection) {
                            if (item instanceof InMemoryEntity) {
                                // Ako je element u kolekciji instanca InMemoryEntity
                                deleteRecursive(item, visited); // Rekurzivno pozovi brisanje za element kolekcije
                            }
                        }
                    }
                    // Ignoriraj druge tipove

                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle exceptions
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        // --------------------------------------------------------
        return deletedCurrent; // Vraća je li prvotni entitet u ovom koraku obrisan
    }

}
