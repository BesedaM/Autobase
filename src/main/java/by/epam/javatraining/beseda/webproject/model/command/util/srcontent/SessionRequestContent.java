package by.epam.javatraining.beseda.webproject.model.command.util.srcontent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Encapsulates request data in object fielda
 */
public class SessionRequestContent {

    private HttpServletRequest request;

    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private HttpSession session;

    {
        requestAttributes = new HashMap<>();
    }

    /**
     * Constructor retrieves data from HttpServletRequest object into inner data storage maps
     *
     * @param request data source
     */
    public SessionRequestContent(HttpServletRequest request) {
        this.request=request;
        extractAttributes();
        extractParameters();
        extractSession();
    }


    private void extractAttributes() {
        Enumeration<String> enumeration = request.getAttributeNames();
        if (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            Object value = request.getAttribute(key);
            requestAttributes.put(key, value);
        }
    }

    private void extractParameters() {
        requestParameters = request.getParameterMap();
    }

    private void extractSession() {
        session = request.getSession(true);
    }

    public final void insertAttributes(HttpServletRequest request) {
        Set<String> attrArr = requestAttributes.keySet();
        for (String attrName : attrArr) {
            request.setAttribute(attrName, requestAttributes.get(attrName));
        }
    }

    public Map<String, Object> requestAttributes() {
        return requestAttributes;
    }

    public Map<String, String[]> requestParameters() {
        return requestParameters;
    }

    public HttpSession getSession() {
        return session;
    }

    public HttpSession renewSession(){
        return request.getSession(true);
    }
}
