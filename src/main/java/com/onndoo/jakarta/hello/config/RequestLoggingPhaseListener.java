package com.onndoo.jakarta.hello.config;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.component.UIViewRoot;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;

import jakarta.inject.Inject;

public class RequestLoggingPhaseListener implements PhaseListener {

    private static final long serialVersionUID = 1L;

    //@Inject
    //Logger LOG;

    @Override
    public void afterPhase(PhaseEvent event) {
        //LOG.log(Level.INFO, "after phase:{0}", event.getPhaseId());
        printLog(event, "after phase:"); 
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        //LOG.log(Level.INFO, "before phase:{0}", event.getPhaseId());
        printLog(event, "before phase:"); 
    }

    protected void printLog(PhaseEvent event, String msg) { 
		UIViewRoot view = event.getFacesContext().getViewRoot(); 
		String viewID = "no view"; 
		if (view != null) viewID = view.getViewId(); 
		System.out.println(msg + event.getPhaseId() + " " + viewID);
		
        printRequestParameters(event);
        printRequestAttributes(event);        
		printSessionAttributes(event);
		
	}

    private void printRequestParameters(PhaseEvent event) {
        Map<String, String> reqParams = event.getFacesContext().getExternalContext().getRequestParameterMap();
        StringBuilder sb = new StringBuilder();
        for (String key : reqParams.keySet()) {
            sb.append("(" + key + "=" + reqParams.get(key) + ") ");
            }
        System.out.println("Request Parameters : " + sb.toString());
    } 

    private void printRequestAttributes(PhaseEvent event) {
        Map<String,Object> reqAttrs = event.getFacesContext().getExternalContext().getRequestMap();
        StringBuilder sb = new StringBuilder();
        for (String key : reqAttrs.keySet()) {
            sb.append("(" + key + "=" + reqAttrs.get(key) + ") ");
            }
        System.out.println("Request Attributes : " + sb.toString());
    }
    
    private void printSessionAttributes(PhaseEvent event) {
        Map<String,Object> sessAttrs = event.getFacesContext().getExternalContext().getSessionMap();
        StringBuilder sb = new StringBuilder();
        for (String key : sessAttrs.keySet()) {
            sb.append("(" + key + "=" + sessAttrs.get(key) + ") ");
            }
        System.out.println("Session Attributes : " + sb.toString());
    }
    
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
