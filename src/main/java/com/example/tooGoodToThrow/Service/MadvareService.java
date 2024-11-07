package com.example.tooGoodToThrow.Service;

import com.example.tooGoodToThrow.Model.Madvare;
import com.example.tooGoodToThrow.Repository.MadvareRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MadvareService {
    @Autowired
    MadvareRepo madvareRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Madvare> fetchAll(){
        return madvareRepo.fetchAll(); // Returnerer en liste af personer fra repository
    }

    public void addMadvare(Madvare m){
        madvareRepo.addMadvare(m); // Tilføjer personen til databasen
    }

    public Madvare findMadvareById(int id){
        return madvareRepo.findMadvareById(id); // Finder en person ved ID
    }

    public Boolean deleteMadvare(int id){
        boolean deleted = madvareRepo.deleteMadvare(id);
        if (deleted){
            renumberMadvarer();
        }
        return deleted;

    }

    /*public void updateMadvare(Madvare m){
        madvareRepo.updateMadvare(m); // Opdaterer en person i databasen
    }
    */

    private void renumberMadvarer(){
        String fetchSql = "SELECT * FROM madvare";
        List<Madvare> madvarer = jdbcTemplate.query(fetchSql, new BeanPropertyRowMapper<>(Madvare.class));

        int counter = 1;

        int nyttal = 4;

        // Loop through each person and update the desired field
        for (Madvare madvare : madvarer) {
            // Opdater det ønskede felt
            String updateSql = "UPDATE madvare SET id = ? WHERE id = ?";
            jdbcTemplate.update(updateSql, counter, madvare.getId());
            counter += 1;
            System.out.println("Opdateret " + madvare.getMadnavn());
        }
    }



}
