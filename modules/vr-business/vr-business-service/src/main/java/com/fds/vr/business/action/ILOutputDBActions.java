package com.fds.vr.business.action;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;

public interface ILOutputDBActions {

	public boolean processOutputDB() throws ParseException, SearchException, JSONException;
}
