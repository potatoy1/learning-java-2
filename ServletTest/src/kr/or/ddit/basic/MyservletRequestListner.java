package kr.or.ddit.basic;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyservletRequestListner implements ServletRequestListener , ServletRequestAttributeListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("[MyservletRequestListner] requestDestroyed => " + sre);
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("[MyservletRequestListner] requestInitialized => " + sre);
		
	}

	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("[MyservletRequestListner] attributeAdded => " + srae.getName() + ": " + srae.getValue());
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("[MyservletRequestListner] attributeRemoved => " + srae.getName() + ": " + srae.getValue());
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("[MyservletRequestListner] attributeReplaced => " + srae.getName() + ": " + srae.getValue());
	}

}
