package com.example.demo.services;

import com.example.demo.models.Favourites;
import com.example.demo.models.User;
import com.example.demo.repos.FavouritesRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavouritesService {
    private final FavouritesRepo favouritesRepo;

    public FavouritesService(FavouritesRepo favouritesRepo) {
        this.favouritesRepo = favouritesRepo;
    }

    public void save(Favourites favourites) {
        if (favouritesRepo.findByUser(favourites.getUser()).isEmpty())
            favouritesRepo.save(favourites);
    }

    public void addProduct(long id, long productId) {
        favouritesRepo.add(id, productId);
    }

    public Optional<Favourites> findByUser(User user) {
        return favouritesRepo.findByUser(user);
    }

    public void deleteById(long id) {
        favouritesRepo.deleteById(id);
    }
    public void delete(long id, long productId) {
        favouritesRepo.delete(id, productId);
    }

    public List<Long> findProductsByUser(User user) {
        Optional<Favourites> favourites = favouritesRepo.findByUser(user);
        if (favourites.isPresent()) {
            return favouritesRepo.findProductsByFavouritesId(favourites.get().getId());
        }
        return null;
    }

}
