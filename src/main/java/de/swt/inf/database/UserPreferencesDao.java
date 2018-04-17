package de.swt.inf.database;

import de.swt.inf.model.UserPreferences;

public interface UserPreferencesDao {

    public UserPreferences getUserPreferences(int id);

    public boolean updateUserPreferences(UserPreferences userPreferences);

    public boolean deleteUserPreferences(UserPreferences userPreferences);

    public boolean addUserPreferences(UserPreferences userPreferences);
}
