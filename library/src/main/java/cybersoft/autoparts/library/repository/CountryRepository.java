package cybersoft.autoparts.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cybersoft.autoparts.library.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
}