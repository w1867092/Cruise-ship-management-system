public class Cabin {
    int customerCount = 3;
    Passenger[] customerList = new Passenger[customerCount];

    public Passenger[] getCustomerList() {
        return customerList;
    }

    public void initialiseCustomerList() {
        for (int i = 0; i < 3; i++) {
            customerList[i] = new Passenger("e", "e", 0);
        }
    }

    public boolean Full() {
        for (int i = 0; i < 3; i++) {
            if (!customerList[i].firstName.equals("e")) {
                return true;
            }
        }
        return false;
    }

    public boolean Empty() {
        for (Passenger passenger : customerList) {
            if(!passenger.firstName.equals("e")) {
                return false;
            }
        }
        return true;
    }
}