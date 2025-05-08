package com.escuela.colegio.repository;

import com.escuela.colegio.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);


    //DEPRECATED (antes era una clase)

    //Imports de los que depende esto:

    //import com.escuela.colegio.Model.Contact;
    //import com.escuela.colegio.rommapers.ContactRowMapper;
    //import org.springframework.beans.factory.annotation.Autowired;
    //import org.springframework.jdbc.core.JdbcTemplate;
    //import org.springframework.jdbc.core.PreparedStatementSetter;
    //
    //import java.sql.PreparedStatement;
    //import java.sql.SQLException;
    //import java.sql.Timestamp;
    //import java.time.LocalDateTime;
    //import java.util.List;


    //JDBC Template example:
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public ContactRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//
//    public int saveContactMsg(Contact contact) {
//        String sql = "INSERT INTO contact_msg (NAME,MOBILE_NUM,EMAIL,SUBJECT,MESSAGE,STATUS," +
//                "CREATED_AT,CREATED_BY) VALUES (?,?,?,?,?,?,?,?)";
//        return jdbcTemplate.update(sql, contact.getName(), contact.getMobileNum(),
//                contact.getEmail(), contact.getSubject(), contact.getMessage(),
//                contact.getStatus(), contact.getCreatedAt(), contact.getCreatedBy());
//    }
//
//    public List<Contact> findMsgsWithStatus(String status) {
//        String sql = "SELECT * FROM contact_msg WHERE STATUS = ?";
//        return jdbcTemplate.query(sql, preparedStatement ->
//                preparedStatement.setString(1, status), new ContactRowMapper());
//    }
//
//    public int updateMsgStatus(int contactId, String status, String updatedBy) {
//        String sql = "UPDATE contact_msg SET STATUS = ?, UPDATED_BY = ?,UPDATED_AT =? WHERE CONTACT_ID = ?";
//        return jdbcTemplate.update(sql, new PreparedStatementSetter() {
//            public void setValues(PreparedStatement preparedStatement) throws SQLException {
//                preparedStatement.setString(1, status);
//                preparedStatement.setString(2, updatedBy);
//                preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//                preparedStatement.setInt(4, contactId);
//            }
//        });
//    }

}
