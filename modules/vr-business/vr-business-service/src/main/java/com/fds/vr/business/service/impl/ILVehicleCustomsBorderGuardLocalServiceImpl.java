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

import com.fds.vr.business.action.util.ConvertFormatDate;
import com.fds.vr.business.exception.NoSuchILVehicleCustomsBorderGuardException;
import com.fds.vr.business.model.ILVehicleCustomsBorderGuard;
import com.fds.vr.business.service.base.ILVehicleCustomsBorderGuardLocalServiceBaseImpl;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the il vehicle customs border guard local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.ILVehicleCustomsBorderGuardLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author LamTV
 * @see ILVehicleCustomsBorderGuardLocalServiceBaseImpl
 * @see com.fds.vr.business.service.ILVehicleCustomsBorderGuardLocalServiceUtil
 */
@ProviderType
public class ILVehicleCustomsBorderGuardLocalServiceImpl
	extends ILVehicleCustomsBorderGuardLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.ILVehicleCustomsBorderGuardLocalServiceUtil} to access the il vehicle customs border guard local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(ILVehicleCustomsBorderGuardLocalServiceImpl.class);

	public List<ILVehicleCustomsBorderGuard> getBorderGuardList(String registrationNumber){
			return ilVehicleCustomsBorderGuardPersistence.findByF_REG_NUMBER(registrationNumber);
	}

	public ILVehicleCustomsBorderGuard createCustomsBorderGuard(JSONObject tempGuardJSON, String registrationNumberForm) {
		_log.info("LamTV_START ADD ILVehicleCustomsBorderGuard");
		Date now = new Date();

		long ilCbGuardId = counterLocalService.increment(ILVehicleCustomsBorderGuard.class.getName());

		ILVehicleCustomsBorderGuard object = ilVehicleCustomsBorderGuardPersistence.create(ilCbGuardId);

		/// Add audit fields
		object.setSyncDate(now);
//		_log.info("Vao KHONG syncDateList:"+object.getSyncDate());
		String registrationNumber = String.valueOf(tempGuardJSON.get("RegistrationNumber"));

		// Add other fields
		object.setBorderGuardsDepartureDate(
				ConvertFormatDate.parseStringToDate(String.valueOf(tempGuardJSON.get("CustomsDepartureDate"))));
		object.setCustomsArrivalDate(
				ConvertFormatDate.parseStringToDate(String.valueOf(tempGuardJSON.get("CustomsArrivalDate"))));
		object.setBorderGuardsDepartureDate(
				ConvertFormatDate.parseStringToDate(String.valueOf(tempGuardJSON.get("BorderGuardsDepartureDate"))));
		object.setBorderGuardsArrivalDate(
				ConvertFormatDate.parseStringToDate(String.valueOf(tempGuardJSON.get("BorderGuardsArrivalDate"))));
		if (Validator.isNotNull(registrationNumber)) {
			object.setRegistrationNumber(registrationNumber);
		}

		return ilVehicleCustomsBorderGuardPersistence.update(object);

	}

	public ILVehicleCustomsBorderGuard addCustomBorderGuard(String registrationNumber, String expImpGateType,
			String expImpGate, String driverName, String driverLicenceNo, Date regDate) {
		_log.info("LamTV_START addCustomBorderGuard");
		Date now = new Date();

		long ilCbGuardId = counterLocalService.increment(ILVehicleCustomsBorderGuard.class.getName());
		ILVehicleCustomsBorderGuard object = ilVehicleCustomsBorderGuardPersistence.create(ilCbGuardId);

		// Add audit fields
		object.setSyncDate(now);
//		_log.info("Vao KHONG syncDateList:"+object.getSyncDate());
		if (Validator.isNotNull(registrationNumber)) {
			object.setRegistrationNumber(registrationNumber);
		}
		if (Validator.isNotNull(expImpGateType)) {
			object.setExpImpGateType(expImpGateType);
		}
		if (Validator.isNotNull(expImpGate)) {
			object.setExpImpGate(expImpGate);
		}
		if (Validator.isNotNull(driverName)) {
			object.setDriverName(driverName);
		}
		if (Validator.isNotNull(driverLicenceNo)) {
			object.setDriverLicenceNo(driverLicenceNo);
		}
		if (Validator.isNotNull(regDate)) {
			object.setRegistrationDate(regDate);
		}

		return ilVehicleCustomsBorderGuardPersistence.update(object);
	}

}