// CloudCoder - a web-based pedagogical programming environment
// Copyright (C) 2011-2014, Jaime Spacco <jspacco@knox.edu>
// Copyright (C) 2011-2014, David H. Hovemeyer <david.hovemeyer@gmail.com>
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

package org.cloudcoder.app.server.rpc;

import java.net.MalformedURLException;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import org.cloudcoder.app.client.model.ChangeList;
import org.cloudcoder.app.client.rpc.RPC;
import org.cloudcoder.app.server.persist.Database;
import org.cloudcoder.app.shared.model.Change;
import org.cloudcoder.app.shared.model.ChangeType;
import org.cloudcoder.app.shared.model.Problem;
import org.cloudcoder.app.shared.model.User;
import org.json.JSONObject;

/**
 * @author gtomar
 *
 */
public class ChatSocketCallback implements IOCallback
{

	int userID;
	Integer problemID;
	boolean happened;
	ChatSocketCallback(int i, Integer integer)
	{
		this.userID = i;
		this.problemID = integer;
		happened = false;
	}
	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {
		
		System.out.println("I am here: " + event + "#" + (String) args[1]);
		// TODO Auto-generated method stub
		if(event.equals("updatemethodstub"))
		{
			System.out.println("method stub: "+ (String) args[1]);
			Long Id = Database.getInstance().getProblemIDForProblem((String) args[1]);
			Problem problem = Database.getInstance().getProblem((int) (long) Id);
			
			Change fullTextChange = new Change(
					ChangeType.STUB_TEXT,
					0, 0, 0, 0,
					System.currentTimeMillis(),
					userID,
					problemID,
					problem.getSkeleton());
			Change [] changelist;
			changelist = new Change[1];
			changelist[0] = fullTextChange;

			//Database.getInstance().storeChanges(changelist);
			happened = true;
		}
		
	}

	@Override
	public void onConnect() {
		// TODO Auto-generated method stub
		System.out.println("");
	}

	@Override
	public void onDisconnect() {
		// TODO Auto-generated method stub
		System.out.println("");
	}

	@Override
	public void onError(SocketIOException arg0) {
		// TODO Auto-generated method stub

		System.out.println("an Error occurred...");
		
	}

	@Override
	public void onMessage(String arg0, IOAcknowledge arg1) {
		// TODO Auto-generated method stub
		System.out.println("");
	}

	@Override
	public void onMessage(JSONObject arg0, IOAcknowledge arg1) {
		// TODO Auto-generated method stub
		System.out.println("");
	}
	
}	

