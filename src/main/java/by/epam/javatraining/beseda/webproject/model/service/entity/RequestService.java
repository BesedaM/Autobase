package by.epam.javatraining.beseda.webproject.model.service.entity;

import by.epam.javatraining.beseda.webproject.model.entity.Request;

import static by.epam.javatraining.beseda.webproject.model.dao.util.dataloader.DatabaseEnumLoader.REQUEST_STATUS_MAP;

public class RequestService extends AbstractEntityService<Request> {

    public RequestService() {
        super();
        entityDAO = daoEntityFactory.getRequestDAO();
    }

//    private static class SingletonHolder {
//        public static final RequestService instance = new RequestService();
//    }
//
//    public static RequestService getService() {
//        return SingletonHolder.instance;
//    }

    /**
     * Creates entity WITHOUT ID with the given data
     */
    public Request createRequest(String comment) {
        Request req = null;
        if (comment != null) {
            String status = REQUEST_STATUS_MAP.get(1);
            req = new Request(comment, status);
        }

        return req;
    }

}
