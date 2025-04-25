package com.escuela.colegio.Repository;

import com.escuela.colegio.Model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {


    //DEPRECATED (antes era una clase)

    //Imports de los que depende esto:
    //import com.escuela.colegio.Model.Holiday;
    //import org.springframework.beans.factory.annotation.Autowired;
    //import org.springframework.jdbc.core.BeanPropertyRowMapper;
    //import org.springframework.jdbc.core.JdbcTemplate;
//    private final JdbcTemplate jdbcTemplate;

    //JDBC Template example:
//    @Autowired
//    public HolidayRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Holiday> findAllHolidays() {
//        String sql = "SELECT * FROM holidays";
//        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
//        return jdbcTemplate.query(sql, rowMapper);
//    }
}
