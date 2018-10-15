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
import com.liferay.portal.kernel.service.ServiceContext;

import org.opencps.dossiermgt.service.persistence.DossierActionUserPK;

import java.io.Serializable;

/**
 * The base model interface for the DossierActionUser service. Represents a row in the &quot;opencps_dossieractionuser&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.opencps.dossiermgt.model.impl.DossierActionUserModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.opencps.dossiermgt.model.impl.DossierActionUserImpl}.
 * </p>
 *
 * @author huymq
 * @see DossierActionUser
 * @see org.opencps.dossiermgt.model.impl.DossierActionUserImpl
 * @see org.opencps.dossiermgt.model.impl.DossierActionUserModelImpl
 * @generated
 */
@ProviderType
public interface DossierActionUserModel extends BaseModel<DossierActionUser> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dossier action user model instance should use the {@link DossierActionUser} interface instead.
	 */

	/**
	 * Returns the primary key of this dossier action user.
	 *
	 * @return the primary key of this dossier action user
	 */
	public DossierActionUserPK getPrimaryKey();

	/**
	 * Sets the primary key of this dossier action user.
	 *
	 * @param primaryKey the primary key of this dossier action user
	 */
	public void setPrimaryKey(DossierActionUserPK primaryKey);

	/**
	 * Returns the uuid of this dossier action user.
	 *
	 * @return the uuid of this dossier action user
	 */
	@AutoEscape
	public String getUuid();

	/**
	 * Sets the uuid of this dossier action user.
	 *
	 * @param uuid the uuid of this dossier action user
	 */
	public void setUuid(String uuid);

	/**
	 * Returns the dossier action ID of this dossier action user.
	 *
	 * @return the dossier action ID of this dossier action user
	 */
	public long getDossierActionId();

	/**
	 * Sets the dossier action ID of this dossier action user.
	 *
	 * @param dossierActionId the dossier action ID of this dossier action user
	 */
	public void setDossierActionId(long dossierActionId);

	/**
	 * Returns the user ID of this dossier action user.
	 *
	 * @return the user ID of this dossier action user
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this dossier action user.
	 *
	 * @param userId the user ID of this dossier action user
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this dossier action user.
	 *
	 * @return the user uuid of this dossier action user
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this dossier action user.
	 *
	 * @param userUuid the user uuid of this dossier action user
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the dossier ID of this dossier action user.
	 *
	 * @return the dossier ID of this dossier action user
	 */
	public long getDossierId();

	/**
	 * Sets the dossier ID of this dossier action user.
	 *
	 * @param dossierId the dossier ID of this dossier action user
	 */
	public void setDossierId(long dossierId);

	/**
	 * Returns the step code of this dossier action user.
	 *
	 * @return the step code of this dossier action user
	 */
	@AutoEscape
	public String getStepCode();

	/**
	 * Sets the step code of this dossier action user.
	 *
	 * @param stepCode the step code of this dossier action user
	 */
	public void setStepCode(String stepCode);

	/**
	 * Returns the moderator of this dossier action user.
	 *
	 * @return the moderator of this dossier action user
	 */
	public int getModerator();

	/**
	 * Sets the moderator of this dossier action user.
	 *
	 * @param moderator the moderator of this dossier action user
	 */
	public void setModerator(int moderator);

	/**
	 * Returns the assigned of this dossier action user.
	 *
	 * @return the assigned of this dossier action user
	 */
	public int getAssigned();

	/**
	 * Sets the assigned of this dossier action user.
	 *
	 * @param assigned the assigned of this dossier action user
	 */
	public void setAssigned(int assigned);

	/**
	 * Returns the visited of this dossier action user.
	 *
	 * @return the visited of this dossier action user
	 */
	public boolean getVisited();

	/**
	 * Returns <code>true</code> if this dossier action user is visited.
	 *
	 * @return <code>true</code> if this dossier action user is visited; <code>false</code> otherwise
	 */
	public boolean isVisited();

	/**
	 * Sets whether this dossier action user is visited.
	 *
	 * @param visited the visited of this dossier action user
	 */
	public void setVisited(boolean visited);

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
	public int compareTo(DossierActionUser dossierActionUser);

	@Override
	public int hashCode();

	@Override
	public CacheModel<DossierActionUser> toCacheModel();

	@Override
	public DossierActionUser toEscapedModel();

	@Override
	public DossierActionUser toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}