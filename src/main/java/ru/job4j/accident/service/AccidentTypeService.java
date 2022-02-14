package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;

import java.util.*;

@Service
public class AccidentTypeService {
    private final AccidentMem mem;

    public AccidentTypeService(AccidentMem mem) {
        this.mem = mem;
    }


    public Collection<AccidentType> accidentTypes() {
        return mem.findAllTypes();
    }

    public AccidentType findTypeById(int id) {
        return mem.findTypeById(id);
    }

    public List<AccidentType> listForEdit(int id) {
        List<AccidentType> list = new ArrayList<>(accidentTypes());
        Collections.swap(list, 0, id - 1);
        return list;
    }

    public void setType(Accident accident) {
        accident.setAccidentType(mem.findTypeById(accident.getAccidentType().getId()));
    }
}
