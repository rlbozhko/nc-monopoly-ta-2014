package com.monopoly.database.dao;

import com.monopoly.database.PersistException;

import java.sql.SQLException;

public interface DaoConnect<Context> {
    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }
    public Context getConnection() throws SQLException;

    public GenericDao getDao(Context connection, Class dtoClass) throws PersistException;
}
