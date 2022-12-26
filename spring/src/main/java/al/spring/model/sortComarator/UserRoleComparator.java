package al.spring.model.sortComarator;

import al.spring.model.UserRole;

import java.util.Comparator;

public class UserRoleComparator implements Comparator<UserRole> {
    @Override
    public int compare(UserRole o1, UserRole o2) {
        if (o1.getId().equals(o2.getId())) {
            return 0;
        }

        return o1.getId().equals(o2.getId()) ? -1 : 1;
    }
}
