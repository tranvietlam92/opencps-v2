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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProcessSequenceLocalService}.
 *
 * @author huymq
 * @see ProcessSequenceLocalService
 * @generated
 */
@ProviderType
public class ProcessSequenceLocalServiceWrapper
	implements ProcessSequenceLocalService,
		ServiceWrapper<ProcessSequenceLocalService> {
	public ProcessSequenceLocalServiceWrapper(
		ProcessSequenceLocalService processSequenceLocalService) {
		_processSequenceLocalService = processSequenceLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessSequence addProcessSequence(
		long userId, long groupId, long serviceProcessId, String sequenceNo,
		String sequenceName, double durationCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processSequenceLocalService.addProcessSequence(userId, groupId,
			serviceProcessId, sequenceNo, sequenceName, durationCount);
	}

	/**
	* Adds the process sequence to the database. Also notifies the appropriate model listeners.
	*
	* @param processSequence the process sequence
	* @return the process sequence that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence addProcessSequence(
		org.opencps.dossiermgt.model.ProcessSequence processSequence) {
		return _processSequenceLocalService.addProcessSequence(processSequence);
	}

	/**
	* Creates a new process sequence with the primary key. Does not add the process sequence to the database.
	*
	* @param processSequenceId the primary key for the new process sequence
	* @return the new process sequence
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence createProcessSequence(
		long processSequenceId) {
		return _processSequenceLocalService.createProcessSequence(processSequenceId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processSequenceLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process sequence with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence that was removed
	* @throws PortalException if a process sequence with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence deleteProcessSequence(
		long processSequenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processSequenceLocalService.deleteProcessSequence(processSequenceId);
	}

	/**
	* Deletes the process sequence from the database. Also notifies the appropriate model listeners.
	*
	* @param processSequence the process sequence
	* @return the process sequence that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence deleteProcessSequence(
		org.opencps.dossiermgt.model.ProcessSequence processSequence) {
		return _processSequenceLocalService.deleteProcessSequence(processSequence);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _processSequenceLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _processSequenceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _processSequenceLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _processSequenceLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _processSequenceLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _processSequenceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessSequence fetchProcessSequence(
		long processSequenceId) {
		return _processSequenceLocalService.fetchProcessSequence(processSequenceId);
	}

	/**
	* Returns the process sequence matching the UUID and group.
	*
	* @param uuid the process sequence's UUID
	* @param groupId the primary key of the group
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence fetchProcessSequenceByUuidAndGroupId(
		String uuid, long groupId) {
		return _processSequenceLocalService.fetchProcessSequenceByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessSequence> findByG_SN(
		long groupId, String sequenceNo) {
		return _processSequenceLocalService.findByG_SN(groupId, sequenceNo);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessSequence findBySID_SNO(
		long groupId, long serviceProcessId, String sequenceNo) {
		return _processSequenceLocalService.findBySID_SNO(groupId,
			serviceProcessId, sequenceNo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _processSequenceLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getByServiceProcess(
		long groupId, long serviceProcessId) {
		return _processSequenceLocalService.getByServiceProcess(groupId,
			serviceProcessId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _processSequenceLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _processSequenceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processSequenceLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processSequenceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process sequence with the primary key.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence
	* @throws PortalException if a process sequence with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence getProcessSequence(
		long processSequenceId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processSequenceLocalService.getProcessSequence(processSequenceId);
	}

	/**
	* Returns the process sequence matching the UUID and group.
	*
	* @param uuid the process sequence's UUID
	* @param groupId the primary key of the group
	* @return the matching process sequence
	* @throws PortalException if a matching process sequence could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence getProcessSequenceByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processSequenceLocalService.getProcessSequenceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of process sequences
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getProcessSequences(
		int start, int end) {
		return _processSequenceLocalService.getProcessSequences(start, end);
	}

	/**
	* Returns all the process sequences matching the UUID and company.
	*
	* @param uuid the UUID of the process sequences
	* @param companyId the primary key of the company
	* @return the matching process sequences, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getProcessSequencesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _processSequenceLocalService.getProcessSequencesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of process sequences matching the UUID and company.
	*
	* @param uuid the UUID of the process sequences
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process sequences, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessSequence> getProcessSequencesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessSequence> orderByComparator) {
		return _processSequenceLocalService.getProcessSequencesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of process sequences.
	*
	* @return the number of process sequences
	*/
	@Override
	public int getProcessSequencesCount() {
		return _processSequenceLocalService.getProcessSequencesCount();
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessSequence updateProcessSequence(
		long userId, long groupId, long processSequenceId,
		long serviceProcessId, String sequenceNo, String sequenceName,
		double durationCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processSequenceLocalService.updateProcessSequence(userId,
			groupId, processSequenceId, serviceProcessId, sequenceNo,
			sequenceName, durationCount);
	}

	/**
	* Updates the process sequence in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processSequence the process sequence
	* @return the process sequence that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessSequence updateProcessSequence(
		org.opencps.dossiermgt.model.ProcessSequence processSequence) {
		return _processSequenceLocalService.updateProcessSequence(processSequence);
	}

	@Override
	public void updateProcessSequenceDB(long userId, long groupId,
		long serviceProcessId, String sequenceNo, String sequenceName,
		String sequenceRole, Double durationCount,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		_processSequenceLocalService.updateProcessSequenceDB(userId, groupId,
			serviceProcessId, sequenceNo, sequenceName, sequenceRole,
			durationCount, serviceContext);
	}

	@Override
	public ProcessSequenceLocalService getWrappedService() {
		return _processSequenceLocalService;
	}

	@Override
	public void setWrappedService(
		ProcessSequenceLocalService processSequenceLocalService) {
		_processSequenceLocalService = processSequenceLocalService;
	}

	private ProcessSequenceLocalService _processSequenceLocalService;
}