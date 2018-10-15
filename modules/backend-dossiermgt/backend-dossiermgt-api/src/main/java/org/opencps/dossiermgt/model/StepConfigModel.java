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

package org.opencps.dossiermgt.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the StepConfig service. Represents a row in the &quot;opencps_stepconfig&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.opencps.dossiermgt.model.impl.StepConfigModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.opencps.dossiermgt.model.impl.StepConfigImpl}.
 * </p>
 *
 * @author huymq
 * @see StepConfig
 * @see org.opencps.dossiermgt.model.impl.StepConfigImpl
 * @see org.opencps.dossiermgt.model.impl.StepConfigModelImpl
 * @generated
 */
@ProviderType
public interface StepConfigModel extends BaseModel<StepConfig>, ShardedModel,
	StagedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a step config model instance should use the {@link StepConfig} interface instead.
	 */

	/**
	 * Returns the primary key of this step config.
	 *
	 * @return the primary key of this step config
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this step config.
	 *
	 * @param primaryKey the primary key of this step config
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this step config.
	 *
	 * @return the uuid of this step config
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this step config.
	 *
	 * @param uuid the uuid of this step config
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the step config ID of this step config.
	 *
	 * @return the step config ID of this step config
	 */
	public long getStepConfigId();

	/**
	 * Sets the step config ID of this step config.
	 *
	 * @param stepConfigId the step config ID of this step config
	 */
	public void setStepConfigId(long stepConfigId);

	/**
	 * Returns the company ID of this step config.
	 *
	 * @return the company ID of this step config
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this step config.
	 *
	 * @param companyId the company ID of this step config
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this step config.
	 *
	 * @return the group ID of this step config
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this step config.
	 *
	 * @param groupId the group ID of this step config
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the user ID of this step config.
	 *
	 * @return the user ID of this step config
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this step config.
	 *
	 * @param userId the user ID of this step config
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this step config.
	 *
	 * @return the user uuid of this step config
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this step config.
	 *
	 * @param userUuid the user uuid of this step config
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this step config.
	 *
	 * @return the create date of this step config
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this step config.
	 *
	 * @param createDate the create date of this step config
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this step config.
	 *
	 * @return the modified date of this step config
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this step config.
	 *
	 * @param modifiedDate the modified date of this step config
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the step code of this step config.
	 *
	 * @return the step code of this step config
	 */
	@AutoEscape
	public String getStepCode();

	/**
	 * Sets the step code of this step config.
	 *
	 * @param stepCode the step code of this step config
	 */
	public void setStepCode(String stepCode);

	/**
	 * Returns the step name of this step config.
	 *
	 * @return the step name of this step config
	 */
	@AutoEscape
	public String getStepName();

	/**
	 * Sets the step name of this step config.
	 *
	 * @param stepName the step name of this step config
	 */
	public void setStepName(String stepName);

	/**
	 * Returns the step type of this step config.
	 *
	 * @return the step type of this step config
	 */
	public int getStepType();

	/**
	 * Sets the step type of this step config.
	 *
	 * @param stepType the step type of this step config
	 */
	public void setStepType(int stepType);

	/**
	 * Returns the dossier status of this step config.
	 *
	 * @return the dossier status of this step config
	 */
	@AutoEscape
	public String getDossierStatus();

	/**
	 * Sets the dossier status of this step config.
	 *
	 * @param dossierStatus the dossier status of this step config
	 */
	public void setDossierStatus(String dossierStatus);

	/**
	 * Returns the dossier sub status of this step config.
	 *
	 * @return the dossier sub status of this step config
	 */
	@AutoEscape
	public String getDossierSubStatus();

	/**
	 * Sets the dossier sub status of this step config.
	 *
	 * @param dossierSubStatus the dossier sub status of this step config
	 */
	public void setDossierSubStatus(String dossierSubStatus);

	/**
	 * Returns the menu group of this step config.
	 *
	 * @return the menu group of this step config
	 */
	@AutoEscape
	public String getMenuGroup();

	/**
	 * Sets the menu group of this step config.
	 *
	 * @param menuGroup the menu group of this step config
	 */
	public void setMenuGroup(String menuGroup);

	/**
	 * Returns the menu step name of this step config.
	 *
	 * @return the menu step name of this step config
	 */
	@AutoEscape
	public String getMenuStepName();

	/**
	 * Sets the menu step name of this step config.
	 *
	 * @param menuStepName the menu step name of this step config
	 */
	public void setMenuStepName(String menuStepName);

	/**
	 * Returns the button config of this step config.
	 *
	 * @return the button config of this step config
	 */
	@AutoEscape
	public String getButtonConfig();

	/**
	 * Sets the button config of this step config.
	 *
	 * @param buttonConfig the button config of this step config
	 */
	public void setButtonConfig(String buttonConfig);

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
	public int compareTo(StepConfig stepConfig);

	@Override
	public int hashCode();

	@Override
	public CacheModel<StepConfig> toCacheModel();

	@Override
	public StepConfig toEscapedModel();

	@Override
	public StepConfig toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}