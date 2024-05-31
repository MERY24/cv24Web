package fr.univrouen.xmlProject.Configuration;

import fr.univrouen.xmlProject.Entities.Role;
import fr.univrouen.xmlProject.Entities.Enums.RoleEnum;
import fr.univrouen.xmlProject.Repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleConfig implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) { //no need to wait till spring fully init everything (case: ApplicationReadyEvent)
        this.loadRoles();
    }
    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.ADMIN, RoleEnum.USER};
        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();
                roleToCreate.setName(roleName);
                roleRepository.save(roleToCreate);
            });
        });
    }
}
