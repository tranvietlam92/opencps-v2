webpackJsonp([10],{124:function(e,n,t){function o(e){var n=i[e];return n?t.e(n[1]).then(function(){return t(n[0])}):Promise.reject(new Error("Cannot find module '"+e+"'."))}var i={"./BarChartReport.vue":[192,6],"./BarChartReportHorizontal.vue":[193,5],"./Landing.vue":[196,2],"./LandingLinePublic.vue":[197,1],"./LandingPiePublic.vue":[198,0],"./LineChartReport.vue":[194,3],"./NotFound.vue":[199,7],"./PieChartReport.vue":[195,4]};o.keys=function(){return Object.keys(i)},e.exports=o,o.id=124},125:function(e,n){e.exports={renderURLInit:"?p_p_id=FrontendWebChartjs&p_p_lifecycle=2&p_p_state=exclusive&p_p_mode=view&p_p_resource_id=renderURLInit",trangThaiHoSoList:[{code:"0",active:!1,type:"thong_ke",title:"TÌNH HÌNH GIẢI QUYẾT TTHC"},{code:"1",active:!1,type:"dossier",title:"TỔNG HỢP TIẾP NHẬN HỒ SƠ"},{code:"2",active:!1,type:"dossier",title:"CHI TIẾT TIẾP NHẬN HỒ SƠ"},{code:"3",active:!1,type:"dossier",title:"TỔNG HỢP TRẢ KẾT QUẢ"},{code:"4",active:!1,type:"dossier",title:"CHI TIẾT TRẢ KẾT QUẢ"},{code:"5",active:!1,type:"thong_ke",title:"TỔNG HỢP HỒ SƠ DVC MỨC ĐỘ 3"},{code:"6",active:!1,type:"tai_chinh",title:"TỔNG HỢP PHÍ, LỆ PHÍ"},{code:"7",active:!1,type:"tai_chinh",title:"TỔNG HỢP PHÍ, LỆ PHÍ THEO TTHC"},{code:"8",active:!1,type:"dossier",title:"CHI TIẾT HỒ SƠ ĐÃ GIẢI QUYẾT"},{code:"9",active:!1,type:"dossier",title:"CHI TIẾT HỒ SƠ ĐANG XỬ LÝ"}],fakeReport:[{betimesCount:22,cancelledCount:10,deniedCount:50,domainCode:"YTE ",domainName:"Y Tế ",doneCount:40,govAgencyCode:"TPT",govAgencyName:"UBND Tỉnh Phú Thọ",interoperatingCount:0,month:1,onlineCount:30,ontimeCount:10,ontimePercentage:85,overdueCount:5,overtimeCount:5,overtimeInside:0,overtimeOutside:0,processCount:30,processingCount:0,receivedCount:40,releaseCount:10,releasingCount:11,remainingCount:10,reporting:!0,totalCount:100,undueCount:5,unresolvedCount:11,waitingCount:21,year:2018,onegateCount:12},{betimesCount:22,cancelledCount:11,deniedCount:51,domainCode:"YTE ",domainName:"Y Tế ",doneCount:41,govAgencyCode:"TPT2",govAgencyName:"UBND Tỉnh Phú Thọ 2",interoperatingCount:0,month:2,onlineCount:31,ontimeCount:15,ontimePercentage:80,overdueCount:5,overtimeCount:6,overtimeInside:0,overtimeOutside:0,processCount:31,processingCount:0,receivedCount:41,releaseCount:11,releasingCount:12,remainingCount:11,reporting:!0,totalCount:150,undueCount:5,unresolvedCount:15,waitingCount:12,year:2018,onegateCount:12}]}},185:function(e,n){},190:function(e,n,t){var o=t(126)(t(219),t(463),null,null);e.exports=o.exports},217:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var o=t(64),i=(t.n(o),t(63)),a=(t.n(i),t(65)),r=(t.n(a),t(32)),u=t.n(r),c=t(190),s=t.n(c),d=t(35),p=t(87),l=t.n(p),f=t(62),g=t(86),v=t(85);t.n(v);u.a.use(g.default),u.a.use(l.a),u.a.config.productionTip=!0,new u.a({el:"#app",router:d.a,store:f.a,render:function(e){return e(s.a)}})},219:function(e,n,t){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var o=t(35);n.default={data:function(){return{isCallBack:!0}},computed:{currentIndex:function(){return this.$store.getters.index}},created:function(){this.$nextTick(function(){})},updated:function(){var e=this;e.$nextTick(function(){var n=e.$router.history.current.params,t=e.$router.history.current.query;if(e.isCallBack){e.isCallBack=!1;var i=0;n.hasOwnProperty("index")&&(i=n.index),o.a.push({path:"/bao-cao/"+i,query:t})}})},watch:{$route:function(e,n){e.params,e.query}},methods:{toTableIndexing:function(e,n){this.$store.commit("setIndex",n),o.a.push({path:"/bao-cao/"+n,query:{renew:Math.floor(100*Math.random())+1,q:e.queryParams}})},filterSteps:function(e){}}}},35:function(e,n,t){"use strict";var o=t(136),i=t.n(o),a=t(32),r=t.n(a),u=t(188),c=[{path:"/bao-cao/:index",component:"Landing",props:!0},{path:"/report/bar",component:"LandingLinePublic",props:!0},{path:"/report/pie",component:"LandingPiePublic",props:!0},{path:"*",component:"NotFound"}],s=c.map(function(e){return i()({},e,{component:function(){return t(124)("./"+e.component+".vue")}})});r.a.use(u.a);var d=new u.a({routes:s});n.a=d},463:function(e,n){e.exports={render:function(){var e=this,n=e.$createElement,t=e._self._c||n;return t("v-app",[t("v-content",[t("router-view")],1)],1)},staticRenderFns:[]}},62:function(e,n,t){"use strict";t.d(n,"a",function(){return l});var o=t(135),i=t.n(o),a=t(32),r=t.n(a),u=t(189),c=t(129),s=t.n(c),d=t(125),p=t.n(d);r.a.use(u.a);var l=new u.a.Store({state:{initData:null,agencyGroups:null,loading:!1,index:0},actions:{loadInitResource:function(e){var n=e.commit,t=e.state;return null==t.initData?new i.a(function(e,t){var o={},i=window.location.href,a=window.location.href.lastIndexOf("#/");a>0&&(i=window.location.href.substr(0,a)),s.a.get(i+p.a.renderURLInit,o).then(function(t){var o=t.data;n("setInitData",o),e(o)}).catch(function(e){console.log(e),t(e)})}):new i.a(function(e,n){e(t.initData)})},getAgencyReportLists:function(e,n){var t=(e.commit,e.state);return new i.a(function(e,o){l.dispatch("loadInitResource").then(function(i){var a={headers:{groupId:t.initData.groupId,Accept:"application/json"},params:{year:n.year,month:n.month,group:n.group,reporting:!1,agency:n.agency}};n.report&&(a.params.domain="total"),"linemonth"===n.report&&(a.params.domain=""),s.a.get("/o/rest/statistics",a).then(function(n){var t=n.data;if(t.data){var o=t.data;e(o)}else e(null)}).catch(function(e){console.log(e),o(e)})})})},getAgencyGroups:function(e,n){var t=e.commit,o=e.state;return null==o.agencyGroups?new i.a(function(e,n){l.dispatch("loadInitResource").then(function(i){var a={headers:{groupId:o.initData.groupId}};s.a.get("/o/rest/v2/dictcollections/GOVERNMENT_AGENCY/dictgroups",a).then(function(n){var o=n.data;if(o.data){var i=o.data;t("setAgencyGroups",i),e(i)}else e(null)}).catch(function(e){console.log(e),n(e)})})}):new i.a(function(e,n){e(o.agencyGroups)})}},mutations:{setLoading:function(e,n){e.loading=n},setMenuConfigToDo:function(e,n){e.trangThaiHoSoList=n},setIndex:function(e,n){e.index=n},setInitData:function(e,n){e.initData=n},setAgencyGroups:function(e,n){e.agencyGroups=n}},getters:{loading:function(e){return e.loading},index:function(e){return e.index},loadingMenuConfigToDo:function(e){return p.a.trangThaiHoSoList}}})},63:function(e,n){},64:function(e,n){},65:function(e,n){}},[217]);
//# sourceMappingURL=app.c93dae3ce1cc0d5e72e9.js.map