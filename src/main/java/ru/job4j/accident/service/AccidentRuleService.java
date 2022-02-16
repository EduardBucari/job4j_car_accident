package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;

@Service
public class AccidentRuleService {
    private final AccidentJdbcTemplate jdbc;

    public AccidentRuleService(AccidentJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Rule> findAllRules() {
        return jdbc.findAllRules();
    }

    public Rule findRuleById(int id) {
        return jdbc.findRuleById(id);
    }

    public void addRules(Accident accident, String[] ids) {
        for (String id : ids) {
            accident.addRule(findRuleById(Integer.parseInt(id)));
        }
    }
}
