package com.example.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

import static java.lang.Class.forName;

public class DbPool {

    private final int POOL_SIZE = 10;

    private ArrayBlockingQueue<Connection> threadPool = new ArrayBlockingQueue<>(POOL_SIZE);

    private static DbPool instance;

    //initialize the pool(Singleton class)
    private  DbPool() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        for( int i = 0 ; i < POOL_SIZE ; i++ ) {

            threadPool.offer(
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/test",
                            "sudharsan",
                            "mysql123"
                    )
            );

        }

    }

    //function to get the instance of the thread
    public static synchronized DbPool getInstance() throws SQLException, ClassNotFoundException {

        if( instance == null )
            instance = new DbPool();

        return instance;

    }

    //thread to get connection
    public Connection getConnection() throws InterruptedException {

        return threadPool.take();

    }

    //To release thread
    public void release( Connection conn) {

        threadPool.offer( conn );

    }

    public void closeAll() {

        for (Connection conn : threadPool) {

            try {

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException e) {

                e.printStackTrace();

            }

        }

    }


    public PooledConnection getPooledConnection() throws InterruptedException {

        Connection conn = threadPool.take(); // get connection from pool
        return new PooledConnection(conn, this); // wrap it

    }


    // to make this pool connection autoclosable

    public class PooledConnection implements AutoCloseable {

        private final Connection conn;
        private final DbPool pool;

        public PooledConnection(Connection conn, DbPool pool) {
            this.conn = conn;
            this.pool = pool;
        }

        public Connection getConnection() {
            return conn;
        }

        @Override
        public void close() {

            pool.release(conn);

        }
    }


}
