(function(t){function e(e){for(var s,l,i=e[0],r=e[1],c=e[2],u=0,p=[];u<i.length;u++)l=i[u],Object.prototype.hasOwnProperty.call(n,l)&&n[l]&&p.push(n[l][0]),n[l]=0;for(s in r)Object.prototype.hasOwnProperty.call(r,s)&&(t[s]=r[s]);d&&d(e);while(p.length)p.shift()();return o.push.apply(o,c||[]),a()}function a(){for(var t,e=0;e<o.length;e++){for(var a=o[e],s=!0,i=1;i<a.length;i++){var r=a[i];0!==n[r]&&(s=!1)}s&&(o.splice(e--,1),t=l(l.s=a[0]))}return t}var s={},n={app:0},o=[];function l(e){if(s[e])return s[e].exports;var a=s[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,l),a.l=!0,a.exports}l.m=t,l.c=s,l.d=function(t,e,a){l.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},l.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},l.t=function(t,e){if(1&e&&(t=l(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(l.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var s in t)l.d(a,s,function(e){return t[e]}.bind(null,s));return a},l.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return l.d(e,"a",e),e},l.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},l.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],r=i.push.bind(i);i.push=e,i=i.slice();for(var c=0;c<i.length;c++)e(i[c]);var d=r;o.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},2531:function(t,e){},"56d7":function(t,e,a){"use strict";a.r(e);a("e260"),a("e6cf"),a("cca6"),a("a79d");var s=a("2b0e"),n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-app",[a("v-navigation-drawer",{attrs:{"mini-variant":t.mini,permanent:"",app:""},on:{"update:miniVariant":function(e){t.mini=e},"update:mini-variant":function(e){t.mini=e}},model:{value:t.drawer,callback:function(e){t.drawer=e},expression:"drawer"}},[a("v-list-item",{staticClass:"px-2"},[a("v-list-item-avatar",[a("v-img",{attrs:{src:"https://www.dmrbrescia.it/wp-content/uploads/2018/03/Logo_DMR_Brescia.jpg"}})],1),a("v-list-item-title",[t._v("Manage System")]),a("v-btn",{attrs:{icon:""},on:{click:function(e){e.stopPropagation(),t.mini=!t.mini}}},[a("v-icon",[t._v("mdi-chevron-left")])],1)],1),a("v-divider"),a("v-list",{attrs:{dense:""}},t._l(t.items,(function(e){return a("v-list-item",{key:e.title,attrs:{link:"",router:"",to:e.route}},[a("v-list-item-icon",[a("v-icon",[t._v(t._s(e.icon))])],1),a("v-list-item-content",[a("v-list-item-title",[t._v(t._s(e.title))])],1)],1)})),1)],1),a("v-app-bar",{attrs:{app:"",color:"blue"}}),a("v-content",[a("v-container",{attrs:{fluid:""}},[a("router-view")],1)],1),a("v-footer",{attrs:{app:""}})],1)},o=[],l={name:"App",components:{},data:function(){return{drawer:!0,items:[{title:"Home",icon:"mdi-home-city",route:"/home"},{title:"Hosts",icon:"mdi-server",route:"/hosts"},{title:"Clusters",icon:"mdi-server-network",route:"/clusters"},{title:"API",icon:"mdi-emoticon-poop",route:"/api"}],mini:!0}}},i=l,r=a("2877"),c=a("6544"),d=a.n(c),u=a("7496"),p=a("40dc"),_=a("8336"),h=a("a523"),v=a("a75b"),m=a("ce7e"),f=a("553a"),b=a("132d"),T=a("adda"),k=a("8860"),x=a("da13"),O=a("8270"),E=a("5d23"),C=a("34c3"),S=a("f774"),g=Object(r["a"])(i,n,o,!1,null,null,null),y=g.exports;d()(g,{VApp:u["a"],VAppBar:p["a"],VBtn:_["a"],VContainer:h["a"],VContent:v["a"],VDivider:m["a"],VFooter:f["a"],VIcon:b["a"],VImg:T["a"],VList:k["a"],VListItem:x["a"],VListItemAvatar:O["a"],VListItemContent:E["a"],VListItemIcon:C["a"],VListItemTitle:E["b"],VNavigationDrawer:S["a"]});var A=a("8c4f"),w=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-card",{attrs:{fluid:""}},[a("v-expansion-panels",[a("v-expansion-panel",[a("v-expansion-panel-header",[t._v(" ACTIONS ")]),a("v-expansion-panel-content",[a("v-card",[a("v-tabs",[a("v-tab",[t._v("Details")]),a("v-tab",[t._v("Delete host")]),a("v-tab",[t._v("Add new host")]),a("v-tab-item",[a("v-card",{attrs:{flat:""}},[a("p",{staticClass:"pa-2 ma-2"},[t._v("ID: "+t._s(t.c_host_id))]),a("p",{staticClass:"pa-2 ma-2"},[t._v("Creator: "+t._s(t.c_host_creator))]),a("p",{staticClass:"pa-2 ma-2"},[t._v("Create date: "+t._s(t.c_host_date))]),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"Host ip"},model:{value:t.c_host_ip,callback:function(e){t.c_host_ip=e},expression:"c_host_ip"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"DNS name"},model:{value:t.c_host_dns,callback:function(e){t.c_host_dns=e},expression:"c_host_dns"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"ssh user"},model:{value:t.c_host_user,callback:function(e){t.c_host_user=e},expression:"c_host_user"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"ssh password"},model:{value:t.c_host_pass,callback:function(e){t.c_host_pass=e},expression:"c_host_pass"}}),a("v-textarea",{staticClass:"pa-2 ma-2",attrs:{counter:"",label:"Description",rules:t.rules,value:t.value},model:{value:t.c_host_desc,callback:function(e){t.c_host_desc=e},expression:"c_host_desc"}},[t._v(t._s(t.c_host_desc))]),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"Used"},model:{value:t.c_host_used,callback:function(e){t.c_host_used=e},expression:"c_host_used"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"token for api"},model:{value:t.token,callback:function(e){t.token=e},expression:"token"}}),a("v-btn",{staticClass:"pa-2 ma-2",attrs:{block:""},on:{click:t.updateHostDataOnHost}},[t._v(" UPDATE DATA ON SERVER ")])],1)],1),a("v-tab-item",[a("v-card",{attrs:{flat:""}},[a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"Host id"},model:{value:t.destroy_host_id,callback:function(e){t.destroy_host_id=e},expression:"destroy_host_id"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"token for api"},model:{value:t.token,callback:function(e){t.token=e},expression:"token"}}),a("v-btn",{staticClass:"pa-2 ma-2",attrs:{block:""},on:{click:t.deleteHost}},[t._v(" SEND REQUEST ")])],1)],1),a("v-tab-item",[a("v-card",{attrs:{flat:""}},[a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"Host ip"},model:{value:t.add_host_ip,callback:function(e){t.add_host_ip=e},expression:"add_host_ip"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"DNS name"},model:{value:t.add_host_dns,callback:function(e){t.add_host_dns=e},expression:"add_host_dns"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"ssh user"},model:{value:t.add_host_user,callback:function(e){t.add_host_user=e},expression:"add_host_user"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"ssh pass"},model:{value:t.add_host_pass,callback:function(e){t.add_host_pass=e},expression:"add_host_pass"}}),a("v-textarea",{staticClass:"pa-2 ma-2",attrs:{counter:"",label:"Description",rules:t.rules,value:t.value},model:{value:t.add_host_desc,callback:function(e){t.add_host_desc=e},expression:"add_host_desc"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"token for api"},model:{value:t.token,callback:function(e){t.token=e},expression:"token"}}),a("v-btn",{staticClass:"pa-2 ma-2",attrs:{block:""},on:{click:t.addNewHost}},[t._v(" SEND REQUEST ")])],1)],1)],1)],1)],1)],1)],1),a("v-card-title",[a("v-text-field",{attrs:{"append-icon":"mdi-magnify",label:"Search","single-line":"","hide-details":""},model:{value:t.search,callback:function(e){t.search=e},expression:"search"}})],1),a("v-data-table",{staticClass:"elevation-1",attrs:{headers:t.headers,items:this.$store.state.hosts,"items-per-page":10,search:t.search},scopedSlots:t._u([{key:"item.action",fn:function(e){var s=e.item;return[a("v-btn",{attrs:{color:"primary",elevation:"2"},on:{click:function(e){return t.getDetails(s.id)}}},[t._v("Details")])]}}])})],1)},U=[],D=(a("a4d3"),a("e01a"),a("5530")),I=a("2f62"),V=a("bc3a"),j=a.n(V),N=a("d4ec"),P=function t(){Object(N["a"])(this,t),this.baseUrl="/api/v1/",this.authUrl=this.baseUrl+"auth",this.hostsUrl=this.baseUrl+"hosts/all",this.clusterUrl=this.baseUrl+"clusters/all",this.infoUrl=this.baseUrl+"info",this.hostDestroyUrl=this.baseUrl+"hosts/",this.addHostUrl=this.baseUrl+"hosts/add",this.hostInfo=this.baseUrl+"hosts/",this.generateToken=this.baseUrl+"generate",this.addUser=this.baseUrl+"account/create",this.clusterCreate=this.baseUrl+"cluster/create",this.destroyClusterUrl=this.baseUrl+"cluster/destroy/",this.clusterDetails=this.baseUrl+""},H=new P,R={name:"App",components:{},data:function(){return{search:"",headers:[{text:"id",value:"id"},{text:"Host",value:"host"},{text:"DNS name",value:"dnsName"},{text:"Creator",value:"creator"},{text:"CreateDate",value:"createDate"},{text:"Used",value:"used"},{text:"Actions",value:"action"}],destroy_host_id:"-1000",token:null,add_host_ip:null,add_host_dns:null,add_host_desc:null,add_host_user:null,add_host_pass:null,d_host_ip:null,d_host_dns:null,d_host_desc:null,d_host_user:null,d_host_pass:null,d_host_create_date:null,d_host_creator:null,d_host_used:null,c_host_id:null,c_host_ip:null,c_host_dns:null,c_host_user:null,c_host_pass:null,c_host_desc:null,c_host_creator:null,c_host_date:null,c_host_used:null}},computed:Object(D["a"])({},Object(I["c"])(["HOSTS","TOKEN"])),methods:Object(D["a"])(Object(D["a"])({},Object(I["b"])(["GET_HOSTS_FROM_API","MODIFY_TOKEN"])),{},{deleteHost:function(){this.MODIFY_TOKEN(this.token);var t=H.hostDestroyUrl+this.destroy_host_id;return j()(t,{headers:{"Content-type":"application/json",token:this.token},method:"DELETE"}).then((function(t){return console.log(t),t}))},addNewHost:function(){j()(H.addHostUrl,{headers:{"Content-type":"application/json",token:this.token},method:"POST",data:{host:this.add_host_ip,dnsName:this.add_host_dns,ssh_user:this.add_host_user,ssh_pass:this.add_host_pass,description:this.add_host_desc}}).then((function(t){return console.log(t),t}))},getDetails:function(t){var e=this;console.log(t);var a=H.hostInfo+t;j()(a,{headers:{"Content-type":"application/json"},method:"GET"}).then((function(t){return console.log(t),e.c_host_id=t.data.id,e.c_host_ip=t.data.host,e.c_host_dns=t.data.dnsName,e.c_host_user=t.data.ssh_user,e.c_host_pass=t.data.ssh_pass,e.c_host_desc=t.data.description,e.c_host_creator=t.data.creator,e.c_host_date=t.data.createDate,e.c_host_used=t.data.used,t}))},updateHostDataOnHost:function(){var t=H.hostInfo;j()(t,{headers:{"Content-type":"application/json",token:this.token},method:"POST",data:{id:this.c_host_id,host:this.c_host_ip,dnsName:this.c_host_dns,ssh_user:this.c_host_user,ssh_pass:this.c_host_pass,description:this.c_host_desc,creator:this.c_host_creator,createDate:this.c_host_createDate,used:this.used}}).then((function(t){return console.log(t),t}))}}),mounted:function(){this.GET_HOSTS_FROM_API()}},G=R,L=a("b0af"),F=a("99d9"),M=a("8fea"),K=a("cd55"),$=a("49e2"),B=a("c865"),Q=a("0393"),Y=a("71a3"),q=a("c671"),z=a("fe57"),W=a("8654"),J=a("a844"),X=Object(r["a"])(G,w,U,!1,null,null,null),Z=X.exports;d()(X,{VBtn:_["a"],VCard:L["a"],VCardTitle:F["a"],VDataTable:M["a"],VExpansionPanel:K["a"],VExpansionPanelContent:$["a"],VExpansionPanelHeader:B["a"],VExpansionPanels:Q["a"],VTab:Y["a"],VTabItem:q["a"],VTabs:z["a"],VTextField:W["a"],VTextarea:J["a"]});var tt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-card",{attrs:{fluid:""}},[a("v-expansion-panels",[a("v-expansion-panel",[a("v-expansion-panel-header",[t._v(" ACTIONS ")]),a("v-expansion-panel-content",[a("v-card",[a("v-tabs",[a("v-tab",[t._v("Delete Cluster")]),a("v-tab-item",[a("v-card",{attrs:{flat:""}},[a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"Cluster name"},model:{value:t.destroy_cluster,callback:function(e){t.destroy_cluster=e},expression:"destroy_cluster"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"token for api"},model:{value:t.token,callback:function(e){t.token=e},expression:"token"}}),a("v-btn",{staticClass:"pa-2 ma-2",attrs:{block:""},on:{click:t.destroyCluster}},[t._v(" SEND REQUEST ")])],1)],1)],1)],1)],1)],1)],1),a("v-card-title",[a("v-text-field",{attrs:{"append-icon":"mdi-magnify",label:"Search","single-line":"","hide-details":""},model:{value:t.search,callback:function(e){t.search=e},expression:"search"}})],1),a("v-data-table",{staticClass:"elevation-1",attrs:{headers:t.headers,items:this.$store.state.clusters,"items-per-page":10,search:t.search},scopedSlots:t._u([{key:"item.action",fn:function(e){var s=e.item;return[a("v-btn",{attrs:{color:"primary",elevation:"2","pa-2":""},on:{click:function(e){return t.getDetails(s.id)}}},[t._v("Details")])]}}])})],1)},et=[],at={data:function(){return{search:"",headers:[{text:"id",value:"id"},{text:"Cluster name",value:"clusterName"},{text:"Creator",value:"creator"},{text:"Create Date",value:"createDate"},{text:"Destroy date",value:"destroyDate"},{text:"Actions",value:"action"}],destroy_cluster:null,token:null,size:null,cluster_name:null,hosts:"",reserve_time:null,reserve_type:null,description:null}},computed:Object(D["a"])({},Object(I["c"])(["CLUSTERS"])),methods:Object(D["a"])(Object(D["a"])({},Object(I["b"])(["GET_CLUSTERS_FROM_API"])),{},{createCluster:function(){console.log(this.hosts),console.log(this.size),console.log(this.cluster_name),console.log(this.description),console.log(this.reserve_time),console.log(this.reserve_type),j()(H.clusterCreate,{headers:{"Content-type":"application/json",token:this.token},method:"GET",data:{host:this.hosts,size:this.size,name:this.cluster_name,desc:this.description,time:this.reserve_time,type:this.reserve_type}}).then((function(t){return console.log(t),t}))},destroyCluster:function(){var t=H.destroyClusterUrl+this.destroy_cluster;j()(t,{headers:{"Content-type":"application/json",token:this.token},method:"POST"}).then((function(t){return console.log(t),t}))}}),mounted:function(){this.GET_CLUSTERS_FROM_API()}},st=at,nt=Object(r["a"])(st,tt,et,!1,null,null,null),ot=nt.exports;d()(nt,{VBtn:_["a"],VCard:L["a"],VCardTitle:F["a"],VDataTable:M["a"],VExpansionPanel:K["a"],VExpansionPanelContent:$["a"],VExpansionPanelHeader:B["a"],VExpansionPanels:Q["a"],VTab:Y["a"],VTabItem:q["a"],VTabs:z["a"],VTextField:W["a"]});var lt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"api-page"},[a("div",{staticClass:"api-page"},[t._l(this.$store.state.apis,(function(e){return a("div",{key:e.endpoint},[a("v-card",{attrs:{elevation:"2"}},[a("p",{staticClass:"pa-2 ma-2"},[t._v("endpoint: "+t._s(e.endpoint))]),a("p",{staticClass:"pa-2 ma-2"},[t._v("security: "+t._s(e.security))]),a("p",{staticClass:"pa-2 ma-2"},[t._v("requestType: "+t._s(e.requestType))]),a("p",{staticClass:"pa-2 ma-2"},[t._v("requestExample: "+t._s(e.requestExample))]),a("p",{staticClass:"pa-2 ma-2"},[t._v("responseExample: "+t._s(e.responseExample))]),a("p",{staticClass:"pa-2 ma-2"},[t._v("description: "+t._s(e.description))])])],1)}))],2)])},it=[],rt={name:"App",components:{},data:function(){return{}},computed:Object(D["a"])({},Object(I["c"])(["API"])),methods:Object(D["a"])({},Object(I["b"])(["GET_INFO_FROM_API"])),created:function(){this.GET_INFO_FROM_API()}},ct=rt,dt=Object(r["a"])(ct,lt,it,!1,null,null,null),ut=dt.exports;d()(dt,{VCard:L["a"]});var pt=a("5de4"),_t=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"home-page"},[a("v-tabs",[a("v-tab",[t._v("GET TOKEN (AUTH)")]),a("v-tab",[t._v("GENERATE TOKEN(UPDATE TOKEN IN DB)")]),a("v-tab",[t._v("ADD NEW ACCOUNT")]),a("v-tab-item",[a("v-card",{attrs:{elevation:"2"}},[a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"LOGIN"},model:{value:t.login,callback:function(e){t.login=e},expression:"login"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"PASSWORD"},model:{value:t.password,callback:function(e){t.password=e},expression:"password"}}),a("v-btn",{staticClass:"pa-2 ma-2",attrs:{block:""},on:{click:t.auth}},[t._v(" SEND REQUEST ")]),a("v-textarea",{staticClass:"pa-2 ma-2",attrs:{counter:"",label:"YOUR API TOKEN",rules:t.rules,value:t.value},model:{value:t.token,callback:function(e){t.token=e},expression:"token"}},[t._v(t._s(this.token))])],1)],1),a("v-tab-item",[a("v-card",{attrs:{elevation:"2"}},[a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"LOGIN"},model:{value:t.login,callback:function(e){t.login=e},expression:"login"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"PASSWORD"},model:{value:t.password,callback:function(e){t.password=e},expression:"password"}}),a("v-btn",{staticClass:"pa-2 ma-2",attrs:{block:""},on:{click:t.generate}},[t._v(" SEND REQUEST ")]),a("v-textarea",{staticClass:"pa-2 ma-2",attrs:{counter:"",label:"YOUR API TOKEN",rules:t.rules,value:t.value},model:{value:t.token,callback:function(e){t.token=e},expression:"token"}},[t._v(t._s(this.token))])],1)],1),a("v-tab-item",[a("v-card",{attrs:{elevation:"2"}},[a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"LOGIN"},model:{value:t.login,callback:function(e){t.login=e},expression:"login"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"PASSWORD"},model:{value:t.password,callback:function(e){t.password=e},expression:"password"}}),a("v-text-field",{staticClass:"pa-2 ma-2",attrs:{label:"api token"},model:{value:t.token,callback:function(e){t.token=e},expression:"token"}}),a("v-btn",{staticClass:"pa-2 ma-2",attrs:{block:""},on:{click:t.addNewAccount}},[t._v(" SEND REQUEST ")])],1)],1)],1)],1)},ht=[],vt={name:"App",components:{},data:function(){return{login:null,password:null,token:null}},computed:Object(D["a"])({},Object(I["c"])(["HOSTS"])),methods:Object(D["a"])(Object(D["a"])({},Object(I["b"])(["GET_HOSTS_FROM_API","AUTH"])),{},{auth:function(){var t=this.login,e=this.password;this.AUTH({login:t,password:e}),this.token=this.$store.state.token},generate:function(){var t=this;j()(H.generateToken,{headers:{"Content-type":"application/json",token:this.token},method:"POST",data:{login:this.login,password:this.password}}).then((function(e){return console.log(e),t.token=e.data.token,e}))},addNewAccount:function(){var t=this;j()(H.addUser,{headers:{"Content-type":"application/json",token:this.token},method:"POST",data:{login:this.login,password:this.password}}).then((function(e){return console.log(e),t.token=e.data.token,e}))}}),mounted:function(){this.GET_HOSTS_FROM_API()}},mt=vt,ft=Object(r["a"])(mt,_t,ht,!1,null,null,null),bt=ft.exports;d()(ft,{VBtn:_["a"],VCard:L["a"],VTab:Y["a"],VTabItem:q["a"],VTabs:z["a"],VTextField:W["a"],VTextarea:J["a"]});var Tt=a("f309");s["a"].use(Tt["a"]);var kt=new Tt["a"]({});s["a"].use(I["a"]);var xt=new I["a"].Store({state:{token:"default",login:null,hosts:[],clusters:[],apis:[]},mutations:{SET_HOSTS_TO_STATE:function(t,e){t.hosts=e},SET_LOGIN_TO_STATE:function(t,e){t.login=e},SET_CLUSTERS_TO_STATE:function(t,e){t.clusters=e},SET_TOKEN:function(t,e){t.token=e},SET_API_TO_STATE:function(t,e){t.apis=e}},actions:{GET_HOSTS_FROM_API:function(t){var e=t.commit;return j()(H.hostsUrl,{headers:{"Content-type":"application/json"},method:"GET"}).then((function(t){return console.log(t),e("SET_HOSTS_TO_STATE",t.data.hosts),t})).catch((function(t){console.log(t)}))},GET_CLUSTERS_FROM_API:function(t){var e=t.commit;return j()(H.clusterUrl,{headers:{"Content-type":"application/json"},method:"GET"}).then((function(t){return console.log(t),e("SET_CLUSTERS_TO_STATE",t.data),t})).catch((function(t){console.log(t)}))},AUTH:function(t,e){var a=t.commit,s=e.login,n=e.password;return console.log(s),console.log(n),j()(H.authUrl,{headers:{"Content-type":"application/json"},method:"POST",data:{login:s,password:n}}).then((function(t){return console.log(t),a("SET_LOGIN_TO_STATE",s),a("SET_TOKEN",t.data.token),t.data}))},MODIFY_TOKEN:function(t,e){var a=t.commit;a("SET_TOKEN",e)},GET_INFO_FROM_API:function(t){var e=t.commit;return j()(H.infoUrl,{headers:{"Content-type":"application/json"},method:"GET"}).then((function(t){return console.log(t),e("SET_API_TO_STATE",t.data.API),t.data.API})).catch((function(t){console.log(t)}))}},getters:{HOSTS:function(t){return t.hosts},CLUSTERS:function(t){return t.clusters},TOKEN:function(t){return t.token},LOGIN:function(t){return t.login},API:function(t){return t.apis}}}),Ot=xt;s["a"].use(A["a"]),s["a"].config.productionTip=!1,s["a"].use(j.a),window.axios=a("bc3a");var Et=new A["a"]({routes:[{path:"/hosts",component:Z,name:"Hosts"},{path:"/clusters",component:ot,name:"Clusters"},{path:"/api",component:ut,name:"API"},{path:"/login",component:pt["default"],name:"login"},{path:"/home",component:bt,name:"home"}]});new s["a"]({el:"#app",render:function(t){return t(y)},vuetify:kt,store:Ot,router:Et})},"5de4":function(t,e,a){"use strict";var s=a("ca4d"),n=a("cabb"),o=a("2877"),l=Object(o["a"])(n["default"],s["a"],s["b"],!1,null,null,null);e["default"]=l.exports},ca4d:function(t,e,a){"use strict";a.d(e,"a",(function(){return s})),a.d(e,"b",(function(){return n}));var s=function(){var t=this,e=t.$createElement;t._self._c;return t._m(0)},n=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"login=page"},[a("h1",[t._v("Login page")])])}]},cabb:function(t,e,a){"use strict";var s=a("2531"),n=a.n(s);e["default"]=n.a}});
//# sourceMappingURL=app.a07c74a8.js.map