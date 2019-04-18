
public class aaa(){


	// 맵핑 정보 web.xml이랑 맞춰주기 
	@RequestMapping( value="loginAjax.Ajax" /*,method = {RequestMethod.POST}*/)
	public String AjaxLogin(@RequestParam("id") String id,
			@RequestParam("pw") String pw, Model model,
			HttpServletRequest request,	HttpServletResponse response) {

		// 1. RequestPrameter로 id , pw 에 저장함.
		// 2. JsonArray, JSONObject 생성후 response로 돌려보냄 .
		// 3. Controller Try void 에 null 반환 하거나 
		//    String 에 null 반환할것.

		id = id.trim();
		
		/*
		 서비스 구현부 
		 */
		
		try {
			/*Json 객체 생성*/
			JSONArray jCase = new JSONArray();  // 큰배열
			
			JSONObject jObj = new JSONObject(); // 내부 오브젝트
			jObj.put("bool", bool);
			jCase.add(jObj);
			
			response.getWriter().print(jCase);;
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}

}