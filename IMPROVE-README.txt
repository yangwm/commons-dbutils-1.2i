
INTRODUCTION:

This document contains the improve notes for the 1.2i version of Apache Commons Dbutils.

10. IMPROVEMENTS -- CaseInsensitiveHashMap's lowerCaseMap remove and realKey is key.toString().toLowerCase()
	"row_num >= 0 + 1" modify "row_num > 0"[Oracle] :
Jar changes
===========
Modified : org.apache.commons.dbutils.BasicRowProcessor
Modified : org.apache.commons.dbutils.PageUtils

Class changes
=============
org.apache.commons.dbutils.BasicRowProcessor
--------------------
Modified : private static class CaseInsensitiveHashMap extends HashMap

org.apache.commons.dbutils.PageUtils
--------------------
Modified : public static String generatePageSqlForOracle(String sql, PageArgument pageArg)



9. IMPROVEMENTS -- sub string before "order" by generateCountSql[Optimize Performance] 
   USABILITY -- public class modify to package class (2009-03-27) :
Jar changes
===========
Modified : org.apache.commons.dbutils.PageUtils
Added : org.apache.commons.dbutils.PrintUtils
Added : org.apache.commons.dbutils.ConfigUtil

Class changes
=============
org.apache.commons.dbutils.PageUtils
--------------------
Modified : public static String generateCountSql(String sql, Connection conn) 
			throws SQLException


8. BUG FIXES -- when where condition have super case match not true (2009-02-13) :
Jar changes
===========
Modified : org.apache.commons.dbutils.PageUtils


Class changes
=============
org.apache.commons.dbutils.PageUtils
--------------------
Modified : public static String generateCountSql(String sql, Connection conn) 
			throws SQLException


7. IMPROVEMENTS -- add PageUtils with result set paged, support PostgreSQL[Optimize Performance with PostgreSQL] (2009-02-04) :
Jar changes
===========
Modified : org.apache.commons.dbutils.PageUtils
Modified : org.apache.commons.dbutils.QueryRunner


Class changes
=============
org.apache.commons.dbutils.PageUtils
--------------------
Added : public static String PGSQL_JDBC_DRIVER = "PostgreSQL Native Driver"
Added : public static String SYBASE_JDBC_DRIVER = "jConnect (TM) for JDBC (TM)"
Modified public static String generateCountSql(String sql)
TO : public static String generateCountSql(String sql, Connection conn)
Added : public static boolean isSpecificDriver(Connection conn) 
			throws SQLException
Modified public static String generatePageSql(String sql, PageArgument pageArg, String driverName)
TO : public static String generatePageSql(String sql, Connection conn, PageArgument pageArg)
Added : public static String generatePageSqlForPgsql(String sql, PageArgument pageArg)
Added : public static String generatePageSqlForSybase(String sql, PageArgument pageArg)

org.apache.commons.dbutils.QueryRunner
--------------------
Modified : public Object query(Connection conn, String sql, ResultSetHandler rsh,
            PageArgument pageArg, Object... params) throws SQLException


6. IMPROVEMENTS -- add PageUtils with result set paged, support MySQL[Optimize Performance with MySQL] and Optimize generate page sql by origin sql for oracle  
   BUG FIXES -- when pageArg.getTotalRow() <= 0 then PageArgument{curPage=1, pageSize=5, totalRow=0, totalPage=1} (2009-01-23) :

Jar changes
===========
Modified : org.apache.commons.dbutils.PageUtils
Modified : org.apache.commons.dbutils.QueryRunner


Class changes
=============
org.apache.commons.dbutils.PageUtils
--------------------
Added : public static String MYSQL_JDBC_DRIVER = "MySQL-AB JDBC Driver"
Modified public static String generatePageSql(String sql, PageArgument pageArg, Object... params)
TO : public static String generatePageSql(String sql, PageArgument pageArg, String driverName)
Added : public static String generatePageSqlForOracle(String sql, PageArgument pageArg)
Added : public static String generatePageSqlForMysql(String sql, PageArgument pageArg)

org.apache.commons.dbutils.QueryRunner
--------------------
Modified : public Object query(Connection conn, String sql, ResultSetHandler rsh,
            PageArgument pageArg, Object... params) throws SQLException


5. IMPROVEMENTS -- format the pageSql, if (pageArg.getTotalRow() < 0) execute pageArg.setTotalRow(0) (2009-01-06) :
Jar changes
===========
Modified : org.apache.commons.dbutils.PageUtils


Class changes
=============
org.apache.commons.dbutils.PageUtils
--------------------
Modified : public static String generatePageSql(String sql, PageArgument pageArg, Object... params)
Modified public static void initPageArg(long totalRow, PageArgument pageArg)
            throws SQLException 
TO : public static void initPageArg(PageArgument pageArg)
            throws SQLException 


4. IMPROVEMENTS -- add PageUtils with result set paged, support common and Oracle[Optimize Performance with Oracle] (2009-12-01) :

Jar changes
===========
Modified : org.apache.commons.dbutils.QueryRunner
Added : org.apache.commons.dbutils.PageUtils


Class changes
=============
org.apache.commons.dbutils.QueryRunner
--------------------
Deleted : public static void initPageArg(ResultSet rs, PageArgument pageArg)
            throws SQLException 
Deleted : public static void page(ResultSet rs, PageArgument pageArg) 
            throws SQLException
Deleted : public static int getCount(ResultSet rs) 
			throws SQLException
Deleted : public static void moveToPageNo(ResultSet rs, int pageNo, int pageSize)
            throws SQLException


3. BUG FIXES -- when pageArg.getCurPage() > pageArg.getTotalRow() move ResultSet not true (2009-07-20) :

Jar changes
===========
Modified : org.apache.commons.dbutils.QueryRunner


Class changes
=============
org.apache.commons.dbutils.QueryRunner
--------------------
Modified : public void page(ResultSet rs, PageArgument pageArg) 
            throws SQLException
Modified : private void initPageArg(ResultSet rs, PageArgument pageArg)
            throws SQLException
Deleted : private void moveToPageNumber(ResultSet rs, int pageNumer, int pageSize, int totalRow)
            throws SQLException
Added : private void moveToPageNo(ResultSet rs, int pageNo, int pageSize)
            throws SQLException


2. BUG FIXES -- when (pageNumer - 1) * pageSize > totalRow move ResultSet not true (2009-06-04) :

Jar changes
===========
Modified : org.apache.commons.dbutils.QueryRunner
Modified : org.apache.commons.dbutils.PageArgument


Class changes
=============
org.apache.commons.dbutils.QueryRunner
--------------------
Modified : public void page(ResultSet rs, PageArgument pageArg) 
            throws SQLException
Modified private void moveToPageNumber(ResultSet rs, int pageNumer, int pageSize)
            throws SQLException
TO : private void moveToPageNumber(ResultSet rs, int pageNumer, int pageSize, int totalRow)
            throws SQLException

org.apache.commons.dbutils.PageArgument
--------------------
Added : public String toString();


1. IMPROVEMENTS -- add page function (2009-05-30) :

Jar changes
===========
Modified : org.apache.commons.dbutils.BasicRowProcessor
Modified : org.apache.commons.dbutils.BeanProcessor
Added : org.apache.commons.dbutils.PageArgment
Modified : org.apache.commons.dbutils.QueryRunner
Modified : org.apache.commons.dbutils.ResultSetHandler
Modified : org.apache.commons.dbutils.RowProcessor
Modified : org.apache.commons.dbutils.AbstractListHandler
Modified : org.apache.commons.dbutils.ArrayHandler
Modified : org.apache.commons.dbutils.BeanHandler
Modified : org.apache.commons.dbutils.BeanListHandler
Modified : org.apache.commons.dbutils.KeyedHandler
Modified : org.apache.commons.dbutils.MapHandler
Modified : org.apache.commons.dbutils.ScalarHandler


Class changes
=============
org.apache.commons.dbutils.BasicRowProcessor
--------------------
Added : public List toBeanList(ResultSet rs, Class type, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.BeanProcessor
--------------------
Added : public List toBeanList(ResultSet rs, Class type, PageArgument pageArg) throws SQLException
Modified : public List toBeanList(ResultSet rs, Class type) throws SQLException;

org.apache.commons.dbutils.QueryRunner
--------------------
Added : protected PreparedStatement prepareStatement(Connection conn, String sql,
            PageArgument pageArg) throws SQLException;
Added : public void page(ResultSet rs, PageArgument pageArg) 
            throws SQLException;
Added : private int getCount(ResultSet rs) throws SQLException;
Added : private void moveToPageNumber(ResultSet rs, int pageNumer, int pageSize);
Added : public Object query(Connection conn, String sql, ResultSetHandler rsh,
            PageArgument pageArg, Object... params) throws SQLException;
Added : public Object query(String sql, ResultSetHandler rsh, PageArgument pageArg,
            Object... params) throws SQLException;
Modified public Object query(Connection conn, String sql, ResultSetHandler rsh,
            Object... params) throws SQLException;
TO : public Object query(Connection conn, String sql, ResultSetHandler rsh,
            Object[] params) throws SQLException;
Modified public Object query(String sql, ResultSetHandler rsh, Object... params) 
			throws SQLException;
TO : public Object query(String sql, ResultSetHandler rsh, Object[] params) 
			throws SQLException;
Deleted : public Object query(Connection conn, String sql, ResultSetHandler rsh) 
			throws SQLException;
Deleted : public Object query(String sql, ResultSetHandler rsh) 
			throws SQLException;

org.apache.commons.dbutils.ResultSetHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.RowProcessor
--------------------
Added : public List toBeanList(ResultSet rs, Class type, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.AbstractListHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.ArrayHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.BeanHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.BeanListHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.KeyedHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.MapHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException

org.apache.commons.dbutils.ScalarHandler
--------------------
Added : public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException
