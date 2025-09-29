package com.gestiondestock.repository;

import com.gestiondestock.model.MvtStk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {

    @Query("select sum (m.quantite) from MvtStk m where m.article.id =:idArticle")
    BigDecimal stockReelArticle(@Param("idArticle")Integer idArticle);

    List<MvtStk> findAllByArticleId(Integer idArticle);
}
