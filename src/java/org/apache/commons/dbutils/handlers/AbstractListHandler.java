/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.dbutils.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.PageArgument;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * Abstract class that simplify development of <code>ResultSetHandler</code>
 * classes that convert <code>ResultSet</code> into <code>List</code>.
 *
 * @see org.apache.commons.dbutils.ResultSetHandler
 */
public abstract class AbstractListHandler implements ResultSetHandler {
    /**
     * Whole <code>ResultSet</code> handler. It produce <code>List</code> as
     * result. To convert individual rows into Java objects it uses
     * <code>handleRow(ResultSet)</code> method.
     *
     * @see #handleRow(ResultSet)
     */
    public Object handle(ResultSet rs) throws SQLException {
        List rows = new ArrayList();
        while (rs.next()) {
            rows.add(this.handleRow(rs));
        }
        return rows;
    }
    
    /**
     * Row handler. Method converts current row into some Java object.
     *
     * @param rs <code>ResultSet</code> to process.
     * @return row processing result
     * @throws SQLException error occurs
     */
    protected abstract Object handleRow(ResultSet rs) throws SQLException;

	// ------------------ improve ---------------------

    /**
     * Whole <code>ResultSet</code> handler. It produce <code>List</code> as
     * result. To convert individual rows into Java objects it uses
     * <code>handleRow(ResultSet)</code> method.
     *
     * @see #handleRow(ResultSet)
     */
    public Object handle(ResultSet rs, PageArgument pageArg) throws SQLException {
        List rows = new ArrayList();
		int i = 0;
        while (rs.next() && (i++) < pageArg.getPageSize()) {
            rows.add(this.handleRow(rs));
        }
        return rows;
    }

}
