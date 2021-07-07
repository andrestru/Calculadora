package calculadora.horas.infrastructureR.adapters.out;
import calculadora.horas.applicationR.domain.Report;
import calculadora.horas.applicationR.ports.out.ReportRepository;
import calculadora.horas.common.NonEmptyString;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;

@Repository
public class SqlReportRepository implements ReportRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Report> reportRowMapper = (resultSet, i) -> result(resultSet);

    public SqlReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Report result(ResultSet resultSet) throws SQLException {
        return new Report(
                new NonEmptyString(resultSet.getString("identificationT")),
                new NonEmptyString(resultSet.getString("identificationS")),
                new NonEmptyString(resultSet.getString("startDate")),
                new NonEmptyString(resultSet.getString("finalDate"))
        );
    }

    @Override
    public void save(Report report) {
        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO report(identificationT, identificationS, startDate, finalDate) VALUES(?,?,?,?)"
            );
            preparedStatement.setString(1, report.getIdentificationT().getValue());
            preparedStatement.setString(2, report.getIdentificationS().getValue());
            preparedStatement.setString(3, report.getStartDate().getValue());
            preparedStatement.setString(4, report.getFinalDate().getValue());
            return preparedStatement;
        });
    }

    @Override
    public Collection<Report> getById(NonEmptyString Calculate) {
        String sql = "SELECT * FROM report WHERE identificationT=?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, Calculate.getValue());
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, reportRowMapper);
    }
}
