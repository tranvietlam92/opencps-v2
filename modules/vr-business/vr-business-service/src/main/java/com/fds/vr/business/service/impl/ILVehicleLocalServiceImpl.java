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

import com.fds.vr.business.model.ILVehicle;
import com.fds.vr.business.service.base.ILVehicleLocalServiceBaseImpl;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the il vehicle local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.ILVehicleLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author LamTV
 * @see ILVehicleLocalServiceBaseImpl
 * @see com.fds.vr.business.service.ILVehicleLocalServiceUtil
 */
@ProviderType
public class ILVehicleLocalServiceImpl extends ILVehicleLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.ILVehicleLocalServiceUtil} to access the il vehicle local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(ILVehicleLocalServiceImpl.class);
	
	public ILVehicle createVehicle(JSONObject jsonData, String issuingDispatchNo) {
		_log.info("LamTV_START ADD ILVehicle");
		Date now = new Date();

		long ilVehicleId = counterLocalService.increment(ILVehicle.class.getName());

		ILVehicle object = ilVehiclePersistence.create(ilVehicleId);

		/// Add audit fields
		object.setSyncDate(now);
//		_log.info("Vao KHONG syncDateList:"+object.getSyncDate());
		String registrationNumber = String.valueOf(jsonData.get("RegistrationNumber"));
		String registrationDriver = String.valueOf(jsonData.get("RegistrationDriver"));
		String vehicleModelCode = String.valueOf(jsonData.get("VehicleModelCode"));
		String vehicleModelName = String.valueOf(jsonData.get("VehicleModelName"));
		String capacity = String.valueOf(jsonData.get("Capacity"));
		String manufacturedYear = String.valueOf(jsonData.get("ManufacturedYear"));
		String vehicleExpImpGateCode = String.valueOf(jsonData.get("VehicleExpImpGateCode"));
		String vehicleExpImpGate = String.valueOf(jsonData.get("ExpImpGate"));
		String operationType = String.valueOf(jsonData.get("OperationType"));
		String passengerOrCargo = String.valueOf(jsonData.get("PassengerOrCargo"));
		String lienVanNo = String.valueOf(jsonData.get("LienVanNo"));

		// Add other fields
		object.setSequenceNo(GetterUtil.getInteger(String.valueOf(jsonData.get("SequenceNo"))));
//		object.setValidUntil(ConvertFormatDate.parseStringToDate(String.valueOf(jsonData.get("ValidUtil"))));
//		object.setValidUntil(ConvertFormatDate.parseStringToDate(String.valueOf(jsonData.get("ValidFrom"))));
		if (Validator.isNotNull(registrationNumber)) {
			object.setRegistrationNumber(registrationNumber);
		}
		if (Validator.isNotNull(registrationDriver)) {
			object.setRegistrationDriver(registrationDriver);
		}
		if (Validator.isNotNull(vehicleModelCode)) {
			object.setVehicleModelCode(vehicleModelCode);
		}
		if (Validator.isNotNull(vehicleModelName)) {
			object.setVehicleModelName(vehicleModelName);
		}
		if (Validator.isNotNull(capacity)) {
			object.setCapacity(capacity);
		}
		if (Validator.isNotNull(manufacturedYear)) {
			object.setManufacturedYear(manufacturedYear);
		}
		if (Validator.isNotNull(vehicleExpImpGateCode)) {
			object.setVehicleExpImpGateCode(vehicleExpImpGateCode);
		}
		if (Validator.isNotNull(vehicleExpImpGate)) {
			object.setVehicleExpImpGate(vehicleExpImpGate);
		}
		if (Validator.isNotNull(operationType)) {
			object.setOperationType(operationType);
		}
		if (Validator.isNotNull(passengerOrCargo)) {
			object.setPassengerOrCargo(passengerOrCargo);
		}
		if (Validator.isNotNull(lienVanNo)) {
			object.setLienVanNo(lienVanNo);
		}
		if (Validator.isNotNull(issuingDispatchNo)) {
			object.setIssuingDispatchNo(issuingDispatchNo);
		}

		return ilVehiclePersistence.update(object);
	}

	public List<ILVehicle> getVehicleList(String issuingDispatchNo) {
		return ilVehiclePersistence.findByF_ISSU_NO(issuingDispatchNo);
	}

}