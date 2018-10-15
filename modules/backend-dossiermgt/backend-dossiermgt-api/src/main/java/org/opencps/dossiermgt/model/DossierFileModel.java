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
import com.liferay.portal.kernel.model.GroupedModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the DossierFile service. Represents a row in the &quot;opencps_dossierfile&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link org.opencps.dossiermgt.model.impl.DossierFileModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link org.opencps.dossiermgt.model.impl.DossierFileImpl}.
 * </p>
 *
 * @author huymq
 * @see DossierFile
 * @see org.opencps.dossiermgt.model.impl.DossierFileImpl
 * @see org.opencps.dossiermgt.model.impl.DossierFileModelImpl
 * @generated
 */
@ProviderType
public interface DossierFileModel extends BaseModel<DossierFile>, GroupedModel,
	ShardedModel, StagedAuditedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dossier file model instance should use the {@link DossierFile} interface instead.
	 */

	/**
	 * Returns the primary key of this dossier file.
	 *
	 * @return the primary key of this dossier file
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this dossier file.
	 *
	 * @param primaryKey the primary key of this dossier file
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this dossier file.
	 *
	 * @return the uuid of this dossier file
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this dossier file.
	 *
	 * @param uuid the uuid of this dossier file
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the dossier file ID of this dossier file.
	 *
	 * @return the dossier file ID of this dossier file
	 */
	public long getDossierFileId();

	/**
	 * Sets the dossier file ID of this dossier file.
	 *
	 * @param dossierFileId the dossier file ID of this dossier file
	 */
	public void setDossierFileId(long dossierFileId);

	/**
	 * Returns the group ID of this dossier file.
	 *
	 * @return the group ID of this dossier file
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this dossier file.
	 *
	 * @param groupId the group ID of this dossier file
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this dossier file.
	 *
	 * @return the company ID of this dossier file
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this dossier file.
	 *
	 * @param companyId the company ID of this dossier file
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this dossier file.
	 *
	 * @return the user ID of this dossier file
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this dossier file.
	 *
	 * @param userId the user ID of this dossier file
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this dossier file.
	 *
	 * @return the user uuid of this dossier file
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this dossier file.
	 *
	 * @param userUuid the user uuid of this dossier file
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this dossier file.
	 *
	 * @return the user name of this dossier file
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this dossier file.
	 *
	 * @param userName the user name of this dossier file
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this dossier file.
	 *
	 * @return the create date of this dossier file
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this dossier file.
	 *
	 * @param createDate the create date of this dossier file
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this dossier file.
	 *
	 * @return the modified date of this dossier file
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this dossier file.
	 *
	 * @param modifiedDate the modified date of this dossier file
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the dossier ID of this dossier file.
	 *
	 * @return the dossier ID of this dossier file
	 */
	public long getDossierId();

	/**
	 * Sets the dossier ID of this dossier file.
	 *
	 * @param dossierId the dossier ID of this dossier file
	 */
	public void setDossierId(long dossierId);

	/**
	 * Returns the reference uid of this dossier file.
	 *
	 * @return the reference uid of this dossier file
	 */
	@AutoEscape
	public String getReferenceUid();

	/**
	 * Sets the reference uid of this dossier file.
	 *
	 * @param referenceUid the reference uid of this dossier file
	 */
	public void setReferenceUid(String referenceUid);

	/**
	 * Returns the dossier template no of this dossier file.
	 *
	 * @return the dossier template no of this dossier file
	 */
	@AutoEscape
	public String getDossierTemplateNo();

	/**
	 * Sets the dossier template no of this dossier file.
	 *
	 * @param dossierTemplateNo the dossier template no of this dossier file
	 */
	public void setDossierTemplateNo(String dossierTemplateNo);

	/**
	 * Returns the dossier part no of this dossier file.
	 *
	 * @return the dossier part no of this dossier file
	 */
	@AutoEscape
	public String getDossierPartNo();

	/**
	 * Sets the dossier part no of this dossier file.
	 *
	 * @param dossierPartNo the dossier part no of this dossier file
	 */
	public void setDossierPartNo(String dossierPartNo);

	/**
	 * Returns the dossier part type of this dossier file.
	 *
	 * @return the dossier part type of this dossier file
	 */
	public int getDossierPartType();

	/**
	 * Sets the dossier part type of this dossier file.
	 *
	 * @param dossierPartType the dossier part type of this dossier file
	 */
	public void setDossierPartType(int dossierPartType);

	/**
	 * Returns the file template no of this dossier file.
	 *
	 * @return the file template no of this dossier file
	 */
	@AutoEscape
	public String getFileTemplateNo();

	/**
	 * Sets the file template no of this dossier file.
	 *
	 * @param fileTemplateNo the file template no of this dossier file
	 */
	public void setFileTemplateNo(String fileTemplateNo);

	/**
	 * Returns the display name of this dossier file.
	 *
	 * @return the display name of this dossier file
	 */
	@AutoEscape
	public String getDisplayName();

	/**
	 * Sets the display name of this dossier file.
	 *
	 * @param displayName the display name of this dossier file
	 */
	public void setDisplayName(String displayName);

	/**
	 * Returns the form data of this dossier file.
	 *
	 * @return the form data of this dossier file
	 */
	@AutoEscape
	public String getFormData();

	/**
	 * Sets the form data of this dossier file.
	 *
	 * @param formData the form data of this dossier file
	 */
	public void setFormData(String formData);

	/**
	 * Returns the file entry ID of this dossier file.
	 *
	 * @return the file entry ID of this dossier file
	 */
	public long getFileEntryId();

	/**
	 * Sets the file entry ID of this dossier file.
	 *
	 * @param fileEntryId the file entry ID of this dossier file
	 */
	public void setFileEntryId(long fileEntryId);

	/**
	 * Returns the original of this dossier file.
	 *
	 * @return the original of this dossier file
	 */
	public boolean getOriginal();

	/**
	 * Returns <code>true</code> if this dossier file is original.
	 *
	 * @return <code>true</code> if this dossier file is original; <code>false</code> otherwise
	 */
	public boolean isOriginal();

	/**
	 * Sets whether this dossier file is original.
	 *
	 * @param original the original of this dossier file
	 */
	public void setOriginal(boolean original);

	/**
	 * Returns the e form of this dossier file.
	 *
	 * @return the e form of this dossier file
	 */
	public boolean getEForm();

	/**
	 * Returns <code>true</code> if this dossier file is e form.
	 *
	 * @return <code>true</code> if this dossier file is e form; <code>false</code> otherwise
	 */
	public boolean isEForm();

	/**
	 * Sets whether this dossier file is e form.
	 *
	 * @param eForm the e form of this dossier file
	 */
	public void setEForm(boolean eForm);

	/**
	 * Returns the is new of this dossier file.
	 *
	 * @return the is new of this dossier file
	 */
	public boolean getIsNew();

	/**
	 * Returns <code>true</code> if this dossier file is is new.
	 *
	 * @return <code>true</code> if this dossier file is is new; <code>false</code> otherwise
	 */
	public boolean isIsNew();

	/**
	 * Sets whether this dossier file is is new.
	 *
	 * @param isNew the is new of this dossier file
	 */
	public void setIsNew(boolean isNew);

	/**
	 * Returns the removed of this dossier file.
	 *
	 * @return the removed of this dossier file
	 */
	public boolean getRemoved();

	/**
	 * Returns <code>true</code> if this dossier file is removed.
	 *
	 * @return <code>true</code> if this dossier file is removed; <code>false</code> otherwise
	 */
	public boolean isRemoved();

	/**
	 * Sets whether this dossier file is removed.
	 *
	 * @param removed the removed of this dossier file
	 */
	public void setRemoved(boolean removed);

	/**
	 * Returns the sign check of this dossier file.
	 *
	 * @return the sign check of this dossier file
	 */
	public int getSignCheck();

	/**
	 * Sets the sign check of this dossier file.
	 *
	 * @param signCheck the sign check of this dossier file
	 */
	public void setSignCheck(int signCheck);

	/**
	 * Returns the sign info of this dossier file.
	 *
	 * @return the sign info of this dossier file
	 */
	@AutoEscape
	public String getSignInfo();

	/**
	 * Sets the sign info of this dossier file.
	 *
	 * @param signInfo the sign info of this dossier file
	 */
	public void setSignInfo(String signInfo);

	/**
	 * Returns the form script of this dossier file.
	 *
	 * @return the form script of this dossier file
	 */
	@AutoEscape
	public String getFormScript();

	/**
	 * Sets the form script of this dossier file.
	 *
	 * @param formScript the form script of this dossier file
	 */
	public void setFormScript(String formScript);

	/**
	 * Returns the form report of this dossier file.
	 *
	 * @return the form report of this dossier file
	 */
	@AutoEscape
	public String getFormReport();

	/**
	 * Sets the form report of this dossier file.
	 *
	 * @param formReport the form report of this dossier file
	 */
	public void setFormReport(String formReport);

	/**
	 * Returns the form schema of this dossier file.
	 *
	 * @return the form schema of this dossier file
	 */
	@AutoEscape
	public String getFormSchema();

	/**
	 * Sets the form schema of this dossier file.
	 *
	 * @param formSchema the form schema of this dossier file
	 */
	public void setFormSchema(String formSchema);

	/**
	 * Returns the deliverable code of this dossier file.
	 *
	 * @return the deliverable code of this dossier file
	 */
	@AutoEscape
	public String getDeliverableCode();

	/**
	 * Sets the deliverable code of this dossier file.
	 *
	 * @param deliverableCode the deliverable code of this dossier file
	 */
	public void setDeliverableCode(String deliverableCode);

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
	public int compareTo(DossierFile dossierFile);

	@Override
	public int hashCode();

	@Override
	public CacheModel<DossierFile> toCacheModel();

	@Override
	public DossierFile toEscapedModel();

	@Override
	public DossierFile toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}