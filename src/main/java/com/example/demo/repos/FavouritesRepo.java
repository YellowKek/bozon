package com.example.demo.repos;

import com.example.demo.models.Favourites;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouritesRepo extends JpaRepository<Favourites, Long> {
    Optional<Favourites> findByUser(User user);
    @Query(value = "select product_id from products_favourites where favourites_id=:favouritesId", nativeQuery = true)
    List<Long> findProductsByFavouritesId(@Param("favouritesId") Long favouritesId);
    @Modifying
    @Query(value = "insert into products_favourites (favourites_id, product_id) values (:favourites, :product);", nativeQuery = true)
    void add(@Param("favourites") long favourites, @Param("product") long product);
    @Modifying
    @Query(value = "delete from products_favourites where (favourites_id = :favourites and product_id = :product);", nativeQuery = true)
    void delete(@Param("favourites") long favourites, @Param("product") long product);
}
