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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceFileTemplateModel;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the ServiceFileTemplate service. Represents a row in the &quot;opencps_services_filetemplates&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ServiceFileTemplateModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ServiceFileTemplateImpl}.
 * </p>
 *
 * @author huymq
 * @see ServiceFileTemplateImpl
 * @see ServiceFileTemplate
 * @see ServiceFileTemplateModel
 * @generated
 */
@ProviderType
public class ServiceFileTemplateModelImpl extends BaseModelImpl<ServiceFileTemplate>
	implements ServiceFileTemplateModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a service file template model instance should use the {@link ServiceFileTemplate} interface instead.
	 */
	public static final String TABLE_NAME = "opencps_services_filetemplates";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "serviceInfoId", Types.BIGINT },
			{ "fileTemplateNo", Types.VARCHAR },
			{ "templateName", Types.VARCHAR },
			{ "fileEntryId", Types.BIGINT }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("serviceInfoId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("fileTemplateNo", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("templateName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("fileEntryId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE = "create table opencps_services_filetemplates (uuid_ VARCHAR(75) null,serviceInfoId LONG not null,fileTemplateNo VARCHAR(75) not null,templateName VARCHAR(1000) null,fileEntryId LONG,primary key (serviceInfoId, fileTemplateNo))";
	public static final String TABLE_SQL_DROP = "drop table opencps_services_filetemplates";
	public static final String ORDER_BY_JPQL = " ORDER BY serviceFileTemplate.id.serviceInfoId ASC, serviceFileTemplate.id.fileTemplateNo ASC";
	public static final String ORDER_BY_SQL = " ORDER BY opencps_services_filetemplates.serviceInfoId ASC, opencps_services_filetemplates.fileTemplateNo ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.org.opencps.dossiermgt.model.ServiceFileTemplate"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.org.opencps.dossiermgt.model.ServiceFileTemplate"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.org.opencps.dossiermgt.model.ServiceFileTemplate"),
			true);
	public static final long SERVICEINFOID_COLUMN_BITMASK = 1L;
	public static final long UUID_COLUMN_BITMASK = 2L;
	public static final long FILETEMPLATENO_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(org.opencps.backend.dossiermgt.service.util.ServiceProps.get(
				"lock.expiration.time.org.opencps.dossiermgt.model.ServiceFileTemplate"));

	public ServiceFileTemplateModelImpl() {
	}

	@Override
	public ServiceFileTemplatePK getPrimaryKey() {
		return new ServiceFileTemplatePK(_serviceInfoId, _fileTemplateNo);
	}

	@Override
	public void setPrimaryKey(ServiceFileTemplatePK primaryKey) {
		setServiceInfoId(primaryKey.serviceInfoId);
		setFileTemplateNo(primaryKey.fileTemplateNo);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new ServiceFileTemplatePK(_serviceInfoId, _fileTemplateNo);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((ServiceFileTemplatePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return ServiceFileTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return ServiceFileTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("serviceInfoId", getServiceInfoId());
		attributes.put("fileTemplateNo", getFileTemplateNo());
		attributes.put("templateName", getTemplateName());
		attributes.put("fileEntryId", getFileEntryId());

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

		Long serviceInfoId = (Long)attributes.get("serviceInfoId");

		if (serviceInfoId != null) {
			setServiceInfoId(serviceInfoId);
		}

		String fileTemplateNo = (String)attributes.get("fileTemplateNo");

		if (fileTemplateNo != null) {
			setFileTemplateNo(fileTemplateNo);
		}

		String templateName = (String)attributes.get("templateName");

		if (templateName != null) {
			setTemplateName(templateName);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
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
	public long getServiceInfoId() {
		return _serviceInfoId;
	}

	@Override
	public void setServiceInfoId(long serviceInfoId) {
		_columnBitmask |= SERVICEINFOID_COLUMN_BITMASK;

		if (!_setOriginalServiceInfoId) {
			_setOriginalServiceInfoId = true;

			_originalServiceInfoId = _serviceInfoId;
		}

		_serviceInfoId = serviceInfoId;
	}

	public long getOriginalServiceInfoId() {
		return _originalServiceInfoId;
	}

	@Override
	public String getFileTemplateNo() {
		if (_fileTemplateNo == null) {
			return "";
		}
		else {
			return _fileTemplateNo;
		}
	}

	@Override
	public void setFileTemplateNo(String fileTemplateNo) {
		_fileTemplateNo = fileTemplateNo;
	}

	@Override
	public String getTemplateName() {
		if (_templateName == null) {
			return "";
		}
		else {
			return _templateName;
		}
	}

	@Override
	public void setTemplateName(String templateName) {
		_templateName = templateName;
	}

	@Override
	public long getFileEntryId() {
		return _fileEntryId;
	}

	@Override
	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ServiceFileTemplate toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ServiceFileTemplate)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ServiceFileTemplateImpl serviceFileTemplateImpl = new ServiceFileTemplateImpl();

		serviceFileTemplateImpl.setUuid(getUuid());
		serviceFileTemplateImpl.setServiceInfoId(getServiceInfoId());
		serviceFileTemplateImpl.setFileTemplateNo(getFileTemplateNo());
		serviceFileTemplateImpl.setTemplateName(getTemplateName());
		serviceFileTemplateImpl.setFileEntryId(getFileEntryId());

		serviceFileTemplateImpl.resetOriginalValues();

		return serviceFileTemplateImpl;
	}

	@Override
	public int compareTo(ServiceFileTemplate serviceFileTemplate) {
		ServiceFileTemplatePK primaryKey = serviceFileTemplate.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ServiceFileTemplate)) {
			return false;
		}

		ServiceFileTemplate serviceFileTemplate = (ServiceFileTemplate)obj;

		ServiceFileTemplatePK primaryKey = serviceFileTemplate.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		ServiceFileTemplateModelImpl serviceFileTemplateModelImpl = this;

		serviceFileTemplateModelImpl._originalUuid = serviceFileTemplateModelImpl._uuid;

		serviceFileTemplateModelImpl._originalServiceInfoId = serviceFileTemplateModelImpl._serviceInfoId;

		serviceFileTemplateModelImpl._setOriginalServiceInfoId = false;

		serviceFileTemplateModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ServiceFileTemplate> toCacheModel() {
		ServiceFileTemplateCacheModel serviceFileTemplateCacheModel = new ServiceFileTemplateCacheModel();

		serviceFileTemplateCacheModel.serviceFileTemplatePK = getPrimaryKey();

		serviceFileTemplateCacheModel.uuid = getUuid();

		String uuid = serviceFileTemplateCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			serviceFileTemplateCacheModel.uuid = null;
		}

		serviceFileTemplateCacheModel.serviceInfoId = getServiceInfoId();

		serviceFileTemplateCacheModel.fileTemplateNo = getFileTemplateNo();

		String fileTemplateNo = serviceFileTemplateCacheModel.fileTemplateNo;

		if ((fileTemplateNo != null) && (fileTemplateNo.length() == 0)) {
			serviceFileTemplateCacheModel.fileTemplateNo = null;
		}

		serviceFileTemplateCacheModel.templateName = getTemplateName();

		String templateName = serviceFileTemplateCacheModel.templateName;

		if ((templateName != null) && (templateName.length() == 0)) {
			serviceFileTemplateCacheModel.templateName = null;
		}

		serviceFileTemplateCacheModel.fileEntryId = getFileEntryId();

		return serviceFileTemplateCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", serviceInfoId=");
		sb.append(getServiceInfoId());
		sb.append(", fileTemplateNo=");
		sb.append(getFileTemplateNo());
		sb.append(", templateName=");
		sb.append(getTemplateName());
		sb.append(", fileEntryId=");
		sb.append(getFileEntryId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("org.opencps.dossiermgt.model.ServiceFileTemplate");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>serviceInfoId</column-name><column-value><![CDATA[");
		sb.append(getServiceInfoId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileTemplateNo</column-name><column-value><![CDATA[");
		sb.append(getFileTemplateNo());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>templateName</column-name><column-value><![CDATA[");
		sb.append(getTemplateName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileEntryId</column-name><column-value><![CDATA[");
		sb.append(getFileEntryId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ServiceFileTemplate.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ServiceFileTemplate.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _serviceInfoId;
	private long _originalServiceInfoId;
	private boolean _setOriginalServiceInfoId;
	private String _fileTemplateNo;
	private String _templateName;
	private long _fileEntryId;
	private long _columnBitmask;
	private ServiceFileTemplate _escapedModel;
}