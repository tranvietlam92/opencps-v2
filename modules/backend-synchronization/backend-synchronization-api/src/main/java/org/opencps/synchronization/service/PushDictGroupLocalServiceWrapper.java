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

package org.opencps.synchronization.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PushDictGroupLocalService}.
 *
 * @author trungdk
 * @see PushDictGroupLocalService
 * @generated
 */
@ProviderType
public class PushDictGroupLocalServiceWrapper
	implements PushDictGroupLocalService,
		ServiceWrapper<PushDictGroupLocalService> {
	public PushDictGroupLocalServiceWrapper(
		PushDictGroupLocalService pushDictGroupLocalService) {
		_pushDictGroupLocalService = pushDictGroupLocalService;
	}

	@Override
	public org.opencps.synchronization.model.PushDictGroup addPushDictGroup(
		long userId, long groupId, String serverNo, String collectionCode,
		String groupCode, String groupName, String groupNameEN,
		String groupDescription, String itemCode, String method,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushDictGroupLocalService.addPushDictGroup(userId, groupId,
			serverNo, collectionCode, groupCode, groupName, groupNameEN,
			groupDescription, itemCode, method, serviceContext);
	}

	/**
	* Adds the push dict group to the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was added
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup addPushDictGroup(
		org.opencps.synchronization.model.PushDictGroup pushDictGroup) {
		return _pushDictGroupLocalService.addPushDictGroup(pushDictGroup);
	}

	/**
	* Creates a new push dict group with the primary key. Does not add the push dict group to the database.
	*
	* @param pushDictGroupId the primary key for the new push dict group
	* @return the new push dict group
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup createPushDictGroup(
		long pushDictGroupId) {
		return _pushDictGroupLocalService.createPushDictGroup(pushDictGroupId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictGroupLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the push dict group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group that was removed
	* @throws PortalException if a push dict group with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup deletePushDictGroup(
		long pushDictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictGroupLocalService.deletePushDictGroup(pushDictGroupId);
	}

	@Override
	public org.opencps.synchronization.model.PushDictGroup deletePushDictGroup(
		long pushDictGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _pushDictGroupLocalService.deletePushDictGroup(pushDictGroupId,
			serviceContext);
	}

	/**
	* Deletes the push dict group from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was removed
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup deletePushDictGroup(
		org.opencps.synchronization.model.PushDictGroup pushDictGroup) {
		return _pushDictGroupLocalService.deletePushDictGroup(pushDictGroup);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _pushDictGroupLocalService.dynamicQuery();
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
		return _pushDictGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushDictGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _pushDictGroupLocalService.dynamicQuery(dynamicQuery, start,
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
		return _pushDictGroupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _pushDictGroupLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.synchronization.model.PushDictGroup fetchPushDictGroup(
		long pushDictGroupId) {
		return _pushDictGroupLocalService.fetchPushDictGroup(pushDictGroupId);
	}

	/**
	* Returns the push dict group matching the UUID and group.
	*
	* @param uuid the push dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict group, or <code>null</code> if a matching push dict group could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup fetchPushDictGroupByUuidAndGroupId(
		String uuid, long groupId) {
		return _pushDictGroupLocalService.fetchPushDictGroupByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictGroup> findAll(
		int start, int end) {
		return _pushDictGroupLocalService.findAll(start, end);
	}

	@Override
	public org.opencps.synchronization.model.PushDictGroup findByCollectionCode_GroupCode_ItemCode_Method(
		long groupId, String collectionCode, String groupCode, String itemCode,
		String method)
		throws org.opencps.synchronization.exception.NoSuchPushDictGroupException {
		return _pushDictGroupLocalService.findByCollectionCode_GroupCode_ItemCode_Method(groupId,
			collectionCode, groupCode, itemCode, method);
	}

	@Override
	public org.opencps.synchronization.model.PushDictGroup findByCollectionCode_GroupCode_Method(
		long groupId, String collectionCode, String groupCode, String method)
		throws org.opencps.synchronization.exception.NoSuchPushDictGroupException {
		return _pushDictGroupLocalService.findByCollectionCode_GroupCode_Method(groupId,
			collectionCode, groupCode, method);
	}

	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictGroup> findByGroupId_ServerNo(
		long groupId, String serverNo, int start, int end) {
		return _pushDictGroupLocalService.findByGroupId_ServerNo(groupId,
			serverNo, start, end);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _pushDictGroupLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _pushDictGroupLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _pushDictGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _pushDictGroupLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the push dict group with the primary key.
	*
	* @param pushDictGroupId the primary key of the push dict group
	* @return the push dict group
	* @throws PortalException if a push dict group with the primary key could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup getPushDictGroup(
		long pushDictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictGroupLocalService.getPushDictGroup(pushDictGroupId);
	}

	/**
	* Returns the push dict group matching the UUID and group.
	*
	* @param uuid the push dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching push dict group
	* @throws PortalException if a matching push dict group could not be found
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup getPushDictGroupByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _pushDictGroupLocalService.getPushDictGroupByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the push dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.PushDictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @return the range of push dict groups
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictGroup> getPushDictGroups(
		int start, int end) {
		return _pushDictGroupLocalService.getPushDictGroups(start, end);
	}

	/**
	* Returns all the push dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the push dict groups
	* @param companyId the primary key of the company
	* @return the matching push dict groups, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictGroup> getPushDictGroupsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _pushDictGroupLocalService.getPushDictGroupsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of push dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the push dict groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of push dict groups
	* @param end the upper bound of the range of push dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching push dict groups, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.synchronization.model.PushDictGroup> getPushDictGroupsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.PushDictGroup> orderByComparator) {
		return _pushDictGroupLocalService.getPushDictGroupsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of push dict groups.
	*
	* @return the number of push dict groups
	*/
	@Override
	public int getPushDictGroupsCount() {
		return _pushDictGroupLocalService.getPushDictGroupsCount();
	}

	@Override
	public org.opencps.synchronization.model.PushDictGroup updatePushDictGroup(
		long userId, long groupId, long pushDictGroupId, String serverNo,
		String collectionCode, String groupCode, String groupName,
		String groupNameEN, String groupDescription, String itemCode,
		String method,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.synchronization.exception.NoSuchPushDictGroupException,
			com.liferay.portal.kernel.exception.SystemException {
		return _pushDictGroupLocalService.updatePushDictGroup(userId, groupId,
			pushDictGroupId, serverNo, collectionCode, groupCode, groupName,
			groupNameEN, groupDescription, itemCode, method, serviceContext);
	}

	/**
	* Updates the push dict group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param pushDictGroup the push dict group
	* @return the push dict group that was updated
	*/
	@Override
	public org.opencps.synchronization.model.PushDictGroup updatePushDictGroup(
		org.opencps.synchronization.model.PushDictGroup pushDictGroup) {
		return _pushDictGroupLocalService.updatePushDictGroup(pushDictGroup);
	}

	@Override
	public PushDictGroupLocalService getWrappedService() {
		return _pushDictGroupLocalService;
	}

	@Override
	public void setWrappedService(
		PushDictGroupLocalService pushDictGroupLocalService) {
		_pushDictGroupLocalService = pushDictGroupLocalService;
	}

	private PushDictGroupLocalService _pushDictGroupLocalService;
}