package com.fds.vr.util;

import java.util.ArrayList;
import java.util.List;

import com.fds.vr.business.action.util.ConvertFormatDate;
import com.fds.vr.business.model.ILCertificate;
import com.fds.vr.business.model.ILVehicleCustomsBorderGuard;
import com.fds.vr.ilcertificate.model.ILCBGuardModel;
import com.fds.vr.ilcertificate.model.ILCertificateModel;

public class ILCertificateUtils {

	public static List<ILCertificateModel> mappingToResultCertificate (List<ILCertificate> certList) {

		if (certList != null && certList.size() > 0) {
			List<ILCertificateModel> certModelList = new ArrayList<ILCertificateModel>();
			for (ILCertificate certInfo : certList) {
				ILCertificateModel model = mappingToDetailCertificate(certInfo);

				certModelList.add(model);
			}
			return certModelList;
		} else {
			return null;
		}
	}

	public static ILCertificateModel mappingToDetailCertificate (ILCertificate certInfo) {

		ILCertificateModel model = new ILCertificateModel();
		model.setCertificateId(certInfo.getId());
		model.setDossierId(certInfo.getDossierId());
		model.setDossierFileId(certInfo.getDossierFileId());
		model.setDossierTemplateNo(certInfo.getDossierTemplateNo());
		model.setDossierPartNo(certInfo.getDossierPartNo());
		model.setFileTemplateNo(certInfo.getFileTemplateNo());
		model.setReferenceUid(certInfo.getReferenceUid());
		model.setServiceCode(certInfo.getServiceCode());
		model.setValidFrom(
				ConvertFormatDate.convertDateToString(certInfo.getValidFrom(), ConvertFormatDate._NORMAL_DATE));
		model.setValidUntil(ConvertFormatDate.convertDateToString(certInfo.getValidUntil(),
				ConvertFormatDate._NORMAL_DATE));
		model.setTransportOperation(certInfo.getTransportOperation());
		model.setApplicantIdNo(certInfo.getApplicantIdNo());
		model.setApplicantName(certInfo.getApplicantName());
		model.setApplicantAddress(certInfo.getApplicantAddress());
		model.setApplicantFax(certInfo.getApplicantFax());
		model.setApplicantTel(certInfo.getApplicantTel());
		model.setApplicantEmail(certInfo.getApplicantEmail());
		model.setApplicantWebsite(certInfo.getApplicantWebsite());
		model.setSignName(certInfo.getSignName());
		model.setSignTitle(certInfo.getSignTitle());
		model.setSignPlace(certInfo.getSignPlace());
		model.setSignDate(
			ConvertFormatDate.convertDateToString(certInfo.getSignDate(), ConvertFormatDate._NORMAL_DATE));
		model.setLicenceName(certInfo.getLicenceName());
		model.setLicenceNo(certInfo.getLicenceNo());
		model.setGovAgencyCode(certInfo.getGovAgencyCode());
		model.setGovAgencyName(certInfo.getGovAgencyName());
		model.setRegistrationNumber(certInfo.getRegistrationNumber());
		model.setDepartureFrom(certInfo.getDepartureFrom());
		model.setDepartureTo(certInfo.getDepartureTo());
		model.setRoutes(certInfo.getRoutes());
		model.setNumberOfLot(certInfo.getNumberOfLot());
		model.setManufacturedYear(certInfo.getManufacturedYear());
		model.setTechnicalData(certInfo.getTechnicalData());
		model.setTrademarkCode(certInfo.getTrademarkCode());
		model.setTrademarkName(certInfo.getTrademarkName());
		model.setModel(certInfo.getModel());
		model.setVehicleType(certInfo.getVehicleType());
		model.setVehicleColor(certInfo.getVehicleColor());
		model.setEngineNumber(certInfo.getEngineNumber());
		model.setChassisNumber(certInfo.getChassisNumber());
		model.setExpiredDate(
				ConvertFormatDate.convertDateToString(certInfo.getExpiredDate(), ConvertFormatDate._NORMAL_DATE));
		model.setBorderGate(certInfo.getBorderGate());
		model.setTravelingArea(certInfo.getTravelingArea());
		model.setDestination(certInfo.getDestination());
		model.setExtendedUntil(
				ConvertFormatDate.convertDateToString(certInfo.getExtendedUntil(), ConvertFormatDate._NORMAL_DATE));
		model.setNoticesOfExtension(certInfo.getNoticesOfExtension());
		model.setPurposeOfTheTrip(certInfo.getPurposeOfTheTrip());
		model.setIssuingDispatchNo(certInfo.getIssuingDispatchNo());
		model.setOfficialDispatchNo(certInfo.getOfficialDispatchNo());
		model.setOfficialDispatchDate(ConvertFormatDate.convertDateToString(certInfo.getOfficialDispatchDate(),
				ConvertFormatDate._NORMAL_DATE));
		model.setFromParkingLot(certInfo.getFromParkingLot());
		model.setFromProvinceCode(certInfo.getFromProvinceCode());
		model.setFromProvinceName(certInfo.getFromProvinceName());
		model.setToParkingLot(certInfo.getToParkingLot());
		model.setToProvinceCode(certInfo.getToProvinceCode());
		model.setToProvinceName(certInfo.getToProvinceName());
		model.setDistance(certInfo.getDistance());
		model.setRouteDescription(certInfo.getRouteDescription());
		model.setExpImpGateCode(certInfo.getExpImpGateCode());
		model.setExpImpGate(certInfo.getExpImpGate());
		model.setItemNo(certInfo.getItemNo());
		model.setCircularNo(certInfo.getCircularNo());
		model.setCircularDate(
				ConvertFormatDate.convertDateToString(certInfo.getCircularDate(), ConvertFormatDate._NORMAL_DATE));
		model.setNumberOfVehicle(certInfo.getNumberOfVehicle());
		model.setToVNAuthority(certInfo.getToVNAuthority());
		model.setToLAOAuthority(certInfo.getToLAOAuthority());
		model.setAtParkingLot(certInfo.getAtParkingLot());
		model.setAtProvinceName(certInfo.getAtProvinceName());
		model.setApplicantContactName(certInfo.getApplicantContactName());
		model.setPassenger(certInfo.getPassenger());
		model.setCapacity(certInfo.getCapacity());
		model.setPurpose(certInfo.getPurpose());
		model.setGoodsType(certInfo.getGoodsType());
		model.setStops(certInfo.getStops());
		model.setLicenceType(certInfo.getLicenceType());
		

		return model;
	}

	public static List<ILCBGuardModel> mappingToResultCBGuard (List<ILVehicleCustomsBorderGuard> cbGuardList) {

		if (cbGuardList != null && cbGuardList.size() > 0) {
			List<ILCBGuardModel> cbGuardModelList = new ArrayList<ILCBGuardModel>();
			for (ILVehicleCustomsBorderGuard cbGuardInfo : cbGuardList) {
				ILCBGuardModel model = mappingToDetailCBGuard(cbGuardInfo);

				cbGuardModelList.add(model);
			}
			return cbGuardModelList;
		} else {
			return null;
		}
	}

	public static ILCBGuardModel mappingToDetailCBGuard (ILVehicleCustomsBorderGuard cbGuardInfo) {

			ILCBGuardModel model = new ILCBGuardModel();
//			model.setRegistrationNumber(cbGuardInfo.getRegistrationNumber());
			model.setExpImpGateType(cbGuardInfo.getExpImpGateType());
			model.setExpImpGate(cbGuardInfo.getExpImpGate());
			model.setDriverName(cbGuardInfo.getDriverName());
			model.setDriverLicenceNo(cbGuardInfo.getDriverLicenceNo());
			model.setRegistrationDate(ConvertFormatDate.convertDateToString(cbGuardInfo.getRegistrationDate(),
					ConvertFormatDate._NORMAL_DATE));
			model.setCustomsDepartureDate(ConvertFormatDate.convertDateToString(cbGuardInfo.getCustomsDepartureDate(),
					ConvertFormatDate._NORMAL_DATE));
			model.setCustomsArrivalDate(ConvertFormatDate.convertDateToString(cbGuardInfo.getCustomsArrivalDate(),
					ConvertFormatDate._NORMAL_DATE));
			model.setBorderGuardsDepartureDate(ConvertFormatDate
					.convertDateToString(cbGuardInfo.getBorderGuardsDepartureDate(), ConvertFormatDate._NORMAL_DATE));
			model.setBorderGuardsArrivalDate(ConvertFormatDate
					.convertDateToString(cbGuardInfo.getBorderGuardsArrivalDate(), ConvertFormatDate._NORMAL_DATE));

			return model;
	}
}
