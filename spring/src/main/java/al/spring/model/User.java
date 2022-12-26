package al.spring.model;

import al.spring.model.sortComarator.UserRoleComparator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Fetch(FetchMode.JOIN)
    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @Fetch(FetchMode.JOIN)
    @OneToMany(fetch = FetchType.LAZY)
    // @SortNatural
    // @OrderBy("id")
    @SortComparator(UserRoleComparator.class)
    private Set<UserRole> roles = new TreeSet<>();
}
