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

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the DossierActionUser service. Represents a row in the &quot;opencps_dossieractionuser&quot; database table, with each column mapped to a property of this class.
 *
 * @author huymq
 * @see DossierActionUserModel
 * @see org.opencps.dossiermgt.model.impl.DossierActionUserImpl
 * @see org.opencps.dossiermgt.model.impl.DossierActionUserModelImpl
 * @generated
 */
@ImplementationClassName("org.opencps.dossiermgt.model.impl.DossierActionUserImpl")
@ProviderType
public interface DossierActionUser extends DossierActionUserModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link org.opencps.dossiermgt.model.impl.DossierActionUserImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<DossierActionUser, Long> DOSSIER_ACTION_ID_ACCESSOR =
		new Accessor<DossierActionUser, Long>() {
			@Override
			public Long get(DossierActionUser dossierActionUser) {
				return dossierActionUser.getDossierActionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DossierActionUser> getTypeClass() {
				return DossierActionUser.class;
			}
		};

	public static final Accessor<DossierActionUser, Long> USER_ID_ACCESSOR = new Accessor<DossierActionUser, Long>() {
			@Override
			public Long get(DossierActionUser dossierActionUser) {
				return dossierActionUser.getUserId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<DossierActionUser> getTypeClass() {
				return DossierActionUser.class;
			}
		};
}