import clase10.transacciones.db.H2Connection;
import clase10.transacciones.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;

public class Main {
    public static Logger LOGGER = Logger.getLogger(Main.class);
    public static String SQL_CREATE = "DROP TABLE IF EXISTS ODONTOLOGOS;" +
            "CREATE TABLE ODONTOLOGOS (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY," +
            "NOMBRE VARCHAR(50) NOT NULL," +
            "MATRICULA VARCHAR(100) NOT NULL);";
    public static String SQL_INSERT = "INSERT INTO ODONTOLOGOS VALUES(DEFAULT, ?, ?);";
    public static String SQL_SELECT_TODOS = "SELECT * FROM ODONTOLOGOS;";
    public static String SQL_UPDATE = "UPDATE ODONTOLOGOS SET NOMBRE=?, MATRICULA=? WHERE ID=?";

    public static void main(String[] args) {
        Connection connection = null;
        Odontologo odontologo = new Odontologo("COSME FULANITO","696969");
        Odontologo odontologoDesdeDB = null;

        try{
            connection = H2Connection.getConnection();

            Statement statement = connection.createStatement();
            //CREO BD
            statement.execute(SQL_CREATE);
            //INSERTO
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getMatricula());
            preparedStatement.executeUpdate();

            //MUESTRO
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_TODOS);
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String nombre = resultSet.getString(2);
                String matricula = resultSet.getString(3);
                odontologoDesdeDB = new Odontologo(id, nombre, matricula);
            }
            LOGGER.info("Datos de ODONTOLOGO: "+odontologoDesdeDB);


            //UPDATE
            connection.setAutoCommit(false);
            try{
                PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_UPDATE);
                preparedStatement1.setString(1,"Karen Dominguez");
                preparedStatement1.setString(2,"707070");
                preparedStatement1.setInt(3,odontologoDesdeDB.getId());
                preparedStatement1.executeUpdate();
                int numero = 10/0;
                connection.commit();
                odontologoDesdeDB.setNombre("Karen Dominguez");
                odontologoDesdeDB.setMatricula("707070");
            }catch (Exception e){
                connection.rollback();
                LOGGER.error(e.getMessage());
            }
            connection.setAutoCommit(true);
            LOGGER.info("Datos de ODONTOLOGO: "+odontologoDesdeDB);


        }catch (Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}