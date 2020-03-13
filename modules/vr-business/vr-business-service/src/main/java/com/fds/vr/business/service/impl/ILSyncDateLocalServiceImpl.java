/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.vr.business.service.impl;

import java.util.Date;
import java.util.List;

import com.fds.vr.business.model.ILSyncDate;
import com.fds.vr.business.service.base.ILSyncDateLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the il sync date local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.ILSyncDateLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author LamTV
 * @see ILSyncDateLocalServiceBaseImpl
 * @see com.fds.vr.business.service.ILSyncDateLocalServiceUtil
 */
@ProviderType
public class ILSyncDateLocalServiceImpl extends ILSyncDateLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.ILSyncDateLocalServiceUtil} to access the il sync date local service.
	 */

	public Date getSyncDate() {
		Date syncDate = null;
		List<ILSyncDate> syncDateList = ilSyncDatePersistence.findAll();
		if (syncDateList != null && syncDateList.size() > 0) {
			syncDate = syncDateList.get(0).getSyncDate();
		}

		return syncDate;
	}

	public ILSyncDate updateSyncDate(Date modifiedDate) {

		ILSyncDate object = null;
		try {
			object = ilSyncDatePersistence.findByPrimaryKey(1);
			
			object.setSyncDate(modifiedDate);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ilSyncDatePersistence.update(object);
	}
}