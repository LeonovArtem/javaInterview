## N + One Problem

```java
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
    private List<Post> posts;
}
```
1. Будет выбраны все пользователи (1 запрос)
```sql
select u1_0.id,u1_0.name from user u1_0
```
2. Для каждого пользователя будут выбраны все посты (N запросов)
```sql
select p1_0.*
from user_posts p1_0
         join post p1_1 on p1_1.id = p1_0.posts_id
         left join user u1_0 on u1_0.id = p1_1.user_id
where p1_0.user_id = ?
```
Решение:
1. EntityGraph 
```java
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    @EntityGraph(attributePaths = {"posts"})
    List<User> findAll();
}
```