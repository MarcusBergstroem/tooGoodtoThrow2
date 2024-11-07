package com.example.TooGoodToThrow.Repository;
import com.example.TooGoodToThrow.Model.Madvare;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MadvareRepo {
    // Autowired opretter et objekt - "template" tager fat i databasen
    private final JdbcTemplate template;

    public MadvareRepo(JdbcTemplate template) {
        this.template = template;
    }

    public List<Madvare> fetchAll(){
        String sql = "select * from madvare";
        // Tager en række fra resultatsættet og opretter et objekt med det og laver en liste med de objekter
        RowMapper<Madvare> rowMapper = new BeanPropertyRowMapper<>(Madvare.class);
        return template.query(sql, rowMapper);
    }

    public void addMadvare(Madvare m){
        // Finder sidst anvendte ID
        String sql = "SELECT MAX(id) FROM madvare";
        int highest = template.queryForObject(sql, Integer.class);

        sql = "INSERT INTO madvare (id, madnavn, pris, antal, udlobsdato, adresse, virksomhed) VALUES (?, ?, ?, ?, ?, ?, ?)";

        template.update(sql, highest+1, m.getMadnavn(), m.getPris(), m.getAntal(), m.getUdlobsdato(), m.getAdresse(), m.getVirksomhed());
    }

    public Madvare findMadvareById(int id){
        String sql = "SELECT * FROM madvare WHERE id = ?";
        RowMapper<Madvare> rowMapper = new BeanPropertyRowMapper<>(Madvare.class);
        return template.queryForObject(sql, rowMapper, id);
    }


    public boolean deleteMadvare(int id){
        String sql = "DELETE FROM madvare WHERE id = ?";
        return template.update(sql, id) > 0;
    }

    /*public void updateMadvare(Madvare m){
        String sql = "UPDATE madvare SET firstname = ?, lastname = ? WHERE id = ?";
        template.update(sql, p.getFirstname(), p.getLastname(), p.getId());
    }
    */

}
