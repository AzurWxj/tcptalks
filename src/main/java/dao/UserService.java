package dao;

import model.User;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private static final String
            addUser="INSERT INTO users(id,username,password) VALUES(?,?,?)",
            queryUserNumber="SELECT id FROM users WHERE username=? AND password=?";

    private static DBConnectionManager connectionManager=DBConnectionManager.getInstance();

    private static Logger logger=Logger.getLogger(UserService.class);

    public static int registerUser(User user){
        int status;
        String password=user.getPassword();
        if(password==null||password.trim().equals("")){
            status=StatusMapper.CORRUPTED_RECORD;
        }else {
            try (
                    Connection connection = connectionManager.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {
                preparedStatement.setString(1, user.getId());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, password);
                status = preparedStatement.executeUpdate() > 0 ? StatusMapper.SUCCESS : StatusMapper.UNKNOWN_STATUS;
            } catch (SQLException e) {
                logger.fatal(e.getLocalizedMessage());
                status = e.getErrorCode();
            }
        }
        return status;
    }

    public static int validateUser(User user){
        int status=StatusMapper.UNKNOWN_STATUS;
        try(
                Connection connection=connectionManager.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(queryUserNumber))
        {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                status=StatusMapper.SUCCESS;
                user.setId(resultSet.getString(1));
            }
        } catch (SQLException e) {
            logger.fatal(e.getLocalizedMessage());
            status = e.getErrorCode();
        }
        return status;
    }

}
