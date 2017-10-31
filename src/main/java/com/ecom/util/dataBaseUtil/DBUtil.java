package com.ecom.util.dataBaseUtil;

import java.sql.*;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
public class DBUtil {
    // 三属性四方法（3-4）
    private Connection conn = null; // 创建程序与数据库的连接对象
    private PreparedStatement pstmt = null; // 发送SQL语句，并将处理结果返回程序端
    private ResultSet rs = null; // 接受数据库端返回的结果集

    // 方法1：获取数据库的连接
    private void getConnection() {
        try {
            // 步骤1：加载数据库连接驱动-reflect反射（CGLIB）
            Class.forName(DataBaseConfig.DATABASE_DRIVER).newInstance();
            // 步骤2：设置数据库的连接参数
            String url = "jdbc:mysql://" + DataBaseConfig.IP_ADDRESS + ":" + DataBaseConfig.PORT + "/"
                    + DataBaseConfig.DATABASE + "?user=" + DataBaseConfig.ACCOUNT + "&password="
                    + DataBaseConfig.PASSWORD + "&Unicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";

//            // 步骤1：加载数据库连接驱动-reflect反射（CGLIB）
//            Class.forName(DataBaseConfig.getDatabaseDriver()).newInstance();
//            // 步骤2：设置数据库的连接参数
//            String url = "jdbc:mysql://" + DataBaseConfig.getIpAddress() + ":" + DataBaseConfig.getPort() + "/"
//                    + DataBaseConfig.getDatabase() + "?user=" + DataBaseConfig.getAccount() + "&password="
//                    + DataBaseConfig.getPassword() + "&Unicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";

            this.conn = DriverManager.getConnection(url);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 方法2：关闭数据库连接对象
    public void closeConnection() {
        try {
            if (this.rs != null) {
                this.rs.close();
            }
            if (this.pstmt != null) {
                this.pstmt.close();
            }
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 方法3：专门用于执行select读操作的方法
    public ResultSet execQuery(final String strSQL, final Object... params) {
        // 步骤1：调用自定义的数据库连接方法
        this.getConnection();
        // 步骤2：在控制台输出即将执行的SQL语句，便于开发测试
        // System.out.println("SQ::> " + strSQL);
        try {
            // 步骤3：实例化pstmt对象
            this.pstmt = this.conn.prepareStatement(strSQL);
            // 步骤4：使用for循环遍历参数数组并进行赋值
            for (int i = 0; i < params.length; i++) {
                this.pstmt.setObject(i + 1, params[i]);
            }
            // 步骤5：发送SQL语句，并将结果集带回程序端
            this.rs = this.pstmt.executeQuery();
            // 步骤6：将获取的结果集返回
            return this.rs;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    // 方法4：专门用于执行写操作的方法
    public int execOther(final String strSQL, final Object... params) {
        // 步骤1：调用自定义的数据库连接方法
        this.getConnection();
        // 步骤2：在控制台输出即将执行的SQL语句，便于开发测试
        // System.out.println("SQ::> " + strSQL);
        try {
            // 步骤3：实例化pstmt对象
            this.pstmt = this.conn.prepareStatement(strSQL);
            // 步骤4：使用for循环遍历参数数组并进行赋值
            for (int i = 0; i < params.length; i++) {
                this.pstmt.setObject(i + 1, params[i]);
            }
            // 步骤5：发送SQL语句，并将影响行数带回程序端
            int affectedRows = this.pstmt.executeUpdate();
            // 步骤6：将获取的结果集返回
            return affectedRows;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
    }
}
