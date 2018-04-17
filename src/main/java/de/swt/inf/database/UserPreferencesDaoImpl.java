package de.swt.inf.database;

import de.swt.inf.model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPreferencesDaoImpl implements UserPreferencesDao{

    private Connection connection;

    public UserPreferencesDaoImpl(Connection connection){this.connection =connection;}

    @Override
    public UserPreferences getUserPreferences(int id) {
        try{
            String query = "SELECT * FROM userpreferences WHERE USER_PREFERENCES_ID = " + id;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            UserPreferences tempUserPref = new UserPreferences();
            tempUserPref.setProvince(ProvinceEnum.valueOf(rs.getString("Province")));
            tempUserPref.setUniversity(UniversityEnum.valueOf(rs.getString("University")));
            tempUserPref.setCourse(CourseEnum.valueOf(rs.getString("Course")));
            tempUserPref.setSemester(rs.getInt("Semester"));
            tempUserPref.setMusic(MusicPreferencesEnum.valueOf(rs.getString("Music")));
            tempUserPref.setGender(GenderEnum.valueOf(rs.getString("Gender")));
            tempUserPref.setAge(rs.getInt("Age"));

            return tempUserPref;

        }catch (SQLException e){
            System.err.println(e);
        }
        return null;
    }

    @Override
    public boolean updateUserPreferences(UserPreferences userPreferences) {
       /* try{
            String query = "UPDATE userpreferences SET Province = '" + userPreferences.getProvince() + "', Color ='" + category.getColor() +
                    "' WHERE CATEGORY_ID = '" + category.getId() + "'";
            Statement statement = connection.createStatement();
            statement.executeQuery(query);
            return true;
        }catch (SQLException e){
            System.err.println(e);
        }*/
        return false;
    }

    @Override
    public boolean deleteUserPreferences(UserPreferences userPreferences) {
        return false;
    }

    @Override
    public boolean addUserPreferences(UserPreferences userPreferences) {
        return false;
    }
}
