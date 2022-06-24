### a) Identify a couple of examples on the use of AssertJ expressive methods chaining.

From Test A line 75:

```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

From Test B line 113:

```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());
```

### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

```java
...

@ExtendWith(MockitoExtension.class)
class B_EmployeeService_UnitTest {

    // mocking the responses of the repository (i.e., no database will be used)
    // lenient is required because we load more expectations in the setup
    // than those used in some tests. As an alternative, the expectations
    // could move into each test method and be trimmed (no need for lenient then)
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
		...
}
```

### c) What is the difference between standard @Mock and @MockBean?

@Mock is the shortend for Mock.mock() with the use of the annotations support for mock, this annotation creates a mock object of a class or an interface. The @MockBean adds mock objects similar to the @Mock annotation but their origin is different, in this case the mock objects are created by replacing an already existing bean of the same type or by adding a new one.

### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

The file “application-integrationtest.properties” has the role of giving the right properties of key variables needed to run the integration test, it will be used only on tests of integration. In this case it is defined with credentials to a database and the url of the DB.

### e) The sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

On C we mock the Employee service via the Mckmvc but we dont test the repository, this way we only test the controller and the service. On the D it tests still via Mckmvc but we test E2E (end to end). On the E we test it via the spring boot launching, also E2E but with spring that gives us a heavier load but a more accurate one compared to the final product.