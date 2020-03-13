package com.fds.vr.business.service.persistence.impl;

import java.util.List;

import com.fds.vr.business.model.ILCertificate;
import com.fds.vr.business.model.impl.ILCertificateImpl;
import com.fds.vr.business.service.persistence.ILCertificateFinder;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ILCertificateFinderImpl extends ILCertificateFinderBaseImpl
	implements ILCertificateFinder {

	Log _log = LogFactoryUtil.getLog(ILCertificateFinderImpl.class);

	@SuppressWarnings("unchecked")
	public List<ILCertificate> findILCertificateByServiceCode(String keywords, String serviceCode, String govAgencyCode,
			String routeCode, String fromDate, String toDate, int start, int limit) {

		Session session = null;
		List<ILCertificate> certList = null;

//		StringBuilder sb = null;
//		String sql = StringPool.BLANK;
//		boolean flagSearch = false;
//		if (Validator.isNotNull(serviceCode) || Validator.isNotNull(govAgencyCode)) {
//			if (Validator.isNotNull(routeCode) || Validator.isNotNull(keywords)) {
//				if (Validator.isNotNull(fromDate) || Validator.isNotNull(toDate)) {
//					flagSearch = true;
//				}
//			}
//		}

		String sql = checkInputSearch(keywords, serviceCode, govAgencyCode, routeCode, fromDate, toDate);
		if (start >= 0 && limit > 0) {
			sql += " LIMIT "+start+", "+limit+"";
		}

		_log.info("SQL: " + sql);
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			 q.addEntity("il_certificate", ILCertificateImpl.class);

			certList = q.list();
			// _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return certList;
	}

	public long countILCertificateByServiceCode(String keywords, String serviceCode, String govAgencyCode,
			String routeCode, String fromDate, String toDate) {

		Session session = null;
		long total = 0;

		String sql = checkInputSearch(keywords, serviceCode, govAgencyCode, routeCode, fromDate, toDate);
		
		_log.info("SQL: " + sql);
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			 q.addEntity("il_certificate", ILCertificateImpl.class);

			 total = q.list().size();
			// _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return total;
	}

	private String checkInputSearch(String keywords, String serviceCode, String govAgencyCode, String routeCode,
			String fromDate, String toDate) {
		StringBuilder sb = new StringBuilder();

		int countCheck = 0;
		if (Validator.isNotNull(keywords)) {
			countCheck = 1;
		} else if (Validator.isNotNull(serviceCode)) {
			countCheck = 2;
		} else if (Validator.isNotNull(govAgencyCode)) {
			countCheck = 3;
		} else if (Validator.isNotNull(routeCode)) {
			countCheck = 4;
		} else if (Validator.isNotNull(fromDate)) {
			countCheck = 5;
		} else if (Validator.isNotNull(toDate)) {
			countCheck = 6;
		}
		_log.info("countCheck: "+countCheck);
		//
		if (countCheck > 0) {
			sb.append("SELECT * FROM il_certificate WHERE");
			switch (countCheck) {
			case 1:
				appendKeyWord(sb, keywords, serviceCode, govAgencyCode, routeCode, fromDate, toDate);
				break;
			case 2:
				appendServiceCode(sb, serviceCode, govAgencyCode, routeCode, fromDate, toDate);
				break;
			case 3:
				appendGovAgencyCode(sb, govAgencyCode, routeCode, fromDate, toDate);
				break;
			case 4:
				appendRouteCode(sb, routeCode, fromDate, toDate);
				break;
			case 5:
				appendFromDate(sb, fromDate, toDate);
				break;
			case 6:
				appendToDate(sb, toDate);
				break;
			}
		} else {
			sb.append("SELECT * FROM il_certificate");
		}

		return sb.toString();
		
	}

	private StringBuilder appendKeyWord(StringBuilder sb, String keywords, String serviceCode, String govAgencyCode,
			String routeCode, String fromDate, String toDate) {
		sb.append(" (LOWER(applicantName) LIKE '"+keywords+"'");
		sb.append(" OR LOWER(registrationNumber) LIKE '"+keywords+"')");
		if (Validator.isNotNull(serviceCode)) {
			sb.append(" AND LOWER(serviceCode) IN ("+serviceCode+")");
		}
		if (Validator.isNotNull(govAgencyCode)) {
			sb.append(" AND LOWER(govAgencyCode) = '"+govAgencyCode+"'");
		}
		if (Validator.isNotNull(routeCode)) {
			sb.append(" AND LOWER(routeCode) = '"+routeCode+"'");
		}
		if (Validator.isNotNull(fromDate)) {
			sb.append(" AND DATE(validFrom) = '"+fromDate+"'");
		}
		if (Validator.isNotNull(toDate)) {
			sb.append(" AND DATE(validUntil) = '"+fromDate+"'");
		}
		return sb;
	}

	private StringBuilder appendServiceCode(StringBuilder sb, String serviceCode, String govAgencyCode,
			String routeCode, String fromDate, String toDate) {
		sb.append(" LOWER(serviceCode) IN ("+serviceCode+")");
		if (Validator.isNotNull(govAgencyCode)) {
			sb.append(" AND LOWER(govAgencyCode) = '"+govAgencyCode+"'");
		}
		if (Validator.isNotNull(routeCode)) {
			sb.append(" AND LOWER(routeCode) = '"+routeCode+"'");
		}
		if (Validator.isNotNull(fromDate)) {
			sb.append(" AND DATE(validFrom) = '"+fromDate+"'");
		}
		if (Validator.isNotNull(toDate)) {
			sb.append(" AND DATE(validUntil) = '"+fromDate+"'");
		}
		return sb;
	}

	private StringBuilder appendGovAgencyCode(StringBuilder sb, String govAgencyCode, String routeCode, String fromDate,
			String toDate) {
		sb.append(" LOWER(govAgencyCode) = '"+govAgencyCode+"'");
		if (Validator.isNotNull(routeCode)) {
			sb.append(" AND LOWER(routeCode) = '"+routeCode+"'");
		}
		if (Validator.isNotNull(fromDate)) {
			sb.append(" AND DATE(validFrom) = '"+fromDate+"'");
		}
		if (Validator.isNotNull(toDate)) {
			sb.append(" AND DATE(validUntil) = '"+fromDate+"'");
		}
		return sb;
	}

	private StringBuilder appendRouteCode(StringBuilder sb, String routeCode, String fromDate, String toDate) {
		sb.append(" LOWER(routeCode) = '"+routeCode+"'");
		if (Validator.isNotNull(fromDate)) {
			sb.append(" AND DATE(validFrom) = '"+fromDate+"'");
		}
		if (Validator.isNotNull(toDate)) {
			sb.append(" AND DATE(validUntil) = '"+fromDate+"'");
		}
		return sb;
	}

	private StringBuilder appendFromDate(StringBuilder sb, String fromDate, String toDate) {
		sb.append(" DATE(validFrom) = '"+fromDate+"'");
		if (Validator.isNotNull(toDate)) {
			sb.append(" AND DATE(validUntil) = '"+fromDate+"'");
		}
		return sb;
	}

	private StringBuilder appendToDate(StringBuilder sb, String toDate) {
		return sb.append(" DATE(validUntil) = '"+toDate+"'");
	}

	@SuppressWarnings("unchecked")
	public List<ILCertificate> findILCertificateByApplicant(String serviceCode, String applicant) {

		Session session = null;
		List<ILCertificate> certList = null;

		String sql = "SELECT * FROM il_certificate"
				+ " WHERE LOWER(serviceCode) = '"+serviceCode+"'"
				+ " AND certificateId IN ("
				+ "SELECT MAX(certificateId) FROM il_certificate GROUP BY applicantIdNo)";

		_log.info("SQL: " + sql);
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			 q.addEntity("il_certificate", ILCertificateImpl.class);

			certList = q.list();
			// _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return certList;
	}

	public long countILCertificateByApplicant(String serviceCode, String applicant) {

		Session session = null;
		long total = 0;

		String sql = "SELECT * FROM il_certificate"
				+ " WHERE LOWER(serviceCode) = '"+serviceCode+"'"
				+ " AND certificateId IN ("
				+ "SELECT MAX(certificateId) FROM il_certificate GROUP BY applicantIdNo)";
		
		_log.info("SQL: " + sql);
		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);
			q.setCacheable(false);
			 q.addEntity("il_certificate", ILCertificateImpl.class);

			 total = q.list().size();
			// _log.info("SQL list deliverable: "+ deliverableList);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}

		return total;
	}

}
