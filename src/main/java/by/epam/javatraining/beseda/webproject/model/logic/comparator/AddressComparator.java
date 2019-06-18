package by.epam.javatraining.beseda.webproject.model.logic.comparator;

import by.epam.javatraining.beseda.webproject.model.entity.route.Address;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddressComparator implements Comparator<Address> {

    private List<Comparator<Address>> listComparators;

    public AddressComparator() {
        listComparators = new ArrayList<>();
        listComparators.add(new AddressCountryComparator());
        listComparators.add(new AddressDistrictComparator());
        listComparators.add(new AddressCityComparator());
        listComparators.add(new AddressStreetComparator());
        listComparators.add(new AddressHouseComparator());
        listComparators.add(new AddressBuildingComparator());
    }


    @Override
    public int compare(Address o1, Address o2) {
        for (Comparator<Address> comp : listComparators) {
            int result = comp.compare(o1, o2);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

}

class AddressCountryComparator implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getCountry().compareTo(o2.getCountry());
    }
}

class AddressDistrictComparator implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getDistrict().compareTo(o2.getDistrict());
    }
}

class AddressCityComparator implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getCity().compareTo(o2.getCity());
    }
}

class AddressStreetComparator implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getStreet().compareTo(o2.getStreet());
    }
}

class AddressHouseComparator implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getHouse() - o2.getHouse();
    }
}

class AddressBuildingComparator implements Comparator<Address> {
    @Override
    public int compare(Address o1, Address o2) {
        return o1.getBuilding().compareTo(o2.getBuilding());
    }
}