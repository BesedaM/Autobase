package by.epam.javatraining.beseda.webproject.model.service.dependence;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;
import by.epam.javatraining.beseda.webproject.model.entity.route.Task;

public class TaskAddressService extends ToOneDependenceService<Task, Address>{

    public TaskAddressService(){
        super();
        dependenceDAO = DAODependenceFactory.getTaskAddressDAO();
    }

//    private static class SingletonHolder {
//        public static final TaskAddressService instance = new TaskAddressService();
//    }
//
//    public static TaskAddressService getService() {
//        return SingletonHolder.instance;
//    }

//    public void addDependence(Task task, Address address) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (task != null && address != null) {
//            try {
//                taskAddressDependenceDAO.setDependence(task, address);
////                task.setAddress(address);
////                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
////            } catch (IllegalAddressException e) {
////                throw new ServiceLogicException(e);
//            }
//        }
////        return succeed;
//    }

//    public void deleteDependence(Task task, Address address) throws ServiceTechnicalException {
////        boolean succeed = false;
//        if (task != null && address != null) {
//            try {
//                taskAddressDependenceDAO.deleteDependence(task);
////                task.removeAddress();
////                succeed = true;
//            } catch (DAOTechnicalException e) {
//                throw new ServiceTechnicalException(e);
//            }
//        }
////        return succeed;
//    }

//    public Address getAddress(Task task) throws ServiceLayerException {
//        Address address = null;
//        if (task != null) {
//            try {
//                int addressId = taskAddressDependenceDAO.getDependenceId(task);
//                address = AddressDAO.getDAO().getEntityById(addressId);
//                task.setAddress(address);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            } catch (IllegalAddressException e) {
//                throw new ServiceLogicException(e);
//            }
//        }
//        return address;
//    }
//
//    public List<Task> getTaskList(Address address) throws ServiceLayerException {
//        List<Task> list = null;
//        if (address != null) {
//            try {
//                int[] tasksId = taskAddressDependenceDAO.getEntitiesIdByDependenceId(address);
//                list = TaskDAO.getDAO().getEntitiesByIdList(tasksId);
//            } catch (DAOLayerException e) {
//                throw new ServiceLayerException(e);
//            }
//        }
//        return list;
//    }
}
