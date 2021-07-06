package calculadora.horas.InfrastructureR.Adapters.Out;
import calculadora.horas.ApplicationR.Domain.report;
import calculadora.horas.ApplicationR.Ports.Out.reportRepository;
import calculadora.horas.Common.nonEmptyString;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Collection;

@Repository
public class sqlReportRepository implements reportRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<report> reportRowMapper = (resultSet, i) -> result(resultSet);

    public sqlReportRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private report result(ResultSet resultSet) throws SQLException {
        return new report(
                new nonEmptyString(resultSet.getString("identificationT")),
                new nonEmptyString(resultSet.getString("identificationS")),
                new nonEmptyString(resultSet.getString("startDate")),
                new nonEmptyString(resultSet.getString("finalDate"))
        );
    }

    @Override
    public void save(report report) {
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
    public Collection<report> getById(nonEmptyString Calculate) {
        String sql = "SELECT * FROM report WHERE identificationT=?";
        PreparedStatementSetter preparedStatementSetter = preparedStatement -> {
            preparedStatement.setString(1, Calculate.getValue());
        };
        return jdbcTemplate.query(sql, preparedStatementSetter, reportRowMapper);
    }
}
