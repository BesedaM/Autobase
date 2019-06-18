package by.epam.javatraining.beseda.webproject.util.srcontent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Encapsulates request data in object fielda
 */
public class SessionRequestContent {
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;

    {
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    /**
     * Constructor retrieves data from HttpServletRequest object into inner data storage maps
     *
     * @param request data source
     */
    public SessionRequestContent(HttpServletRequest request) {
        extractAttributes(request);
        extractParameters(request);
        extractSessionAttributes(request);
    }

    /**
     * @param request
     */
    private void extractAttributes(HttpServletRequest request) {
        Enumeration<String> enumeration = request.getAttributeNames();
        if (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            Object value = request.getAttribute(key);
            requestAttributes.put(key, value);
        }
    }

    private void extractParameters(HttpServletRequest request) {
        requestParameters = request.getParameterMap();
    }

    private void extractSessionAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> enumeration = session.getAttributeNames();
        if (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            Object value = session.getAttribute(key);
            sessionAttributes.put(key, value);
        }
    }

    public final void insertAttributes(HttpServletRequest request) {
        Set<String> attrArr = requestAttributes.keySet();
        for (String attrName : attrArr) {
            request.setAttribute(attrName, requestAttributes.get(attrName));
        }
    }

    public final void insertSessionAttributes(HttpServletRequest request) {
        Set<String> attrArr = sessionAttributes.keySet();
        for (String attrName : attrArr) {
            request.getSession().setAttribute(attrName, sessionAttributes.get(attrName));
        }
    }

    public Map<String, Object> requestAttributes() {
        return requestAttributes;
    }

    public Map<String, String[]> requestParameters() {
        return requestParameters;
    }

    public Map<String, Object> sessionAttributes() {
        return sessionAttributes;
    }

}
