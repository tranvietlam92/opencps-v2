package com.fds.vr.business.action;

import java.util.Date;
import java.util.List;

import com.fds.vr.business.model.ILCertificate;
import com.fds.vr.business.model.ILVehicleCustomsBorderGuard;
import com.liferay.portal.kernel.json.JSONObject;

public interface ILCertificateActions {

	public JSONObject getDocAcceptList(String keywords, String serviceCode, String govAgencyCode, String routeCode,
			String fromDate, String toDate, int start, int limit);

	public ILCertificate getDetailCert(long certificateId);

	public List<ILVehicleCustomsBorderGuard> getBorderGuardList(String registrationNumber);

	public ILVehicleCustomsBorderGuard addCustomBorderGuard(String registrationNumber, String expImpGateType,
			String expImpGate, String driverName, String driverLicenceNo, Date regDate);

	public ILCertificate getCertByValidFrom(String serviceCode, String applicant, String regSearch);

	public JSONObject getCertByApplicant(String serviceCode, String applicant);

}
