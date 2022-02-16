package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import java.util.*;

@Service
public class AccidentTypeService {
    private final AccidentJdbcTemplate jdbc;

    public AccidentTypeService(AccidentJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<AccidentType> accidentTypes() {
        return jdbc.findAllTypes();
    }

    public AccidentType findTypeById(int id) {
        return jdbc.findTypeById(id);
    }

    public List<AccidentType> listForEdit(int id) {
        List<AccidentType> list = new ArrayList<>(accidentTypes());
        Collections.swap(list, 0, id - 1);
        return list;
    }

    public void setType(Accident accident) {
        accident.setAccidentType(jdbc.findTypeById(accident.getAccidentType().getId()));
    }
}
