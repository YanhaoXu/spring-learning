package com.github.xuyh.tacocloud.domain.repository.jdbc;

import com.github.xuyh.tacocloud.domain.entity.Ingredient;
import com.github.xuyh.tacocloud.domain.entity.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepositoryImpl implements JdbcTacoRepository {
  private final JdbcTemplate jdbc;

  @Autowired
  public JdbcTacoRepositoryImpl(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public Taco save(Taco taco) {
    long tacoId = saveTacoInfo(taco);
    taco.setId(tacoId);

    taco.getIngredients()
        .forEach(
            ingredient -> {
              saveIngredientToTaco(ingredient, tacoId);
            });

    return taco;
  }

  private long saveTacoInfo(Taco taco) {
    taco.setCreatedAt(new Date());
    PreparedStatementCreator psc =
        new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP)
            .newPreparedStatementCreator(
                Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbc.update(psc, keyHolder);

    return keyHolder.getKey().longValue();
  }

  private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
    jdbc.update(
        "insert into Taco_Ingredients (taco, ingredient) " + "values (?, ?)",
        tacoId,
        ingredient.getId());
  }
}
