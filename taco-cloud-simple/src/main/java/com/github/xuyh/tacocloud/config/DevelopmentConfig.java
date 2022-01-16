package com.github.xuyh.tacocloud.config;

import com.github.xuyh.tacocloud.domain.entity.Ingredient;
import com.github.xuyh.tacocloud.domain.entity.User;
import com.github.xuyh.tacocloud.domain.repository.jpa.JpaIngredientRepository;
import com.github.xuyh.tacocloud.domain.repository.jpa.JpaUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.github.xuyh.tacocloud.domain.entity.Ingredient.Type;

@Configuration
@Profile("!prod")
public class DevelopmentConfig {
  @Bean
  public CommandLineRunner dataLoader(
      JpaIngredientRepository repo, JpaUserRepository userRepo, PasswordEncoder encoder) {
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
        repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
        repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
        repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
        repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
        repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
        repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
        repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
        repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        userRepo.save(
            new User(
                "user",
                encoder.encode("password"),
                "Craig Walls",
                "123 North Street",
                "Cross Roads",
                "TX",
                "76227",
                "123-123-1234"));
      }
    };
  }
}
