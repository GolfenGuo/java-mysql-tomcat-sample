package daocloud.dbconnect.service;

import daocloud.dbconnect.model.NameDirectory;
import daocloud.dbconnect.model.NameDirectoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * @since 11/22/2015
 */
public class NameDirectoryServiceMysql implements NameDirectoryService {
    private static final Logger log = LoggerFactory.getLogger(NameDirectoryServiceMysql.class);

    @Autowired
    private DataSource dataSource;

    private final NameDirectoryMapper mapper = new NameDirectoryMapper();

    @Override
    public List<NameDirectory> getAllRows() {
        return getJdbcTemplate().query("SELECT * FROM NameDirectory", new Object[]{}, mapper);
    }

    @Override
    public void addNameDirectory(NameDirectory nd) {
        getJdbcTemplate().update("INSERT INTO NameDirectory (firstName, lastName) VALUES (?,?)",
                new Object[]{nd.getFirstName(), nd.getLastName()});
    }

    @Override
    public void deleteNameDirectoryById(Long id) {
        getJdbcTemplate().update("DELETE FROM NameDirectory WHERE id = ?",
                new Object[]{id});
    }

    @Override
    public void deleteAll() {
        getJdbcTemplate().update("DELETE FROM NameDirectory",
                new Object[]{});
    }

    protected JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
