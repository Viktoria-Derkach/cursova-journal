package com.journal.services;

import com.journal.entities.Ad;
import com.journal.filters.Filter;
import com.journal.repositories.AdRepository;
import com.journal.services.interfaces.ExtractableAll;
import com.journal.services.interfaces.Insertable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdService implements ExtractableAll<Ad>, Insertable<Ad> {

    private final AdRepository adRepository;

    @Autowired
    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public List<Map<String, Object>> getAll(Filter<?> filter) {
        List<Ad> all = adRepository.findAll();
        return all.stream().sorted(Comparator.comparing(Ad::getPostDate).reversed()).map(ad -> {
            Map<String, Object> map = new HashMap<>();
            map.put("title", ad.getLabel());
            map.put("from", ad.getPostedBy() == null ? "Деканат" : ad.getPostedBy().getName());
            map.put("postDate", ad.getPostDate());
            map.put("text", ad.getText());
            return map;
        }).collect(Collectors.toList());
    }

    @Override
    public List<Ad> getAllEntities(Filter<?> filter) {
        return null;
    }

    @Override
    public boolean InsertEntity(Ad entity) {
        adRepository.save(entity);
        return true;
    }
}
