package Agenda;

import java.util.Comparator;

public class ComparePhone implements Comparator {
    @Override
    public int compare(Object o1, Object o2) throws ClassCastException {
        Contact c1 = (Contact) o1;
        Contact c2 = (Contact) o2;
        if (c1.getPhone() > c2.getPhone()) return 1;
        if (c1.getPhone() < c2.getPhone()) return -1;
        return 0;
    }
}