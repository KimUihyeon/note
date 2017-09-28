// JavaScript Document
// 



var AjaxContain = function ( Url , Data) {

	this.url = Url+"Ajax.Ajax";
	this.data = Data;

	this.getUrl = function (){
		return this.url;
	}

	this.getData= function (){
		return this.data;
	}

}


/* Login Ajax */

function LoginAjax(path,data){

	var ajaxObj = new AjaxContain(path,data);

	var Data = ajaxObj.getData();

	var Url = ajaxObj.getUrl();

	$.ajax({

		type : 'post',
		url : Url ,
		data : {
			id : Data[0],
			pw : Data[1]
		},
		dataType : 'json',

		success : function (data){

			console.log(data);

			// 데이터가 Json 타입으로 오면 자동으로 success로 오게된다.
			// parseJson을 해줄 필요가 없다.
			
		},
		error : function (xht,status,error) {
			
			console.log(xht+"\r\n"+status+"\r\n"+error);
			
		}


	})

}