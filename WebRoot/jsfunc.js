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
    
    initval("years","yearsvalue");
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
	
	xmlhttp.open("GET",context.value+"/loadteacher.jsp?depid="+sel,true);
	xmlhttp.send();
	xmlhttp.onreadystatechange = callback;
	//alert("hello");
}
function callback(){ 
     if(4==xmlhttp.readyState && 200 ==xmlhttp.status){ 
    	 var s = xmlhttp.responseText;
    	 var result = s.split("||");
    	 //alert(result[0]);
    	 document.getElementById("tid").innerHTML = result[0];
    	 
    	 if(result.length == 2) {
    	 	document.getElementById("mid").innerHTML = result[1];
    	 }
    	 document.getElementById("cname").innerHTML="<option>--请选择班级--</option>";
     }  
 } 
 function loadclazz() {
    
 	var context = document.getElementById("context");
 	var majorid = document.getElementById("mid").value;
 	if(!/^\d{1,}$/.test(majorid))
 	return;
 	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  //alert("code for IE7+, Firefox, Chrome, Opera, Safari");
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }

	xmlhttp.open("GET",context.value+"/loadclazz.jsp?mid="+majorid,true);
	xmlhttp.send(null);
	xmlhttp.onreadystatechange = callback3;
 }
 function callback3(){ 
     if(4==xmlhttp.readyState && 200 ==xmlhttp.status){  
    	 var s = xmlhttp.responseText;
    	 document.getElementById("cid").innerHTML = s;
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
	
     if(4==xmlhttp.readyState && 200 ==xmlhttp.status){  
    	 var s = xmlhttp.responseText;
//    	 alert(s);
    	 document.getElementById("depname").innerHTML = s;
    	 
     }  
 }
function initModify() {
    //alert("hello");
	initval("years","yearsvalue");
	
	loadteacher();
	setTimeout(function() {initval("mid","midvalue");},200);
	setTimeout(function() {initval("tid","tidvalue");},200);
	setTimeout(function() {loadclazz();},200);
	
	setTimeout(function() {initval("cid","cidvalue");},300);
}
function initval(idname,idval) {

    var selvalue=document.getElementById(idval).value;
	var idvalue=document.getElementById(idname).options;
	//alert(idname+" "+idval+" "+idvalue);
	for(i=0; i<idvalue.length; i++) {
		if(idvalue[i].value == selvalue) {
			idvalue[i].selected = true;
			}
	}

}
function check24() {
	var flag = 0;
	var message="";
	for(i=0; i<24; i++) {
			flag = 0;
			temp = document.getElementsByName("qb.question"+(i+1));
			for(j=0; j<temp.length; j++) {
				if(temp[j].checked) {
					flag = 1;
					break;
				}
			}
			if(flag ==0)
			message +=(i+1)+",";
	}
	if(message!="") {
		alert("请完成选择"+message+"题");
		return false;
	} else {
		return true;
	}
}

function lookback(grade) {
	
	var info = "";
	var gra = new Array();
	gra[0]="一年级";
	gra[1]="二年级";
	gra[2]="三年级";
	gra[3]="四年级";
	gra[4]="五年级";
	gra[5]="六年级";
	gra[6]="七年级";
	gra[7]="八年级";
	for(i=1; i<=grade; i++) {
		info +="<td><a href='load_answer_modify?grade="+i+"'>"+gra[i-1]+"</a></td>";
	}
	if(info != "") {
		info = "<table align='center'><tr>" +info +"</tr></table>";
		document.getElementById("content").innerHTML = info;
	}
	
}
function test() {
	alert("hello");
	
	var sel = document.getElementById("depid");
	alert(sel.value);
}