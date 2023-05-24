package uz.pdp.projectism.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.projectism.entity.Role;
import uz.pdp.projectism.entity.User;
import uz.pdp.projectism.entity.enums.RoleType;
import uz.pdp.projectism.repository.RoleRepository;
import uz.pdp.projectism.repository.UserRepository;
import uz.pdp.projectism.exceptions.RestException;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;




    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
        Role roleUser = new Role("User", RoleType.USER);
        roleRepository.save(roleUser);
        Role roleAdmin = new Role("Admin", RoleType.ADMIN);
        roleRepository.save(roleAdmin);
        Role roleDirector = new Role("Director", RoleType.DIRECTOR);
        roleRepository.save(roleDirector);
            Role director = roleRepository.findByRoleType(RoleType.DIRECTOR).orElseThrow(() -> RestException.notFound("RoleDirector Not found"));

            User userDirector = new User(
                    "Director",
                    "Director",
                    "director@gmail.com",
                    passwordEncoder.encode("dir123"),
                    director,
                    true
                    );
            userRepository.save(userDirector);
        }

    }
}
