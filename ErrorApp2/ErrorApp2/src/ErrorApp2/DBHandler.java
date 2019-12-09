package ErrorApp2;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBHandler
{
    public static DBHandler instance = null;
    public static synchronized DBHandler getInstance() throws SQLException
    {
        if(instance == null) instance = new DBHandler();
        return instance;
    }
    private Connection connection;
    public DBHandler() throws SQLException
    {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/User/Desktop/ErrorApp2/ErrorsDB.sqlite");
    }
    public ArrayList<ErrErrors> getAllErrors()
    {
        try (Statement statement = this.connection.createStatement())
        {
            ArrayList<ErrErrors> errErrors = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Error_ID, Name, Date, Description, Type_ID FROM Errors");
            while(resultSet.next())
            {
                errErrors.add(new ErrErrors(resultSet.getInt("Error_ID"), resultSet.getString("Name"), resultSet.getString("Date"), resultSet.getString("Description"),
                        resultSet.getInt("Type_ID")));
            }
            return errErrors;
        }
        catch(SQLException e)
        {
            return new ArrayList<ErrErrors>();
        }
    }
    public List<ErrorsType> getAllErrorsTypes()
    {
        try(Statement statement = this.connection.createStatement())
        {
            List<ErrorsType> errorsTypes = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Type_ID, Name, Description FROM Types");
            while(resultSet.next())
            {
                errorsTypes.add(new ErrorsType(resultSet.getInt("Type_ID"), resultSet.getString("Name"), resultSet.getString("Description")));
            }
            return errorsTypes;
        }
        catch(SQLException e)
        {
            return Collections.emptyList();
        }
    }
    public List<ErrErrors> findErrorsyDate(String date)
    {
        try(Statement statement = this.connection.createStatement())
        {
            List<ErrErrors> errErrors = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Error_ID, Name, Date, Description, Type_ID FROM Errors where Date = '" + date + "'");
            while(resultSet.next())
            {
                errErrors.add(new ErrErrors(resultSet.getInt("Error_ID"), resultSet.getString("Name"), resultSet.getString("Date"), resultSet.getString("Description"),
                        resultSet.getInt("Type_ID")));
            }
            return errErrors;
        }
        catch (SQLException e)
        {
            return Collections.emptyList();
        }
    }
    public List<ErrErrors> findErrorsByDesc(String desc)
    {
        try(Statement statement = this.connection.createStatement())
        {
            List<ErrErrors> errErrors = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT Error_ID, Name, Date, Description, Type_ID FROM Errors where Description '" + desc + "'");
            while(resultSet.next())
            {
                errErrors.add(new ErrErrors(resultSet.getInt("Error_ID"), resultSet.getString("Name"), resultSet.getString("Date"), resultSet.getString("Description"),
                        resultSet.getInt("Type_ID")));
            }
            return errErrors;
        }
        catch(SQLException e)
        {
            return Collections.emptyList();
        }
    }
    public int findidbyType(String type)
    {
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Type_ID, Name, Description FROM Types");
            while(resultSet.next())
            {
                int id = resultSet.getInt("Type_ID");
                if(type.equals(resultSet.getString("Name"))) return id;
                resultSet.getString("Description");
            }
            return 333;
        }
        catch(SQLException e)
        {
            return 333;
        }
    }
    public String findTypeByID(int id)
    {
        String type = null;
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Type_ID, Name, Description FROM Types");
            while(resultSet.next())
            {
                if(id == resultSet.getInt("Type_ID")) return resultSet.getString("Name");
                resultSet.getString("Name");
                resultSet.getString("Description");
            }
            return type;
        }
        catch(SQLException e)
        {
            return type;
        }
    }
    public void addErrors(ErrErrors errErrors) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Errors(`Name`, `Date`, `Description`, `Type_ID`) VALUES(?, ?, ?, ?)"))
        {
            statement.setObject(1, errErrors.name);
            statement.setObject(2, errErrors.date);
            statement.setObject(3, errErrors.description);
            statement.setObject(4, errErrors.typeID);
            statement.execute();
        }
    }
    public void addType(ErrorsType errorsType) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Types(`Name`, `Description`) VALUES(?, ?)"))
        {
            statement.setObject(1, errorsType.name);
            statement.setObject(2, errorsType.Desc);
            statement.execute();
        }
    }
    public void deleteErrors(int id) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Errors WHERE Error_ID = ?"))
        {
            statement.setObject(1, id);
            statement.execute();
        }
    }
    public void deleteType(int id) throws SQLException
    {
        try(PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Types WHERE Type_ID = ?"))
        {
            statement.setObject(1, id);
            statement.execute();
        }
    }
    public void updErrors(int id, String name, String date, String desc, int typeID, String type) throws SQLException
    {
        String oldName, oldDate, oldDesc;
        int oldTypeID;
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Error_ID, Name, Date, Description, Type_ID FROM Errors where Error_ID = '" + id + "'");
            oldName = resultSet.getString("Name");
            oldDate = resultSet.getString("Date");
            oldDesc = resultSet.getString("Description");
            oldTypeID = resultSet.getInt("Type_ID");
        }
        try(PreparedStatement pstmt = this.connection.prepareStatement("UPDATE Errors SET Name = ?, Date = ?, Description = ?, Type_ID = ? WHERE Error_ID = ?"))
        {
            if(!name.equals("-")) pstmt.setString(1, name);
            else pstmt.setString(1, oldName);
            if(!date.equals("-")) pstmt.setString(2, date);
            else pstmt.setString(2, oldDate);
            if(!desc.equals("-")) pstmt.setString(3, desc);
            else pstmt.setString(3, oldDesc);
            if(!type.equals("-")) pstmt.setInt(4, typeID);
            else pstmt.setInt(4, oldTypeID);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        }
    }
    public void updType(int id, String name, String desc) throws SQLException
    {
        String oldName, oldDescription;
        try(Statement statement = this.connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery("SELECT Name, Description FROM Types where Type_ID = '" + id + "'");
            oldName = resultSet.getString("Name");
            oldDescription = resultSet.getString("Description");
        }
        try(PreparedStatement pstmt = this.connection.prepareStatement("UPDATE Types SET Name = ?, Description = ? WHERE Type_ID = ?"))
        {
            if(!name.equals("-")) pstmt.setString(1, name);
            else pstmt.setString(1, oldName);
            if(!desc.equals("-")) pstmt.setString(2, desc);
            else pstmt.setString(2, oldDescription);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        }
    }
    public boolean isErrorsEx(int id)
    {
        boolean isExist = false;
        List<ErrErrors> allErrErrors = getAllErrors();
        for(ErrErrors errErrors : allErrErrors)
        {
            if(errErrors.ID == id) isExist = true;
        }
        return isExist;
    }
    public boolean isTypeIDEx(int id)
    {
        boolean isExist = false;
        List<ErrorsType> allErrorsTypes = getAllErrorsTypes();
        for(ErrorsType errorsType : allErrorsTypes)
        {
            if(errorsType.id == id) isExist = true;
        }
        return isExist;
    }

    public ErrErrors findErrorsByID(int id)
    {
        ErrErrors errErrors = null;
        try(Statement statement = this.connection.createStatement())
        {

            ResultSet resultSet = statement.executeQuery("SELECT Error_ID, Name, Date, Description, Type_ID FROM Errors where Error_ID = '" + id + "'");
            while(resultSet.next())
            {
                errErrors = new ErrErrors(resultSet.getInt("Error_ID"), resultSet.getString("Name"), resultSet.getString("Date"), resultSet.getString("Description"),
                        resultSet.getInt("Type_ID"));
            }
            return errErrors;
        } catch (SQLException e) {
            e.printStackTrace();
            return errErrors;
        }
    }
}