

$(document).ready(function() {

	//使用 Ajax 的方式 判断登录   
		$("#history").click(function() {
			var url = 'history';
			//获取表单值，并以json的数据形式保存到params中   
				var params = {
					grade : $("#grade").val(),
					tid : $("#tid").val(),
					depid : $("#depid").val(),
					clazz : $("#clazz").val()
				}
				//使用$.post方式   
				$.post(url, //服务器要接受的url   
						params, //传递的参数   
						function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据   
							var member = eval("(" + data + ")"); //包数据解析为json 格式   
							$('#result').html("欢迎您： " + member.name);
						}, 'json' //数据传递的类型 json   
				);
			});
	}) ;

$(document).ready(function() {

	//使用 Ajax 的方式 判断登录   
		$("#ability11").click(function() {
			var url = 'query11';
			//获取表单值，并以json的数据形式保存到params中   
				var params = {
				grade : $("#grade").val(),
					gradename : $("#grade").find("option:selected").text(),
					tid : $("#tid").val(),
					tname : $("#tid").find("option:selected").text(),
					depid : $("#depid").val(),
					dname : $("#depid").find("option:selected").text(),
					clazz : $("#clazz").val(),
					cname : $("#clazz").find("option:selected").text()
				}
				//使用$.post方式   
				$.post(url, //服务器要接受的url   
						params, //传递的参数   
						function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据   
							var member = eval("(" + data + ")"); //包数据解析为json 格式   
							$('#result').html( member.page11);
						}, 'json' //数据传递的类型 json   
				);
			});
	}) ;
$(document).ready(function() {

	//使用 Ajax 的方式 判断登录   
		$("#suyang").click(function() {
			var url = 'querysuyang';
			//获取表单值，并以json的数据形式保存到params中   
				var params = {
				grade : $("#grade").val(),
					gradename : $("#grade").find("option:selected").text(),
					tid : $("#tid").val(),
					tname : $("#tid").find("option:selected").text(),
					depid : $("#depid").val(),
					dname : $("#depid").find("option:selected").text(),
					clazz : $("#clazz").val(),
					cname : $("#clazz").find("option:selected").text()
				}
				//使用$.post方式   
				$.post(url, //服务器要接受的url   
						params, //传递的参数   
						function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据   
							var member = eval("(" + data + ")"); //包数据解析为json 格式   
							$('#result').html(member.pageSuyang);
						}, 'json' //数据传递的类型 json   
				);
			});
	}) ;
$(document).ready(function() {

	//使用 Ajax 的方式 判断登录   
		$("#ti24").click(function() {
			var url = 'query24';
			//获取表单值，并以json的数据形式保存到params中   
				var params = {
				grade : $("#grade").val(),
					gradename : $("#grade").find("option:selected").text(),
					tid : $("#tid").val(),
					tname : $("#tid").find("option:selected").text(),
					depid : $("#depid").val(),
					dname : $("#depid").find("option:selected").text(),
					clazz : $("#clazz").val(),
					cname : $("#clazz").find("option:selected").text()
				}
				//使用$.post方式   
				$.post(url, //服务器要接受的url   
						params, //传递的参数   
						function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据   
							var member = eval("(" + data + ")"); //包数据解析为json 格式   
							$('#result').html( member.page24);
						}, 'json' //数据传递的类型 json   
				);
			});
	}) ;



$(document).ready(function() {

	//使用 Ajax 的方式 判断登录   
		$("#personal").click(function() {
			var url = 'singelquery';
			
			//获取表单值，并以json的数据形式保存到params中   
				var params = {
					grade : $("#grade").val(),
					tid : $("#tid").val(),
					depid : $("#depid").val(),
					clazz : $("#clazz").val()
				}
				//使用$.post方式   
				$.post(url, //服务器要接受的url   
						params, //传递的参数   
						function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据   
							var member = eval("(" + data + ")"); //包数据解析为json 格式   
							$('#result').html("欢迎您： " + member.name);
						}, 'json' //数据传递的类型 json   
				);
			});
	}) ;



$(document).ready(function() {

	//使用 Ajax 的方式 判断登录   
		$("#students").click(function() {
			var url = 'getstudents';

			//获取表单值，并以json的数据形式保存到params中   
				var params = {
					clazzid : $("#clazz").val()
				}
				//使用$.post方式   
				$.post(url, //服务器要接受的url   
						params, //传递的参数   
						function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据   
							var member = eval("(" + data + ")"); //包数据解析为json 格式   
							$('#result').html( member.pagestudents);
						}, 'json' //数据传递的类型 json   
				);
			});
	}) ;
 $(document).ready(function() {

	//使用 Ajax 的方式 判断登录   
		$("#joinnumber").click(function() {
			var url = 'cplquery';

			//获取表单值，并以json的数据形式保存到params中   
				var params = {
					grade : $("#grade").val(),
					gradename : $("#grade").find("option:selected").text(),
					tid : $("#tid").val(),
					tname : $("#tid").find("option:selected").text(),
					depid : $("#depid").val(),
					dname : $("#depid").find("option:selected").text(),
					clazz : $("#clazz").val(),
					cname : $("#clazz").find("option:selected").text()
				}
				//使用$.post方式   
				$.post(url, //服务器要接受的url   
						params, //传递的参数   
						function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据   
							var member = eval("(" + data + ")"); //包数据解析为json 格式   
							$('#result').html( member.pagecpl);
						}, 'json' //数据传递的类型 json   
				);
			});
	}) ;


 $(document).ready(function(){
       $("#a").click(function(event){
       var myval = $("#result").text();
    	   if(myval=='欢迎使用'){
    		    alert("当前页面没有表格，请先查询");
                event.preventDefault();
    	   }
    	   
        
       });
     });

 

      $(function(){
        $('li:has(ul)')
          .click(function(event){
            if (this == event.target) {
              if ($(this).children().is(':hidden')) {
                $(this)
                  .css('list-style-image','url(images/query/minus.gif)')
                  .children().slideDown();
              }
              else {
                $(this)
                  .css('list-style-image','url(images/query/plus.gif)')
                  .children().slideUp();
              }
            }
            return true;
          })
          .css({cursor:'pointer',
                'list-style-image':'url(images/query/plus.gif)'})
          .children().hide();
        $('li:not(:has(ul))').css({
          cursor: 'default',
          'list-style-image':'none'
        	  
        });
      });

