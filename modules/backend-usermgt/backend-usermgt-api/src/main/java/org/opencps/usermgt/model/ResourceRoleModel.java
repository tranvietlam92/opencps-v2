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

package org.opencps.usermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ResourceRole service. Represents a row in the &quot;opencps_resourcerole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.opencps.usermgt.model.impl.ResourceRoleModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.opencps.usermgt.model.impl.ResourceRoleImpl}.
 * </p>
 *
 * @author khoavu
 * @see ResourceRole
 * @see org.opencps.usermgt.model.impl.ResourceRoleImpl
 * @see org.opencps.usermgt.model.impl.ResourceRoleModelImpl
 * @generated
 */
@ProviderType
public interface ResourceRoleModel extends BaseModel<ResourceRole>, GroupedModel,
	ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a resource role model instance should use the {@link ResourceRole} interface instead.
	 */

	/**
	 * Returns the primary key of this resource role.
	 *
	 * @return the primary key of this resource role
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this resource role.
	 *
	 * @param primaryKey the primary key of this resource role
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this resource role.
	 *
	 * @return the uuid of this resource role
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this resource role.
	 *
	 * @param uuid the uuid of this resource role
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the resource role ID of this resource role.
	 *
	 * @return the resource role ID of this resource role
	 */
	public long getResourceRoleId();

	/**
	 * Sets the resource role ID of this resource role.
	 *
	 * @param resourceRoleId the resource role ID of this resource role
	 */
	public void setResourceRoleId(long resourceRoleId);

	/**
	 * Returns the group ID of this resource role.
	 *
	 * @return the group ID of this resource role
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this resource role.
	 *
	 * @param groupId the group ID of this resource role
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this resource role.
	 *
	 * @return the company ID of this resource role
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this resource role.
	 *
	 * @param companyId the company ID of this resource role
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this resource role.
	 *
	 * @return the user ID of this resource role
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this resource role.
	 *
	 * @param userId the user ID of this resource role
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this resource role.
	 *
	 * @return the user uuid of this resource role
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this resource role.
	 *
	 * @param userUuid the user uuid of this resource role
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this resource role.
	 *
	 * @return the user name of this resource role
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this resource role.
	 *
	 * @param userName the user name of this resource role
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this resource role.
	 *
	 * @return the create date of this resource role
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this resource role.
	 *
	 * @param createDate the create date of this resource role
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this resource role.
	 *
	 * @return the modified date of this resource role
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this resource role.
	 *
	 * @param modifiedDate the modified date of this resource role
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the class name of this resource role.
	 *
	 * @return the class name of this resource role
	 */
	@AutoEscape
	public String getClassName();

	/**
	 * Sets the class name of this resource role.
	 *
	 * @param className the class name of this resource role
	 */
	public void setClassName(String className);

	/**
	 * Returns the class pk of this resource role.
	 *
	 * @return the class pk of this resource role
	 */
	@AutoEscape
	public String getClassPK();

	/**
	 * Sets the class pk of this resource role.
	 *
	 * @param classPK the class pk of this resource role
	 */
	public void setClassPK(String classPK);

	/**
	 * Returns the role ID of this resource role.
	 *
	 * @return the role ID of this resource role
	 */
	public long getRoleId();

	/**
	 * Sets the role ID of this resource role.
	 *
	 * @param roleId the role ID of this resource role
	 */
	public void setRoleId(long roleId);

	/**
	 * Returns the readonly of this resource role.
	 *
	 * @return the readonly of this resource role
	 */
	public int getReadonly();

	/**
	 * Sets the readonly of this resource role.
	 *
	 * @param readonly the readonly of this resource role
	 */
	public void setReadonly(int readonly);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(ResourceRole resourceRole);

	@Override
	public int hashCode();

	@Override
	public CacheModel<ResourceRole> toCacheModel();

	@Override
	public ResourceRole toEscapedModel();

	@Override
	public ResourceRole toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}