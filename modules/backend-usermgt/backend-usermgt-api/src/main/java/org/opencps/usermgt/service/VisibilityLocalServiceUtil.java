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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Visibility. This utility wraps
 * {@link org.opencps.usermgt.service.impl.VisibilityLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see VisibilityLocalService
 * @see org.opencps.usermgt.service.base.VisibilityLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.VisibilityLocalServiceImpl
 * @generated
 */
@ProviderType
public class VisibilityLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.VisibilityLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the visibility to the database. Also notifies the appropriate model listeners.
	*
	* @param visibility the visibility
	* @return the visibility that was added
	*/
	public static org.opencps.usermgt.model.Visibility addVisibility(
		org.opencps.usermgt.model.Visibility visibility) {
		return getService().addVisibility(visibility);
	}

	/**
	* Creates a new visibility with the primary key. Does not add the visibility to the database.
	*
	* @param visibilityId the primary key for the new visibility
	* @return the new visibility
	*/
	public static org.opencps.usermgt.model.Visibility createVisibility(
		long visibilityId) {
		return getService().createVisibility(visibilityId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the visibility with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility that was removed
	* @throws PortalException if a visibility with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.Visibility deleteVisibility(
		long visibilityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteVisibility(visibilityId);
	}

	/**
	* Deletes the visibility from the database. Also notifies the appropriate model listeners.
	*
	* @param visibility the visibility
	* @return the visibility that was removed
	*/
	public static org.opencps.usermgt.model.Visibility deleteVisibility(
		org.opencps.usermgt.model.Visibility visibility) {
		return getService().deleteVisibility(visibility);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.usermgt.model.Visibility fetchVisibility(
		long visibilityId) {
		return getService().fetchVisibility(visibilityId);
	}

	/**
	* Returns the visibility matching the UUID and group.
	*
	* @param uuid the visibility's UUID
	* @param groupId the primary key of the group
	* @return the matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public static org.opencps.usermgt.model.Visibility fetchVisibilityByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchVisibilityByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of visibilities
	*/
	public static java.util.List<org.opencps.usermgt.model.Visibility> getVisibilities(
		int start, int end) {
		return getService().getVisibilities(start, end);
	}

	/**
	* Returns all the visibilities matching the UUID and company.
	*
	* @param uuid the UUID of the visibilities
	* @param companyId the primary key of the company
	* @return the matching visibilities, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.Visibility> getVisibilitiesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getVisibilitiesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of visibilities matching the UUID and company.
	*
	* @param uuid the UUID of the visibilities
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching visibilities, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.Visibility> getVisibilitiesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.Visibility> orderByComparator) {
		return getService()
				   .getVisibilitiesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of visibilities.
	*
	* @return the number of visibilities
	*/
	public static int getVisibilitiesCount() {
		return getService().getVisibilitiesCount();
	}

	/**
	* Returns the visibility with the primary key.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility
	* @throws PortalException if a visibility with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.Visibility getVisibility(
		long visibilityId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVisibility(visibilityId);
	}

	/**
	* Returns the visibility matching the UUID and group.
	*
	* @param uuid the visibility's UUID
	* @param groupId the primary key of the group
	* @return the matching visibility
	* @throws PortalException if a matching visibility could not be found
	*/
	public static org.opencps.usermgt.model.Visibility getVisibilityByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getVisibilityByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the visibility in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param visibility the visibility
	* @return the visibility that was updated
	*/
	public static org.opencps.usermgt.model.Visibility updateVisibility(
		org.opencps.usermgt.model.Visibility visibility) {
		return getService().updateVisibility(visibility);
	}

	public static VisibilityLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VisibilityLocalService, VisibilityLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VisibilityLocalService.class);

		ServiceTracker<VisibilityLocalService, VisibilityLocalService> serviceTracker =
			new ServiceTracker<VisibilityLocalService, VisibilityLocalService>(bundle.getBundleContext(),
				VisibilityLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}