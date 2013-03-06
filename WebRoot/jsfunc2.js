var xmlhttp;
function initAnswers() {
	var answers = document.getElementById("answers");
	var answer_array = answers.value.split(",");
	for(i=0; i<answer_array.length; i++) {
		var sel_val=document.getElementsByName("qb.question"+(i+1));
		for(j=0; j<sel_val.length; j++) {
			if(answer_array[i] == sel_val[j].value)
				sel_val[j].checked = true;
		}
	}
}
function loadteacher() {
	var sel = document.getElementById("depid").value;
	selteacher(sel);
}
function loadmajorandteacher(){
	var sel = document.getElementById("depid").value;
	 selteacher(sel);
	
}


function selteacher(sel) {
	
	var context = document.getElementById("context");
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  //alert("code for IE7+, Firefox, Chrome, Opera, Safari");
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	 // alert("code for IE6, IE5");
	  }
	//alert("hello");
	xmlhttp.open("GET",context.value+"/loadteacher2.jsp?depid="+sel,true);
	xmlhttp.send(null);
	xmlhttp.onreadystatechange = callback;
	
}

function showclazz(){
	var sel = document.getElementById("tid").value;
	loadclazz(sel);
}

function loadclazz(sel){
	var context = document.getElementById("context");
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  //alert("code for IE7+, Firefox, Chrome, Opera, Safari");
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	 // alert("code for IE6, IE5");
	  }
	//alert("hello");
	xmlhttp.open("GET",context.value+"/loadclazz2.jsp?tid="+sel,true);

	xmlhttp.send(null);
	xmlhttp.onreadystatechange = callbackclazz;
}
function callback(){ 
	//alert("hello2");
     if(4==xmlhttp.readyState && 200 ==xmlhttp.status){  
    	 var s = xmlhttp.responseText;
//    	 alert(s);
//    	 alert(result[0]);
//    	 alert(result[1]);
    	 document.getElementById("daoyuan").innerHTML = s;
     }  
 } 
function callbackclazz(){ 
	//alert("hello2");
     if(4==xmlhttp.readyState && 200 ==xmlhttp.status){  
    	 var s = xmlhttp.responseText;
//    	 alert(s);
//    	 alert(result[0]);
//    	 alert(result[1]);
    	 document.getElementById("myclazz").innerHTML = s;
     }  
 } 

function loadDepartment() {
	var context = document.getElementById("context");
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  //alert("code for IE7+, Firefox, Chrome, Opera, Safari");
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	 // alert("code for IE6, IE5");
	  }
	//alert("hello");
	xmlhttp.open("GET",context.value+"/loaddepnames.jsp",true);
	xmlhttp.send(null);
	xmlhttp.onreadystatechange = callback2;
}
function callback2(){ 
	//alert("hello2");
     if(4==xmlhttp.readyState && 200 ==xmlhttp.status){  
    	 var s = xmlhttp.responseText;
//    	 alert(s);
//    	 alert(result[0]);
//    	 alert(result[1]);
    	 document.getElementById("depname").innerHTML = s;
     }  
 }
//ѧ���춯��ʼ��
function initmodify() {

	initval("years","selyears");
}
function initval(idname,idval) {
	var idvalue=document.getElementById(idname).options;
	var selvalue=document.getElementById(idval).value;
	for(i=0; i<idvalue.length; i++) {
		if(idvalue[i].value == selvalue)
			idvalue[i].selected = true;
	}
}
function test() {
	alert("hello");
	var sel = document.getElementById("depid");
	alert(sel.value);
}
function getExcel(){
	var grade=document.getElementById(grade).value;
	var gradename=document.getElementById(grade).text;
	var tid=document.getElementById(tid).value;
	var tname=document.getElementById(tid).text;
	var depid=document.getElementById(depid).value;
	var dname=document.getElementById(depid).text;
    var clazz=document.getElementById(clazz).value;
	var cname=document.getElementById(clazz).text;
	location.href="toExcel.action?grade="+grade+"&gradename="+gradename+"&tid="+tid+"&tname="
	+tname+"&depid="+depid+"&dname="+dname+"&clazz="+clazz+"&cname="+cname;
}
	