package org.rapidoid.sql;

/*
 * #%L
 * rapidoid-sql
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Desc;
import org.rapidoid.annotation.Since;
import org.rapidoid.meta.MetaModel;
import org.rapidoid.sql.dsl.SQLDeleteDSL;
import org.rapidoid.sql.dsl.SQLInsertDSL;
import org.rapidoid.sql.dsl.SQLUpdateDSL;

import java.util.List;
import java.util.Map;

@Authors("Nikolche Mihajlovski")
@Since("6.0.0")
public interface SQLMetaModel extends MetaModel {

	@Desc("Equivalent to INSERT INTO [table] ...")
	SQLInsertDSL insert(String table);


	@Desc("Equivalent to INSERT INTO [table] ...")
	SQLInsertDSL insert(Class<?> entityType);


	@Desc("Equivalent to UPDATE [table] ...")
	SQLUpdateDSL update(String table);


	@Desc("Equivalent to DELETE FROM [table] ...")
	SQLDeleteDSL delete(String table);


	@Desc("Equivalent to DROP TABLE [table]")
	void dropTable(String table);


	@Desc("Equivalent to DROP TABLE [table] IF EXISTS")
	void dropTableIfExists(String table);


	@Desc("Equivalent to CREATE TABLE [table] ([columns])")
	void createTable(String table, Map<String, String> columns);

	String tableOf(Class<?> resultType);

	void execute(String sql, Object... args);

	void tryToExecute(String sql, Object... args);

	<T> List<T> query(Class<T> resultType, String sql, Object... args);

	List<Map<String, Object>> query(String sql, Object... args);
}
