package com.sailfish.gateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class CurrentLimitingFilter extends ZuulFilter {
	private static final boolean SHOULD_FILTER_YES = true;
	private static final int FILTER_ORDER = -4;
	private static final int PERMITS_PER_SECOND = 1000;
	private static final RateLimiter RATE_LIMITER = RateLimiter.create(PERMITS_PER_SECOND);

	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER_YES;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		if (!RATE_LIMITER.tryAcquire()) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
			try {
				ctx.getResponse().getWriter().write("too many request");
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}
}
