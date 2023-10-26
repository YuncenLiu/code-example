package com.liuyuncen.calendar;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.calendar
 * @author: Xiang想
 * @createTime: 2023-10-26  14:22
 * @description: TODO
 * @version: 1.0
 */
public class analyzeCalendar {

    public static DataSource getLocalSqliteDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:/Users/xiang/Desktop/Calendar.sqlitedb");
        return dataSource;
    }

    public static DataSource getMySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.58.10:3306/calendar?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JdbcTemplate sqlite = new JdbcTemplate(getLocalSqliteDataSource());

        String sql = "SELECT  " +
                " i.rowid as rowid,  " +
                " c.title as title,  " +
                " i.summary as summary,  " +
                " datetime( i.start_date, 'unixepoch', 'localtime' ) as startDt, " +
                " datetime( i.end_date, 'unixepoch', 'localtime' ) as endDt, " +
                " datetime( i.last_modified, 'unixepoch', 'localtime' ) as modifyDt, " +
                " i.description as description, " +
                " i.url as url " +
                "FROM  " +
                " CalendarItem i  " +
                " INNER JOIN Calendar c ON i.calendar_id = c.ROWID";

        List<Cal> calendars = new ArrayList<>();
        List<Map<String, Object>> maps = sqlite.queryForList(sql);
        for (Map<String, Object> map : maps) {
            Set<String> set2 = map.keySet();
            Cal calendar = new Cal();
            for (String key : set2) {
                String value = String.valueOf(map.get(key));
                Calendar cal = Calendar.getInstance();
                switch (key) {
                    case "rowid":
                        calendar.setRowId(Integer.valueOf(value));
                        break;
                    case "title":
                        calendar.setTitle(value);
                        break;
                    case "summary":
                        calendar.setSummary(value);
                        break;
                    case "startDt":

                        cal.setTime(sim.parse(value));
                        cal.add(Calendar.YEAR, 31);
                        cal.add(Calendar.DATE, 1);
                        calendar.setStartDate(cal.getTime());
                        break;
                    case "endDt":
                        cal.setTime(sim.parse(value));
                        cal.add(Calendar.DATE, 1);
                        cal.add(Calendar.YEAR, 31);
                        calendar.setEndDate(cal.getTime());
                        break;
                    case "modifyDt":
                        cal.setTime(sim.parse(value));
                        cal.add(Calendar.YEAR, 31);
                        cal.add(Calendar.DATE, 1);
                        calendar.setModifyDate(cal.getTime());
                        break;
                    case "description":
                        calendar.setDescribe(value);
                        break;
                    case "url":
                        calendar.setUrl(value);
                        break;
                    default:
                }
            }
            calendars.add(calendar);
        }


        JdbcTemplate mysql = new JdbcTemplate(getMySQLDataSource());

        mysql.execute("truncate table calendar");
        System.out.println("清空历史数据");

        String insertSql = "INSERT INTO calendar (   " +
                "   row_id,   " +
                "   title,   " +
                "   summary,   " +
                "   start_date,   " +
                "   end_date,   " +
                "   modify_date,   " +
                "   description,   " +
                "   url    " +
                ")   " +
                "VALUES   " +
                "   (?,?,?,?,?,?,?,?);";

        mysql.batchUpdate(insertSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Cal cal = calendars.get(i);
                ps.setInt(1,cal.getRowId());
                ps.setString(2,cal.getTitle());
                ps.setString(3,cal.getSummary());
                ps.setTimestamp(4, new java.sql.Timestamp(cal.getStartDate().getTime()));
                ps.setTimestamp(5,new java.sql.Timestamp(cal.getEndDate().getTime()));
                ps.setTimestamp(6, new java.sql.Timestamp(cal.getModifyDate().getTime()));
                ps.setString(7,cal.getDescribe());
                ps.setString(8,cal.getUrl());
            }

            @Override
            public int getBatchSize() {
                return calendars.size();
            }
        });

        System.out.println("执行完成！");

    }
}
