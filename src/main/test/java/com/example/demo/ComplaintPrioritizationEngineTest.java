@BeforeClass
public void setUp() {
    servlet = new SimpleEchoServlet();

    // Mock repositories
    userRepository = Mockito.mock(UserRepository.class);
    complaintRepository = Mockito.mock(ComplaintRepository.class);
    priorityRuleRepository = Mockito.mock(PriorityRuleRepository.class);
    passwordEncoder = Mockito.mock(PasswordEncoder.class);

    // Services
    userService = new UserServiceImpl(userRepository, passwordEncoder);
    priorityRuleService = new PriorityRuleServiceImpl(priorityRuleRepository);
    complaintService = new ComplaintServiceImpl(
            complaintRepository,
            priorityRuleService // ✅ Only 2 args, matches constructor
    );

    jwtUtil = Mockito.mock(JwtUtil.class);

    // Sample data
    sampleUser = new User();
    sampleUser.setId(1L);
    sampleUser.setEmail("customer@example.com");
    sampleUser.setFullName("Test Customer");
    sampleUser.setPassword("encoded");
    sampleUser.setRole(User.Role.CUSTOMER);

    highSeverityRule = new PriorityRule();
    highSeverityRule.setId(1L);
    highSeverityRule.setRuleName("High Severity Boost");
    highSeverityRule.setDescription("Boost score for high severity");
    highSeverityRule.setWeight(5);
    highSeverityRule.setActive(true);
}
