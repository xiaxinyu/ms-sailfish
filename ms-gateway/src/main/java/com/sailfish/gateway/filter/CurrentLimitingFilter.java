package com.sailfish.gateway.filter;


public class CurrentLimitingFilter {
//@Component
//public class CurrentLimitingFilter extends ZuulFilter {
//	private static final boolean SHOULD_FILTER_YES = true;
//	private static final int FILTER_ORDER = -4;
//	private static final int PERMITS_PER_SECOND = 1000;
//	private static final RateLimiter RATE_LIMITER = RateLimiter.create(PERMITS_PER_SECOND);
//
//	@Override
//	public boolean shouldFilter() {
//		return SHOULD_FILTER_YES;
//	}
//
//	@Override
//	public Object run() throws ZuulException {
//		RequestContext ctx = RequestContext.getCurrentContext();
//		if (!RATE_LIMITER.tryAcquire()) {
//			ctx.setSendZuulResponse(false);
//			ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
//			try {
//				ctx.getResponse().getWriter().write("too many request");
//			} catch (Exception e) {
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public String filterType() {
//		return FilterConstants.PRE_TYPE;
//	}
//
//	@Override
//	public int filterOrder() {
//		return FILTER_ORDER;
//	}
//}
}