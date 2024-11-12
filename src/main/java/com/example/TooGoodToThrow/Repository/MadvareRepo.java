package com.example.TooGoodToThrow.Repository;
import com.example.TooGoodToThrow.Model.Madvare;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MadvareRepo {
    // Der oprettes et JdbcTemplate object som tager fat i databasen
    private final JdbcTemplate template;

    public MadvareRepo(JdbcTemplate template) {
        this.template = template;
    }

    public List<Madvare> fetchAll(){
        String sql = "select * from madvare";
        // Tager en række fra resultatsættet og opretter et objekt med det og laver en liste med de objekter
        RowMapper<Madvare> rowMapper = new BeanPropertyRowMapper<>(Madvare.class);
        return template.query(sql, rowMapper); //Her får man en liste af madvare retur
    }

    public void addMadvare(Madvare m){
        // Finder sidst anvendte ID
        String sql = "SELECT MAX(id) FROM madvare";
        int highest = template.queryForObject(sql, Integer.class);
        //sql quary som opretter en ny madvare i databasen
        sql = "INSERT INTO madvare (id, madnavn, pris, antal, udlobsdato, adresse, virksomhed) VALUES (?, ?, ?, ?, ?, ?, ?)";

        template.update(sql, highest+1, m.getMadnavn(), m.getPris(), m.getAntal(), m.getUdlobsdato(), m.getAdresse(), m.getVirksomhed());
    }
    //Finder en bestemt madvare
    public Madvare findMadvareById(int id){
        String sql = "SELECT * FROM madvare WHERE id = ?";
        RowMapper<Madvare> rowMapper = new BeanPropertyRowMapper<>(Madvare.class);
        return template.queryForObject(sql, rowMapper, id); //Her får man en enkelt madvare retur
    }
    public void deleteMadvare(int id){
        //Her findes antallet af den pågældende madvare
        String sql = "SELECT antal FROM madvare WHERE id = ?";
        int gammeltAntal = template.queryForObject(sql, new Object[]{id}, Integer.class);
        //Hvis der er mere end 1 reduceres antallet med 1
        if (gammeltAntal >1) {
            sql = "UPDATE madvare SET antal = ? WHERE id = ?";
            template.update(sql, gammeltAntal-1, id);
        }
        //Hvis der kun er 1 fjernes madvaren fra databasen
        else {
            sql = "DELETE FROM madvare WHERE id = ?";
            template.update(sql, id);
        }
    }
}
