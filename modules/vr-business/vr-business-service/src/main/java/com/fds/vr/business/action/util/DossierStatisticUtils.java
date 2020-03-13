package com.fds.vr.business.action.util;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class DossierStatisticUtils {
	
	public void getKeyValue(String templateKey) {
		
	}
	
	public int getDelayReason(long dossierId, long actionId) {
		
		int reasonCode = 0;
		
		String actionStr = String.valueOf(actionId);
		
		switch (actionStr) {
		case "1":
			reasonCode = 1;
			break;

		default:
			break;
		}
		
		return reasonCode;
	}

	
	public int getStatusProcessing(long dossierId) {
		//TODO getStatusProcessing
		return 1;
	}
	
/*	public String getDossierStatus(long dossierId) {
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
	}*/
	
}
