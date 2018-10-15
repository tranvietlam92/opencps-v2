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

package org.opencps.dossiermgt.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.RegistrationForm;
import org.opencps.dossiermgt.model.RegistrationFormModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the RegistrationForm service. Represents a row in the &quot;opencps_registrationform&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link RegistrationFormModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RegistrationFormImpl}.
 * </p>
 *
 * @author huymq
 * @see RegistrationFormImpl
 * @see RegistrationForm
 * @see RegistrationFormModel
 * @generated
 */
@ProviderType
public class RegistrationFormModelImpl extends BaseModelImpl<RegistrationForm>
	implements RegistrationFormModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a registration form model instance should use the {@link RegistrationForm} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_registrationform";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "registrationFormId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "registrationId", Types.BIGINT },
			{ "referenceUid", Types.VARCHAR },
			{ "formNo", Types.VARCHAR },
			{ "formName", Types.VARCHAR },
			{ "formData", Types.CLOB },
			{ "formScript", Types.CLOB },
			{ "formReport", Types.CLOB },
			{ "fileEntryId", Types.BIGINT },
			{ "isNew", Types.BOOLEAN },
			{ "removed", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("registrationFormId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("registrationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("referenceUid", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("formData", Types.CLOB);
		TABLE_COLUMNS_MAP.put("formScript", Types.CLOB);
		TABLE_COLUMNS_MAP.put("formReport", Types.CLOB);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("isNew", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("removed", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_registrationform (uuid_ VARCHAR(75) null,registrationFormId LONG not null primary key,companyId LONG,groupId LONG,userId LONG,createDate DATE null,modifiedDate DATE null,registrationId LONG,referenceUid VARCHAR(75) null,formNo VARCHAR(75) null,formName VARCHAR(255) null,formData TEXT null,formScript TEXT null,formReport TEXT null,fileEntryId LONG,isNew BOOLEAN,removed BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table opencps_registrationform";
	public static final String ORDER_BY_JPQL = " ORDER BY registrationForm.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_registrationform.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.dossiermgt.model.RegistrationForm"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.dossiermgt.model.RegistrationForm"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.dossiermgt.model.RegistrationForm"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long FORMNO_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long ISNEW_COLUMN_BITMASK = 8L;
	public static final long REFERENCEUID_COLUMN_BITMASK = 16L;
	public static final long REGISTRATIONID_COLUMN_BITMASK = 32L;
	public static final long UUID_COLUMN_BITMASK = 64L;
	public static final long CREATEDATE_COLUMN_BITMASK = 128L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.dossiermgt.model.RegistrationForm"));

	public RegistrationFormModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _registrationFormId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRegistrationFormId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _registrationFormId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return RegistrationForm.class;
	}

	@Override
	public String getModelClassName() {
		return RegistrationForm.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("registrationFormId", getRegistrationFormId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("registrationId", getRegistrationId());
		attributes.put("referenceUid", getReferenceUid());
		attributes.put("formNo", getFormNo());
		attributes.put("formName", getFormName());
		attributes.put("formData", getFormData());
		attributes.put("formScript", getFormScript());
		attributes.put("formReport", getFormReport());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("isNew", isIsNew());
		attributes.put("removed", isRemoved());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long registrationFormId = (Long)attributes.get("registrationFormId");

		if (registrationFormId != null) {
			setRegistrationFormId(registrationFormId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long registrationId = (Long)attributes.get("registrationId");

		if (registrationId != null) {
			setRegistrationId(registrationId);
		}

		String referenceUid = (String)attributes.get("referenceUid");

		if (referenceUid != null) {
			setReferenceUid(referenceUid);
		}

		String formNo = (String)attributes.get("formNo");

		if (formNo != null) {
			setFormNo(formNo);
		}

		String formName = (String)attributes.get("formName");

		if (formName != null) {
			setFormName(formName);
		}

		String formData = (String)attributes.get("formData");

		if (formData != null) {
			setFormData(formData);
		}

		String formScript = (String)attributes.get("formScript");

		if (formScript != null) {
			setFormScript(formScript);
		}

		String formReport = (String)attributes.get("formReport");

		if (formReport != null) {
			setFormReport(formReport);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Boolean isNew = (Boolean)attributes.get("isNew");

		if (isNew != null) {
			setIsNew(isNew);
		}

		Boolean removed = (Boolean)attributes.get("removed");

		if (removed != null) {
			setRemoved(removed);
		}
	}

	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@Override
	public long getRegistrationFormId() {
		return _registrationFormId;
	}

	@Override
	public void setRegistrationFormId(long registrationFormId) {
		_registrationFormId = registrationFormId;
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@Override
	public long getRegistrationId() {
		return _registrationId;
	}

	@Override
	public void setRegistrationId(long registrationId) {
		_columnBitmask |= REGISTRATIONID_COLUMN_BITMASK;

		if (!_setOriginalRegistrationId) {
			_setOriginalRegistrationId = true;

			_originalRegistrationId = _registrationId;
		}

		_registrationId = registrationId;
	}

	public long getOriginalRegistrationId() {
		return _originalRegistrationId;
	}

	@Override
	public String getReferenceUid() {
		if (_referenceUid == null) {
			return "";
		}
		else {
			return _referenceUid;
		}
	}

	@Override
	public void setReferenceUid(String referenceUid) {
		_columnBitmask |= REFERENCEUID_COLUMN_BITMASK;

		if (_originalReferenceUid == null) {
			_originalReferenceUid = _referenceUid;
		}

		_referenceUid = referenceUid;
	}

	public String getOriginalReferenceUid() {
		return GetterUtil.getString(_originalReferenceUid);
	}

	@Override
	public String getFormNo() {
		if (_formNo == null) {
			return "";
		}
		else {
			return _formNo;
		}
	}

	@Override
	public void setFormNo(String formNo) {
		_columnBitmask |= FORMNO_COLUMN_BITMASK;

		if (_originalFormNo == null) {
			_originalFormNo = _formNo;
		}

		_formNo = formNo;
	}

	public String getOriginalFormNo() {
		return GetterUtil.getString(_originalFormNo);
	}

	@Override
	public String getFormName() {
		if (_formName == null) {
			return "";
		}
		else {
			return _formName;
		}
	}

	@Override
	public void setFormName(String formName) {
		_formName = formName;
	}

	@Override
	public String getFormData() {
		if (_formData == null) {
			return "";
		}
		else {
			return _formData;
		}
	}

	@Override
	public void setFormData(String formData) {
		_formData = formData;
	}

	@Override
	public String getFormScript() {
		if (_formScript == null) {
			return "";
		}
		else {
			return _formScript;
		}
	}

	@Override
	public void setFormScript(String formScript) {
		_formScript = formScript;
	}

	@Override
	public String getFormReport() {
		if (_formReport == null) {
			return "";
		}
		else {
			return _formReport;
		}
	}

	@Override
	public void setFormReport(String formReport) {
		_formReport = formReport;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	@Override
	public boolean getIsNew() {
		return _isNew;
	}

	@Override
	public boolean isIsNew() {
		return _isNew;
	}

	@Override
	public void setIsNew(boolean isNew) {
		_columnBitmask |= ISNEW_COLUMN_BITMASK;

		if (!_setOriginalIsNew) {
			_setOriginalIsNew = true;

			_originalIsNew = _isNew;
		}

		_isNew = isNew;
	}

	public boolean getOriginalIsNew() {
		return _originalIsNew;
	}

	@Override
	public boolean getRemoved() {
		return _removed;
	}

	@Override
	public boolean isRemoved() {
		return _removed;
	}

	@Override
	public void setRemoved(boolean removed) {
		_removed = removed;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				RegistrationForm.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			RegistrationForm.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public RegistrationForm toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (RegistrationForm)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		RegistrationFormImpl registrationFormImpl = new RegistrationFormImpl();

		registrationFormImpl.setUuid(getUuid());
		registrationFormImpl.setRegistrationFormId(getRegistrationFormId());
		registrationFormImpl.setCompanyId(getCompanyId());
		registrationFormImpl.setGroupId(getGroupId());
		registrationFormImpl.setUserId(getUserId());
		registrationFormImpl.setCreateDate(getCreateDate());
		registrationFormImpl.setModifiedDate(getModifiedDate());
		registrationFormImpl.setRegistrationId(getRegistrationId());
		registrationFormImpl.setReferenceUid(getReferenceUid());
		registrationFormImpl.setFormNo(getFormNo());
		registrationFormImpl.setFormName(getFormName());
		registrationFormImpl.setFormData(getFormData());
		registrationFormImpl.setFormScript(getFormScript());
		registrationFormImpl.setFormReport(getFormReport());
		registrationFormImpl.setFileEntryId(getFileEntryId());
		registrationFormImpl.setIsNew(isIsNew());
		registrationFormImpl.setRemoved(isRemoved());

		registrationFormImpl.resetOriginalValues();

		return registrationFormImpl;
	}

	@Override
	public int compareTo(RegistrationForm registrationForm) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				registrationForm.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RegistrationForm)) {
			return false;
		}

		RegistrationForm registrationForm = (RegistrationForm)obj;

		long primaryKey = registrationForm.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		RegistrationFormModelImpl registrationFormModelImpl = this;

		registrationFormModelImpl._originalUuid = registrationFormModelImpl._uuid;

		registrationFormModelImpl._originalCompanyId = registrationFormModelImpl._companyId;

		registrationFormModelImpl._setOriginalCompanyId = false;

		registrationFormModelImpl._originalGroupId = registrationFormModelImpl._groupId;

		registrationFormModelImpl._setOriginalGroupId = false;

		registrationFormModelImpl._setModifiedDate = false;

		registrationFormModelImpl._originalRegistrationId = registrationFormModelImpl._registrationId;

		registrationFormModelImpl._setOriginalRegistrationId = false;

		registrationFormModelImpl._originalReferenceUid = registrationFormModelImpl._referenceUid;

		registrationFormModelImpl._originalFormNo = registrationFormModelImpl._formNo;

		registrationFormModelImpl._originalIsNew = registrationFormModelImpl._isNew;

		registrationFormModelImpl._setOriginalIsNew = false;

		registrationFormModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<RegistrationForm> toCacheModel() {
		RegistrationFormCacheModel registrationFormCacheModel = new RegistrationFormCacheModel();

		registrationFormCacheModel.uuid = getUuid();

		String uuid = registrationFormCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			registrationFormCacheModel.uuid = null;
		}

		registrationFormCacheModel.registrationFormId = getRegistrationFormId();

		registrationFormCacheModel.companyId = getCompanyId();

		registrationFormCacheModel.groupId = getGroupId();

		registrationFormCacheModel.userId = getUserId();

		Date createDate = getCreateDate();

		if (createDate != null) {
			registrationFormCacheModel.createDate = createDate.getTime();
		}
		else {
			registrationFormCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			registrationFormCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			registrationFormCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		registrationFormCacheModel.registrationId = getRegistrationId();

		registrationFormCacheModel.referenceUid = getReferenceUid();

		String referenceUid = registrationFormCacheModel.referenceUid;

		if ((referenceUid != null) && (referenceUid.length() == 0)) {
			registrationFormCacheModel.referenceUid = null;
		}

		registrationFormCacheModel.formNo = getFormNo();

		String formNo = registrationFormCacheModel.formNo;

		if ((formNo != null) && (formNo.length() == 0)) {
			registrationFormCacheModel.formNo = null;
		}

		registrationFormCacheModel.formName = getFormName();

		String formName = registrationFormCacheModel.formName;

		if ((formName != null) && (formName.length() == 0)) {
			registrationFormCacheModel.formName = null;
		}

		registrationFormCacheModel.formData = getFormData();

		String formData = registrationFormCacheModel.formData;

		if ((formData != null) && (formData.length() == 0)) {
			registrationFormCacheModel.formData = null;
		}

		registrationFormCacheModel.formScript = getFormScript();

		String formScript = registrationFormCacheModel.formScript;

		if ((formScript != null) && (formScript.length() == 0)) {
			registrationFormCacheModel.formScript = null;
		}

		registrationFormCacheModel.formReport = getFormReport();

		String formReport = registrationFormCacheModel.formReport;

		if ((formReport != null) && (formReport.length() == 0)) {
			registrationFormCacheModel.formReport = null;
		}

		registrationFormCacheModel.fileEntryId = getFileEntryId();

		registrationFormCacheModel.isNew = isIsNew();

		registrationFormCacheModel.removed = isRemoved();

		return registrationFormCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", registrationFormId=");
		sb.append(getRegistrationFormId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", registrationId=");
		sb.append(getRegistrationId());
		sb.append(", referenceUid=");
		sb.append(getReferenceUid());
		sb.append(", formNo=");
		sb.append(getFormNo());
		sb.append(", formName=");
		sb.append(getFormName());
		sb.append(", formData=");
		sb.append(getFormData());
		sb.append(", formScript=");
		sb.append(getFormScript());
		sb.append(", formReport=");
		sb.append(getFormReport());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append(", isNew=");
		sb.append(isIsNew());
		sb.append(", removed=");
		sb.append(isRemoved());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("org.opencps.dossiermgt.model.RegistrationForm");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationFormId</column-name><column-value><![CDATA[");
		sb.append(getRegistrationFormId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>registrationId</column-name><column-value><![CDATA[");
		sb.append(getRegistrationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referenceUid</column-name><column-value><![CDATA[");
		sb.append(getReferenceUid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>formNo</column-name><column-value><![CDATA[");
		sb.append(getFormNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>formName</column-name><column-value><![CDATA[");
		sb.append(getFormName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>formData</column-name><column-value><![CDATA[");
		sb.append(getFormData());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>formScript</column-name><column-value><![CDATA[");
		sb.append(getFormScript());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>formReport</column-name><column-value><![CDATA[");
		sb.append(getFormReport());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>isNew</column-name><column-value><![CDATA[");
		sb.append(isIsNew());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>removed</column-name><column-value><![CDATA[");
		sb.append(isRemoved());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = RegistrationForm.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			RegistrationForm.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _registrationFormId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _userId;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _registrationId;
	private long _originalRegistrationId;
	private boolean _setOriginalRegistrationId;
	private String _referenceUid;
	private String _originalReferenceUid;
	private String _formNo;
	private String _originalFormNo;
	private String _formName;
	private String _formData;
	private String _formScript;
	private String _formReport;
	private long _fileEntryId;
	private boolean _isNew;
	private boolean _originalIsNew;
	private boolean _setOriginalIsNew;
	private boolean _removed;
	private long _columnBitmask;
	private RegistrationForm _escapedModel;
}