webpackJsonp([4],{616:function(t,e,s){var o=s(132)(s(630),s(634),null,null);t.exports=o.exports},630:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var o=s(133);e.default={data:function(){return{minDate:null,dataPostDossier:{serviceCode:"",govAgencyCode:"",processOptionId:""}}},created:function(){var t=this;t.$nextTick(function(){t.minDate=t.getCurentDateTime("date")})},computed:{loading:function(){return this.$store.getters.loading},isDetail:function(){return this.$store.getters.isDetail},thongTinChungHoSo:function(){return this.$store.getters.thongTinChungHoSo},serviceConfigItems:function(){return this.$store.getters.serviceConfigItems},serviceOptionItems:function(){return this.$store.getters.serviceOptionItems},dossierTemplates:function(){return this.$store.getters.dossierTemplates},subStatusNew:function(){return this.$store.getters.subStatusNew}},watch:{},methods:{getCurentDateTime:function(t){var e=new Date;return"datetime"===t?e.getDate().toString().padStart(2,"0")+"/"+(e.getMonth()+1).toString().padStart(2,"0")+"/"+e.getFullYear()+" | "+e.getHours().toString().padStart(2,"0")+":"+e.getMinutes().toString().padStart(2,"0"):"date"===t?e.getFullYear()+"-"+(e.getMonth()+1).toString().padStart(2,"0")+"-"+e.getDate():void 0},getDuedate:function(){var t=this,e=new Date(t.thongTinChungHoSo.dueDate).getTime()-new Date(t.thongTinChungHoSo.receiveDate).getTime();return Math.ceil(e/1e3/60/60/24)<=0?1:Math.ceil(e/1e3/60/60/24)},changeServiceConfigs:function(){var t=this;t.$store.commit("setSubStatusNew",!1),setTimeout(function(){var e=t.thongTinChungHoSo.serviceConfig.options;t.dataPostDossier.serviceCode=t.thongTinChungHoSo.serviceConfig.serviceCode,t.dataPostDossier.govAgencyCode=t.thongTinChungHoSo.serviceConfig.govAgencyCode,t.$store.commit("setServiceConfigObj",t.thongTinChungHoSo.serviceConfig),1!==e.length?t.$store.commit("setServiceOptionItems",e):(t.dataPostDossier.templateNo=e[0].templateNo,t.$store.commit("setServiceOptionItems",e),t.$store.commit("setServiceOptionThongTinChungHoSo",e[0].templateNo),t.$store.commit("setSubStatusNew",!1),t.$store.dispatch("getCpsAuthen").then(function(e){t.dataPostDossier.cps_auth=e,t.$store.dispatch("postDossier",t.dataPostDossier).then(function(e){console.log("result",e),o.a.push("/danh-sach-ho-so/"+t.$store.getters.index+"/tiep-nhan-ho-so/"+e.dossierId),t.$store.dispatch("loadDossierFiles"),t.$store.dispatch("loadDossierTemplates",e).then(function(e){setTimeout(function(s){console.log("result dossierTemplates=-------",e),e.forEach(function(e){t.$store.dispatch("loadAlpcaForm",e)})},500)})})}))},300)},changeServiceOption:function(){var t=this;t.$store.commit("setSubStatusNew",!1),setTimeout(function(){t.dataPostDossier.templateNo=t.thongTinChungHoSo.serviceOption,t.$store.dispatch("getCpsAuthen").then(function(e){t.dataPostDossier.cps_auth=e,t.$store.dispatch("postDossier",t.dataPostDossier).then(function(e){console.log("log",t.$store.getters.index,e.dossierId),o.a.push("/danh-sach-ho-so/"+t.$store.getters.index+"/tiep-nhan-ho-so/"+e.dossierId)})}).catch(function(t){console.log(t)})},300)}},filters:{dateTimeView:function(t){if(t){var e=new Date(t);return e.getDate().toString().padStart(2,"0")+"/"+(e.getMonth()+1).toString().padStart(2,"0")+"/"+e.getFullYear()+" | "+e.getHours().toString().padStart(2,"0")+":"+e.getMinutes().toString().padStart(2,"0")}}}}},634:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticStyle:{position:"relative"}},[s("v-expansion-panel",{staticClass:"expansion-pl"},[s("v-expansion-panel-content",{attrs:{"hide-actions":"",value:"1"}},[s("div",{attrs:{slot:"header"},slot:"header"},[s("div",{staticClass:"background-triangle-small"},[t._v(" I. ")]),t._v("THÔNG TIN CHUNG")]),t._v(" "),s("v-card",[s("v-card-text",[s("v-layout",{attrs:{wrap:""}},[s("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticClass:"pl-0"},[t._v("Thủ tục: ")])],1),t._v(" "),s("v-flex",{attrs:{xs12:"",sm10:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):t._e(),t._v(" "),!1===t.loading&&!1===t.isDetail?s("v-select",{attrs:{items:t.serviceConfigItems,"item-text":"serviceName","item-value":"serviceCode",autocomplete:"","return-object":"","hide-selected":""},on:{change:t.changeServiceConfigs},model:{value:t.thongTinChungHoSo.serviceConfig,callback:function(e){t.$set(t.thongTinChungHoSo,"serviceConfig",e)},expression:"thongTinChungHoSo.serviceConfig"}}):t._e(),t._v(" "),!1===t.loading&&t.isDetail?s("v-subheader",{staticStyle:{float:"left",height:"100%"}},[s("i",[t._v(t._s(t.thongTinChungHoSo.serviceName))])]):t._e()],1),t._v(" "),s("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticClass:"pl-0"},[t._v("Dịch vụ: ")])],1),t._v(" "),s("v-flex",{attrs:{xs12:"",sm10:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):t._e(),t._v(" "),!1===t.loading&&!1===t.isDetail?s("v-select",{attrs:{items:t.serviceOptionItems,"item-text":"optionName","item-value":"templateNo","hide-selected":"",autocomplete:""},on:{change:t.changeServiceOption},model:{value:t.thongTinChungHoSo.serviceOption,callback:function(e){t.$set(t.thongTinChungHoSo,"serviceOption",e)},expression:"thongTinChungHoSo.serviceOption"}}):t._e(),t._v(" "),!1===t.loading&&t.isDetail?s("v-subheader",{staticStyle:{float:"left",height:"100%"}},[s("i",[t._v(t._s(t.thongTinChungHoSo.dossierTemplateName))])]):t._e()],1),t._v(" "),s("v-flex",{attrs:{xs12:""}}),t._v(" "),s("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticClass:"pl-0"},[t._v("\n                Mã hồ sơ: \n              ")])],1),t._v(" "),s("v-flex",{attrs:{xs12:"",sm4:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticStyle:{float:"left"}},[s("i",[t._v(t._s(t.thongTinChungHoSo.dossierIdCTN))])])],1),t._v(" "),s("v-flex",{attrs:{xs12:""}}),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticClass:"pl-0"},[t._v("\n                Mã tiếp nhận: \n              ")])],1):t._e(),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm4:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticStyle:{float:"left"}},[s("i",[t._v(t._s(t.thongTinChungHoSo.dossierNo))])])],1):t._e(),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticClass:"pl-0"},[t._v("Thời gian giải quyết: ")])],1):t._e(),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm4:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):t._e(),t._v(" "),!t.loading&&t.thongTinChungHoSo.durationDate?s("v-subheader",{staticStyle:{float:"left"}},[s("i",[t._v(t._s(t.thongTinChungHoSo.durationDate)+" làm việc")])]):t._e()],1):t._e(),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticClass:"pl-0"},[t._v("Ngày giờ tiếp nhận: ")])],1):t._e(),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm4:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticStyle:{float:"left"}},[s("i",[t._v(t._s(t._f("dateTimeView")(t.thongTinChungHoSo.receiveDate)))])])],1):t._e(),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm2:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticClass:"pl-0"},[t._v("Ngày hẹn trả: ")])],1):t._e(),t._v(" "),!1===t.subStatusNew?s("v-flex",{attrs:{xs12:"",sm4:""}},[t.loading?s("content-placeholders",{staticClass:"mt-1"},[s("content-placeholders-text",{attrs:{lines:1}})],1):s("v-subheader",{staticStyle:{float:"left",height:"100%"}},[s("datetime",{attrs:{type:"datetime","input-format":"DD/MM/YYYY | HH:mm",i18n:{ok:"Chọn",cancel:"Thoát"},"moment-locale":"vi",zone:"local","min-date":t.minDate,"monday-first":"","wrapper-class":"wrapper-datetime","auto-continue":"","auto-close":"",required:""},model:{value:t.thongTinChungHoSo.dueDate,callback:function(e){t.$set(t.thongTinChungHoSo,"dueDate",e)},expression:"thongTinChungHoSo.dueDate"}}),t._v(" "),s("v-icon",[t._v("event")])],1)],1):t._e()],1)],1)],1)],1)],1),t._v(" "),s("v-btn",{staticClass:"absolute__btn",attrs:{flat:""}},[s("v-icon",{attrs:{size:"16"}},[t._v("file_copy")]),t._v("\n    Hướng dẫn\n  ")],1)],1)},staticRenderFns:[]}}});
//# sourceMappingURL=4.5827cc4ea45e941f44a7.js.map