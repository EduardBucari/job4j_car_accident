package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentJdbcTemplate jdbc;

    public AccidentService(AccidentJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Accident> findAllAccidents() {
        return jdbc.findAllAccidents();
    }

    public void create(Accident accident) {
        jdbc.save(accident);
    }

    public Accident findById(int id) {
        return jdbc.findAccidentById(id);
    }
}