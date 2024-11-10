package com.biblioteca.bibliotecaElo;

import java.sql.Connection;
import java.sql.SQLException;

public class teste {

    public boolean isConnected(Connection connection) {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}