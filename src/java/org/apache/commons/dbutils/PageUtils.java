
package org.apache.commons.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.handlers.ScalarHandler;

/**
 * page utils 
 *
 * @author yangwm in Dec 01, 2009 10:02:21 PM
 */
class PageUtils {

	/** Constant identifying the oracle jdbc driver */
	public static String ORACLE_JDBC_DRIVER = "Oracle JDBC driver";
	/** Constant identifying the mysql jdbc driver */
	public static String MYSQL_JDBC_DRIVER = "MySQL-AB JDBC Driver";
    /** Constant identifying the PostgreSQL jdbc driver */
    public static String PGSQL_JDBC_DRIVER = "PostgreSQL Native Driver";
    /** Constant identifying the Sybase jdbc driver */
    public static String SYBASE_JDBC_DRIVER = "jConnect (TM) for JDBC (TM)";
    
    /**
     * The total row move to current page number. 
     * 
     * @param totalRow The result set cursor move to page number.
     * @param pageArg The current page number and The page size of one page 
     * @throws SQLException
     */
    public static void initPageArg(PageArgument pageArg)
            throws SQLException {
		//PrintUtils.println(">>>pageArg=" + pageArg);
        if (pageArg.getTotalRow() < 0) {
			pageArg.setTotalRow(0);
        }
		if (pageArg.getCurPage() <= 0) {
            pageArg.setCurPage(1);
        }
        if (pageArg.getPageSize() <= 0) {
			pageArg.setPageSize(1);
        }

		int tempPage = (int) (pageArg.getTotalRow() / pageArg.getPageSize());
		if (pageArg.getTotalRow() % pageArg.getPageSize() > 0 || tempPage == 0) {
			tempPage += 1;
		}
		pageArg.setTotalPage(tempPage);

		if (pageArg.getCurPage() > pageArg.getTotalPage()) {
			pageArg.setCurPage(pageArg.getTotalPage());
		}
		//PrintUtils.println("<<<pageArg=" + pageArg);
    }


	// common db implements -------------------------------------------------------

    /**
     * Page the result set cursor.
     * Set PageArgument of totalRow with result set of count.
     * 
     * @param rs The result set cursor move to current page number. 
     * @param pageArg The PageArgument set totalRow by result set.
     * @throws SQLException
     */
    public static void page(ResultSet rs, PageArgument pageArg) 
            throws SQLException {
		pageArg.setTotalRow(getCount(rs));
		initPageArg(pageArg);
        moveToPageNo(rs, pageArg.getCurPage(), pageArg.getPageSize());
    }

    /**
     * Get the result set of count.
     * 
     * @param rs The result set of count.
     * @return  The total row of result set.
     * @throws SQLException
     */
    public static int getCount(ResultSet rs) 
			throws SQLException {
        rs.last();
        int count = rs.getRow();
        rs.beforeFirst();
        return count;
    }

    /**
     * The result set cursor move to current page number. 
     * 
     * @param rs The result set cursor move to page number.
     * @param pageNo The current page number. 
     * @param pageSize The page size of one page.
     * @throws SQLException
     */
    public static void moveToPageNo(ResultSet rs, int pageNo, int pageSize)
            throws SQLException {
		if (pageNo > 1) {
            int row = (pageNo - 1) * pageSize;
            rs.absolute(row);
        }
    }


	// specific db implements -------------------------------------------------------

    /**
     * Page the result set cursor.
     * Set PageArgument of totalRow with result set of count.
     * 
     * @param sql The sql with generate page sql get total row move to current page number. 
	 * @param conn The Connection of database.
     * @param pageArg The PageArgument set totalRow by result set.
	 * @param params The replacement parameters.
     * @throws SQLException
     */
    public static void page(String sql, Connection conn, PageArgument pageArg, Object... params) 
            throws SQLException {
		pageArg.setTotalRow(getCount(generateCountSql(sql, conn), conn, params));
		initPageArg(pageArg);
        //moveToPageNo(rs, pageArg.getCurPage(), pageArg.getPageSize()); // not needed 
    }
	
    /**
     * Generate count sql by origin sql.  
     * 
     * @param sql The origin sql.
	 * @param conn The Connection of database.
     * @throws SQLException
     */
    public static String generateCountSql(String sql, Connection conn) 
			throws SQLException {
		String driverName = conn.getMetaData().getDriverName();
		PrintUtils.println(">>>generateCountSql sql=" + sql + ", driverName=" + driverName);
        String tempSql = sql.toLowerCase();

        int tempIndex = 0;
        int beginIndex = 0;
        tempIndex = tempSql.indexOf("select");
        if (tempIndex != -1) {
            beginIndex = tempIndex + 6;
        }
        int endIndex = 0;
        tempIndex = tempSql.indexOf("from");
        if (tempIndex != -1) {
            endIndex = tempIndex;
        }
        int lengthIndex = sql.length();
		tempIndex = tempSql.indexOf("order");
		if (tempIndex != -1) {
			lengthIndex = tempIndex;
		}
        //PrintUtils.println(beginIndex + ", " + endIndex + ", " + lengthIndex);
        
        StringBuilder result = new StringBuilder();
        result.append(sql.substring(0, beginIndex))
        .append(" count(*) ")
        .append(sql.substring(endIndex, lengthIndex));
        
		PrintUtils.println("<<<generateCountSql result.toString()=" + result.toString());
        return result.toString();
    }

    /**
     * Get the sql's result set of count.
     * 
     * @param sql sql's result set of count.
	 * @param conn The Connection of database.
	 * @param params The replacement parameters.
     * @return  The total row of sql's result set.
     * @throws SQLException
     */
    public static int getCount(String sql, Connection conn, Object... params) 
		throws SQLException {
        int count = ( (Number) new QueryRunner().query(conn, sql, new ScalarHandler(), params) )
			.intValue();
        return count;
    }

	
    /**
     * Is specific driver implements.  
     * 
     * @param conn The Connection of database.
	 * @return  if the driver Oracle, MySQL, PostgreSQL is true, other is false
     * @throws SQLException
     */
	public static boolean isSpecificDriver(Connection conn) 
			throws SQLException {
		String driverName = conn.getMetaData().getDriverName();
		boolean result = ORACLE_JDBC_DRIVER.equals(driverName) 
			|| MYSQL_JDBC_DRIVER.equals(driverName)
			|| PGSQL_JDBC_DRIVER.equals(driverName)
			//|| SYBASE_JDBC_DRIVER.equals(driverName)
			;
		return result;
	}

    /**
     * Generate page sql by origin sql.
     * 
     * @param sql The origin sql.
	 * @param pageArg The PageArgument for page sql.
	 * @param conn The Connection of database.
     * @throws SQLException
     */  
    public static String generatePageSql(String sql, Connection conn, PageArgument pageArg) 
			throws SQLException {
		String driverName = conn.getMetaData().getDriverName();
		PrintUtils.println(">>>generatePageSql sql=" + sql + ", driverName=" + driverName 
			+ ", pageArg=" + pageArg);
		String pageSql = sql;
		if (ORACLE_JDBC_DRIVER.equals(driverName)) {
			pageSql = generatePageSqlForOracle(sql, pageArg);
		} else if (MYSQL_JDBC_DRIVER.equals(driverName)) {
			pageSql = generatePageSqlForMysql(sql, pageArg);
		} else if (PGSQL_JDBC_DRIVER.equals(driverName)) {
            pageSql = generatePageSqlForPgsql(sql, pageArg);
        }/* else if (Sybase_JDBC_DRIVER.equals(driverName)) {
            pageSql = generatePageSqlForSybase(sql, pageArg);
        }*/

		PrintUtils.println("<<<generatePageSql pageSql=" + pageSql);
        return pageSql;
    }
    

    /**
     * Generate page sql by origin sql for Oracle.   
     * 
     * @param sql The origin sql.
	 * @param pageArg The PageArgument for page sql.
     * @throws SQLException
     */	    
    private static String generatePageSqlForOracle(String sql, PageArgument pageArg) {
        StringBuilder result = new StringBuilder();
        result.append("select ").append("*")
        .append("\nfrom (select ").append("temp.*").append(", rownum row_num ")
        .append("\n      from (").append(sql).append(") temp where rownum <= ")
			.append(pageArg.getCurPage()* pageArg.getPageSize())
        .append("\n) where row_num > ").append((pageArg.getCurPage() - 1) * pageArg.getPageSize());

        return result.toString();
    }
    
    /**
     * Generate page sql by origin sql for MySQL.   
     * 
     * @param sql The origin sql.
	 * @param pageArg The PageArgument for page sql.
     * @throws SQLException
     */	    
    private static String generatePageSqlForMysql(String sql, PageArgument pageArg) {
        StringBuilder result = new StringBuilder();
        result.append(sql).append(" limit ").append((pageArg.getCurPage() - 1) * pageArg.getPageSize())
			.append(", ").append(pageArg.getPageSize());

        return result.toString();
    }
    
    /**
     * Generate page sql by origin sql for PostgreSQL.   
     * 
     * @param sql The origin sql.
	 * @param pageArg The PageArgument for page sql.
     * @throws SQLException
     */	    
    private static String generatePageSqlForPgsql(String sql, PageArgument pageArg) {
        StringBuilder result = new StringBuilder();
        result.append(sql).append(" limit ").append(pageArg.getPageSize()).append(" offset ")
			.append((pageArg.getCurPage() - 1) * pageArg.getPageSize());

        return result.toString();
    }
    
    /**
     * Generate page sql by origin sql for Sybase.   
     * 
     * @param sql The origin sql.
	 * @param pageArg The PageArgument for page sql.
     * @throws SQLException
     */	    
    private static String generatePageSqlForSybase(String sql, PageArgument pageArg) {
        StringBuilder result = new StringBuilder();
        result.append("set rowcount ").append(15)
		.append("\n").append(sql)
		.append("\nset rowcount ").append(0);

        return result.toString();
    }

}
