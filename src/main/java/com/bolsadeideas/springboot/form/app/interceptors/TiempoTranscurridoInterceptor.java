package com.bolsadeideas.springboot.form.app.interceptors;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//Se crea una clase interceptor
@Component("tiempoTranscurridoInterceptor")
public class TiempoTranscurridoInterceptor implements HandlerInterceptor{
	
	// Se agrega un atributo
	
	private static final Logger logger = LoggerFactory.getLogger(TiempoTranscurridoInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//Validacion que se cumpla interceptor solo en el Form/ get 
		if (request.getMethod().equalsIgnoreCase("post")) {
			return true;
		}
		
		if (handler instanceof HandlerMethod ) {
			HandlerMethod metodo = (HandlerMethod)handler;
			logger.info("Es un metodo de controlador: " + metodo.getMethod().getName());
		}
		logger.info("TiempoTranscurridoInterceptor: preHandle() entrando....");
		long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);
		Random random = new Random();
		
		Integer demora = random.nextInt(100);
		
		Thread.sleep(demora);
		//Opcion de redireccion cuando falla 
		/*response.sendRedirect(request.getContextPath().concat("/login"));*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if (request.getMethod().equalsIgnoreCase("post")) {
			return ;
		}
		
		long tiempoFin = System.currentTimeMillis();
		//Se realiza un cast para convertir de objeto a long (Long)
		long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		long tiempoTranscurrido = tiempoFin - tiempoInicio;
		 
		if (handler instanceof HandlerMethod && modelAndView != null) {
			modelAndView.addObject("tiempoTranscurrido", tiempoTranscurrido);
		}
	
		logger.info("Tiempo Transcurrido: "+ tiempoTranscurrido + " milisegundos");
		logger.info("TiempoTranscurridoInterceptor: postHandle() Saliendo....");

	}

}
