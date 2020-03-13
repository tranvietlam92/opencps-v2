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

import aQute.bnd.annotation.ProviderType;

import com.fds.vr.business.model.VRRPDossierStatistics;
import com.fds.vr.business.service.base.VRRPDossierStatisticsLocalServiceBaseImpl;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.util.Validator;

import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierRequestUD;
import org.opencps.dossiermgt.service.DossierRequestUDLocalServiceUtil;

/**
 * The implementation of the vrrp dossier statistics local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.fds.vr.business.service.VRRPDossierStatisticsLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author LamTV
 * @see VRRPDossierStatisticsLocalServiceBaseImpl
 * @see com.fds.vr.business.service.VRRPDossierStatisticsLocalServiceUtil
 */
@ProviderType
public class VRRPDossierStatisticsLocalServiceImpl
	extends VRRPDossierStatisticsLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.fds.vr.business.service.VRRPDossierStatisticsLocalServiceUtil} to access the vrrp dossier statistics local service.
	 */
	
	
	
	public VRRPDossierStatistics initStatistic(Dossier dossier) {
		
		long id = CounterLocalServiceUtil.increment(VRRPDossierStatistics.class.getName());
		
		VRRPDossierStatistics dossierStatistics = vrrpDossierStatisticsPersistence.create(id);
		
		dossierStatistics.setDossierid((int)dossier.getDossierId());
		dossierStatistics.setDossierno(dossier.getDossierNo());
		
		vrrpDossierStatisticsPersistence.update(dossierStatistics);
		
		return dossierStatistics;
	}
	
	public VRRPDossierStatistics updateDossierDateTime(long id, Dossier dossier) {
		VRRPDossierStatistics dossierStatistics = vrrpDossierStatisticsPersistence.fetchByPrimaryKey(id);
		
		if (Validator.isNotNull(dossierStatistics)) {
			//sending date
			if (Validator.isNull(dossierStatistics.getDossiersendingdate()) && Validator.isNotNull(dossier.getSubmitDate())) {
				dossierStatistics.setDossiersendingdate(dossier.getSubmitDate());
			}
			
			//receiving date
			if (Validator.isNull(dossierStatistics.getDossierreceivingdate()) && Validator.isNotNull(dossier.getReceiveDate())) {
				dossierStatistics.setDossierreceivingdate(dossier.getReceiveDate());
			}
			
			//the first time request
			if (Validator.isNull(dossierStatistics.getDossierfirstupdatingdate())) {
				if(dossier.getDossierStatus().contentEquals("waiting")) {
					dossierStatistics.setDossierfirstupdatingdate(dossier.getModifiedDate());
				}
			}
			
			//dossierendorsementdate
			if (Validator.isNull(dossierStatistics.getDossierfirstupdatingdate())) {
				DossierRequestUD dossierRequestUD = DossierRequestUDLocalServiceUtil.getByDossierAndType(dossier.getDossierId(), "submitting");
				
				if (Validator.isNotNull(dossierRequestUD)) {
					dossierStatistics.setDossierfirstupdatingdate(dossierRequestUD.getCreateDate());
				}
			}
			
			//dossiersubmittingdate
			if (Validator.isNull(dossierStatistics.getDossiersubmittingdate())) {
				if(dossier.getDossierStatus().contentEquals("processing")) {
					dossierStatistics.setDossierfirstupdatingdate(dossier.getModifiedDate());
				}
			}
			
			//dossierfirstcertificatesigndate
			// TODO ngay ky giai chung nhan 
			
		}
		
		vrrpDossierStatisticsPersistence.update(dossierStatistics);

		return dossierStatistics;
	}
}