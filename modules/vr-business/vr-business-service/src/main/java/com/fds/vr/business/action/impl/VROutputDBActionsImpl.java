package com.fds.vr.business.action.impl;

import com.fds.vr.business.action.VROutputDBActions;
import com.fds.vr.business.action.util.ConvertJONObjectUtils;
import com.fds.vr.business.model.VRConfigTechSpec;
import com.fds.vr.business.model.VRVehicleSpecification;
import com.fds.vr.business.model.VRVehicleTypeCertificate;
import com.fds.vr.business.model.impl.VRVehicleSpecificationImpl;
import com.fds.vr.business.service.VRConfigTechSpecLocalServiceUtil;
import com.fds.vr.business.service.VRSyncDateLocalServiceUtil;
import com.fds.vr.business.service.VRVehicleSpecificationLocalServiceUtil;
import com.fds.vr.business.service.VRVehicleTypeCertificateLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.RegistrationLocalServiceUtil;

public class VROutputDBActionsImpl implements VROutputDBActions{

	private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	private Log _log = LogFactoryUtil.getLog(VROutputDBActionsImpl.class);

	@Override
	public boolean processOutputDB() throws ParseException, SearchException, JSONException {

		Date syncDate = VRSyncDateLocalServiceUtil.getSyncDate();

//		_log.info("synsDate: "+syncDate);
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
		String strSyncDate = sdf.format(syncDate);

		List<Deliverable> deliverableList = DeliverableLocalServiceUtil.getDeliverableByModifiedDate(strSyncDate,
				"GCN_TDTK_XCG", 2);

		if (deliverableList != null && deliverableList.size() > 0) {
//			List<String> formDataList = new ArrayList<String>();
//			List<Date> modifiedDateList = new ArrayList<Date>();
			String formData = StringPool.BLANK;
			Date modifiedDate = null;
			DossierFile dossierFile = null;
			Registration registration = null;
			for (Deliverable deliverable : deliverableList) {
				String applicantIdNo = deliverable.getApplicantIdNo();
				String deliverableCode = deliverable.getDeliverableCode();
				if (Validator.isNotNull(applicantIdNo)) {
					registration = RegistrationLocalServiceUtil.getByApplicantIdNo(applicantIdNo);
				}
				if (Validator.isNotNull(deliverableCode)) {
					dossierFile = DossierFileLocalServiceUtil.getByDeliverableCode(deliverableCode);
				}

//				_log.info("registration: "+registration);
//				_log.info("dossierFile: "+dossierFile);
				formData = deliverable.getFormData();
				modifiedDate = deliverable.getModifiedDate();
				if (Validator.isNotNull(formData)) {
					LinkedHashMap<String, String> mapValues = ConvertJONObjectUtils.getKeyValuesMap(formData);
					if (mapValues != null) {
						outputDBAction(mapValues, modifiedDate, registration, dossierFile);
					}
				}
			}
		}

		return true;
	}

	private void outputDBAction(LinkedHashMap<String, String> mapValues, Date modifiedDate,
			Registration registration, DossierFile dossierFile) {
		// update table VRVehicleTypeCertificate
		VRVehicleTypeCertificate vrVehicleTypeCertificate = VRVehicleTypeCertificateLocalServiceUtil
				.updateVehicleTypeCertificate(mapValues, modifiedDate, registration, dossierFile);

		long vrVehicleTypeCertificateId = vrVehicleTypeCertificate.getId();
		
		updateVehicleSpecification(mapValues, vrVehicleTypeCertificateId, modifiedDate);

		// update table VR_VEHICLESPECIFICATION
		VRVehicleSpecificationLocalServiceUtil.updateVehicleSpecification(mapValues, vrVehicleTypeCertificateId,
				modifiedDate, registration, dossierFile);
		// update VR_INSPECTIONSTANDARD
		//VRInspectionStandardLocalServiceUtil.updateInspectionStandard(mapValues, vrVehicleTypeCertificateId,
				//modifiedDate, registration, dossierFile);
		// update VR_APPLICANTPROFILE
		 //VRApplicantProfileLocalServiceUtil.updateApplicantProfile(mapValues);
		// update VR_PRODUCTIONPLANT
		//VRProductionPlantLocalServiceUtil.updateProductionPlant(mapValues);
		// update syncDate
		VRSyncDateLocalServiceUtil.updateSyncDate(modifiedDate);

	}
	
	private void updateVehicleSpecification(LinkedHashMap<String, String> mapValues, long vrVehicleTypeCertificateId, Date modifiedDate) {
		//_log.info("**mapValues** " + mapValues);
		
		String strChungChi = mapValues.get("bien_ban");
		
		try {
			JSONObject objChungChi = JSONFactoryUtil.createJSONObject(strChungChi);
			
			List<VRConfigTechSpec> configTechSpecs = VRConfigTechSpecLocalServiceUtil.getByVCMD("XCG", "21");

			String prefix = "bb_";
			
			String prefixResult = "kl_";

			for (VRConfigTechSpec configTechSpec : configTechSpecs) {
				
				
				String specificationCode = prefix + configTechSpec.getSpecificationCode();
				
				String specificationValue = objChungChi.getString(specificationCode);
				
				String specificationResult = objChungChi.getString(prefixResult + configTechSpec.getSpecificationCode());
				
				VRVehicleSpecification elm = VRVehicleSpecificationLocalServiceUtil.findByCode(vrVehicleTypeCertificateId, configTechSpec.getSpecificationCode());

				VRVehicleSpecification specification = new VRVehicleSpecificationImpl();
				
				boolean isnew = false;
				
				if (Validator.isNotNull(elm)) {
					 specification = elm;
				} else {
					long specId = CounterLocalServiceUtil.increment(VRVehicleSpecification.class.getName());
					
					specification.setPrimaryKey(specId);
					
					isnew = true;
				}
				
				
				specification.setVehicleCertificateId(vrVehicleTypeCertificateId);
				specification.setSpecificationCode(configTechSpec.getSpecificationCode());
				specification.setSpecificationName(configTechSpec.getSpecificationDisplayName());
				specification.setSpecificationValue(specificationValue);
				specification.setSpecificationValueDescription(configTechSpec.getSpecificationEntryPlaceholder());
				//TODO
				specification.setSequenceNo(configTechSpec.getSequenceNo());
				specification.setSpecificationBasicUnit(configTechSpec.getSpecificationBasicUnit());
				specification.setSpecificationStandard(configTechSpec.getSpecificationStandard());
				specification.setSpecificationGroup(Integer.toString(configTechSpec.getSpecificationGroup()));
				specification.setSpecificationDataCollectionID(configTechSpec.getSpecificationDataCollectionId());
				specification.setSpecificationResult(specificationResult);
				specification.setSyncDate(GetterUtil.getDate(modifiedDate, new SimpleDateFormat("dd:MM:yyyy hh:mm:ss")));
				
				if (isnew) {
					VRVehicleSpecificationLocalServiceUtil.addVRVehicleSpecification(specification);

				} else {
					VRVehicleSpecificationLocalServiceUtil.updateVRVehicleSpecification(specification);

				}
			}
			
		} catch (JSONException e) {
			_log.info("**ERROR** CAN NOT CREATE");

		}
	}

}
