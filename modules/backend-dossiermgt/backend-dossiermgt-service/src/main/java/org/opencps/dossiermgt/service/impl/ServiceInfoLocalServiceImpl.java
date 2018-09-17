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

package org.opencps.dossiermgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.utils.DictCollectionUtils;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.exception.DuplicateServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredAdministrationCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceNameException;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.base.ServiceInfoLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the service info local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ServiceInfoLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ServiceInfoLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil
 */
@ProviderType
public class ServiceInfoLocalServiceImpl extends ServiceInfoLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil} to access the
	 * service info local service.
	 */
	@Indexable(type = IndexableType.DELETE)
	public ServiceInfo removeServiceInfo(long serviceInfoId) throws PortalException {
		ServiceInfo serviceInfo = serviceInfoPersistence.fetchByPrimaryKey(serviceInfoId);

		// Validate
		valdiateRemove(serviceInfoId);

		serviceInfoPersistence.remove(serviceInfo);

		List<ServiceFileTemplate> fileTemplates = serviceFileTemplateLocalService.getByServiceInfoId(serviceInfoId);

		for (ServiceFileTemplate fileTemplate : fileTemplates) {
			if (fileTemplate.getFileEntryId() != 0) {
				serviceFileTemplateLocalService.removeServiceFileTemplate(serviceInfoId,
						fileTemplate.getFileTemplateNo());
			}
		}

		return serviceInfo;
	}

	public ServiceInfo getByCode(long groupId, String serviceCode) throws PortalException {
		return serviceInfoPersistence.fetchBySC_GI(serviceCode, groupId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceInfo addServiceInfo(long userId, long groupId, String serviceCode, String serviceName,
			String processText, String methodText, String dossierText, String conditionText, String durationText,
			String applicantText, String resultText, String regularText, String feeText, String administrationCode,
			String domainCode, int maxLevel, boolean activeStatus, ServiceContext serviceContext) throws PortalException {

		User user = userLocalService.getUser(userId);

		Date now = new Date();

		// TODO: validate
		valdiate(serviceCode, serviceName, administrationCode, domainCode, groupId);

		long serviceInfoId = counterLocalService.increment(ServiceInfo.class.getName());

		ServiceInfo serviceInfo = serviceInfoPersistence.create(serviceInfoId);

		serviceInfo.setGroupId(groupId);
		serviceInfo.setCompanyId(user.getCompanyId());
		serviceInfo.setUserId(user.getUserId());
		serviceInfo.setUserName(user.getFullName());
		serviceInfo.setCreateDate(now);
		serviceInfo.setModifiedDate(now);

		serviceInfo.setServiceCode(serviceCode);
		serviceInfo.setServiceName(serviceName);
		serviceInfo.setProcessText(processText);
		serviceInfo.setMethodText(methodText);
		serviceInfo.setDossierText(dossierText);
		serviceInfo.setConditionText(conditionText);
		serviceInfo.setDurationText(durationText);
		serviceInfo.setApplicantText(applicantText);
		serviceInfo.setResultText(resultText);
		serviceInfo.setRegularText(regularText);
		serviceInfo.setFeeText(feeText);
		serviceInfo.setAdministrationCode(administrationCode);
		serviceInfo.setDomainCode(domainCode);
		serviceInfo.setMaxLevel(maxLevel);

		DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE, administrationCode,
				groupId);
		
		DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, domainCode, groupId);

		if (Validator.isNotNull(adm)) {
			serviceInfo.setAdministrationName(adm.getItemName());
			serviceInfo.setAdministrationIndex(adm.getTreeIndex());
		}

		if (Validator.isNotNull(dom)) {
			serviceInfo.setDomainName(dom.getItemName());
			serviceInfo.setDomainIndex(dom.getTreeIndex());
		}

		serviceInfo.setPublic_(activeStatus);

		serviceInfoPersistence.update(serviceInfo);

		return serviceInfo;
	}

	public int countServiceInfosByGroupId(long groupId) {
		return serviceInfoPersistence.countByGroupId(groupId);
	}

	public List<ServiceInfo> getServiceInfosByGroupId(long groupId) {
		return serviceInfoPersistence.findByGroupId(groupId);
	}

	public List<ServiceInfo> getServiceInfosByGroupId(long groupId, int start, int end) {
		return serviceInfoPersistence.findByGroupId(groupId, start, end);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ServiceInfo updateServiceInfo(long groupId, long serviceInfoId, String serviceCode, String serviceName,
			String processText, String methodText, String dossierText, String conditionText, String durationText,
			String applicantText, String resultText, String regularText, String feeText, String administrationCode,
			String domainCode, int maxLevel, boolean activeStatus, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();

		ServiceInfo serviceInfo = serviceInfoPersistence.findByPrimaryKey(serviceInfoId);

		// TODO: validate

		serviceInfo.setModifiedDate(now);

		if (Validator.isNotNull(serviceCode))
			serviceInfo.setServiceCode(serviceCode);

		if (Validator.isNotNull(serviceName))
			serviceInfo.setServiceName(serviceName);

		if (Validator.isNotNull(processText))
			serviceInfo.setProcessText(processText);

		if (Validator.isNotNull(methodText))
			serviceInfo.setMethodText(methodText);

		if (Validator.isNotNull(dossierText))
			serviceInfo.setDossierText(dossierText);

		if (Validator.isNotNull(conditionText))
			serviceInfo.setConditionText(conditionText);

		if (Validator.isNotNull(durationText))
			serviceInfo.setDurationText(durationText);

		if (Validator.isNotNull(applicantText))
			serviceInfo.setApplicantText(applicantText);

		if (Validator.isNotNull(resultText))
			serviceInfo.setResultText(resultText);

		if (Validator.isNotNull(regularText))
			serviceInfo.setRegularText(regularText);

		if (Validator.isNotNull(feeText))
			serviceInfo.setFeeText(feeText);

		if (Validator.isNotNull(administrationCode))
			serviceInfo.setAdministrationCode(administrationCode);

		if (Validator.isNotNull(domainCode))
			serviceInfo.setDomainCode(domainCode);

		if (Validator.isNotNull(maxLevel))
			serviceInfo.setMaxLevel(maxLevel);

		
		serviceInfo.setPublic_(activeStatus);
		
		
		DictItem adm = DictCollectionUtils.getDictItemByCode(DataMGTConstants.ADMINTRATION_CODE, administrationCode,
				groupId);
		DictItem dom = DictCollectionUtils.getDictItemByCode(DataMGTConstants.SERVICE_DOMAIN, domainCode, groupId);

		if (Validator.isNotNull(adm)) {
			serviceInfo.setAdministrationName(adm.getItemName());
			serviceInfo.setAdministrationIndex(adm.getTreeIndex());
		}

		if (Validator.isNotNull(dom)) {
			serviceInfo.setDomainName(dom.getItemName());
			serviceInfo.setDomainIndex(dom.getTreeIndex());
		}

		
		serviceInfoPersistence.update(serviceInfo);

		return serviceInfo;
	}

	private void valdiate(String serviceCode, String serviceName, String administrationCode, String domainCode,
			long groupId) throws PortalException {

		if (Validator.isNull(serviceCode)) {
			throw new RequiredServiceCodeException();
		}

		if (Validator.isNull(serviceName)) {
			throw new RequiredServiceNameException();
		}

		if (Validator.isNull(administrationCode)) {
			throw new RequiredAdministrationCodeException();
		}

		ServiceInfo si = null;

		try {
			si = serviceInfoPersistence.findBySC_GI(serviceCode, groupId);
		} catch (Exception e) {

		}

		if (Validator.isNotNull(si)) {
			throw new DuplicateServiceCodeException();
		}
	}

	private void valdiateRemove(long serviceInfoId) throws PortalException {
		// TODO implement
	}
	
	public List<ServiceInfo> fetchByDomain(long groupId, String domainCode) {
		return serviceInfoPersistence.findByGI_DC(domainCode, groupId);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceInfo> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ServiceInfoTerm.SERVICE_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String administration = GetterUtil.getString(params.get(ServiceInfoTerm.ADMINISTRATION_CODE));
		String domain = GetterUtil.getString(params.get(ServiceInfoTerm.DOMAIN_CODE));
		String level = String.valueOf((params.get(ServiceInfoTerm.MAX_LEVEL)));
		//sondt add to search serviceInfo active or not
		String public_ = GetterUtil.getString(params.get(ServiceInfoTerm.PUBLIC_));

		if (Validator.isNotNull(administration)) {
			MultiMatchQuery query = new MultiMatchQuery(administration);

			query.addFields(ServiceInfoTerm.ADMINISTRATION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(domain)) {
			MultiMatchQuery query = new MultiMatchQuery(domain);

			query.addFields(ServiceInfoTerm.DOMAIN_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (!level.equalsIgnoreCase("0") && Validator.isNotNull(level)) {
			MultiMatchQuery query = new MultiMatchQuery(level);

			query.addFields(ServiceInfoTerm.MAX_LEVEL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		if (Validator.isNotNull(public_)) {
			MultiMatchQuery query = new MultiMatchQuery(public_);

			query.addFields(ServiceInfoTerm.PUBLIC_);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ServiceInfo> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ServiceInfoTerm.SERVICE_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields

		String administration = GetterUtil.getString(params.get(ServiceInfoTerm.ADMINISTRATION_CODE));
		String domain = GetterUtil.getString(params.get(ServiceInfoTerm.DOMAIN_CODE));
		String level = String.valueOf((params.get(ServiceInfoTerm.MAX_LEVEL)));
		//sondt add to search serviceInfo active or not
		String public_ = GetterUtil.getString(params.get(ServiceInfoTerm.PUBLIC_));
		
		if (Validator.isNotNull(administration)) {
			MultiMatchQuery query = new MultiMatchQuery(administration);

			query.addFields(ServiceInfoTerm.ADMINISTRATION_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(domain)) {
			MultiMatchQuery query = new MultiMatchQuery(domain);

			query.addFields(ServiceInfoTerm.DOMAIN_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (!level.equalsIgnoreCase("0") && Validator.isNotNull(level)) {
			MultiMatchQuery query = new MultiMatchQuery(level);

			query.addFields(ServiceInfoTerm.MAX_LEVEL);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		if (Validator.isNotNull(public_)) {
			MultiMatchQuery query = new MultiMatchQuery(public_);

			query.addFields(ServiceInfoTerm.PUBLIC_);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = ServiceInfo.class.getName();
}