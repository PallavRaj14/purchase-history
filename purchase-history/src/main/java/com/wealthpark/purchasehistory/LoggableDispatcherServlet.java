package com.wealthpark.purchasehistory;

import java.util.UUID;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@Component("dispatcherServlet")
public class LoggableDispatcherServlet extends DispatcherServlet {

	private static final Logger logger = LoggerFactory.getLogger(LoggableDispatcherServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String requestID = requestId();
		String clientIP = request.getRemoteAddr();
		String requestURL = request.getRequestURI();
		String requestMethod = request.getMethod();

		// Inject in context MDC for log details
		String mdcData = String.format("[ip: %s | reqId: %s | %s | %s ] ", clientIP, requestID, requestURL,
				requestMethod);

		MDC.put("mdcData", mdcData); // Referenced from logging configuration.

		super.doService(request, response);

	}

	private String requestId() {
		return UUID.randomUUID().toString();
	}

}
