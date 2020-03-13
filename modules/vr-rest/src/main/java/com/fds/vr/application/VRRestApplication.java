package com.fds.vr.application;

import com.fds.vr.business.action.ILCertificateActions;
import com.fds.vr.business.action.VRActions;
import com.fds.vr.business.action.impl.DictItemUtil;
import com.fds.vr.business.action.impl.ILCertificateActionsImpl;
import com.fds.vr.business.action.impl.VRActionsImpl;
import com.fds.vr.business.action.util.ConvertFormatDate;
import com.fds.vr.business.model.ILCertificate;
import com.fds.vr.business.model.ILVehicleCustomsBorderGuard;
import com.fds.vr.business.model.VRReport;
import com.fds.vr.business.service.VRReportLocalServiceUtil;
import com.fds.vr.ilcertificate.model.ILCBGuardModel;
import com.fds.vr.ilcertificate.model.ILCBGuardResultModel;
import com.fds.vr.ilcertificate.model.ILCertificateModel;
import com.fds.vr.ilcertificate.model.ILCertificateResultModel;
import com.fds.vr.util.ILCertificateUtils;
import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author admin
 */
@ApplicationPath("/vr-app")
@Component(immediate = true, service = Application.class)
public class VRRestApplication extends Application {

	public Set<Object> getSingletons() {
		
		return Collections.<Object>singleton(this);
	}

	public static final String PRE_FIX_CERT = "DKLR_CERT@";
	public static final String PRE_FIX_CERT_CURR = "DKLR_CERT_CURR@";
	public static final String PRE_FIX_CERT_ELM = "DKLR_CERT_ELM@";


	@GET //method
	@Path("/techspecs/vehicleclass/{vehicleClass}") // duong dan API POST ~ ADD, PUT ~ UPDATE, DELELTE - REMOVE (Xoa)
	@Produces(MediaType.APPLICATION_JSON) // Loai gia tri trar ve 
	public Response getTechSpecByVehicleClass(@Context HttpHeaders header,
			@PathParam("vehicleClass") String vehicleClass) {
		VRActions actions = new VRActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String module = header.getHeaderString("module");
		long dossierId = GetterUtil.getLong(header.getHeaderString("dossierId"));
		long dossierFileId = GetterUtil.getLong(header.getHeaderString("dossierFileId"));
		
	
		
		try {
			JSONObject object = actions.getTechSpecByVehicleClass(groupId, module, dossierId, dossierFileId,
					vehicleClass);

			return Response.status(object.getInt("status")).entity(object.getString("content")).build();

		} catch (Exception e) {

			return Response.status(500).entity("error").build();
		}

	}

	@GET
	@Path("/techspecs/{vehicleClass}/vehicleclassexts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTechSpecByVehicleClassExt(@Context HttpHeaders header,
			@PathParam("vehicleClass") String vehicleClass) {
		VRActions actions = new VRActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String module = header.getHeaderString("module");
		long dossierId = GetterUtil.getLong(header.getHeaderString("dossierId"));
		long dossierFileId = GetterUtil.getLong(header.getHeaderString("dossierFileId"));
		String fileTemplateNo = GetterUtil.getString(header.getHeaderString("fileTemplateNo"));

		try {
			JSONObject object = actions.getTechSpecByVehicleClassExt(groupId, module, dossierId, dossierFileId,
					fileTemplateNo, vehicleClass);

			return Response.status(object.getInt("status")).entity(object.getString("content")).build();

		} catch (Exception e) {

			return Response.status(500).entity("error").build();
		}

	}

	@GET
	@Path("/techspecs/{vehicleclass:.*}/{vehicletype: .*}/vehicleclassexts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTechSpecByVehicleType(@Context HttpHeaders header,
			@PathParam("vehicleclass") String vehicleClass, @PathParam("vehicletype") String vehicleType) {
		VRActions actions = new VRActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String module = header.getHeaderString("module");
		long dossierId = GetterUtil.getLong(header.getHeaderString("dossierId"));
		long dossierFileId = GetterUtil.getLong(header.getHeaderString("dossierFileId"));
		String fileTemplateNo = GetterUtil.getString(header.getHeaderString("fileTemplateNo"));

		try {
			JSONObject object = actions.getTechSpecByVehicleType(groupId, module, dossierId, dossierFileId,
					fileTemplateNo, vehicleClass, vehicleType);

			return Response.status(object.getInt("status")).entity(object.getString("content")).build();

		} catch (Exception e) {

			return Response.status(500).entity("error").build();
		}

	}

	@GET
	@Path("/techspecslimit/vehicleclass/{vehicleclass: .*}/vehicletype/{vehicletype: .*}/formulatype/{formulatype: .*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTecSpecs(@Context HttpHeaders header, @PathParam("vehicleclass") String vehicleClass,
			@PathParam("vehicletype") String vehicleType, @PathParam("formulatype") long fomulaType) {
		VRActions actions = new VRActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String module = header.getHeaderString("module");
		long dossierId = GetterUtil.getLong(header.getHeaderString("dossierId"));
		long dossierFileId = GetterUtil.getLong(header.getHeaderString("dossierFileId"));
		try {
			JSONObject object = actions.getTechSpecLimit(groupId, module, dossierId, dossierFileId, vehicleClass,
					vehicleType, fomulaType);

			return Response.status(object.getInt("status")).entity(object.getString("content")).build();

		} catch (Exception e) {

			return Response.status(500).entity("error").build();
		}

	}

	@GET
	@Path("/techspecs/{vehicleclass:.*}/{vehicletype: .*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTechSpecByVehicleClass(@Context HttpHeaders header,
			@PathParam("vehicleclass") String vehicleClass, @PathParam("vehicletype") String vehicleType) {
		VRActions actions = new VRActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		String module = header.getHeaderString("module");
		long dossierId = GetterUtil.getLong(header.getHeaderString("dossierId"));
		long dossierFileId = GetterUtil.getLong(header.getHeaderString("dossierFileId"));
		try {
			JSONObject object = actions.getTechSpecByVehicleClassType(groupId, module, dossierId, dossierFileId,
					vehicleClass, vehicleType);

			return Response.status(object.getInt("status")).entity(object.getString("content")).build();

		} catch (Exception e) {

			return Response.status(500).entity("error").build();
		}

	}

	@GET
	@Path("/json/{code: .*}/items")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getForJasper(@Context HttpHeaders header, @PathParam("code") String code) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		JSONObject resp = JSONFactoryUtil.createJSONObject();

		DictItemUtil dictUtil = new DictItemUtil();

		long dictCollectionId = dictUtil.getCollectionId(code, groupId);

		try {

			List<DictItem> items = DictItemLocalServiceUtil.findByF_dictCollectionId(dictCollectionId);

			JSONArray arrReturn = JSONFactoryUtil.createJSONArray();

			for (DictItem di : items) {
				JSONObject ob = JSONFactoryUtil.createJSONObject();

				ob.put(di.getItemCode(), di.getItemName());

				arrReturn.put(ob);
			}

			resp.put("Items", arrReturn);

			return Response.status(200).entity(resp.toString()).build();

		} catch (Exception e) {
			return Response.status(404).entity(resp.toString()).build();

		}

	}

	@GET
	@Path("/collections/{code: .*}/{type: .*}/items")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getDictItems(@Context HttpHeaders header, @PathParam("code") String code,
			@PathParam("type") String type) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		JSONObject resp = JSONFactoryUtil.createJSONObject();

		DictItemUtil dictUtil = new DictItemUtil();

		long dictCollectionId = dictUtil.getCollectionId(code, groupId);

		try {
			List<DictItem> items = DictItemLocalServiceUtil.findByF_dictCollectionId(dictCollectionId);

			JSONArray itemsJson = dictUtil.getDictItem(items, type);

			resp.put("Items", itemsJson);
			resp.put("Total", itemsJson.length());

			return Response.status(200).entity(resp.toString()).build();

		} catch (Exception e) {
			return Response.status(404).entity(resp.toString()).build();
		}

	}

	@POST
	@Path("/report/{code: .*}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response getReport(@Context HttpHeaders header, @PathParam("code") String code,
			@FormParam("year") String year, @FormParam("fromDate") String fromDate, @FormParam("toDate") String toDate,
			@FormParam("organization") String organization, @FormParam("location") String location,
			@FormParam("reportDate") String reportDate, @FormParam("userCreate") String userCreate,
			@FormParam("userReview") String userReview, @FormParam("note") String note) {

		// long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			VRReport report = VRReportLocalServiceUtil.getReport(code);

			String formReport = report.getFormReport();

			String formData = ""; // TODO: build formData for report

			Message message = new Message();

			message.put("formReport", formReport);

			message.put("formData", formData);

			message.setResponseId(String.valueOf(report.getPrimaryKeyObj()));
			message.setResponseDestinationName("jasper/engine/preview/callback");

			String previewResponse = (String) MessageBusUtil.sendSynchronousMessage("jasper/engine/preview/destination",
					message, 10000);

			File file = new File(previewResponse);

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
			responseBuilder.header("Content-Type", "application/pdf");

			return responseBuilder.build();
		} catch (Exception e) {
			return Response.status(500).entity("error").build();
		}
	}

	@GET
	@Path("/certnumbers")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getCertNumbers(@Context HttpHeaders header) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			List<Counter> counters = CounterLocalServiceUtil.getCounters(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			JSONObject jsObj = JSONFactoryUtil.createJSONObject();

			JSONArray jsArr = JSONFactoryUtil.createJSONArray();

			for (Counter cnt : counters) {
				JSONObject elm = JSONFactoryUtil.createJSONObject();

				if (cnt.getName().contains(PRE_FIX_CERT)) {

					String[] splitPattern = StringUtil.split(cnt.getName(), StringPool.AT);

					elm.put("certId", cnt.getName());
					elm.put("pattern", splitPattern[1]);
					elm.put("year", splitPattern[2]);
					elm.put("initNumber", cnt.getCurrentId());

					jsArr.put(elm);
				}
			}

			jsObj.put("total", jsArr.length());

			jsObj.put("data", jsArr);

			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			return Response.status(500).entity("error").build();
		}
	}

	@GET
	@Path("/certnumbers/{certid}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getDetailCertNumbers(@Context HttpHeaders header, @PathParam("certid") String certid) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {
			Counter counter = CounterLocalServiceUtil.getCounter(certid);
			String[] splitPattern = StringUtil.split(counter.getName(), StringPool.AT);

			JSONObject elm = JSONFactoryUtil.createJSONObject();

			elm.put("certId", counter.getName());
			elm.put("pattern", splitPattern[1]);
			elm.put("year", splitPattern[2]);
			elm.put("initNumber", counter.getCurrentId());

			return Response.status(200).entity(elm.toString()).build();

		} catch (Exception e) {
			return Response.status(500).entity("error").build();
		}
	}

	@POST
	@Path("/certnumbers")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addCertNumbers(@Context HttpHeaders header, @FormParam("pattern") String pattern,
			@FormParam("year") int year, @FormParam("initNumber") int initNumber) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// CounterLocalServiceUtil.increment();

		JSONObject jsObj = JSONFactoryUtil.createJSONObject();

		try {

			String certId = PRE_FIX_CERT + pattern + StringPool.AT + year;

			Counter counterInit = CounterLocalServiceUtil.createCounter(certId);
			counterInit.setCurrentId(initNumber);

			CounterLocalServiceUtil.updateCounter(counterInit);

			jsObj.put("status", "done");

			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			jsObj.put("status", "error");

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

	@PUT
	@Path("/certnumbers/{certid}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateSertNumbers(@Context HttpHeaders header, @PathParam("certid") long certid,
			@FormParam("pattern") String pattern, @FormParam("year") int year,
			@FormParam("initNumber") int initNumber) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		JSONObject jsObj = JSONFactoryUtil.createJSONObject();

		try {
			String certId = PRE_FIX_CERT + pattern + StringPool.AT + year;

			Counter counter = CounterLocalServiceUtil.getCounter(certId);

			counter.setCurrentId(initNumber);

			CounterLocalServiceUtil.updateCounter(counter);

			jsObj.put("status", "done");
			return Response.status(200).entity(jsObj.toString()).build();
		} catch (Exception e) {
			jsObj.put("status", "error");

			return Response.status(500).entity(jsObj.toString()).build();
		}
	}

	@GET
	@Path("/certnumbers/{pattern}/dossier/{dossierid}/generate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN + ";charset=utf-8")
	public Response generatorCertNumbers(@Context HttpHeaders header, @PathParam("pattern") String pattern,
			@PathParam("dossierid") long dossierid) {

		// long groupId =
		// GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		String certNumber;

		try {

			long _counterNumber = 0;

			Calendar cal = Calendar.getInstance();

			cal.setTime(new Date());

			// int curYear = cal.get(Calendar.YEAR);

			DateFormat df = new SimpleDateFormat("yyyy");
			DateFormat sdf = new SimpleDateFormat("yy");

			String curYear = df.format(cal.getTime());
			String shortCurYear = sdf.format(cal.getTime());

			String certConfigId = PRE_FIX_CERT + pattern + StringPool.AT + curYear;

			_log.info("___certConfigId" + certConfigId);

			String certConfigCurrId = PRE_FIX_CERT_CURR + pattern + StringPool.AT + curYear;

			_log.info("___certConfigCurrId" + certConfigCurrId);

			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigId);

			String elmCertId = PRE_FIX_CERT_ELM + pattern + StringPool.AT + curYear + StringPool.AT + dossierid;

			// Counter counter = CounterLocalServiceUtil.fetchCounter(certId);

			if (Validator.isNotNull(counterConfig)) {
				// create counter config

				Counter currCounter = CounterLocalServiceUtil.fetchCounter(certConfigCurrId);

				if (Validator.isNull(currCounter)) {
					_log.info("COUTER_CURR_CONFIG_IS_NULL");

					currCounter = CounterLocalServiceUtil.createCounter(certConfigCurrId);

					currCounter.setCurrentId(counterConfig.getCurrentId());

					_counterNumber = counterConfig.getCurrentId();

					CounterLocalServiceUtil.updateCounter(currCounter);

					// Create elmCounter
					Counter elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);

					elmCounter.setCurrentId(_counterNumber);

					CounterLocalServiceUtil.updateCounter(elmCounter);

				} else {
					_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");

					// check counter for element
					Counter elmCounter = CounterLocalServiceUtil.fetchCounter(elmCertId);

					if (Validator.isNotNull(elmCounter)) {
						_log.info("ELM_COUTER_CONFIG_IS_NOT_NULL");

						_counterNumber = elmCounter.getCurrentId();
					} else {
						// create elm Counter
						_log.info("ELM_COUTER_CONFIG_IS_NULL");
						elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);

						// increment CurrentCounter

						currCounter.setCurrentId(currCounter.getCurrentId() + 1);
						CounterLocalServiceUtil.updateCounter(currCounter);

						_counterNumber = currCounter.getCurrentId();

						elmCounter.setCurrentId(_counterNumber);

						CounterLocalServiceUtil.updateCounter(elmCounter);
					}

				}

				certNumber = String.format("%05d", _counterNumber) + StringPool.FORWARD_SLASH + pattern
						+ StringPool.FORWARD_SLASH + shortCurYear;

			} else {
				throw new Exception("Don't have counter config");
			}

			return Response.status(200).entity(certNumber).build();

		} catch (Exception e) {

			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	/** Process search result - START */
	// Search document acceptable
	@GET
	@Path("/certDoc")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getDocAccept(@Context HttpHeaders header, @QueryParam("keywords") String keywords,
			@QueryParam("serviceCode") String serviceCode, @QueryParam("govAgencyCode") String govAgencyCode,
			@QueryParam("routeCode") String routeCode, @QueryParam("fromDate") String fromDate,
			@QueryParam("toDate") String toDate, @QueryParam("start") int start, @QueryParam("limit") int limit) {

		ILCertificateActions actions = new ILCertificateActionsImpl();

		try {
			// Process key search
			String keySearch = StringPool.BLANK;
			String serviceSearch = StringPool.BLANK;
			String govCodeSearch = StringPool.BLANK;
			String routeCodeSearch = StringPool.BLANK;
			String fromDateSearch = StringPool.BLANK;
			String toDateSearch = StringPool.BLANK;
			if (Validator.isNotNull(keywords)) {
				keySearch = keywords.toLowerCase() + "%";
			}
			if (Validator.isNotNull(serviceCode)) {
				if (serviceCode.contains(StringPool.COMMA)) {
					String[] serviceSplit = StringUtil.split(serviceCode, StringPool.COMMA);
					if (serviceSplit != null && serviceSplit.length > 0) {
						StringBuilder sb = new StringBuilder();
						int lenghtService = serviceSplit.length;
						for (int i = 0; i < lenghtService; i++) {
							if (i == 0) {
								sb.append(StringPool.APOSTROPHE);
								sb.append(serviceSplit[i]);
								sb.append(StringPool.APOSTROPHE);
							} else {
								sb.append(StringPool.COMMA);
								sb.append(StringPool.APOSTROPHE);
								sb.append(serviceSplit[i]);
								sb.append(StringPool.APOSTROPHE);
							}
						}
						serviceSearch = sb.toString();
					}
				} else {
					serviceSearch = StringPool.APOSTROPHE + serviceCode.toLowerCase() + StringPool.APOSTROPHE;
				}
			}
			_log.info("serviceSearch: " + serviceSearch);
			if (Validator.isNotNull(govAgencyCode)) {
				govCodeSearch = govAgencyCode.toLowerCase();
			}
			if (Validator.isNotNull(routeCode)) {
				routeCodeSearch = routeCode.toLowerCase();
			}
			if (Validator.isNotNull(fromDate)) {
				fromDate = ConvertFormatDate.formatDateSQL(fromDate);
			}
			if (Validator.isNotNull(toDate)) {
				toDateSearch = ConvertFormatDate.formatDateSQL(toDate);
			}

			JSONObject jsonData = actions.getDocAcceptList(keySearch, serviceSearch, govCodeSearch, routeCodeSearch,
					fromDateSearch, toDateSearch, start, limit);

			ILCertificateResultModel result = new ILCertificateResultModel();
			// List<ILCertificateModel> certModelList =
			// ILCertificateUtils.mappingToResultCertificate(certList);
			if (jsonData != null && jsonData.getInt("total") > 0) {
				result.setTotal(jsonData.getInt("total"));
				result.getData().addAll(
						ILCertificateUtils.mappingToResultCertificate((List<ILCertificate>) jsonData.get("data")));
			} else {
				result.setTotal(0);
			}

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity(e.getMessage()).build();

		}

	}

	/** Process search result - END */

	// Get detail certificate info
	@GET
	@Path("/certDoc/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getDetailCert(@Context HttpHeaders header, @PathParam("id") String id) {

		ILCertificateActions actions = new ILCertificateActionsImpl();
		long certificateId = Long.valueOf(id);

		try {
			// Process key search

			ILCertificate certInfo = actions.getDetailCert(certificateId);

			ILCertificateModel result = ILCertificateUtils.mappingToDetailCertificate(certInfo);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity(e.getMessage()).build();

		}

	}

	@GET
	@Path("/certDoc/borderGuard/{registrationNumber}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getBorderGuardList(@Context HttpHeaders header,
			@PathParam("registrationNumber") String registrationNumber) {

		ILCertificateActions actions = new ILCertificateActionsImpl();

		try {
			// Process key search
			List<ILVehicleCustomsBorderGuard> cbGuardList = actions.getBorderGuardList(registrationNumber);
			ILCBGuardResultModel result = new ILCBGuardResultModel();

			if (cbGuardList != null && cbGuardList.size() > 0) {
				result.setRegistrationNumber(registrationNumber);
				result.getData().addAll(ILCertificateUtils.mappingToResultCBGuard(cbGuardList));
			} else {
				result.setRegistrationNumber(registrationNumber);
			}
			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity(e.getMessage()).build();

		}

	}

	@POST
	@Path("/certDoc/borderGuard/{registrationNumber}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response addCustomBorderGuard(@Context HttpHeaders header,
			@PathParam("registrationNumber") String registrationNumber,
			@FormParam("expImpGateType") String expImpGateType, @FormParam("expImpGate") String expImpGate,
			@FormParam("driverName") String driverName, @FormParam("driverLicenceNo") String driverLicenceNo,
			@FormParam("registrationDate") String registrationDate) {

		ILCertificateActions actions = new ILCertificateActionsImpl();

		try {
			Date regDate = null;
			_log.info("registrationDate:" + registrationDate);
			if (Validator.isNotNull(registrationDate)) {
				regDate = ConvertFormatDate.parseStringToDate(registrationDate, ConvertFormatDate._NORMAL_DATE);
				_log.info("regDate:" + regDate);
			}
			ILVehicleCustomsBorderGuard cbGuardInput = actions.addCustomBorderGuard(registrationNumber, expImpGateType,
					expImpGate, driverName, driverLicenceNo, regDate);

			ILCBGuardModel result = ILCertificateUtils.mappingToDetailCBGuard(cbGuardInput);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity(e.getMessage()).build();

		}

	}

	@GET
	@Path("/certDoc/applicant")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getCertByApplicant(@Context HttpHeaders header, @QueryParam("applicantIdNo") String applicantIdNo,
			@QueryParam("registrationNumber") String registrationNumber,
			@QueryParam("serviceCode") String serviceCode) {

		ILCertificateActions actions = new ILCertificateActionsImpl();

		try {
			// Process key search
			String applicant = StringPool.BLANK;
			String regSearch = StringPool.BLANK;
			String serviceSearch = StringPool.BLANK;

			if (Validator.isNotNull(applicantIdNo)) {
				applicant = applicantIdNo.toLowerCase();
			}
			_log.info("applicantIdNo: " + applicantIdNo);
			_log.info("applicant: " + applicant);
			if (Validator.isNotNull(serviceCode)) {
				serviceSearch = serviceCode.toLowerCase();
			}
			_log.info("serviceCode: " + serviceCode);
			_log.info("serviceSearch: " + serviceSearch);
			if (Validator.isNotNull(registrationNumber)) {
				regSearch = registrationNumber.toLowerCase();
				_log.info("registrationNumber: " + registrationNumber);
				_log.info("regSearch: " + regSearch);
				ILCertificate certInfo = actions.getCertByValidFrom(serviceSearch, applicantIdNo, registrationNumber);
				ILCertificateModel result = null;
				if (certInfo != null) {
					result = ILCertificateUtils.mappingToDetailCertificate(certInfo);
				}

				return Response.status(200).entity(result).build();
			} else {
				JSONObject jsonData = actions.getCertByApplicant(serviceSearch, applicant);
				ILCertificateResultModel result = new ILCertificateResultModel();
				// List<ILCertificateModel> certModelList =
				// ILCertificateUtils.mappingToResultCertificate(certList);
				if (jsonData != null && jsonData.getInt("total") > 0) {
					result.setTotal(jsonData.getInt("total"));
					result.getData().addAll(
							ILCertificateUtils.mappingToResultCertificate((List<ILCertificate>) jsonData.get("data")));
				} else {
					result.setTotal(0);
				}
				return Response.status(200).entity(result).build();
			}

		} catch (Exception e) {
			_log.error(e);
			return Response.status(500).entity(e.getMessage()).build();

		}
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

	Log _log = LogFactoryUtil.getLog(VRRestApplication.class);

	@Reference
	private com.fds.vr.provider.CompanyContextProvider _companyContextProvider;

	@Reference
	private com.fds.vr.provider.LocaleContextProvider _localeContextProvider;

	@Reference
	private com.fds.vr.provider.UserContextProvider _userContextProvider;

	@Reference
	private com.fds.vr.provider.ServiceContextProvider _serviceContextProvider;

}