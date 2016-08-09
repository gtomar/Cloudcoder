// CloudCoder - a web-based pedagogical programming environment
// Copyright (C) 2011-2013, Jaime Spacco <jspacco@knox.edu>
// Copyright (C) 2011-2013, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

//Gaurav

package org.cloudcoder.app.server.persist.txn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.cloudcoder.app.server.persist.util.AbstractDatabaseRunnableNoAuthException;
import org.cloudcoder.app.shared.model.TestCase;

/**
 * Transaction to get all {@link TestCase}s for given {@link Problem}.
 */
public class GetProblemIDForProblem extends AbstractDatabaseRunnableNoAuthException<Long> {
	private final String problemName;

	/**
	 * Constructor.
	 * 
	 * @param problemId unique id of the {@link Problem}
	 */
	public GetProblemIDForProblem(String problemName) {
		this.problemName = problemName;
	}

	@Override
	public Long run(Connection conn) throws SQLException {
		return Queries.doGetProblemIDForProblem(conn, problemName, this);
	}

	@Override
	public String getDescription() {
		return " getting problem id for problem";
	}
}