package com.fds.vr.business.action.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.fds.vr.business.action.ILOutputDBActions;
import com.fds.vr.business.model.ILCertificate;
import com.fds.vr.business.model.ILVehicle;
import com.fds.vr.business.model.ILVehicleCustomsBorderGuard;
import com.fds.vr.business.service.ILCertificateLocalServiceUtil;
import com.fds.vr.business.service.ILSyncDateLocalServiceUtil;
import com.fds.vr.business.service.ILVehicleCustomsBorderGuardLocalServiceUtil;
import com.fds.vr.business.service.ILVehicleLocalServiceUtil;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ILOutputDBActionsImpl implements ILOutputDBActions {

	private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	private Log _log = LogFactoryUtil.getLog(ILOutputDBActionsImpl.class);

	@Override
	public boolean processOutputDB() throws ParseException, SearchException, JSONException {

		Date syncDate = ILSyncDateLocalServiceUtil.getSyncDate();

//		_log.info("synsDate: "+syncDate);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
		String strSyncDate = sdf.format(syncDate);

		List<Deliverable> deliverableList = DeliverableLocalServiceUtil.findDeliverableByDateAndState(strSyncDate, 2);

		if (deliverableList != null && deliverableList.size() > 0) {
//			List<String> formDataList = new ArrayList<String>();
//			List<Date> modifiedDateList = new ArrayList<Date>();
			String formData = StringPool.BLANK;
			Date modifiedDate = null;
			DossierFile dossierFile = null;
			Dossier dossier = null;
			long dossierFileId = 0;
//			Registration registration = null;
			for (Deliverable deliverable : deliverableList) {
				_log.info("Length deliverable: "+deliverableList.size());
//				String applicantIdNo = deliverable.getApplicantIdNo();
				String deliverableCode = deliverable.getDeliverableCode();
				String dossierTemplateNo = StringPool.BLANK;
				String dossierPartNo = StringPool.BLANK;
				String fileTemplateNo = StringPool.BLANK;
				String referenceUid = StringPool.BLANK;
//				if (Validator.isNotNull(applicantIdNo)) {
//					registration = RegistrationLocalServiceUtil.getByApplicantIdNo(applicantIdNo);
//				}
				if (Validator.isNotNull(deliverableCode)) {
					dossierFile = DossierFileLocalServiceUtil.getByDeliverableCode(deliverableCode);
					if (dossierFile != null) {
						dossierFileId = dossierFile.getDossierFileId();
						dossierTemplateNo = dossierFile.getDossierTemplateNo();
						dossierPartNo = dossierFile.getDossierPartNo();
						fileTemplateNo = dossierFile.getFileTemplateNo();
						referenceUid = dossierFile.getReferenceUid();
						dossier = DossierLocalServiceUtil.fetchDossier(dossierFile.getDossierId());
					}
				}
				

//				_log.info("registration: "+registration);
//				_log.info("dossierFile: "+dossierFile);
				formData = deliverable.getFormData();
				_log.info("formData: "+formData);
				modifiedDate = deliverable.getModifiedDate();
				if (Validator.isNotNull(formData)) {
//					LinkedHashMap<String, String> mapValues = ConvertJONObjectUtils.getKeyValuesMap(formData);
					JSONObject formJson = JSONFactoryUtil.createJSONObject(formData);
					_log.info("formJson: "+formJson);
					if (formJson != null) {
						outputDBAction(formJson, modifiedDate, dossier, dossierFileId, dossierTemplateNo, dossierPartNo,
								fileTemplateNo, referenceUid);
					}
				}
			}
		}

		return true;
	}

	private void outputDBAction(JSONObject formJsonData, Date modifiedDate, Dossier dossier, long dossierFileId,
			String dossierTemplateNo, String dossierPartNo, String fileTemplateNo, String referenceUid) {
		_log.info("dossierFileId: "+dossierFileId);
		
		// Check condition dossier had exits
		boolean flag = false;
		if (dossierFileId != 0) {
			List<ILCertificate> certificateList = ILCertificateLocalServiceUtil.getAllCertificate();
			if (certificateList != null && certificateList.size() > 0) {
				long dossierIdCert = 0;
				for (ILCertificate certInfo : certificateList) {
					if (certInfo != null) {
						dossierIdCert = certInfo.getDossierFileId();
						if (dossierFileId == dossierIdCert) {
							flag = true;
							break;
						}
					}
				}
			}
		}

		if (flag) {
			_log.info("flag: "+flag);
			// update table ILCertificate
			ILCertificate ilCertificate = ILCertificateLocalServiceUtil.updateCertificate(formJsonData, dossierFileId);
			// Delete all record table IL_Vehicle and IL_Vehicle_CustomsBorderGuard with dossierFileId
			if (ilCertificate != null) {
				String issuingDispatchNo = ilCertificate.getIssuingDispatchNo();
				String registrationNumber = ilCertificate.getRegistrationNumber();
				if (Validator.isNotNull(registrationNumber)) {
					List<ILVehicleCustomsBorderGuard> cbGuardList = ILVehicleCustomsBorderGuardLocalServiceUtil.getBorderGuardList(registrationNumber);
					if (cbGuardList != null && cbGuardList.size() > 0) {
						for (ILVehicleCustomsBorderGuard cbGuard : cbGuardList) {
							ILVehicleCustomsBorderGuardLocalServiceUtil.deleteILVehicleCustomsBorderGuard(cbGuard);
						}
					}
				}

				if (Validator.isNotNull(issuingDispatchNo)) {
					List<ILVehicle> vehicleList = ILVehicleLocalServiceUtil.getVehicleList(issuingDispatchNo);
					if (vehicleList != null && vehicleList.size() > 0) {
						for (ILVehicle vehicle : vehicleList) {
							ILVehicleLocalServiceUtil.deleteILVehicle(vehicle);
						}
					}
				}
				
			}
		} else {
			// update table ILCertificate
			ILCertificate ilCertificate = ILCertificateLocalServiceUtil.addCertificate(formJsonData, dossier, dossierFileId, dossierTemplateNo, dossierPartNo,
					fileTemplateNo, referenceUid);
		}
		
		// Add new record table IL_Vehicle and IL_Vehicle_CustomsBorderGuard
		String issuingDispatchNoForm = formJsonData.getString("IssuingDispatchNo");
		_log.info("issuingDispatchNoForm: "+issuingDispatchNoForm);
		String registrationNumberForm = formJsonData.getString("RegistrationNumber");
		if (Validator.isNotNull(issuingDispatchNoForm)) {
			String vehicleArr = formJsonData.getString("VehicleList");
			_log.info("LamTV_vehicleArr: "+vehicleArr);
			if (Validator.isNotNull(vehicleArr)) {
				try {
					JSONArray jsonArr = JSONFactoryUtil.createJSONArray(vehicleArr);
					if (jsonArr != null && jsonArr.length() > 0) {
						int length = jsonArr.length();
						_log.info("LamTV_length: "+length);
						for (int i = 0; i < length; i++) {
							JSONObject tempObjectJSON = jsonArr.getJSONObject(i);
							_log.info("LamTV_tempJSON: "+tempObjectJSON);
							if (tempObjectJSON != null) {
								ILVehicleLocalServiceUtil.createVehicle(tempObjectJSON, issuingDispatchNoForm);
							}
						}
					}
					_log.info("END");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if (Validator.isNotNull(registrationNumberForm)) {
			String cbGruardArr = formJsonData.getString("CustomsBorderGuards");
			_log.info("LamTV_cbGruardArr: "+cbGruardArr);
			if (Validator.isNotNull(cbGruardArr)) {
				try {
					JSONArray jsonGuardArr = JSONFactoryUtil.createJSONArray(cbGruardArr);
					if (jsonGuardArr != null && jsonGuardArr.length() > 0) {
						int lengthGuard = jsonGuardArr.length();
						_log.info("LamTV_lengthGuard: "+lengthGuard);
						for (int i = 0; i < lengthGuard; i++) {
							JSONObject tempGuardJSON = jsonGuardArr.getJSONObject(i);
							_log.info("LamTV_tempGuardJSON: "+tempGuardJSON);
							if (tempGuardJSON != null) {
								ILVehicleCustomsBorderGuardLocalServiceUtil.createCustomsBorderGuard(tempGuardJSON,
										registrationNumberForm);
							}
						}
					}
					_log.info("END");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}

		// update table ILCertificate
//		ILCertificate ilCertificate = ILCertificateLocalServiceUtil
//				.updateCertificate(formJsonData, dossier);

//		long vrVehicleTypeCertificateId = vrVehicleTypeCertificate.getId();

		// update table VR_VEHICLESPECIFICATION
//		VRVehicleSpecificationLocalServiceUtil.updateVehicleSpecification(mapValues, vrVehicleTypeCertificateId,
//				modifiedDate, registration, dossierFile);
		// update VR_INSPECTIONSTANDARD
//		VRInspectionStandardLocalServiceUtil.updateInspectionStandard(mapValues, vrVehicleTypeCertificateId,
//				modifiedDate, registration, dossierFile);
		// update VR_APPLICANTPROFILE
		// VRApplicantProfileLocalServiceUtil.updateApplicantProfile(mapValues,
		// modifiedDate);
		// update VR_PRODUCTIONPLANT
		// VRProductionPlantLocalServiceUtil.updateProductionPlant(mapValues,
		// modifiedDate);
		// update syncDate
		ILSyncDateLocalServiceUtil.updateSyncDate(modifiedDate);

	}

//	public static void main(String []args) {
//		
//		
//		
//		
//		
////				Properties props=new Properties();
////				//props.load(InputStream)
////				try {
////					props.load(new FileInputStream("Language_vi_VN"));
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////				String value = props.getProperty("IsCargoTransport");
//				
//		Configuration configuration = ConfigurationFactoryUtil.getConfiguration(PortalClassLoaderUtil.getClassLoader(),
//				"Language_vi_VN.properties");
//
//		Configuration configuration11 = ConfigurationFactoryUtil.getConfiguration(PortalClassLoaderUtil.getClassLoader(),
//				"Language_vi_VN");
//
////		System.out.println("liferay custom url->" +configuration.get("IsCargoTransport"));
//	}
}
