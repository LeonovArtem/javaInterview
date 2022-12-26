## Failed to lazily initialize..
1. Самое простое решение
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
    
    // @Fetch - solve the problem    
    @Fetch(FetchMode.JOIN)
    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
}
```
2. @Fetch(FetchMode.JOIN) - откючен
```java
    @Transactional
    @GetMapping("mapper/{id}")
    public UserDto findDtoById(@PathVariable int id) {
        return userMapper.toDto(userService.findById(id));
    }

    @Transactional
    @GetMapping("{id}")
    public User findById(@PathVariable int id) {
        var user = userService.findById(id);
        
        // Если нет прямого обращения - то работать НЕ будет!
        System.out.println(user.getPosts());

        return user;
    }
```
