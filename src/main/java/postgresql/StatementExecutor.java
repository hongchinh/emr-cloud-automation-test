package postgresql;

import io.qameta.allure.Step;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementExecutor {

    @Step
    public void unlockPatient(String ptId) throws SQLException {
        Statement statement = DatabaseConnectionManager.getConnection().createStatement();
        String selectSql = String.format("DELETE FROM \"lock_inf\" WHERE \"pt_id\"=%s", ptId);
        statement.execute(selectSql);
    }
    @Step
    public String getPtNum(String ptName) throws SQLException {
        Statement statement = DatabaseConnectionManager.getConnection().createStatement();
        String selectSql = String.format("SELECT \"pt_num\" FROM \"pt_inf\" WHERE \"kana_name\" = '%s'", ptName);
        ResultSet resultSet = statement.executeQuery(selectSql);
        String x = "";
        while (resultSet.next()) {
            x = resultSet.getString(1);
        };
        return x;
    }
}
