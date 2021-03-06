package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Repository
 */
public class AccidentJdbcTemplate implements Store {

    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return jdbc.query("select id, name, text, address, accident_type_id "
               + "from accidents", (rs, row) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            accident.setAccidentType(findTypeById(rs.getInt("accident_type_id")));
            accident.setRules(findRulesByIdFromAccidentRule(accident.getId()));
            return accident;
        });
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() == 0) {
            create(accident);
        } else {
            update(accident);
        }
    }

    @Override
    public Accident findAccidentById(int id) {
        return jdbc.queryForObject("select id , name,text,address from accidents where id=?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    return accident;
                }, id);
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return jdbc.query("select id, name from accident_types",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                });
    }

    @Override
    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("select id, name from accident_types where id=?",
                (rs, row) -> {
                    AccidentType type = new AccidentType();
                    type.setId(rs.getInt("id"));
                    type.setName(rs.getString("name"));
                    return type;
                }, id);
    }

    @Override
    public Collection<Rule> findAllRules() {
        return jdbc.query("select id, name from rules",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    @Override
    public Rule findRuleById(int id) {
        return jdbc.queryForObject("select id, name from rules where id=?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                }, id);
    }

    private Set<Rule> findRulesByIdFromAccidentRule(int id) {
        List<Integer> rules = jdbc.query(

                "select rules_id from accidents_rules where accident_id = ?",
                (rs, row) -> rs.getInt("rules_id"), id);
        return rules.stream().map(this::findRuleById).collect(Collectors.toSet());
    }

    public void updateAccidentRule(Accident accident, int id) {
        for (Rule rule : accident.getRules()) {
            jdbc.update("insert into accidents_rules (accident_id, rules_id) values (?, ?)",
                    id,
                    rule.getId());
        }
    }

    private void create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accidents (name,text,address,accident_type_id) values (?,?,?,?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getAccidentType().getId());
            return ps;
        }, keyHolder);
        updateAccidentRule(accident, keyHolder.getKey().intValue());

    }


    private void update(Accident accident) {
        jdbc.update("update accidents set name=?,text=?, address=?, accident_type_id=? where id=?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getAccidentType().getId(),
                accident.getId());
        jdbc.update("delete from accidents_rules where accident_id=?",
                accident.getId());
        updateAccidentRule(accident, accident.getId());
    }
}
