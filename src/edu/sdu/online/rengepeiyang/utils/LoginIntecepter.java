package edu.sdu.online.rengepeiyang.utils;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import edu.sdu.online.rengepeiyang.beans.User;

public class LoginIntecepter extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = ActionContext.getContext();
		Map session = ctx.getSession();
		String actionName = invocation.getInvocationContext().getName();
		System.out.println("ActionName:"+actionName);
		User user = (User)session.get("user");
		if(user!=null && user.isIsreg()&& actionName.equals("student_load_for_reg")) {
			ActionContext.getContext().put("tip", "操作失败，您已激活用户，无需重复激活"); 
			return "error";
		}
		if(user!=null &&!user.isIsreg()&& !actionName.equals("student_load_for_reg")) {
			ActionContext.getContext().put("tip", "操作失败，如果您还未激活用户，请先用学号和身份证号激活"); 
			return "login";
		}
		if(actionName.equals("select_answer") && user == null) {
            ActionContext.getContext().put("tip", "请先登录再测评"); 
			return "login";
		}
		if(actionName.equals("profile")) {
			String sid = ((String[])invocation.getInvocationContext().getParameters().get("student.sid"))[0];
			//System.out.println(sid);
			if(user == null) {
	            ActionContext.getContext().put("tip", "请先登录"); 
				return "login";
			} else {
				if(user.getType().equals("student")&&!user.getSid().equals(sid)) {
					ActionContext.getContext().put("tip", "获取信息失败，您只能获取自己的信息"); 
					return "error";
				}
			}
		}
		if(actionName.endsWith("load_answer_modify")) {
			if(user == null) {
	            ActionContext.getContext().put("tip", "请先登录,再对您的测评结果进行查看修改"); 
				return "login";
			}
		}
		if(actionName.endsWith("answer_modify")) {
			if(user == null) {
	            ActionContext.getContext().put("tip", "请先登录,再对您的测评结果进行修改"); 
				return "login";
			}
		}
		if(actionName.equals("student_load_for_modify")) {
			String sid = ((String[])invocation.getInvocationContext().getParameters().get("student.sid"))[0];
			if(user == null) {
	            ActionContext.getContext().put("tip", "请先登录,再对您的基本信息进行修改"); 
				return "login";
			} else {
				if(user.getType().equals("student")&&!user.getSid().equals(sid)) {
					ActionContext.getContext().put("tip", "获取信息失败，您只能获取自己的信息"); 
					return "error";
				}
			}
		}
		if(actionName.equals("student_load_for_reg")) {
			String sid = ((String[])invocation.getInvocationContext().getParameters().get("student.sid"))[0];
			if(user == null) {
				 ActionContext.getContext().put("tip", "如果您还未激活账号，请先用学号和身份证号验证"); 
				 return "login";
			} else {
				//System.out.println(sid+":"+user.getType());
				if(user.getType().equals("student")&&!user.getSid().equals(sid)) {
					ActionContext.getContext().put("tip", "只能激活您自己的账号哦，亲"); 
					return "login";
				}
			}
		}
		if(user!=null) {
			return invocation.invoke();
		}
		ctx.put("tip", "您还没有登录，请先登录");
		return Action.LOGIN;
	}

}
