package ptithcm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ptithcm.entity.TaiKhoan;

public class QLInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		TaiKhoan tk = (TaiKhoan) session.getAttribute("userLogin");
		if(tk == null) {
			response.sendRedirect(request.getContextPath() + "/taikhoan/login.htm");
			return false;
		}
		else if(tk.getVaitro().getMavt()==1) {
			System.out.println("in");
			response.sendRedirect(request.getContextPath() + "/taikhoan/index.htm");
			return false;
		} 
		return true;
	}
}
