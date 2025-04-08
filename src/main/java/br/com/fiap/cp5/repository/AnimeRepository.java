package br.com.fiap.cp5.repository;

import br.com.fiap.cp5.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
}
