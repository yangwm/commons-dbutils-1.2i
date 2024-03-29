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

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BaseTestCase;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * MapListHandlerTest
 */
public class MapListHandlerTest extends BaseTestCase {

	public void testHandle() throws SQLException {
		ResultSetHandler h = new MapListHandler();
		List results = (List) h.handle(this.rs);

		assertNotNull(results);
		assertEquals(ROWS, results.size());

		Iterator iter = results.iterator();
        Map row = null;
        assertTrue(iter.hasNext());
        row = (Map) iter.next();
        assertEquals(COLS, row.keySet().size());
        assertEquals("1", row.get("one"));
        assertEquals("2", row.get("TWO"));
        assertEquals("3", row.get("Three"));
            
        assertTrue(iter.hasNext());
        row = (Map) iter.next();
        assertEquals(COLS, row.keySet().size());

        assertEquals("4", row.get("one"));
        assertEquals("5", row.get("TWO"));
        assertEquals("6", row.get("Three"));
            
        assertFalse(iter.hasNext());
	}

	public void testEmptyResultSetHandle() throws SQLException {
		ResultSetHandler h = new MapListHandler();
		List results = (List) h.handle(this.emptyResultSet);

		assertNotNull(results);
		assertTrue(results.isEmpty());
	}

}
